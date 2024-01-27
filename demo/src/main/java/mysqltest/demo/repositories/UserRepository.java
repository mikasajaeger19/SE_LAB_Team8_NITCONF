package mysqltest.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import mysqltest.demo.models.User;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Integer> {

}
