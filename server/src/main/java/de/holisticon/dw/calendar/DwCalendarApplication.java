package de.holisticon.dw.calendar;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.holisticon.dw.calendar.health.DummyHealthCheck;
import de.holisticon.dw.calendar.ical.ICalendarDao;
import de.holisticon.dw.calendar.ical.rest.CalendarResourceBean;
import de.holisticon.dw.calendar.view.SimpleView;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DwCalendarApplication extends Application<DwCalendarApplication.Configuration> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ICalendarDao dao = null;

    @Override
    public void initialize(final Bootstrap<Configuration> bootstrap) {
        bootstrap.addBundle(new ViewBundle());
        bootstrap.addBundle(new AssetsBundle("/swagger", "/swagger", "index.html", "swagger"));
    }

    @Override
    public void run(final Configuration configuration, final Environment environment) throws Exception {
        environment.jersey().register(new CalendarResourceBean(null));
        environment.jersey().register(new SimpleView());

        environment.healthChecks().register("dummy", new DummyHealthCheck());
    }

    public static void main(String... args) throws Exception {
        new DwCalendarApplication().run(args);
    }

    public static class Configuration extends io.dropwizard.Configuration {

        @JsonProperty
        public String name;

        public String toString() {
            return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
        }


    }
}
