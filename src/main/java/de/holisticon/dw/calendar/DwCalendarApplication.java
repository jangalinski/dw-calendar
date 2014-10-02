package de.holisticon.dw.calendar;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.holisticon.dw.calendar.resource.SimpleCalendarResource;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DwCalendarApplication extends Application<DwCalendarApplication.Configuration> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void run(final Configuration configuration, final Environment environment) throws Exception {
        logger.info(configuration.name);
        environment.jersey().register(SimpleCalendarResource.class);
    }

    public static void main(String... args) throws Exception {
        new DwCalendarApplication().run(args);
    }

    public static class Configuration extends io.dropwizard.Configuration {

        @JsonProperty
        public String name;
    }
}
