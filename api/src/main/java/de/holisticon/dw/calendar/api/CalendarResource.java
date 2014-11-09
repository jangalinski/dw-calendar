package de.holisticon.dw.calendar.api;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.io.Serializable;

import static de.holisticon.dw.calendar.api.CalendarResource.PATH.*;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path(ROOT)
public interface CalendarResource extends Serializable {

    enum TYPE {
        ;
        public static final String JSON = APPLICATION_JSON;
        public static final String ICAL = "text/calendar";
    }

    enum PATH {
        ;
        public static final String ROOT = "/";
        public static final String ADD_EVENTS = "/addEvents";
        public static final String GET_CALENDAR = "/getCalendar";
    }

    @GET
    @Path(GET_CALENDAR)
    @Produces(TYPE.ICAL)
    public String getCalendar();

    @POST
    @Path(ADD_EVENTS)
    @Consumes(APPLICATION_JSON)
    public void addEvents(RemoteCalendar remoteCalendar);

}
