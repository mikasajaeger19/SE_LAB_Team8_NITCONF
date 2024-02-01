package mysqltest.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import mysqltest.demo.models.Comments;
import mysqltest.demo.repositories.CommentRepository;

import mysqltest.demo.models.Version;
import mysqltest.demo.repositories.VersionRepository;

 
@RestController
@RequestMapping(path = "/comments")
public class CommentsController {

    @Autowired
    private CommentRepository commentRepository;
    private VersionRepository versionRepository;

    @PostMapping(path = "/add/{versionId}")
    public @ResponseBody String addNewComment(@RequestBody String comment, @PathVariable Integer versionId) {
        // @ResponseBody means the returned String is the response, not a view name
        Version existingVersion = versionRepository.findByVersionId(versionId);
        existingVersion.setComments(comment);
        //commentRepository.save(comment);
        return "Saved";
    }

    @GetMapping(path = "/")
    public String hello() {
        return "Hello World";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Comments> getAllComments() {
        return commentRepository.findAll();
    }

    @GetMapping(path = "/{versionId}")
    public @ResponseBody Iterable<String> getCommentsForVersion(@PathVariable Integer versionId) {
        // Assuming there is a method in the commentRepository to find comments by paperId
        return versionRepository.findCommentsByVersionId(versionId);
    }

    @GetMapping(path = "/{paperId}")
    public @ResponseBody Iterable<String> getCommentsForPaper(@PathVariable Integer paperId) {
        // Assuming there is a method in the commentRepository to find comments by paperId
        return versionRepository.findCommentsByPaperId(paperId);
    }

}
