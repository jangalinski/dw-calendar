package de.holisticon.dw.calendar.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Converter;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class RemoteICalendarResourceTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ObjectMapper mapper = Jackson.newObjectMapper();

    @Test
    public void _() throws IOException {
        final RemoteICalendar iCalendar = new RemoteICalendar("janhoo@lastfm", "webcal://ws.audioscrobbler.com/1.0/user/janhoo/events.ics");

        final String json = mapper.writeValueAsString(iCalendar);
        mapper.readValue(json, RemoteICalendar.class);
    }
}
