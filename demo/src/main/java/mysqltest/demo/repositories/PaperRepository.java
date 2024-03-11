package mysqltest.demo.repositories;
import org.springframework.data.mongodb.repository.MongoRepository;

import mysqltest.demo.models.Paper;
import java.util.List;

public interface PaperRepository extends MongoRepository<Paper, String> {
    List<Paper> findByApproved(Boolean approved);

    List<Paper> findByAuthorId(String authorId);

    Paper findByPaperId(String paperId);
}
