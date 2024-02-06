package mysqltest.demo.models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


@Entity
public class Version {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer versionId;

  private String title;
  private String abstractUrl;
  private String comments;
  private LocalDate releaseDate;

  private Integer paperId;

  // @ManyToOne
  // private Paper paper;

  public Version() {
    // Default constructor required by JPA
  }

  public Version(String title, String abstractUrl, String comments, LocalDate releaseDate, Integer paperId) {
    this.title = title;
    this.abstractUrl = abstractUrl;
    this.comments = comments;
    this.releaseDate = releaseDate;
    this.paperId = paperId;
  }

  // Getter and Setter methods for all fields

  public Integer getVersionId() {
    return versionId;
  }

  public void setVersionId(Integer versionId) {
    this.versionId = versionId;
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

  // public String[] getComments() {
  //   return comments;
  // }

//   public void setComments(String comment) {
//     // Check if comments array is null
//     if (this.comments == null) {
//         this.comments = new String[]{comment};
//     } else {
//         // Create a new array with increased size
//         String[] newComments = new String[this.comments.length + 1];

//         // Copy existing comments to the new array
//         System.arraycopy(this.comments, 0, newComments, 0, this.comments.length);

//         // Append the new comment to the end of the array
//         newComments[this.comments.length] = comment;

//         // Update the comments array
//         this.comments = newComments;
//     }
// }

  public String getComments() {
    return comments;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }

  public LocalDate getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(LocalDate releaseDate) {
    this.releaseDate = releaseDate;
  }

  public Integer getPaper() {
    return paperId;
  }

  public void setPaper(Integer paperId) {
    this.paperId = paperId;
  }

}
