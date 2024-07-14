package model;


import java.time.LocalDateTime;

public class Comment {
    private int id;
    private int productId;
    private int userId;
    private String username;
    private String content;
    private LocalDateTime date;

    public Comment() {

    }

    public Comment(int id, int productId, int userId, String username, String content, LocalDateTime date) {
        this.id = id;
        this.productId = productId;
        this.userId = userId;
        this.username = username;
        this.content = content;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
