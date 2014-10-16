package de.holisticon.dw.calendar.api;


import javax.ws.rs.Path;

@Path(CalendarResource.ROOT_PATH)
public interface CalendarResource extends LoadCalendar {

    String ROOT_PATH = "/";
    String TEXT_CALENDAR = "text/calendar";


}
