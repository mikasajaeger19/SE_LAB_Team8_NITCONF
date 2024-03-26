package mysqltest.demo.controllers;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import mysqltest.demo.models.Tags;
import mysqltest.demo.repositories.TagRepository;
import mysqltest.demo.repositories.PaperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping(path = "/tag")
public class TagController{

    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private PaperRepository paperRepository;

    @PostMapping(path = "/add")
    public ResponseEntity <String> addNewTag(@RequestBody Tags tag) {
        tagRepository.save(tag);
        return ResponseEntity.ok("Saved");
    }

    @GetMapping(path = "/paper/{paperId}")
    public ResponseEntity <Iterable<Tags>> getTagsByPaperId(@PathVariable String paperId) {
        Iterable <Tags> tags = tagRepository.findByPaperId(paperId);
        return ResponseEntity.ok(tags);
    }
}