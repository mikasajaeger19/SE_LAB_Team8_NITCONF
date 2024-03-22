package mysqltest.demo.models;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import mysqltest.demo.models.Paper;
import java.time.LocalDate;

@Document(collection = "versions")
public class Version {
    @Id
    private String versionId;

    private String title;
    private String abstractUrl;
    private LocalDate releaseDate;

    private String paperId; // Changed to String to store ObjectId
    private byte[] file;

    public Version() {
        // Default constructor required by Spring Data MongoDB
    }

    public Version (Paper paper) {
        this.title = paper.getTitle();
        this.abstractUrl = paper.getAbstractUrl();
        this.releaseDate = paper.getUploadDate();
        this.paperId = paper.getId();
        this.file = paper.getFile();
    }

    public Version(String title, String abstractUrl, LocalDate releaseDate, String paperId, byte[] file) { 
        this.title = title;
        this.abstractUrl = abstractUrl;
        this.releaseDate = releaseDate;
        this.paperId = paperId;
        this.file = file;
    }

    // Getters and setters
    public String getId() {
        return versionId;
    }

    public void setId(String id) {
        this.versionId = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbstractUrl() {
        return abstractUrl;
    }

    public void setAbstractUrl(String abstractUrl) {
        this.abstractUrl = abstractUrl;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
}
