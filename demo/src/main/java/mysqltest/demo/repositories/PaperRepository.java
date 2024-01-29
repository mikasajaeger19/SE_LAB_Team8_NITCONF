package mysqltest.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import mysqltest.demo.models.Paper;
import java.util.List;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface PaperRepository extends CrudRepository<Paper, Integer> {
    List<Paper> findByAuthorId(Integer authorId);
}
