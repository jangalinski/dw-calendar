package de.holisticon.dw.calendar.ical.rest;

import biweekly.Biweekly;
import biweekly.ICalendar;
import biweekly.component.VEvent;
import de.holisticon.dw.calendar.api.CalendarResource;
import de.holisticon.dw.calendar.api.RemoteCalendar;
import de.holisticon.dw.calendar.ical.ICalendarDao;
import de.holisticon.dw.calendar.ical.test.H2MemoryRule;
import de.holisticon.dw.calendar.ical.test.LastfmSupplier;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.util.List;

import static io.dropwizard.testing.junit.ResourceTestRule.builder;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class CalendarResourceBeanTest {

    private final RemoteCalendar remoteCalendar = new RemoteCalendar(LastfmSupplier.CALENDAR_URL);

    @Rule
    public final H2MemoryRule<ICalendarDao> h2 = new H2MemoryRule<>(ICalendarDao.class);

    @Rule
    public final ResourceTestRule resources = builder().addResource(new CalendarResourceBean(h2.get())).build();

    private WebTarget target;
    private Response responsePost;

    @Before
    public void setUp() {
        target = resources.client().target(CalendarResource.PATH.ROOT);
    }

    @Test
    public void loads_calendar_from_url_and_saves_events_in_db() {
        responsePost = postRemoteCalendar();
        assertThat(responsePost.getStatus()).isEqualTo(204);

        final List<VEvent> events = h2.get().findAll();

        // all events from ics file have been created.
        assertThat(events).hasSize(3);
    }

    @Test
    public void creates_new_calendar_from_saved_events() {
        responsePost = postRemoteCalendar();
        final Response response = target.path(CalendarResource.PATH.GET_CALENDAR).request(CalendarResource.TYPE.ICAL).get();

        assertThat(response.getStatus()).isEqualTo(200);

        final ICalendar calendar = Biweekly.parse(response.readEntity(String.class)).first();

        assertThat(calendar.getEvents()).hasSize(3);
    }

    @Test
    public void updates_events_when_posting_the_same_calendar_twice() {
        postRemoteCalendar();
        postRemoteCalendar();

        // all events from ics file have been created.
        final List<VEvent> events = h2.get().findAll();
        assertThat(events).hasSize(3);
    }

    private Response postRemoteCalendar() {
        // @formatter:off
        return target.path(CalendarResource.PATH.ADD_EVENTS)
            .request(CalendarResource.TYPE.JSON)
            .accept(CalendarResource.TYPE.ICAL)
            .post(Entity.json(remoteCalendar));
        // @formatter:on
    }
}
