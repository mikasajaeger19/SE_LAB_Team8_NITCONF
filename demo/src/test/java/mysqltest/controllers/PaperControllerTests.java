package mysqltest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import mysqltest.demo.controllers.PaperController;
import mysqltest.demo.models.Paper;
import mysqltest.demo.models.User;
import mysqltest.demo.models.Version;
import mysqltest.demo.repositories.PaperRepository;
import mysqltest.demo.repositories.VersionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = PaperController.class)
@AutoConfigureMockMvc
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

        Version version = new Version();
        version.setAbstractUrl("https://example.com");
        version.setTitle("Test Paper Version");

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(paper);

        mockMvc.perform(MockMvcRequestBuilders.post("/paper/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetPaper() throws Exception {
        Paper paper = new Paper();
        paper.setId("1");
        paper.setTitle("Test Paper");

        when(paperRepository.findByPaperId(anyString())).thenReturn(paper);

        mockMvc.perform(MockMvcRequestBuilders.get("/paper/1"))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Test Paper"));
    }

    @Test
    public void testUpdatePaper() throws Exception {
        Paper existingPaper = new Paper();
        existingPaper.setId("1");
        existingPaper.setTitle("Existing Paper");

        Paper updatedPaper = new Paper();
        updatedPaper.setTitle("Updated Paper");

        when(paperRepository.findByPaperId(anyString())).thenReturn(existingPaper);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(updatedPaper);

        mockMvc.perform(MockMvcRequestBuilders.put("/paper/update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetMyPapers() throws Exception {
        User user = new User();
        user.setId("1");

        when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn(user);

        List<Paper> papers = new ArrayList<>();
        Paper paper1 = new Paper();
        paper1.setId("1");
        paper1.setTitle("Test Paper 1");
        Paper paper2 = new Paper();
        paper2.setId("2");
        paper2.setTitle("Test Paper 2");
        papers.add(paper1);
        papers.add(paper2);
        when(paperRepository.findByAuthorId("1")).thenReturn(papers);

        mockMvc.perform(MockMvcRequestBuilders.get("/paper/"))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("Test Paper 1"))
               .andExpect(MockMvcResultMatchers.jsonPath("$[1].title").value("Test Paper 2"));
    }

    @Test
    public void testGetAllPapers() throws Exception {
        List<Paper> papers = new ArrayList<>();
        Paper paper1 = new Paper();
        paper1.setId("1");
        paper1.setTitle("Test Paper 1");
        Paper paper2 = new Paper();
        paper2.setId("2");
        paper2.setTitle("Test Paper 2");
        papers.add(paper1);
        papers.add(paper2);
        when(paperRepository.findAll()).thenReturn(papers);

        mockMvc.perform(MockMvcRequestBuilders.get("/paper/all"))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("Test Paper 1"))
               .andExpect(MockMvcResultMatchers.jsonPath("$[1].title").value("Test Paper 2"));
    }

    @Test
    public void testGetPaperById() throws Exception {
        List<Paper> papers = new ArrayList<>();
        Paper paper1 = new Paper();
        paper1.setId("1");
        paper1.setTitle("Test Paper 1");
        Paper paper2 = new Paper();
        paper2.setId("2");
        paper2.setTitle("Test Paper 2");
        papers.add(paper1);
        papers.add(paper2);
        when(paperRepository.findByAuthorId("1")).thenReturn(papers);

        mockMvc.perform(MockMvcRequestBuilders.get("/paper/author/1"))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("Test Paper 1"))
               .andExpect(MockMvcResultMatchers.jsonPath("$[1].title").value("Test Paper 2"));
    }
}
