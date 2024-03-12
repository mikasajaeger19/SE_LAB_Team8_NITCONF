package mysqltest.controllers;
import mysqltest.demo.controllers.PaperController;
import mysqltest.demo.repositories.PaperRepository;
import mysqltest.demo.repositories.VersionRepository;
import mysqltest.demo.repositories.UserRepository;
import mysqltest.demo.models.Paper;
import mysqltest.demo.models.Version;
import mysqltest.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import org.springframework.security.core.context.SecurityContextHolder;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(PaperController.class)
public class PaperControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PaperRepository paperRepository;

    @MockBean
    private VersionRepository versionRepository;

    @Test
    public void testAddNewPaper() throws Exception {
        Paper paper = new Paper();
        paper.setTitle("Test Paper");
        // Set other paper properties as needed

        Version version = new Version();
        version.setAbstractUrl("https://example.com");
        version.setTitle("Test Paper Version");
        // Set other version properties as needed

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(paper);

        mockMvc.perform(MockMvcRequestBuilders.post("/paper/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // Add assertions based on expected behavior after adding a new paper
    }

    @Test
    public void testGetPaper() throws Exception {
        Paper paper = new Paper();
        paper.setId("1");
        paper.setTitle("Test Paper");
        // Set other paper properties as needed

        when(paperRepository.findByPaperId(anyString())).thenReturn(paper);

        mockMvc.perform(MockMvcRequestBuilders.get("/paper/1"))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Test Paper"));

        // Add more assertions based on expected behavior when retrieving a paper
    }

    @Test
    public void testUpdatePaper() throws Exception {
        Paper existingPaper = new Paper();
        existingPaper.setId("1");
        existingPaper.setTitle("Existing Paper");
        // Set other existing paper properties as needed

        Paper updatedPaper = new Paper();
        updatedPaper.setTitle("Updated Paper");
        // Set other updated paper properties as needed

        when(paperRepository.findByPaperId(anyString())).thenReturn(existingPaper);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(updatedPaper);

        mockMvc.perform(MockMvcRequestBuilders.put("/paper/update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // Add assertions based on expected behavior after updating the paper
    }

    @Test
    public void testGetMyPapers() throws Exception {
        // Mocking the currently authenticated user
        // ...

            User user = new User();
            user.setId("1"); // Assuming user ID 1
            // Set other user properties as needed

            // Mocking the SecurityContextHolder behavior
            when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn(user);

            // Mocking the paper repository behavior
            List<Paper> papers = new ArrayList<>();
            Paper paper1 = new Paper();
            paper1.setId("1");
            paper1.setTitle("Test Paper 1");
            // Set other paper properties as needed
            Paper paper2 = new Paper();
            paper2.setId("2");
        paper2.setTitle("Test Paper 2");
        // Set other paper properties as needed
        papers.add(paper1);
        papers.add(paper2);
        when(paperRepository.findByAuthorId("1")).thenReturn(papers);

        mockMvc.perform(MockMvcRequestBuilders.get("/paper/"))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("Test Paper 1"))
               .andExpect(MockMvcResultMatchers.jsonPath("$[1].title").value("Test Paper 2"));

        // Add more assertions based on expected behavior when retrieving the user's papers
    }

    @Test
    public void testGetAllPapers() throws Exception {
        // Mocking the paper repository behavior
        List<Paper> papers = new ArrayList<>();
        Paper paper1 = new Paper();
        paper1.setId("1");
        paper1.setTitle("Test Paper 1");
        // Set other paper properties as needed
        Paper paper2 = new Paper();
        paper2.setId("2");
        paper2.setTitle("Test Paper 2");
        // Set other paper properties as needed
        papers.add(paper1);
        papers.add(paper2);
        when(paperRepository.findAll()).thenReturn(papers);

        mockMvc.perform(MockMvcRequestBuilders.get("/paper/all"))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("Test Paper 1"))
               .andExpect(MockMvcResultMatchers.jsonPath("$[1].title").value("Test Paper 2"));

        // Add more assertions based on expected behavior when retrieving all papers
    }

    @Test
    public void testGetPaperById() throws Exception {
        // Mocking the paper repository behavior
        List<Paper> papers = new ArrayList<>();
        Paper paper1 = new Paper();
        paper1.setId("1");
        paper1.setTitle("Test Paper 1");
        // Set other paper properties as needed
        Paper paper2 = new Paper();
        paper2.setId("2");
        paper2.setTitle("Test Paper 2");
        // Set other paper properties as needed
        papers.add(paper1);
        papers.add(paper2);
        when(paperRepository.findByAuthorId("1")).thenReturn(papers);

        mockMvc.perform(MockMvcRequestBuilders.get("/paper/author/1"))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("Test Paper 1"))
               .andExpect(MockMvcResultMatchers.jsonPath("$[1].title").value("Test Paper 2"));

        // Add more assertions based on expected behavior when retrieving papers by author ID
    }
}
