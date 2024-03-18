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

import javax.xml.stream.events.Comment;

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

import mysqltest.demo.controllers.CommentsController;
import mysqltest.demo.repositories.VersionRepository;
import mysqltest.demo.models.Version;
import mysqltest.demo.models.Comments;

public class CommentsControllerTests {


    @InjectMocks
    private CommentsController commentsController;

    @Mock
    private VersionRepository versionRepository;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void AddCommentsToVersion() {
        String CommentId = "1";
        String VersionId = "1";
        Version version = new Version();
        version.setId("1");
        versionRepository.save(version);

        Comments comments = new Comments();
        comments.setId(CommentId);
        comments.setVersionId(VersionId);

        when(versionRepository.findByVersionId("1")).thenReturn(version);

        ResponseEntity<String> comment = commentsController.addNewComment("hello", "1");

        assertEquals(HttpStatus.OK, comment.getStatusCode());

    }

    @Test
    public void getCommentsOfVersion() {
        String VersionId = "1";
        Version version = new Version();
        version.setId("1");
        versionRepository.save(version);

        when(versionRepository.findByVersionId("1")).thenReturn(version);

        ResponseEntity<Iterable <String>> comment = commentsController.getCommentsForVersion("1");

        assertEquals(HttpStatus.OK, comment.getStatusCode());

    }

}