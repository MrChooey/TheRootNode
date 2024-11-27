/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ronpa
 */
public class Database {
    private String user = "root";
    private String pass = "admin";
    private String url = "jdbc:mysql://localhost/root_node";
    
    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, pass);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }    
}
