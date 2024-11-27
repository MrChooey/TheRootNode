/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Utility.Database;
import Model.User;
import Utility.Session;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ronpa
 */
public class UserController {
    private User u;
    private Database database;
    
    public UserController(User u, Database database) {
        this.u = u;
        this.database = database;
    }
    
    public boolean registerUser() {
        String sql = "INSERT INTO user (email, password) VALUES (?, ?);";

        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, u.getEmail());
            statement.setString(2, u.getPassword());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        u.setID(generatedKeys.getInt(1));

                        Session.setUser(u);
                    }
                }
            }

            return true;

        } catch (SQLException e) {
            System.out.println("Error inserting user: " + e.getMessage());
            return false;
        }
    }
    
    public boolean validateUser(String email, String password, Database database) {
        String sql = "SELECT * FROM user WHERE email = ?";
        
        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, email);
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                String storedPassword = resultSet.getString("password");
                
                if (storedPassword.equals(password)) {
                    User authenticatedUser = new User();
                    authenticatedUser.setEmail(resultSet.getString("email"));
                    authenticatedUser.setPassword(resultSet.getString("password"));
                    authenticatedUser.setID(resultSet.getInt("id"));
                    
                    Session.setUser(authenticatedUser);
                    
                    return true;
                }
            }
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public String getUserRole(int userId) {
        String sql = "SELECT role FROM user where id = ?";
        
        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                return resultSet.getString("role");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
