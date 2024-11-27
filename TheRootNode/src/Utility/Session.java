/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utility;

import Model.User;

/**
 *
 * @author ronpa
 */
public class Session {
    private static User currentUser;
    
    public static User getCurrentUser() {
        return currentUser;
    }
    
    public static void setUser(User user) {
        currentUser = user;
    }
    
    public static boolean isUserLoggedIn() {
        return currentUser != null;
    }
}
