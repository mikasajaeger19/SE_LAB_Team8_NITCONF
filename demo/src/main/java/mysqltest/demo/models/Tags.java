package mysqltest.demo.models;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection = "tags")
public class Tags{
    @Id
    private String tagId;
    private String tagName;
    private String paperId;

    public Tags() {

    }

    public Tags(String tagId, String tagName, String paperId){
        this.paperId = paperId;
        this.tagName = tagName;
        this.tagId = tagId;
    }

    public String getId() {
        return tagId;
    }

    public void setId(String id) {
        this.tagId = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }
}