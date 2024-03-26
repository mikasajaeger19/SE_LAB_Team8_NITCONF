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
import org.springframework.data.domain.Sort;
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
import mysqltest.demo.controllers.TagController;
import mysqltest.demo.repositories.TagRepository;
import mysqltest.demo.models.Tags;



public class TagControllerTests {   

    @Mock
    private TagRepository tagRepository;
    
    @InjectMocks
    private TagController tagController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void AddNewTag() {
        Tags tag = new Tags();
        tag.setId("1");
        tag.setPaperId("1");
        tag.setTagName("tag1");

        when(tagRepository.save(tag)).thenReturn(tag);

        ResponseEntity<String> response = tagController.addNewTag(tag);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void GetTagsByPaperId() {
        Tags tag = new Tags();
        tag.setId("1");
        tag.setPaperId("1");
        tag.setTagName("tag1");

        ArrayList<Tags> tags = new ArrayList<>();
        tags.add(tag);

        when(tagRepository.findByPaperId("1")).thenReturn(tags);

        ResponseEntity<Iterable<Tags>> response = tagController.getTagsByPaperId("1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }


}
