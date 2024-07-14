package database;

import model.Comment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO {

    public void addComment(Comment comment) {
        String sql = "INSERT INTO comments (product_id, user_id, username, content, date) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, comment.getProductId());
            pst.setInt(2, comment.getUserId());
            pst.setString(3, comment.getUsername());
            pst.setString(4, comment.getContent());
            pst.setTimestamp(5, Timestamp.valueOf(comment.getDate()));
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Comment> getCommentsByProductId(int productId) {
        List<Comment> comments = new ArrayList<>();
        String sql = "SELECT * FROM comments WHERE product_id = ? ORDER BY date DESC";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, productId);
            try (ResultSet resultSet = pst.executeQuery()) {
                while (resultSet.next()) {
                    Comment comment = new Comment();
                    comment.setId(resultSet.getInt("id"));
                    comment.setProductId(resultSet.getInt("product_id"));
                    comment.setUserId(resultSet.getInt("user_id"));
                    comment.setUsername(resultSet.getString("username"));
                    comment.setContent(resultSet.getString("content"));
                    comment.setDate(resultSet.getTimestamp("date").toLocalDateTime());
                    comments.add(comment);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return comments;
    }
}
