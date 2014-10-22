package de.holisticon.dw.calendar.rest;

import de.holisticon.dw.calendar.api.CalendarResource;
import de.holisticon.dw.calendar.api.RemoteCalendar;

import javax.ws.rs.core.Response;
import java.io.Serializable;


public class CalendarResourceBean implements Serializable, CalendarResource {
    private static final long serialVersionUID = 1L;


    @Override
    public Response load(final RemoteCalendar remoteCalendar) {
        return Response.ok().build();
    }
}
