package de.holisticon.dw.calendar.ical;

import biweekly.ICalendar;
import com.google.common.collect.Lists;
import com.google.common.io.Resources;
import org.joda.time.DateTime;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

public class CalendarsTest {

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    private static final URL EXAMPLE_ICAL = Resources.getResource("hh-feiertage.ics");
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void creates_url_from_webcal() throws Exception {
        assertThat(Calendars.url("webcal://domain.tld/abc.ics").toString()).isEqualTo("http://domain.tld/abc.ics");
    }

    @Test
    public void fails_to_create_url_for_null() {
        thrown.expect(NullPointerException.class);
        Calendars.url(null);
    }

    @Test
    public void loads_calendar_from_resource() throws Exception {
        ICalendar c = loadExampleIcal();

        assertThat(c).isNotNull();
        assertThat(c.getComponents()).isNotEmpty();
    }

    @Test
    public void creates_new_calendar() {

        final DateTime date = new DateTime(2014, 12, 15, 0, 0, 0, 0);
        final String summary = "Weihnachten";



        ICalendar c = FluentCalendar.calendar(date,summary).get();

        logger.info(c.toString());
    }

    public static ICalendar loadExampleIcal() {
        return Calendars.load(EXAMPLE_ICAL);
    }
}
