package mysqltest.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import mysqltest.demo.models.User;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Integer> {
    
    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.id = :userId")
    User findByUserId(Integer userId);
}
