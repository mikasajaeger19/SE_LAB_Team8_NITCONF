package mysqltest.demo.controllers;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.ResponseEntity;
import mysqltest.demo.models.Version;
import mysqltest.demo.repositories.VersionRepository;
import org.springframework.data.domain.Sort;

@RestController
@RequestMapping("/version")
public class VersionController {
    
    @Autowired
    private VersionRepository versionRepository;
    
    
    /** 
     * Retrieves a Version by its ID.
     * @param paperId
     * @return Iterable<Version>
     */
    @GetMapping(path="/all/{paperId}")
    public ResponseEntity <Iterable<Version>> getVersions(@PathVariable String paperId) {
            Iterable<Version> result = versionRepository.findByPaperId(paperId);
            return ResponseEntity.ok(result);
    }

    @GetMapping("/doc/{id}")
    public ResponseEntity<byte[]> getDocument(@PathVariable String id) {
        if (id == null)
            return ResponseEntity.notFound().build();
        
        Version version = versionRepository.findByVersionId(id);
        if (version == null)
            return ResponseEntity.notFound().build();
        
        byte[] file = version.getFile();
        if (file == null)
            return ResponseEntity.notFound().build();
        
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(file);
    }

}
