package mysqltest.demo.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import mysqltest.demo.models.Paper;
import mysqltest.demo.models.User;
import mysqltest.demo.repositories.PaperRepository;

@RestController
@RequestMapping(path="/paper")
public class PaperController {

    @Autowired
    private PaperRepository paperRepository;

    @PostMapping(path="/add")
    public @ResponseBody String addNewPaper(@RequestBody Paper paper) {
        // @ResponseBody means the returned String is the response, not a view name

        paperRepository.save(paper);
        return "Saved";
    }

//     @PutMapping(path="/update/{paperId}")
//     public @ResponseBody String updatePaper(@RequestBody Paper paper, @PathVariable Integer paperId) {
//     Optional<Paper> existingPaperOptional = paperRepository.findById(paperId);

//     if (existingPaperOptional.isPresent()) {
//         Paper existingPaper = existingPaperOptional.get();
//         // Update fields of the existing paper with the values from the request
//         existingPaper.setTitle(paper.getTitle());
//         existingPaper.setApproved(paper.getApproved());
//         existingPaper.setShortdesc(paper.getShortdesc());
//         existingPaper.setAbstractUrl(paper.getAbstractUrl());
//         existingPaper.setTags(paper.getTags());
//         existingPaper.setUploadDate(paper.getUploadDate());
//         existingPaper.setAuthorId(paper.getAuthorId());

//         paperRepository.save(existingPaper);
//         return "Paper Updated";
//     } else {
//         return "Paper not found";
//     } 
// }


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
