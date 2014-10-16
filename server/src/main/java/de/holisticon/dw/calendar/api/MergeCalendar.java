package de.holisticon.dw.calendar.api;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/calendar")
public interface MergeCalendar {

    @POST
    @Path("/merge/{name}")
    public Response merge(@PathParam("name") String name);
}
