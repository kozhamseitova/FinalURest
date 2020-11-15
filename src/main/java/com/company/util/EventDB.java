package com.company.util;

import com.company.models.Event;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EventDB extends DB{
    Connection connection = null;
    private static EventDB instance = null;

    public static EventDB getInstance(){
        if(instance == null){
            instance = new EventDB();
        }
        return instance;
    }
    private EventDB(){
        connection = super.getConnection();
    }

    public ArrayList<Event> getAllEvents()
    {
        try
        {
            String sql = "SELECT * FROM events";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            ArrayList<Event> events = new ArrayList();
            while (resultSet.next()) {
                Event event = new Event(
                        resultSet.getLong("id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getString("time")
                );
                events.add(event);
            }
            return events;
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return null;
    }

    public boolean addEvents(Event event) {
        try {
            String sql = "INSERT INTO events(id, title, description, time) " +
                    "VALUES(?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, event.getId());
            stmt.setString(2, event.getTitle());
            stmt.setString(3, event.getDescription());
            stmt.setString(4, event.getTime());
            stmt.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return false;
        }
        return true;
    }

    public void update(Event event)
    {
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("update events set title=?, description=?, time=? where id=?");
            preparedStatement.setString(1, event.getTitle());
            preparedStatement.setString(2, event.getDescription());
            preparedStatement.setString(3, event.getTime());
            preparedStatement.setLong(4, event.getId());
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
            PreparedStatement preparedStatement = connection.prepareStatement("delete from events where id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }
        catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
}
