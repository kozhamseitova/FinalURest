package com.company.сontrollers;

import com.company.models.New;
import com.company.util.NewDB;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/new")
public class News{
    private NewDB newDB = NewDB.getInstance();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<New> list() {
        return newDB.getAllNews();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/add/admin")
    public Response add(New news) {
        try {
            newDB.addNews(news);
            return Response.status(Response.Status.OK).entity("Successfully added").build();
        }catch (BadRequestException e){
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/update/admin")
    public Response update(New news){
        try{
            newDB.update(news);
            return Response.status(Response.Status.OK).entity("Successfully updated").build();
        } catch (BadRequestException e){
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/admin/{id}")
    public Response delete(@PathParam("id") Long id){
        try{
            newDB.remove(id);
            return Response.status(Response.Status.OK).entity("Successfully deleted").build();
        } catch (BadRequestException e){
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}

