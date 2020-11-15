package com.company.util;

import com.company.models.New;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NewDB extends DB {
    Connection connection = null;
    private static NewDB instance = null;

    public static NewDB getInstance(){
        if(instance == null){
            instance = new NewDB();
        }
        return instance;
    }
    private NewDB(){
        connection = super.getConnection();
    }

    public ArrayList<New> getAllNews()
    {
        try
        {
            String sql = "SELECT * FROM news";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            ArrayList<New> news = new ArrayList();
            while (resultSet.next()) {
                New new1 = new New(
                        resultSet.getLong("id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getString("time")
                );
                news.add(new1);
            }
            return news;
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return null;
    }

    public boolean addNews(New new1) {
        try {
            String sql = "INSERT INTO news(id, title, description, time) " +
                    "VALUES(?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, new1.getId());
            stmt.setString(2, new1.getTitle());
            stmt.setString(3, new1.getDescription());
            stmt.setString(4, new1.getTime());
            stmt.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return false;
        }
        return true;
    }

    public void update(New news)
    {
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("update news set title=?, description=?, time=? where id=?");
            preparedStatement.setString(1, news.getTitle());
            preparedStatement.setString(2, news.getDescription());
            preparedStatement.setString(3, news.getTime());
            preparedStatement.setLong(4, news.getId());
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
            PreparedStatement preparedStatement = connection.prepareStatement("delete from news where id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
}
