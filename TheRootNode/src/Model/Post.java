/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author ronpa
 */
public class Post {
    private int ID;
    private String title;
    private String content;
    private User user;
    private int likes;
    private boolean isReported;
    private ArrayList<Comment> comments;
    private LocalDateTime dateTime;
    
    public Post() {}
    
    public int getID() {
        return ID;
    }
    
    public void setID(int ID) {
        this.ID = ID;
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
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public LocalDateTime getDateTime() {
        return dateTime;
    }
    
    public void setLocalDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
    
    public String getFormattedDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm");
        return dateTime.format(formatter);
    }
    
    public ArrayList<Comment> getComments() {
        return comments;
    }
    
    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }
    
    public int getLikes() {
        return likes;
    }
    
    public void setLikes(int likes) {
        this.likes = likes;
    }
    
    public boolean isReported() {
        return isReported;
    }
    
    public void setReported(boolean reported) {
        isReported = reported;
    }
}
