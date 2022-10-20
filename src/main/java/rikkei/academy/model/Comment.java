package rikkei.academy.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int rate;
    private String author;
    private String feedback;
    private int likeCount;
    @Temporal(TemporalType.DATE)
    @CreationTimestamp
    private Date date;

    public Comment() {
    }

    public Comment(int id, int rate, String author, String feedback, int likeCount, Date date) {
        this.id = id;
        this.rate = rate;
        this.author = author;
        this.feedback = feedback;
        this.likeCount = likeCount;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public Comment clone() {
        Comment comment = new Comment();
        comment.setId(id);
        comment.setDate(date);
        comment.setFeedback(feedback);
        comment.setRate(rate);
        comment.setAuthor(author);
        comment.setLikeCount(likeCount);
        return comment;
    }


    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", rate=" + rate +
                ", author='" + author + '\'' +
                ", feedback='" + feedback + '\'' +
                ", likeCount=" + likeCount +
                ", date=" + date +
                '}';
    }
}