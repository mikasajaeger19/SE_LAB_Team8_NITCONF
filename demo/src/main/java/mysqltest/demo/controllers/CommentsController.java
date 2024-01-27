package mysqltest.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import mysqltest.demo.models.Comments;
import mysqltest.demo.repositories.CommentRepository;

@RestController
@RequestMapping(path = "/comments")
public class CommentsController {

    @Autowired
    private CommentRepository commentRepository;

    @PostMapping(path = "/add")
    public @ResponseBody String addNewComment(@RequestBody Comments comment) {
        // @ResponseBody means the returned String is the response, not a view name

        commentRepository.save(comment);
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


    @GetMapping(path = "/{paperId}")
    public @ResponseBody Iterable<Comments> getCommentsForPaper(@PathVariable Integer paperid) {
    // Assuming there is a method in the commentRepository to find comments by paperId
    return commentRepository.findByPaperId(paperid);
}

}
