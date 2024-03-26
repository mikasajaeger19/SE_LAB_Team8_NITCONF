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

// import com.fasterxml.jackson.core.Version;

import mysqltest.demo.controllers.PaperController;
import mysqltest.demo.models.Paper;
import mysqltest.demo.models.User;
import mysqltest.demo.models.Version;
import mysqltest.demo.repositories.PaperRepository;
import mysqltest.demo.repositories.UserRepository;
import mysqltest.demo.repositories.VersionRepository;
import mysqltest.demo.repositories.TagRepository;


public class PaperControllerTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PaperRepository paperRepository;

    @Mock
    private VersionRepository versionRepository;

    @Mock
    private TagRepository tagRepository;

    @InjectMocks
    private PaperController paperController;

    @Mock
    private Authentication authentication;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testNewPaper_ValidRequest() {

        SecurityContext securityContext = mock(SecurityContext.class);
        SecurityContextHolder.setContext(securityContext);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("test@example.com");
        Paper request = new Paper();
        request.setAuthorId("1");
        request.setTitle("Test Title");
        request.setShortdesc("Test Description");
        request.setApproved(false);
        Version version = new Version(request);
        List<String> tags = new ArrayList<>();
        tags.add("Java");

        User mockUser = new User();
        mockUser.setEmail("test@example.com");
        mockUser.setPassword("lol");
        mockUser.setName("naeem");
        //mockUser.papers = new ArrayList<Paper>();

        // Tag mockTag = new Tag("Java");
        // List<Tag> mockTags = new ArrayList<>();
        // mockTags.add(mockTag);

        // Mock repository behavior
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(mockUser));
        //when(tagsRepository.findById(anyString())).thenReturn(Optional.of(mockTag));
        when(versionRepository.save(any(Version.class))).thenReturn(new Version());
        when(paperRepository.save(any(Paper.class))).thenReturn(new Paper());

        // Test
        ResponseEntity<Paper> responseEntity = paperController.addNewPaper(request);

        // Verify
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    // Test for getPaperById
    @Test
    public void testGetPaperById_ValidId_ReturnsPaperList() {
        String userId = "user123";

        // Mock repository behavior
        List<Paper> papers = new ArrayList<>();
        Paper paper1 = new Paper();
        paper1.setId("1");
        paper1.setTitle("Test Paper 1");
        Paper paper2 = new Paper();
        paper2.setId("2");
        paper2.setTitle("Test Paper 2");
        papers.add(paper1);
        papers.add(paper2);
        when(paperRepository.findByAuthorId(userId)).thenReturn(papers);

        // Test
        ResponseEntity<Iterable<Paper>> responseEntity = paperController.getPaperById(userId);

        // Verify
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(papers, responseEntity.getBody());
    }


    // Test for getPaperById
    @Test
    public void getPaperById(){
        String userId = "user123";
        Paper paper1 = new Paper();
        paper1.setId("1");
        paper1.setTitle("Test Paper 1");
        paper1.setAuthorId(userId);

        when(paperRepository.findByPaperId("1")).thenReturn(paper1);

        ResponseEntity<Paper> responseEntity = paperController.getPaper("1");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void updatePaperTest() {
        String userId = "user123";
        Paper paper1 = new Paper();
        paper1.setId("1");
        paper1.setTitle("Test Paper 1");
        paper1.setAuthorId(userId);

        when(paperRepository.findByPaperId("1")).thenReturn(paper1);

        ResponseEntity<String> responseEntity = paperController.updatePaperDetails(paper1, "1");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    
    @Test
    public void getDocumentTest() {
        String userId = "user123";
        Paper paper1 = new Paper();
        paper1.setId("1");
        paper1.setTitle("Test Paper 1");
        paper1.setAuthorId(userId);
        byte[] file = new byte[10];
        paper1.setFile(file);

        when(paperRepository.findByPaperId("1")).thenReturn(paper1);

        ResponseEntity<byte[]> responseEntity = paperController.getDocument("1");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void getDocumentTestNull() {
        String userId = "user123";
        Paper paper1 = new Paper();
        paper1.setId("1");
        paper1.setTitle("Test Paper 1");
        paper1.setAuthorId(userId);
        byte[] file = new byte[10];
        paper1.setFile(null);

        when(paperRepository.findByPaperId("1")).thenReturn(paper1);

        ResponseEntity<byte[]> responseEntity = paperController.getDocument("1");
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void getDocumentTestNullId() {
        String userId = "user123";
        Paper paper1 = new Paper();
        paper1.setId("1");
        paper1.setTitle("Test Paper 1");
        paper1.setAuthorId(userId);
        byte[] file = new byte[10];
        paper1.setFile(null);

        when(paperRepository.findByPaperId("1")).thenReturn(null);

        ResponseEntity<byte[]> responseEntity = paperController.getDocument("1");
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void testNewPaper_InvalidRequest() {
        Paper request = new Paper();
        request.setTitle("Test Title");
        request.setShortdesc("Test Description");
        request.setApproved(false);

        // Mock repository behavior
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        // Test
        ResponseEntity<Paper> responseEntity = paperController.addNewPaper(request);

        // Verify
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    public void uploadFileTest() throws IOException {
        String userId = "user123";
        Paper paper1 = new Paper();
        paper1.setId("1");
        paper1.setTitle("Test Paper 1");
        paper1.setAuthorId(userId);
        byte[] file = new byte[10];
        paper1.setFile(file);

        when(paperRepository.findByPaperId("1")).thenReturn(paper1);

        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "test.txt", "text/plain", "test data".getBytes());
        ResponseEntity<String> responseEntity = paperController.uploadFile(mockMultipartFile, "1");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void reuploadPaperTest () throws IOException {
        String userId = "user123";
        Paper paper1 = new Paper();
        paper1.setId("1");
        paper1.setTitle("Test Paper 1");
        paper1.setAuthorId(userId);
        byte[] file = new byte[10];
        paper1.setFile(file);

        when(paperRepository.findByPaperId("1")).thenReturn(paper1);

        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "test.txt", "text/plain", "test data".getBytes());
        ResponseEntity<String> responseEntity = paperController.reuploadPaper("1", mockMultipartFile);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

}

