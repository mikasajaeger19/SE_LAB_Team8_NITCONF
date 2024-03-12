package mysqltest.demo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

import mysqltest.demo.models.User;

public interface UserRepository extends MongoRepository<User, String> {
    
    Optional<User> findByEmail(String email);

    User findByUserId(Integer userId); // Spring Data MongoDB supports method naming conventions
}
