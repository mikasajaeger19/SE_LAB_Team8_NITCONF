// package mysqltest.controllers;

// import com.fasterxml.jackson.databind.ObjectMapper;
// import mysqltest.demo.controllers.VersionController;
// import mysqltest.demo.models.Version;
// import mysqltest.demo.repositories.VersionRepository;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.http.MediaType;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
// import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

// import java.util.ArrayList;
// import java.util.List;

// import static org.mockito.ArgumentMatchers.anyString;
// import static org.mockito.Mockito.when;


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
