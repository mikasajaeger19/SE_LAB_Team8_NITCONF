package mysqltest.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import mysqltest.demo.models.Comments;

public interface CommentRepository extends CrudRepository<Comments, Integer> {
    // You can add custom query methods here if needed
    Iterable<Comments> findByPaperId(Integer paperId);
}
