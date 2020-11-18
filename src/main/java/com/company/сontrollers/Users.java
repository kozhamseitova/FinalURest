package com.company.сontrollers;

import com.company.models.User;
import com.company.util.UserDB;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class Users {

    private final UserDB userDB = UserDB.getInstance();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(User user) {
        User user1 = userDB.get(user.getEmail(), user.getPassword());
        if(user1!=null){
            return Response.status(Response.Status.OK).entity("success").build();
        }else {
            return Response.status(Response.Status.NOT_FOUND).entity("failed").build();
        }
    }
}