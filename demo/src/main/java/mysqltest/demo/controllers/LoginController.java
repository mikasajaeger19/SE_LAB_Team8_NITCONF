package mysqltest.demo.controllers;

import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class LoginController {
    
    @GetMapping("/")
    public Map<String, Object> currentUser(OAuth2AuthenticationToken oAuth2AuthenticationToken) {
        return oAuth2AuthenticationToken.getPrincipal().getAttributes();
    }
    
    @GetMapping("/login")
    public String login() {
        return "loginsecured";
    }
}
