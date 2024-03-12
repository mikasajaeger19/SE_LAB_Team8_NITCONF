package mysqltest.demo.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import mysqltest.demo.models.User;
import mysqltest.demo.repositories.UserRepository;

@RestController
@RequestMapping(path = "/demo")
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping(path="/add")
    public @ResponseBody String addNewUser(@RequestBody User user) {
        userRepository.save(user);
        return "Saved";
    }

    @GetMapping(path = "/")
    public User hello() {
        // Retrieve the current user based on the authentication
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return currentUser;
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping(path = "/user/{id}")
    public @ResponseBody User getUser(@PathVariable String id) {
        return userRepository.findById(id).orElse(null);
    }

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
