package nitconf.nitconf.models;

import jakarta.persistence.*;
import java.util.Arrays;

@Entity
public class PdfDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;
    private byte[] content;

    // Constructors

    public PdfDocument() {
        // Default constructor
    }

    public PdfDocument(String fileName, byte[] content) {
        this.fileName = fileName;
        this.content = content;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    // Additional methods

    @Override
    public String toString() {
        return "PdfDocument{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", content=" + Arrays.toString(content) +
                '}';
    }

    // You can add more methods here based on your requirements

    // For example, a method to get the size of the content
    public int getContentSize() {
        return content != null ? content.length : 0;
    }
}
