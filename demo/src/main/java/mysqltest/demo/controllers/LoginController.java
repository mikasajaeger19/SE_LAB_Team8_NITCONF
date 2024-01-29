package mysqltest.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import java.util.Map;
import mysqltest.demo.models.User;
import mysqltest.demo.repositories.UserRepository;

@RestController
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public Map<String, Object> currentUser(OAuth2AuthenticationToken oAuth2AuthenticationToken) {
        Map<String, Object> userDetails = oAuth2AuthenticationToken.getPrincipal().getAttributes();

        // Extract necessary information from userDetails and save to the database
        
        // String email = (String) userDetails.get("email");

        // // Extract username from email
        // int atIndex = email.indexOf('@');
        // String username = (atIndex != -1) ? email.substring(0, atIndex) : email;

        // String name = (String) userDetails.get("name");

        // String imageUrl = (String) userDetails.get("picture");

        // // Check if the user already exists in the database
        // User existingUser = userRepository.findByUsername(username);

        // if (existingUser == null) {
        //     // If the user doesn't exist, create a new User entity and save it to the database
        //     User newUser = new User();
        //     newUser.setUsername(username);
        //     newUser.setEmail(email);
        //     userRepository.save(newUser);
        // } else {
        //     // If the user already exists, you may update the information or take other actions
        //     // For now, let's just print a message
        //     System.out.println("User already exists: " + existingUser.getUsername());
        // }

        return userDetails;
    }

    @GetMapping("/login")
    public String login() {
        return "loginsecured";
    }
}
