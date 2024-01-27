package mysqltest.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import mysqltest.demo.models.Paper;
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

    @GetMapping(path="/")
    public String hello() {
        return "Hello World";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Paper> getAllPapers() {
        return paperRepository.findAll();
    }
}
