package Hibernate.BlogProject;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "blogposts")
public class BlogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BlogID")
    private int blogId;

    @Column(name = "username")
    private String username;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "timestamp")
    private Date timestamp;

    public BlogEntity() {
     
    }

    public BlogEntity(String username, String title, String content, Date timestamp) {
        this.username = username;
        this.title = title;
        this.content = content;
        this.timestamp = timestamp;
    }

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BlogID: ").append(blogId).append("\n");
        sb.append("Username: ").append(username).append("\n");
        sb.append("Title: ").append(title).append("\n");
        sb.append("Content: ").append(content).append("\n");
        sb.append("Timestamp: ").append(timestamp).append("\n");
        return sb.toString();
    }
}
