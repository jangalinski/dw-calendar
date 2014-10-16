package de.holisticon.dw.calendar.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/")
public interface LoadCalendar {

    @GET
    @Path("/foo/{name}")
    public Response load(@PathParam("name") String name);
}
