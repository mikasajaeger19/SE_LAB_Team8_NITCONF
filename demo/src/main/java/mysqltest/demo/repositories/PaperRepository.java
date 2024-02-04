package mysqltest.demo.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import mysqltest.demo.models.Paper;
import java.util.List;

public interface PaperRepository extends CrudRepository<Paper, Integer> {
    @Query("SELECT p FROM Paper p WHERE p.approved = :approved")
    List<Paper> findByApproved(Boolean approved);

    @Query("SELECT p FROM Paper p WHERE p.authorId = :authorId")
    List<Paper> findByAuthorId(@Param("authorId") Integer authorId);

    @Query("SELECT p FROM Paper p WHERE p.paperId = :paperId")
    Paper findByPaperId(@Param("paperId") Integer paperId);
}
