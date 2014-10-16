package de.holisticon.dw.calendar.resource;

import de.holisticon.dw.calendar.api.CalendarResource;
import de.holisticon.dw.calendar.api.LoadCalendar;
import de.holisticon.dw.calendar.ical.FluentCalendar;

import javax.ws.rs.core.Response;
import java.io.Serializable;


public class CalendarResourceBean implements Serializable, CalendarResource {
    private static final long serialVersionUID = 1L;

    @Override
    public Response load(final String name) {
        return Response.ok().entity(FluentCalendar.calendar().get().toString()).build();
    }
}
