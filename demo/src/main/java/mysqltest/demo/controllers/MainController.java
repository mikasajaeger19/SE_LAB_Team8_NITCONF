package mysqltest.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import mysqltest.demo.models.User;
import mysqltest.demo.repositories.UserRepository;

/**
 * Controller class for handling User-related operations.
 */
@RestController
@RequestMapping(path = "/demo")
public class MainController {

    @Autowired
    private UserRepository userRepository;

    /**
     * Adds a new User to the system.
     *
     * @param user The User to be added.
     * @return A String indicating the result of the operation.
     */
/**
 * Creates a new user.
 * @param user The user details.
 * @return A response indicating the success of user creation.
 */
    @PostMapping(path="/add")
    public @ResponseBody String addNewUser(@RequestBody User user) {
        // @ResponseBody means the returned String is the response, not a view name
        userRepository.save(user);
        return "Saved";
    }

    /**
     * Retrieves the currently authenticated User.
     *
     * @return The currently authenticated User.
     */
    @GetMapping(path = "/")
    public User hello() {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return currentUser;
    }

    /**
     * Retrieves all Users in the system.
     *
     * @return Iterable of all Users.
     */
    @GetMapping(path = "/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Retrieves a User by their ID.
     *
     * @param id The ID of the User to retrieve.
     * @return The retrieved User.
     */
    @GetMapping(path = "/user/{id}")
    public @ResponseBody User getUser(@PathVariable Integer id) {
        return userRepository.findByUserId(id);
    }

    /**
     * Updates the details of the currently authenticated User.
     *
     * @param updatedUser The updated User information.
     * @return ResponseEntity with a String indicating the result of the operation.
     */
    @PutMapping(path = "/update")
    public ResponseEntity<String> updateUserDetails(@RequestBody User updatedUser) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Update only the fields that are allowed to be modified
        currentUser.setName(updatedUser.getName());
        currentUser.setEmail(updatedUser.getEmail());
        currentUser.setAltEmail(updatedUser.getAltEmail());
        currentUser.setPassword(updatedUser.getPassword());

        userRepository.save(currentUser);

        return ResponseEntity.ok("User details updated");
    }
}
