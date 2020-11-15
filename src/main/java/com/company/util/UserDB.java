package com.company.util;

import com.company.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class UserDB extends DB{
    Connection connection = null;
    private static UserDB instance = null;

    public static UserDB getInstance(){
        if(instance == null){
            instance = new UserDB();
        }
        return instance;
    }
    private UserDB(){
        connection = super.getConnection();
    }

    public User getUserByEmail(String email) {
        try {
            String sql = "SELECT * FROM users WHERE email = ? LIMIT 1";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getLong("id"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("status")
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public User get(String email, String password) {
        try {
            String sql = "SELECT * FROM users WHERE email = ? AND password = ? LIMIT 1";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getLong("id"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("status")
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public String getStatusByEmail(String email) {
        try {
            String sql = "SELECT status FROM users WHERE email = ? LIMIT 1";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return(
                        rs.getString("status")
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public HashMap<Long, String> getAllUsers() {
        try {
            String sql = "SELECT user_id, email FROM users";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            HashMap<Long, String> users = new HashMap<>();
            while (rs.next()) {
                User user = new User(
                        rs.getLong("id"),
                        rs.getString("email")
                );
                users.put(user.getId(), user.getEmail());
            }
            return users;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
