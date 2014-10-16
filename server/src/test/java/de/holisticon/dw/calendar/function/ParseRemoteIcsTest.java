package de.holisticon.dw.calendar.function;

import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.component.CalendarComponent;
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
        final Calendar calendar = function.apply(new URL("http://ifeiertage.de/hh-.ics"));

        for (CalendarComponent c : calendar.getComponents()) {
            logger.info("{}", c);
        }
    }
}
