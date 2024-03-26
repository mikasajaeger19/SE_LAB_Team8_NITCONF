package mysqltest.demo.controllers;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDate;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

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
    public ResponseEntity<Paper> addNewPaper(@RequestBody Paper paper){
        if(paper.getAuthorId() == null || paper.getTitle() == null || paper.getShortdesc() == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        // @ResponseBody means the returned String is the response, not a view name
        Version version = new Version();
        version.setAbstractUrl(paper.getAbstractUrl());
        version.setTitle(paper.getTitle());
        version.setReleaseDate(paper.getUploadDate());
        paperRepository.save(paper);
        version.setPaperId(paper.getId()); // Change to use the ID directly
        versionRepository.save(version);
        
        return ResponseEntity.ok(paper);
    }

    // @PostMapping(path = "/upload/{paperId}")
    // public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, @PathVariable String paperId) {
    //     //Paper paper = paperRepository.findByPaperId(paperId);
    //     return ResponseEntity.ok("File uploaded");
    //     // System.out.println("hello nigga");
    //     // paper.setFile(file.getBytes());

    //     // if (paper != null) {
    //     //     System.out.println("entered");
    //     //     Version version = new Version();
    //     //     version.setAbstractUrl(paper.getAbstractUrl());
    //     //     version.setTitle(paper.getTitle());
    //     //     version.setReleaseDate(paper.getUploadDate());
    //     //     version.setPaperId(paper.getId()); // Change to use the ID directly
    //     //     // paper.setFile(file.getBytes());
    //     //     version.setFile(file.getBytes());
    //     //     versionRepository.save(version);
    //     //     paperRepository.save(paper);
    //     //     return ResponseEntity.ok("File uploaded");
    //     // } else {
    //     //     return ResponseEntity.ok("Paper not found");
    //     // }
    // }


    @PostMapping(path = "/upload/{paperId}")
public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, @PathVariable String paperId) {
    try {
        // Process the file
        byte[] fileBytes = file.getBytes();

        Paper paper = paperRepository.findByPaperId(paperId);
        if (paper == null) {
            return ResponseEntity.ok("Paper not found");
            
        } else {
            Version version = new Version();
            version.setAbstractUrl(paper.getAbstractUrl());
            version.setTitle(paper.getTitle());
            version.setReleaseDate(paper.getUploadDate());
            version.setPaperId(paper.getId());
            version.setFile(fileBytes);
            paper.setFile(fileBytes);
            versionRepository.save(version);
            paperRepository.save(paper);
            return ResponseEntity.ok("File uploaded");
        }
    } catch (IOException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file: " + e.getMessage());
    }
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

    @GetMapping("/doc/{id}")
    public ResponseEntity<byte[]> getDocument(@PathVariable String id) {
        if (id == null)
            return ResponseEntity.notFound().build();
        
        Paper paper = paperRepository.findByPaperId(id);
        if (paper == null)
            return ResponseEntity.notFound().build();
        
        byte[] file = paper.getFile();
        if (file == null)
            return ResponseEntity.notFound().build();
        
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(file);
    }


    /**
     * Updates an existing Paper with the provided information.
     *
     * @param paper   The updated Paper information.
     * @param paperId The ID of the Paper to be updated.
     * @return A String indicating the result of the operation.
     */
    @PutMapping(path = "/update/{paperId}")
    public ResponseEntity <String> updatePaperDetails(@RequestBody Paper paper, @PathVariable String paperId) { // Change the parameter type to String
        Paper existingPaper = paperRepository.findByPaperId(paperId);

        if (existingPaper != null) {
            existingPaper.setTitle(paper.getTitle());
            existingPaper.setApproved(paper.getApproved());
            existingPaper.setShortdesc(paper.getShortdesc());
            existingPaper.setAbstractUrl(paper.getAbstractUrl());
            existingPaper.setTags(paper.getTags());
            existingPaper.setUploadDate(paper.getUploadDate());
            existingPaper.setAuthorId(paper.getAuthorId());
            paperRepository.save(existingPaper);
            return ResponseEntity.ok("Updated");
        } else {
            return ResponseEntity.ok("Error");
        }
    }

    @PutMapping(path = "/reupload/{paperId}")
    public ResponseEntity <String> reuploadPaper(@PathVariable String paperId, @RequestParam("file") MultipartFile file) throws IOException{ // Change the parameter type to String
        Paper existingPaper = paperRepository.findByPaperId(paperId);

        if (existingPaper != null) {
            
            LocalDate currentDate = LocalDate.now();
            // Save the updated paper
            existingPaper.setFile(file.getBytes());
            existingPaper.setUploadDate(currentDate);
            paperRepository.save(existingPaper);

            // Create a new version entry for the reupload
            Version newVersion = new Version();
            newVersion.setAbstractUrl(existingPaper.getAbstractUrl());
            newVersion.setTitle(existingPaper.getTitle());
            newVersion.setReleaseDate(currentDate);
            newVersion.setPaperId(existingPaper.getId()); // Change to use the ID directly
            newVersion.setFile(file.getBytes());
            versionRepository.save(newVersion);

            return ResponseEntity.ok("Updated");
        } else {
            return ResponseEntity.ok("Error");
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
