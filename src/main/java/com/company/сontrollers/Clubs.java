package com.company.сontrollers;


import com.company.models.Club;
import com.company.util.ClubDB;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/club")
public class Clubs {

    private ClubDB clubDB = ClubDB.getInstance();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Club> list() {
        return clubDB.getAllClubs();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/add/admin")
    public Response add(Club club) {
        try {
            clubDB.addClubs(club);
            return Response.status(Response.Status.OK).entity("Successfully added").build();
        }catch (BadRequestException e){
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/update/admin")
    public Response update(Club club){
        try{
            clubDB.update(club);
            return Response.status(Response.Status.OK).entity("Successfully updated").build();
        } catch (BadRequestException e){
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/admin/{id}")
    public Response delete(@PathParam("id") long id){
        try{
            clubDB.remove(id);
            return Response.status(Response.Status.OK).entity("Successfully deleted").build();
        } catch (BadRequestException e){
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}

