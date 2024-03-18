package mysqltest.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.beans.Transient;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import mysqltest.demo.controllers.LoginController;
import mysqltest.demo.models.User;
import mysqltest.demo.repositories.UserRepository;

import mysqltest.demo.auth.AuthenticationRequest;
import mysqltest.demo.auth.AuthenticationResponse;
import mysqltest.demo.auth.AuthenticationService;
import mysqltest.demo.auth.RegisterRequest;

public class LoginControllerTests {
    
        @Mock
        private UserRepository userRepo;
    
        @InjectMocks
        private LoginController loginController;
    
        @Mock
        private Authentication authentication;

        @Mock
        private AuthenticationService authenticationService;
    
        @BeforeEach
        public void setUp() {
            MockitoAnnotations.initMocks(this);
        }
    
        // @Test
        // public void testRegisterUser() {
        //     User user = new User();
        //     user.setEmail("nae@g.com");
        //     user.setName("nae");
        //     user.setPassword("nae");

        //     RegisterRequest registerRequest = new RegisterRequest();
        //     registerRequest.setEmail(user.getEmail());
        //     registerRequest.setName(user.getName());
        //     registerRequest.setPassword(user.getPassword());

        //     when(userRepo.findByEmail(anyString())).thenReturn(Optional.empty());
        //     when(userRepo.save(any(User.class))).thenReturn(user);

        //     ResponseEntity <AuthenticationResponse> response = loginController.registerUser(registerRequest);

        //     assertEquals(response.getStatusCode(), HttpStatus.OK);
            
        // }

        @Test
        void testLogin() {
            AuthenticationRequest user = new AuthenticationRequest("john@doe.com","samplePassword");
            doAnswer(i -> null).when(authenticationService).login(user);

            ResponseEntity<AuthenticationResponse> response = loginController.login(user);
            assertEquals(HttpStatus.OK, response.getStatusCode());
        }

        @Test
        void testLogin_nullEmail() {
            AuthenticationRequest user = new AuthenticationRequest(null, "samplePassword");

            ResponseEntity<AuthenticationResponse> response = loginController.login(user);
            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
         }

         @Test
         void testRegisterUser_MissingFirstName() {
             RegisterRequest user = new RegisterRequest(null, "john@doe.com", "samplePassword", null);
     
             ResponseEntity<AuthenticationResponse> response = loginController.registerUser(user);
             assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
         }

         @Test
        void testRegisterUser_MissingEmail() {
            RegisterRequest user = new RegisterRequest("John Doe", null, "samplePassword", null);
    
            ResponseEntity<AuthenticationResponse> response = loginController.registerUser(user);
            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        }

        // @Test 
        // void testRegister() {
        //     RegisterRequest user = new RegisterRequest("John Doe", "john@doe.com", "samplePassword", null);

        //     doAnswer(i -> null).when(authenticationService).register(user);

        //     ResponseEntity<AuthenticationResponse> response = loginController.registerUser(user);
        //     assertEquals(HttpStatus.OK, response.getStatusCode());
        // }
}