package de.holisticon.dw.calendar.resource;


import de.holisticon.dw.calendar.DwCalendarApplication;
import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Uid;
import net.fortuna.ical4j.model.property.Version;
import net.fortuna.ical4j.util.UidGenerator;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.net.SocketException;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Path("/")
@Produces({"text/calendar", "text/v-calendar"})
public class SimpleCalendarResource {

    @GET
    public Response generateCalendar() throws SocketException {
        //Create an 8 hours event starting at 5/2/2013 9:00 pm ending at 5/2/2013 17:00 am

        Calendar start = new GregorianCalendar();

        start.set(2013, 2,5,9, 0);

        Calendar end=new GregorianCalendar();
        end.set(2013, 2,5,17,0);

        DateTime startTime=new DateTime(start.getTime());
        DateTime endTime=new DateTime(end.getTime());

        //Create event
        VEvent eightHourEvent=new VEvent(startTime,endTime,"Test Event");

        net.fortuna.ical4j.model.Calendar cal = new net.fortuna.ical4j.model.Calendar();
        //add product Id
        cal.getProperties().add(new ProdId("-//Mozilla.org/NONSGML Mozilla Calendar V1.1//EN"));
        cal.getProperties().add(Version.VERSION_2_0);
        cal.getProperties().add(CalScale.GREGORIAN);

        //generate unique identifier
        UidGenerator ug = new UidGenerator("uidGen");
        Uid uid = ug.generateUid();

        eightHourEvent.getProperties().add(uid);

        //add event in ical4j calendar
        cal.getComponents().add(eightHourEvent);
        System.out.println(cal.toString());

        // @formatter:off
        return Response.ok()
                .header("content-disposition", "attachment;filename=calendar.ics")
                .entity(cal.toString())
                .build();
        // @formatter:on
    }
}
