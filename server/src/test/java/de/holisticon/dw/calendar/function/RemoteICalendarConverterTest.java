package de.holisticon.dw.calendar.function;

import com.google.common.base.Throwables;
import de.holisticon.dw.calendar.api.RemoteCalendar;
import io.dropwizard.testing.FixtureHelpers;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;


public class RemoteICalendarConverterTest {

    private final RemoteICalendarConverter converter = new RemoteICalendarConverter();


    @Test
    public void converts_to_json() throws IOException {
        final RemoteCalendar calendar = new RemoteCalendar("webcal://ws.audioscrobbler.com/1.0/user/janhoo/events.ics");
        assertThat(converter.convert(calendar)).isEqualTo(fixture());
    }

    private String fixture() {
        try {
            return FixtureHelpers.fixture("fixtures/remoteICalendar.json");
        } catch (IOException e) {
            throw Throwables.propagate(e);
        }
    }
}
