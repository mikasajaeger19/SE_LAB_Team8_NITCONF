package mysqltest.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity
public class Paper {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer paperId;

  private String title;
  private Boolean approved;
  private String shortdesc;
  private String abstractUrl;

  // Additional fields
  private String tags;
  private LocalDate uploadDate;
  private Long authorId;

  public Paper() {
  }

  public Paper(String title, Boolean approved, String shortdesc, String abstractUrl,
               String tags, LocalDate uploadDate, Long authorId) {
    
    this.title = title;
    this.approved = approved;
    this.shortdesc = shortdesc;
    this.abstractUrl = abstractUrl;
    this.tags = tags;
    this.uploadDate = uploadDate;
    this.authorId = authorId;
  }

  public Integer getId() {
    return paperId;
  }

  public void setId(Integer id) {
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

  public void setAbstractText(String abstractUrl) {
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

  public Long getAuthorId() {
    return authorId;
  }

  public void setAuthorId(Long authorId) {
    this.authorId = authorId;
  }
}
