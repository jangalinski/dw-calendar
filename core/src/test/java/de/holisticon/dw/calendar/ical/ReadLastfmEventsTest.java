package de.holisticon.dw.calendar.ical;

import biweekly.ICalendar;
import biweekly.ValidationWarnings;
import biweekly.component.VEvent;
import biweekly.io.json.JCalRawWriter;
import biweekly.io.json.JCalWriter;
import com.google.common.base.Supplier;
import de.holisticon.dw.calendar.ical.test.LastfmSupplier;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static org.assertj.core.api.Assertions.*;

public class ReadLastfmEventsTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final Supplier<ICalendar> lastfm = new LastfmSupplier();

    private final ICalendar iCalendar = lastfm.get();

    @Test
    public void reads_from_resource() throws IOException {
        assertThat(iCalendar).isNotNull();
        assertThat(iCalendar.getEvents()).isNotEmpty();
        assertThat(iCalendar.validate().getWarnings()).isEmpty();
    }

    @Test
    public void lists_events() throws IOException {
        VEvent event = iCalendar.getEvents().get(0);

        logger.info(ToStringBuilder.reflectionToString(event));


    }
}
