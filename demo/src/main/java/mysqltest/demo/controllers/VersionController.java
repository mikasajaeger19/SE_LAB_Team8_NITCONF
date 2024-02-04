package mysqltest.demo.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import mysqltest.demo.models.Version;
import mysqltest.demo.repositories.VersionRepository;

@RestController
@RequestMapping("/version")
public class VersionController {
    
    private VersionRepository versionRepository;
    
    @GetMapping(path="/all/{paperId}")
    public @ResponseBody Iterable<Version> getVersions(@PathVariable Integer paperId) {
        return versionRepository.findByPaperId(paperId);
    }

}
