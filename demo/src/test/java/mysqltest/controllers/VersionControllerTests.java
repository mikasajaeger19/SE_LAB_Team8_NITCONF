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

// import com.fasterxml.jackson.core.Version;

import mysqltest.demo.controllers.PaperController;
import mysqltest.demo.controllers.VersionController;
import mysqltest.demo.models.Paper;
import mysqltest.demo.models.User;
import mysqltest.demo.models.Version;
import mysqltest.demo.repositories.PaperRepository;
import mysqltest.demo.repositories.UserRepository;
import mysqltest.demo.repositories.VersionRepository;
import mysqltest.demo.repositories.TagRepository;

public class VersionControllerTests {

    @Mock
    private VersionRepository versionRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PaperRepository paperRepository;

    @InjectMocks
    private VersionController versionController;

    @Mock
    private Authentication authentication;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getVersions_test () {
        
        String PaperId = "1";

        List<Version> versions = new ArrayList<>();
        
        Version version1 = new Version();
        version1.setId("1");
        version1.setTitle("title");
        version1.setPaperId(PaperId);

        Version version2 = new Version();
        version2.setId("2");
        version2.setTitle("title");
        version2.setPaperId(PaperId);

        versions.add(version1);
        versions.add(version2);

        when(versionRepository.findByPaperId(anyString())).thenReturn(versions);

        ResponseEntity<Iterable<Version>> response = versionController.getVersions(PaperId);

        assertEquals(HttpStatus.OK, response.getStatusCode());

    }

}


// @SpringBootTest(classes = VersionController.class)
// @AutoConfigureMockMvc
// public class VersionControllerTests {

//     @Autowired
//     private MockMvc mockMvc;

//     @MockBean
//     private VersionRepository versionRepository;

//     @Test
//     public void testGetVersions() throws Exception {
//         // Mocking the paper ID
//         int paperId = 1; // Set the paper ID as needed

//         // Mocking the version repository behavior
//         List<Version> versions = new ArrayList<>();
//         Version version1 = new Version();
//         // Set version1 properties as needed
//         Version version2 = new Version();
//         // Set version2 properties as needed
//         versions.add(version1);
//         versions.add(version2);
//         when(versionRepository.findByPaperId(anyString())).thenReturn(versions);

//         mockMvc.perform(MockMvcRequestBuilders.get("/version/all/1"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").exists())
//                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").exists());

//         // Add more assertions based on expected behavior when retrieving versions
//     }
// }
