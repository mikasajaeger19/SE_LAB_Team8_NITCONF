package mysqltest.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import mysqltest.demo.models.User;
import mysqltest.demo.repositories.UserRepository;

@RestController
@RequestMapping(path="/demo")
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping(path="/add")
    public @ResponseBody String addNewUser(@RequestBody User user) {
        // @ResponseBody means the returned String is the response, not a view name

        userRepository.save(user);
        return "Saved";
    }

    @GetMapping(path="/")
    public User hello() {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return currentUser;
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping(path="/user/{id}")
    public @ResponseBody User getUser(@PathVariable Integer id) {
        return userRepository.findByUserId(id);
    }

}
