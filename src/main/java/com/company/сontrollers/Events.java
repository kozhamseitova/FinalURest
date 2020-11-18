package com.company.сontrollers;

import com.company.models.Event;
import com.company.util.EventDB;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/event")
public class Events{
    private EventDB eventDB = EventDB.getInstance();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Event> list() {
        return eventDB.getAllEvents();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/add/admin")
    public Response add(Event event) {
        try {
            eventDB.addEvents(event);
            return Response.status(Response.Status.OK).entity("Successfully added").build();
        }catch (BadRequestException e){
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/update/admin")
    public Response update(Event event){
        try{
            eventDB.update(event);
            return Response.status(Response.Status.OK).entity("Successfully updated").build();
        } catch (BadRequestException e){
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/admin/{id}")
    public Response delete(@PathParam("id") Long id){
        try{
            eventDB.remove(id);
            return Response.status(Response.Status.OK).entity("Successfully deleted").build();
        } catch (BadRequestException e){
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}

