package mysqltest.demo.repositories;
import org.springframework.data.mongodb.repository.MongoRepository;
import mysqltest.demo.models.Tags;
import java.util.List;

public interface TagRepository extends MongoRepository<Tags, String> {
    List<Tags> findByPaperId(String paperId);
}