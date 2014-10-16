package de.holisticon.dw.calendar.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static de.holisticon.dw.calendar.api.CalendarResource.ROOT_PATH;
import static de.holisticon.dw.calendar.api.CalendarResource.TEXT_CALENDAR;

@Path(ROOT_PATH)
public interface LoadCalendar {

    @GET
    @Path("/foo/{name}")
    @Produces(TEXT_CALENDAR)
    public Response load(@PathParam("name") String name);
}
