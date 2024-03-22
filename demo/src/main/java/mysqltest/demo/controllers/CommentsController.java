package mysqltest.demo.controllers;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import mysqltest.demo.models.Comments;
import mysqltest.demo.repositories.CommentRepository;

/**
 * Controller class for managing comments on versions.
 */
@RestController
@RequestMapping(path = "/comments")
public class CommentsController {
    @Autowired
    private CommentRepository commentRepository;

    /**
     * Adds a new comment to a specific version.
     *
     * @param comment    The comment content.
     * @param versionId  The ID of the version to which the comment is added.
     * @return A string indicating the status of the operation.
     */
    @PostMapping(path = "/add/{versionId}")
    public ResponseEntity <String> addNewComment(@RequestBody String comment, @PathVariable String versionId) {
        Comments newComment = new Comments();
        newComment.setComment(comment);
        newComment.setVersionId(versionId);
        commentRepository.save(newComment);
        return ResponseEntity.ok("Saved");
    }

    /**
     * Retrieves comments for a specific version.
     *
     * @param versionId The ID of the version for which comments are retrieved.
     * @return Iterable of comments for the specified version.
     */
    @GetMapping(path = "/version/{versionId}")
    public ResponseEntity <Iterable<Comments>> getCommentsForVersion(@PathVariable String versionId) {
        // Assuming there is a method in the versionRepository to find comments by versionId
        Iterable<Comments> result = commentRepository.findByVersionId(versionId);
        return ResponseEntity.ok(result);
    }

}
