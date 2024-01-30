package mysqltest.demo.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import mysqltest.demo.models.Paper;
import java.util.List;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface PaperRepository extends CrudRepository<Paper, Integer> {
    @Query("SELECT p FROM Paper p WHERE p.approved = :approved")
    List<Paper> findByApproved(Boolean approved);

    @Query("SELECT p FROM Paper p WHERE p.authorId = :authorId")
    List<Paper> findByAuthorId(@Param("authorId") Integer authorId);
}
