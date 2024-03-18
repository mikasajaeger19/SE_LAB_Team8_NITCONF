package mysqltest.demo.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import mysqltest.demo.models.User;
import mysqltest.demo.repositories.UserRepository;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping(path = "/demo")
public class MainController {

    @Autowired
    private UserRepository userRepository;

    // @PostMapping(path="/add")
    // public @ResponseBody String addNewUser(@RequestBody User user) {
    //     userRepository.save(user);
    //     return "Saved";
    // }

    // @GetMapping(path = "/")
    // public User hello() {
    //     // Retrieve the current user based on the authentication
    //     User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    //     return currentUser;
    // }

    // @GetMapping(path = "/all")
    // public @ResponseBody Iterable<User> getAllUsers() {
    //     return userRepository.findAll();
    // }

    @GetMapping(path = "/user/{id}")
    public ResponseEntity <User> getUser(@PathVariable String id) {
        User user = userRepository.findById(id).orElse(null);
        return ResponseEntity.ok(user);
    }

    @PutMapping(path = "/update/{authorId}")
    public ResponseEntity<String> updateUserDetails(@RequestBody User updatedUser, @PathVariable String authorId) {
        // User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userRepository.findById(authorId).orElse(null);
        // Update only the fields that are allowed to be modified
        if(currentUser != null){
            currentUser.setName(updatedUser.getName());
            currentUser.setEmail(updatedUser.getEmail());
            currentUser.setAltEmail(updatedUser.getAltEmail());
            currentUser.setPassword(updatedUser.getPassword());
        }
        if(currentUser == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
        return ResponseEntity.ok("User details updated");
    }
}
