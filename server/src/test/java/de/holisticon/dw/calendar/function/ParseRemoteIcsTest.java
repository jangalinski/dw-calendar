package de.holisticon.dw.calendar.function;

import biweekly.ICalendar;
import biweekly.component.VEvent;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

public class ParseRemoteIcsTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ParseRemoteIcs function = new ParseRemoteIcs();

    @Test
    public void _() throws MalformedURLException {
        final ICalendar calendar = function.apply(new URL("http://ifeiertage.de/hh-.ics"));

        for (VEvent c : calendar.getEvents()) {
            logger.info("{}", c);
        }
    }
}
