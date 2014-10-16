package de.holisticon.dw.calendar.resource;

import de.holisticon.dw.calendar.api.LoadCalendar;

import javax.ws.rs.core.Response;
import java.io.Serializable;


public class CalendarResourceBean implements Serializable, LoadCalendar {
    private static final long serialVersionUID = 1L;

    @Override
    public Response load(final String name) {
        return Response.ok().entity("name="+name).build();
    }
}
