// package mysqltest.demo.controllers;
// import org.springframework.data.annotation.Id;
// import org.springframework.data.mongodb.core.mapping.Document;
// import mysqltest.demo.models.Tags;
// import mysqltest.demo.repositories.TagRepository;
// import mysqltest.demo.repositories.PaperRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping(path = "/tag")
// public class TagController{

//     @Autowired
//     private TagRepository tagRepository;
//     @Autowired
//     private PaperRepository paperRepository;

//     @PostMapping(path = "/add")
//     public @ResponseBody String addNewTag(@RequestBody Tags tag) {
//         tagRepository.save(tag);
//         return "Saved";
//     }

//     @GetMapping(path = "/{tagId}")
//     public @ResponseBody Tags getTag(@PathVariable String tagId) {
//         return tagRepository.findById(tagId).orElse(null);
//     }

//     @GetMapping(path = "/paper/{paperId}")
//     public @ResponseBody Iterable<Tags> getTagsByPaperId(@PathVariable String paperId) {
//         return tagRepository.findByPaperId(paperId);
//     }
// }