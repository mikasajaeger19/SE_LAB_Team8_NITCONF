package mysqltest.demo.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import mysqltest.demo.models.Paper;
import mysqltest.demo.models.User;
import mysqltest.demo.models.Version;
import mysqltest.demo.repositories.PaperRepository;
import mysqltest.demo.repositories.VersionRepository;

@RestController
@RequestMapping(path="/paper")
public class PaperController {

    @Autowired
    private PaperRepository paperRepository;
    private VersionRepository versionRepository;
    @PostMapping(path="/add")
    public @ResponseBody String addNewPaper(@RequestBody Paper paper) {
        // @ResponseBody means the returned String is the response, not a view name
        Version version = new Version();
        version.setAbstractUrl(paper.getAbstractUrl());
        version.setTitle(paper.getTitle());
        version.setReleaseDate(paper.getUploadDate());
        version.setComments(null);
        version.setPaper(paper);
        versionRepository.save(version);
        paperRepository.save(paper);
        return "Saved";
    }

    @GetMapping(path="/{paperId}")
    public @ResponseBody Paper getPaper(@PathVariable Integer paperId) {
        return paperRepository.findByPaperId(paperId);
    }

    @PutMapping(path="/update/{paperId}")
    public @ResponseBody String updatePaper(@RequestBody Paper paper, @PathVariable Integer paperId) {
    Paper existingPaper = paperRepository.findByPaperId(paperId);

    if (existingPaper != null) {
        // Update fields of the existing paper with the values from the request
        if(paper.getTitle() != null)
            existingPaper.setTitle(paper.getTitle());
        if(paper.getApproved() != null)
            existingPaper.setApproved(paper.getApproved());
        if(paper.getShortdesc() != null)
            existingPaper.setShortdesc(paper.getShortdesc());
        if(paper.getAbstractUrl() != null)
            existingPaper.setAbstractUrl(paper.getAbstractUrl());
        if(paper.getTags() != null)
            existingPaper.setTags(paper.getTags());
        if(paper.getUploadDate() != null)
            existingPaper.setUploadDate(paper.getUploadDate());
        if(paper.getAuthorId() != null)
            existingPaper.setAuthorId(paper.getAuthorId());
        if(paper.getId() != null)
            existingPaper.setId(paperId);
        paperRepository.save(existingPaper);
        return "Paper Updated";
    } else {
        return "Paper not found";
    }
}



    @GetMapping(path="/")
    public @ResponseBody Iterable<Paper> getMyPapers() {    
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();   
        return paperRepository.findByAuthorId(currentUser.getId());
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Paper> getAllPapers() {
        return paperRepository.findAll();
    }

    @GetMapping(path="/author/{id}")
    public @ResponseBody Iterable<Paper> getPaperById(@PathVariable Integer id) {
        return paperRepository.findByAuthorId(id);
    }

}
