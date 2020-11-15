package com.company.util;


import com.company.models.Group;
import com.company.models.Major;
import com.company.models.Student;
import com.company.util.interfaces.IDB;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DB implements IDB<Student>{
    private static Connection connection;

    public DB() {
    }

    public Connection getConnection(){
        if (connection == null){
            Context initalContext = null;
            Connection connection = null;
            try {
                initalContext = new InitialContext();
                Context ctx = (Context) initalContext.lookup("java:comp/env");
                DataSource ds = (DataSource) ctx.lookup("jdbc/final");
                connection = ds.getConnection();
                DB.connection = connection;
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return connection;
    }

    @Override
    public Student getStudentById(int id){
        Connection connection = getConnection();
        Student student = null;
        try {
            Statement st = connection.createStatement();
            String sql = "SELECT * from students where id="+id;
            System.out.println("[Query] "+sql);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                student = new Student(rs.getInt("id"),
                        rs.getInt("student_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getInt("enrol_date")
                );
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return student;
    }

    @Override
    public List<Student> getAllStudents(){
        Connection connection = getConnection();
        List<Student> students = new ArrayList<>();
        try {
            Statement st = connection.createStatement();
            String sql = "SELECT * from students";
            System.out.println("[Query] "+sql);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                Student student = new Student(rs.getInt("id"),
                        rs.getInt("student_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getInt("enrol_date")
                );
                students.add(student);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return students;
    }

    public List<Group> getAllGroups(){
        Connection connection = getConnection();
        List<Group> groups = new ArrayList<>();
        try {
            Statement st = connection.createStatement();
            String sql = "SELECT * from groups";
            System.out.println("[Query] "+sql);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                Group group = new Group(rs.getInt("id"),
                        rs.getString("name")
                );
                groups.add(group);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return groups;
    }

    public List<Major> getAllMajors(){
        Connection connection = getConnection();
        List<Major> majors = new ArrayList<>();
        try {
            Statement st = connection.createStatement();
            String sql = "SELECT * from majors";
            System.out.println("[Query] "+sql);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                Major major = new Major(rs.getInt("id"),
                        rs.getString("name")
                );
                majors.add(major);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return majors;
    }

    @Override
    public List<Student> getAllStudentsByMajor(int major_id){
        Connection connection = getConnection();
        List<Student> students = new ArrayList<>();
        try {
            Statement st = connection.createStatement();
            String sql = "SELECT * from student_major where major_id="+major_id;
            System.out.println("[Query] "+sql);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                Student student = getStudentById(rs.getInt("student_id"));
                students.add(student);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public List<Student> getAllStudentsByGroup(int group_id){
        Connection connection = getConnection();
        List<Student> students = new ArrayList<>();
        try {
            Statement st = connection.createStatement();
            String sql = "SELECT * from student_group where group_id="+group_id;
            System.out.println("[Query] "+sql);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                Student student = getStudentById(rs.getInt("student_id"));
                students.add(student);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return students;
    }
}
