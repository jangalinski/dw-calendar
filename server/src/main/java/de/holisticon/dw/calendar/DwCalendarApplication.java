package de.holisticon.dw.calendar;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import de.holisticon.dw.calendar.health.DummyHealthCheck;
import de.holisticon.dw.calendar.ical.ICalendarDao;
import de.holisticon.dw.calendar.ical.rest.CalendarResourceBean;
import de.holisticon.dw.calendar.view.SimpleView;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.db.ManagedDataSource;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.exceptions.UnableToExecuteStatementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class DwCalendarApplication extends Application<DwCalendarApplication.Configuration> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final DBIFactory factory = new DBIFactory();

    @Override
    public void initialize(final Bootstrap<Configuration> bootstrap) {
        bootstrap.addBundle(new ViewBundle());
        bootstrap.addBundle(new AssetsBundle("/swagger", "/swagger", "index.html", "swagger"));
    }

    @Override
    public void run(final Configuration configuration, final Environment environment) throws Exception {
        final DBI dbi = factory.build(environment, configuration.getDataSourceFactory(), "h2");
        initDB(dbi);


        environment.jersey().register(new CalendarResourceBean(dbi.onDemand(ICalendarDao.class)));
        environment.jersey().register(new SimpleView());

        environment.healthChecks().register("dummy", new DummyHealthCheck());
    }

    private void initDB(DBI dbi) throws URISyntaxException, IOException {
        try (Handle h = dbi.open()) {
            final URL resource = Resources.getResource("db/migration/V1__create_event_table.sql");
            String sql = Files.toString(new File(resource.toURI()), Charsets.UTF_8);
            try {
                h.execute(sql);
            } catch (UnableToExecuteStatementException e) {
                logger.warn(e.getMessage());
            }
            h.commit();
        }
    }

    public static void main(String... args) throws Exception {
        new DwCalendarApplication().run(args);
    }

    /**
     * The central configuration class.
     */
    public static class Configuration extends io.dropwizard.Configuration {

        @JsonProperty
        public String name;

        @Valid
        @NotNull
        @JsonProperty
        private final DataSourceFactory database = new DataSourceFactory();

        public DataSourceFactory getDataSourceFactory() {
            return database;
        }

        public String toString() {
            return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
        }


    }
}
