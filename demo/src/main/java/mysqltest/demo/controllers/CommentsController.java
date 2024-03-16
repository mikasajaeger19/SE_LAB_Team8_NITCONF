package mysqltest.demo.controllers;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import mysqltest.demo.models.Comments;
// import mysqltest.demo.repositories.CommentRepository;
import mysqltest.demo.models.Version;
import mysqltest.demo.repositories.VersionRepository;

/**
 * Controller class for managing comments on versions.
 */
@RestController
@RequestMapping(path = "/comments")
public class CommentsController {
    @Autowired
    private VersionRepository versionRepository;

    /**
     * Adds a new comment to a specific version.
     *
     * @param comment    The comment content.
     * @param versionId  The ID of the version to which the comment is added.
     * @return A string indicating the status of the operation.
     */
    @PostMapping(path = "/add/{versionId}")
    public ResponseEntity <String> addNewComment(@RequestBody String comment, @PathVariable String versionId) {
        Version existingVersion = versionRepository.findByVersionId(versionId);
        existingVersion.setComments(comment);
        versionRepository.save(existingVersion);
        // Uncomment the following line if you have a commentRepository to save comments
        // commentRepository.save(comment);
        return ResponseEntity.ok("Saved");
    }

    /**
     * Returns a greeting message.
     *
     * @return A greeting message.
     */
    // @GetMapping(path = "/")
    // public String hello() {
    //     return "Hello World";
    // }

    /**
     * Retrieves all comments.
     *
     * @return Iterable of all comments.
     */
    // @GetMapping(path = "/all")
    // public @ResponseBody Iterable<Comments> getAllComments() {
    //     return commentRepository.findAll();
    // }

    /**
     * Retrieves comments for a specific version.
     *
     * @param versionId The ID of the version for which comments are retrieved.
     * @return Iterable of comments for the specified version.
     */
    @GetMapping(path = "/version/{versionId}")
    public ResponseEntity <Iterable<String>> getCommentsForVersion(@PathVariable String versionId) {
        // Assuming there is a method in the versionRepository to find comments by versionId
        Iterable<String> result = versionRepository.findCommentsByVersionId(versionId);
        return ResponseEntity.ok(result);
    }

    /**
     * Retrieves comments for a specific paper.
     *
     * @param paperId The ID of the paper for which comments are retrieved.
     * @return Iterable of comments for the specified paper.
     */
    // @GetMapping(path = "/paper/{paperId}")
    // public ResponseEntity <Iterable<String>> getCommentsForPaper(@PathVariable String paperId) {
    //     // Assuming there is a method in the versionRepository to find comments by paperId
    //     Iterable<String> result = versionRepository.findCommentsByPaperId(paperId);
    //     return ResponseEntity.ok(result);
    // }
}
