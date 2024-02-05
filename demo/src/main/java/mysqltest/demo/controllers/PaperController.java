package mysqltest.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import mysqltest.demo.models.Paper;
import mysqltest.demo.models.User;
import mysqltest.demo.models.Version;
import mysqltest.demo.repositories.PaperRepository;
import mysqltest.demo.repositories.VersionRepository;

/**
 * Controller class for managing Paper-related operations.
 */
@RestController
@RequestMapping(path = "/paper")
public class PaperController {

    @Autowired
    private PaperRepository paperRepository;
    private VersionRepository versionRepository;

    /**
     * Adds a new Paper to the system.
     *
     * @param paper The Paper to be added.
     * @return A String indicating the result of the operation.
     */
    @PostMapping(path = "/add")
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

    /**
     * Retrieves a Paper by its ID.
     *
     * @param paperId The ID of the Paper to retrieve.
     * @return The retrieved Paper.
     */
    @GetMapping(path = "/{paperId}")
    public @ResponseBody Paper getPaper(@PathVariable Integer paperId) {
        return paperRepository.findByPaperId(paperId);
    }

    /**
     * Updates an existing Paper with the provided information.
     *
     * @param paper   The updated Paper information.
     * @param paperId The ID of the Paper to be updated.
     * @return A String indicating the result of the operation.
     */
    @PutMapping(path = "/update/{paperId}")
    public @ResponseBody String updatePaper(@RequestBody Paper paper, @PathVariable Integer paperId) {
        Paper existingPaper = paperRepository.findByPaperId(paperId);

        if (existingPaper != null) {
            // Create a new version entry for the reupload
            Version newVersion = new Version();
            newVersion.setAbstractUrl(existingPaper.getAbstractUrl());
            newVersion.setTitle(existingPaper.getTitle());
            newVersion.setReleaseDate(existingPaper.getUploadDate());
            newVersion.setComments(null);  // You may want to add comments from the request if needed
            newVersion.setPaper(existingPaper);
            versionRepository.save(newVersion);

            // Update fields of the existing paper with the values from the request
            if (paper.getTitle() != null)
                existingPaper.setTitle(paper.getTitle());
            if (paper.getApproved() != null)
                existingPaper.setApproved(paper.getApproved());
            if (paper.getShortdesc() != null)
                existingPaper.setShortdesc(paper.getShortdesc());
            if (paper.getAbstractUrl() != null)
                existingPaper.setAbstractUrl(paper.getAbstractUrl());
            if (paper.getTags() != null)
                existingPaper.setTags(paper.getTags());
            if (paper.getUploadDate() != null)
                existingPaper.setUploadDate(paper.getUploadDate());
            if (paper.getAuthorId() != null)
                existingPaper.setAuthorId(paper.getAuthorId());
            if (paper.getId() != null)
                existingPaper.setId(paperId);

            // Save the updated paper
            paperRepository.save(existingPaper);

            return "Paper Updated";
        } else {
            return "Paper not found";
        }
    }

    /**
     * Retrieves Papers authored by the currently authenticated user.
     *
     * @return Iterable of Papers authored by the current user.
     */
    @GetMapping(path = "/")
    public @ResponseBody Iterable<Paper> getMyPapers() {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return paperRepository.findByAuthorId(currentUser.getId());
    }

    /**
     * Retrieves all Papers in the system.
     *
     * @return Iterable of all Papers.
     */
    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Paper> getAllPapers() {
        return paperRepository.findAll();
    }

    /**
     * Retrieves Papers authored by a specific user.
     *
     * @param id The ID of the author.
     * @return Iterable of Papers authored by the specified user.
     */
    @GetMapping(path = "/author/{id}")
    public @ResponseBody Iterable<Paper> getPaperById(@PathVariable Integer id) {
        return paperRepository.findByAuthorId(id);
    }
}
