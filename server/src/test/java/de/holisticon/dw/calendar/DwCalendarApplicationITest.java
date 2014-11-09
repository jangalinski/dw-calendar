package de.holisticon.dw.calendar;

import biweekly.Biweekly;
import biweekly.ICalendar;
import com.google.common.io.Resources;
import de.holisticon.dw.calendar.api.CalendarResource;
import de.holisticon.dw.calendar.api.RemoteCalendar;
import de.holisticon.dw.calendar.test.DwCalendarAppRule;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import static de.holisticon.dw.calendar.api.CalendarResource.PATH.ADD_EVENTS;
import static de.holisticon.dw.calendar.api.CalendarResource.PATH.ROOT;
import static de.holisticon.dw.calendar.api.CalendarResource.TYPE.ICAL;
import static de.holisticon.dw.calendar.api.CalendarResource.TYPE.JSON;
import static org.assertj.core.api.Assertions.assertThat;

public class DwCalendarApplicationITest {

    @ClassRule
    public static final DwCalendarAppRule DW = new DwCalendarAppRule();

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void call_addEvents() throws InterruptedException {
        final WebTarget target = ClientBuilder.newClient().target(DW.getEndpoint());

        target.path(ROOT).path(ADD_EVENTS).request(JSON).post(Entity.json(new RemoteCalendar("webcal://ws.audioscrobbler.com/1.0/user/janhoo/events.ics")));

        final Response cal = target.path(CalendarResource.PATH.GET_CALENDAR).request().accept(ICAL).get();

        final ICalendar iCalendar = Biweekly.parse(cal.readEntity(String.class)).first();

        logger.info(cal.readEntity(String.class));

        assertThat(iCalendar.getEvents()).isNotEmpty();
    }


}
