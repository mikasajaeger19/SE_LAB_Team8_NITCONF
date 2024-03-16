package mysqltest.demo.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    @Autowired
    private VersionRepository versionRepository;

    /**
     * Adds a new Paper to the system.
     *
     * @param paper The Paper to be added.
     * @return A String indicating the result of the operation.
     */
    @PostMapping(path = "/add")
    public ResponseEntity<Paper> addNewPaper(@RequestBody Paper paper) {
        // @ResponseBody means the returned String is the response, not a view name
        Version version = new Version();
        version.setAbstractUrl(paper.getAbstractUrl());
        version.setTitle(paper.getTitle());
        version.setReleaseDate(paper.getUploadDate());
        version.setComments(null);
        paperRepository.save(paper);
        version.setPaperId(paper.getId()); // Change to use the ID directly
        versionRepository.save(version);
        
        return ResponseEntity.ok(paper);
    }

    /**
     * Retrieves a Paper by its ID.
     *
     * @param paperId The ID of the Paper to retrieve.
     * @return The retrieved Paper.
     */
    @GetMapping(path = "/{paperId}")
    public ResponseEntity <Paper> getPaper(@PathVariable String paperId) { // Change the parameter type to String
        Paper result =  paperRepository.findById(paperId).orElse(null);
        return ResponseEntity.ok(result);
    }

    /**
     * Updates an existing Paper with the provided information.
     *
     * @param paper   The updated Paper information.
     * @param paperId The ID of the Paper to be updated.
     * @return A String indicating the result of the operation.
     */
    @PutMapping(path = "/update/{paperId}")
    public ResponseEntity <String> updatePaper(@RequestBody Paper paper, @PathVariable String paperId) { // Change the parameter type to String
        Paper existingPaper = paperRepository.findByPaperId(paperId);

        if (existingPaper != null) {
            // Update fields of the existing paper with the values from the request
            existingPaper.setTitle(paper.getTitle());
            existingPaper.setApproved(paper.getApproved());
            existingPaper.setShortdesc(paper.getShortdesc());
            existingPaper.setAbstractUrl(paper.getAbstractUrl());
            existingPaper.setTags(paper.getTags());
            existingPaper.setUploadDate(paper.getUploadDate());
            existingPaper.setAuthorId(paper.getAuthorId());
            // Don't update ID for an existing entity

            // Save the updated paper
            paperRepository.save(existingPaper);

            // Create a new version entry for the reupload
            Version newVersion = new Version();
            newVersion.setAbstractUrl(existingPaper.getAbstractUrl());
            newVersion.setTitle(existingPaper.getTitle());
            newVersion.setReleaseDate(existingPaper.getUploadDate());
            newVersion.setComments(null);  // You may want to add comments from the request if needed
            newVersion.setPaperId(existingPaper.getId()); // Change to use the ID directly
            versionRepository.save(newVersion);

            return ResponseEntity.ok("Paper Updated");
        } else {
            return ResponseEntity.ok("Paper not found");
        }
    }

    /**
     * Retrieves Papers authored by the currently authenticated user.
     *
     * @return Iterable of Papers authored by the current user.
     */
    // @GetMapping(path = "/")
    // public ResponseEntity <Iterable<Paper>> getMyPapers() {
    //     User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    //     Iterable <Paper> result = paperRepository.findByAuthorId(currentUser.getId());

    //     return ResponseEntity.ok(result);
    // }

    /**
     * Retrieves all Papers in the system.
     *
     * @return Iterable of all Papers.
     */
    // @GetMapping(path = "/all")
    // public ResponseEntity <Iterable<Paper>> getAllPapers() {
    //     Iterable<Paper> result = paperRepository.findAll();
    //     return ResponseEntity.ok(result);
    // }

    /**
     * Retrieves Papers authored by a specific user.
     *
     * @param id The ID of the author.
     * @return Iterable of Papers authored by the specified user.
     */
    @GetMapping(path = "/author/{id}")
    public ResponseEntity <Iterable<Paper>> getPaperById(@PathVariable String id) {
        Iterable<Paper> result = paperRepository.findByAuthorId(id);
        return ResponseEntity.ok(result);
    }

}
