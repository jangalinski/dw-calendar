package de.holisticon.dw.calendar.ical;

import biweekly.Biweekly;
import biweekly.ICalendar;
import com.google.common.io.Resources;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;

public class ReadLastfmEventsTest {


    private final URL lastfm  = Resources.getResource("lastfm.ics");
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void reads_from_resource() throws IOException {
        ICalendar iCalendar = Biweekly.parse(lastfm.openStream()).first();

        logger.info(iCalendar.write());


    }
}
