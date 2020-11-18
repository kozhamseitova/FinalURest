package com.company.сontrollers;

import com.company.models.Group;
import com.company.models.Major;
import com.company.models.Student;
import com.company.util.DB;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/students")
public class StudentSearch{

    private DB db = DB.getInstance();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> students() {
        return db.getAllStudents();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/majors")
    public List<Major> majors() {
        return db.getAllMajors();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/groups")
    public List<Group> groups() {
        return db.getAllGroups();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/majorid/{major_id}")
    public List<Student> byMajor(@PathParam("major_id") int major_id){
        return db.getAllStudentsByMajor(major_id);
    }

    //jwt token to give admin and authorize user
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/groupid/{group_id}")
    public List<Student> byGroup(@PathParam("group_id") int group_id){
        return db.getAllStudentsByGroup(group_id);
    }
}
