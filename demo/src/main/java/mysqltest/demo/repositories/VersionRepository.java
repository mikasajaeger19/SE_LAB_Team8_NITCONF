
package mysqltest.demo.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import mysqltest.demo.models.Version;
import java.util.List;


public interface VersionRepository extends CrudRepository<Version, Integer> {
    // @Query("SELECT p FROM Paper p WHERE p.approved = :approved")
    // List<Paper> findByApproved(Boolean approved);

    // @Query("SELECT p FROM Paper p WHERE p.authorId = :authorId")
    // List<Paper> findByAuthorId(@Param("authorId") Integer authorId);

    // @Query("SELECT p FROM Paper p WHERE p.paperId = :paperId")
    // Paper findByPaperId(@Param("paperId") Integer paperId);

    @Query("SELECT v FROM Version v WHERE v.paperId = :paperId")
    Iterable<Version> findByPaperId(@Param("paperId") Integer paperId);

    @Query("SELECT v FROM Version v WHERE v.versionId = :versionId")
    Version findByVersionId(@Param("versionId") Integer versionId);

    @Query("SELECT v FROM Version v WHERE v.paperId = :paperId ORDER BY v.versionId DESC")
    Version findFirstByPaperIdOrderByVersionIdDesc(Integer paperId);

    @Query("SELECT v.comments FROM Version v WHERE v.paperId = :paperId")
    Iterable<String> findCommentsByPaperId(Integer paperId);

    @Query("SELECT v.comments FROM Version v WHERE v.versionId = :versionId")
    Iterable<String> findCommentsByVersionId(Integer versionId);

}
