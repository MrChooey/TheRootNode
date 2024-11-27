/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Utility.Database;
import Model.Post;
import Model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
/**
 *
 * @author ronpa
 */
public class PostController {
    private Database database;
    
    public PostController(Database database) {
        this.database = database;
    }
    
    public boolean createPost(Post post) {        
        String sql = "INSERT INTO post (title, content, user_id, dateTime) VALUES (?, ?, ?, ?)";
        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
             
            statement.setString(1, post.getTitle());
            statement.setString(2, post.getContent());
            statement.setInt(3, post.getUser().getID());
            statement.setTimestamp(4, java.sql.Timestamp.valueOf(post.getDateTime()));
            
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public ArrayList<Post> getAllPosts() {
        ArrayList<Post> posts = new ArrayList<>();
        String sql = "SELECT * FROM post";

        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Post post = new Post();
                post.setID(resultSet.getInt("id"));
                post.setTitle(resultSet.getString("title"));
                post.setContent(resultSet.getString("content"));
                post.setReported(resultSet.getBoolean("isReported"));
                
                System.out.println(post.getLikes());
                
                int userId = resultSet.getInt("user_id");
                User user = getUserById(userId);
                post.setUser(user);
                
                post.setLocalDateTime(resultSet.getTimestamp("dateTime").toLocalDateTime());
                posts.add(post);
                
                System.out.println("Printed successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }
    
    public void deletePost(int postId) {
        String sql = "DELETE FROM post WHERE id = ?";
        
        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, postId);
            
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void reportPost(int postId) {
        String sql = "UPDATE post SET isReported = ? WHERE id = ?";
        
        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setBoolean(1, true);
            statement.setInt(2, postId);
            
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void dismissReport(int postId) {
        String sql = "UPDATE post SET isReported = ? WHERE id = ?";
        
        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setBoolean(1, false);
            statement.setInt(2, postId);
            
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private User getUserById(int userId) {
        String sql = "SELECT * FROM user WHERE id = ?";
        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                User user = new User();
                user.setID(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));

                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public int getLikeCount(int postId) {
        String sql = "SELECT COUNT(*) FROM likes WHERE post_id = ?";
        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, postId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    
    public boolean hasUserLikedPost(int userId, int postId) {
    String sql = "SELECT * FROM likes WHERE user_id = ? AND post_id = ?";
    try (Connection connection = database.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {
        
        statement.setInt(1, userId);
        statement.setInt(2, postId);
        ResultSet resultSet = statement.executeQuery();
        
        if (resultSet.next()) {
            System.out.println("true");
            return true;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}

    public boolean toggleLike(int userId, int postId) {
        if (hasUserLikedPost(userId, postId)) {
            String deleteSql = "DELETE FROM likes WHERE post_id = ? AND user_id = ?";
            try (Connection connection = database.getConnection();
                 PreparedStatement statement = connection.prepareStatement(deleteSql)) {

                statement.setInt(1, postId);
                statement.setInt(2, userId);
                int rowsAffected = statement.executeUpdate();
                return rowsAffected > 0;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {
            String sql = "INSERT INTO likes (user_id, post_id) VALUES (?, ?)";
            try (Connection connection = database.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setInt(1, userId);
                statement.setInt(2, postId);
                statement.executeUpdate();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
