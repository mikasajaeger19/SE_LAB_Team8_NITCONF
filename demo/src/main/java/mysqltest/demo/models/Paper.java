package mysqltest.demo.models;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "papers")
public class Paper {
    @Id
    private String paperId;

    private String title;
    private Boolean approved;
    private String shortdesc;
    private String abstractUrl;

    // Additional fields
    private String tags;
    private LocalDate uploadDate;
    private String authorId;

    public Paper() {
    }

    public Paper(String title, Boolean approved, String shortdesc, String abstractUrl,
                 String tags, LocalDate uploadDate, String authorId) {

        this.title = title;
        this.approved = approved;
        this.shortdesc = shortdesc;
        this.abstractUrl = abstractUrl;
        this.tags = tags;
        this.uploadDate = uploadDate;
        this.authorId = authorId;
    }

    public String getId() {
        return paperId;
    }

    public void setId(String id) {
        this.paperId = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public String getShortdesc() {
        return shortdesc;
    }

    public void setShortdesc(String shortdesc) {
        this.shortdesc = shortdesc;
    }

    public String getAbstractUrl() {
        return abstractUrl;
    }

    public void setAbstractUrl(String abstractUrl) {
        this.abstractUrl = abstractUrl;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public LocalDate getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDate uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }
}
