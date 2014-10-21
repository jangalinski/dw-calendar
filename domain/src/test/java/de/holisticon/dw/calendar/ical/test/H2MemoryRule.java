package de.holisticon.dw.calendar.ical.test;

import com.google.common.base.Supplier;
import org.flywaydb.core.Flyway;
import org.junit.rules.ExternalResource;
import org.skife.jdbi.v2.DBI;

public class H2MemoryRule<T> extends ExternalResource implements Supplier<T> {

    public static final String H2_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    public static final String H2_USER = "sa";
    public static final String H2_PW = "null";

    private final Flyway flyway = new Flyway();

    private final DBI dbi = new DBI(H2_URL,H2_USER,H2_PW);
    private final Class<T> daoType;

    public H2MemoryRule(Class<T> daoType) {
        this.daoType = daoType;
    }

    @Override
    protected void before() {
        flyway.setDataSource(H2_URL, H2_USER, H2_PW);

        flyway.migrate();
    }

    @Override
    public T get() {
        return dbi.onDemand(daoType);
    }

}
