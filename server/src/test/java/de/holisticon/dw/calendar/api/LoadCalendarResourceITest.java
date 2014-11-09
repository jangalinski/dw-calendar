package de.holisticon.dw.calendar.api;

import de.holisticon.dw.calendar.test.DwCalendarAppRule;
import org.glassfish.jersey.client.ClientResponse;
import org.junit.ClassRule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;


public class LoadCalendarResourceITest {

    @ClassRule
    public static final DwCalendarAppRule DW = new DwCalendarAppRule();

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void call_load_calendar() throws InterruptedException {
        final Client client = ClientBuilder.newBuilder().build();
        logger.error(client.target(DW.getEndpoint() + "foo/jan").request().get().readEntity(String.class));
    }

}
