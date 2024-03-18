package mysqltest.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
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


import mysqltest.demo.controllers.MainController;
import mysqltest.demo.models.User;
import mysqltest.demo.repositories.UserRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.http.MediaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.http.MediaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MainControllerTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private MainController mainController;

    @Mock
    private Authentication authentication;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetUser() {
        // Mocking the user ID
        String userId = "1"; // Set the user ID as needed

        // Mocking the user repository behavior
        User user = new User();
        // Set user properties as needed
        when(userRepository.findById(anyString())).thenReturn(Optional.of(user));

        ResponseEntity<User> response = mainController.getUser(userId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    public void updateUser() {
        // Mocking the user details
        User updatedUser = new User();
        // Set updatedUser properties as needed

        // Mocking the current user
        User currentUser = new User();
        
        // Mocking the user repository behavior
        when(userRepository.findById(anyString())).thenReturn(Optional.of(currentUser));

        // Mocking the user repository behavior
        when(userRepository.save(any(User.class))).thenReturn(updatedUser);

        ResponseEntity <String> response = mainController.updateUserDetails(updatedUser, "1");
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    // @Test
    // public void testUpdateUserDetails_ValidUser_ReturnsOk() {
    //     // Create a sample User object
    //     // ...

    //     // Test
    //     MockMvc mockMvc = MockMvcBuilders.standaloneSetup(mainController).build();
    //     mockMvc.perform(MockMvcRequestBuilders.put("/demo/update")
    //         .contentType(MediaType.APPLICATION_JSON)
    //         .content(new ObjectMapper().writeValueAsString(user)))
    //         .andExpect(status().isOk())
    //         .andExpect(MockMvcResultMatchers.content().string("User details updated"));

    //     // Verify
    //     // No need to verify anything since we are using MockMvc
    // }

}