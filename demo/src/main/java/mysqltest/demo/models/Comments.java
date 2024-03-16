package mysqltest.demo.models;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "comments")
public class Comments {
    @Id
    private String commentId;

    private String comment;

    private String versionId;

    public String getId() {
        return commentId;
    }

    public void setId(String id) {
        this.commentId = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }
}
