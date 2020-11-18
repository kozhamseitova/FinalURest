package com.company.util;

import com.company.models.Club;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class ClubDB extends DB {
    Connection connection = null;
    private static ClubDB instance = null;

    public static ClubDB getInstance(){
        if(instance == null){
            instance = new ClubDB();
        }
        return instance;
    }
    private ClubDB(){
        connection = super.getConnection();
    }

    public LinkedList<Club> getAllClubs()
    {
        try
        {
            String sql = "SELECT * FROM clubs";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            LinkedList<Club> clubList = new LinkedList<>();
            while (resultSet.next()) {
                Club clubs = new Club(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("img"),
                        resultSet.getString("description")
                );
                clubList.add(clubs);
            }
            return clubList;
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return null;
    }

    public boolean addClubs(Club club) {
        try {
            String sql = "INSERT INTO clubs(name, img, description) " +
                    "VALUES(?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, club.getName());
            stmt.setString(2, club.getImg());
            stmt.setString(3, club.getDescription());
            stmt.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return false;
        }
        return true;
    }

    public void update(Club club)
    {
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("update clubs set id=?, img=?, description=? where name=?");
            preparedStatement.setLong(1, club.getId());
            preparedStatement.setString(2, club.getImg());
            preparedStatement.setString(3, club.getDescription());
            preparedStatement.setString(4, club.getName());
            preparedStatement.executeUpdate();
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }

    public void remove(long id){
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from clubs where id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
}
