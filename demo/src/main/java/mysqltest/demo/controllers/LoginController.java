package mysqltest.demo.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import mysqltest.demo.auth.AuthenticationRequest;
import mysqltest.demo.auth.AuthenticationResponse;
import mysqltest.demo.auth.AuthenticationService;
import mysqltest.demo.auth.RegisterRequest;
import mysqltest.demo.models.User;
import mysqltest.demo.repositories.UserRepository;

/**
 * Controller class for handling user registration and login.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("")
public class LoginController {
    
    private final AuthenticationService service;

    @Autowired
    private UserRepository userRepo;

    /**
     * Handles user registration.
     *
     * @param user The registration request containing user details.
     * @return ResponseEntity containing the authentication response.
     */
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> registerUser(@RequestBody RegisterRequest user) {
        if (user.getEmail() == null || user.getPassword() == null || user.getName() == null)
            return ResponseEntity.badRequest().build();

        Optional<User> userExists = userRepo.findByEmail(user.getEmail());

        if (userExists.isPresent())
            return ResponseEntity.badRequest().build();

        ResponseEntity<AuthenticationResponse> response = ResponseEntity.ok(service.register(user));
    
        return response;
    }

    /**
     * Handles user login.
     *
     * @param user The login request containing user credentials.
     * @return ResponseEntity containing the authentication response.
     */
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest user) {
        if (user.getEmail() == null || user.getPassword() == null)
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(service.login(user));
    }
}
