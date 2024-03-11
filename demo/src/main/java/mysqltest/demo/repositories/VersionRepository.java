package mysqltest.demo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import mysqltest.demo.models.Version;

public interface VersionRepository extends MongoRepository<Version, String> {
    Iterable<Version> findByPaperId(String paperId);

    Version findByVersionId(String versionId);

    Version findFirstByPaperIdOrderByVersionIdDesc(String paperId);

    Iterable<String> findCommentsByPaperId(String paperId);

    Iterable<String> findCommentsByVersionId(String versionId);
}
