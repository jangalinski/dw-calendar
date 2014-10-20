package de.holisticon.dw.calendar.ical;

import biweekly.component.VEvent;
import org.skife.jdbi.v2.SQLStatement;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.Binder;
import org.skife.jdbi.v2.sqlobject.BinderFactory;
import org.skife.jdbi.v2.sqlobject.BindingAnnotation;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.io.Closeable;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Dao taking care of persistence.
 */
public interface ICalendarDao extends Closeable {

    final String UID = "uid";
    final String SUMMARY = "summary";
    final String START = "start";

    /**
     * Stores a VEvent in the db.
     * @param event the event to save
     */
    @SqlUpdate("insert into EVENT (uid,summary,start) values(:uid,:summary,:start)")
    void insert(@BindVEvent VEvent event);


    /**
     * Reads a VEvent from db.
     * @param uid the uid of the event
     * @return the found event.
     */
    @SqlQuery("select * from EVENT where uid = :uid")
    @Mapper(VEventMapper.class)
    VEvent findNameById(@Bind(UID) String uid);


    /**
     * Mapping to read events from result.
     */
    class VEventMapper implements ResultSetMapper<VEvent> {

        @Override
        public VEvent map(int index, ResultSet r, StatementContext ctx) throws SQLException {
            final VEvent event = new VEvent();
            event.setSummary(r.getString(SUMMARY));
            event.setUid(r.getString(UID));
            event.setDateStart(r.getTimestamp(START));

            return event;
        }
    }

    /**
     * Bind insert event.
     */
    @BindingAnnotation(BindVEvent.VEventBinderFactory.class)
    @Retention(RUNTIME)
    @Target({PARAMETER})
    @interface BindVEvent {

        /**
         * Bind event to insert statement.
         */
        public static class VEventBinderFactory implements BinderFactory {

            @Override
            public Binder build(Annotation annotation) {
                return new Binder<BindVEvent, VEvent>() {
                    @Override
                    public void bind(SQLStatement<?> q, BindVEvent bind, VEvent event) {
                        q.bind(UID, event.getUid().getValue());
                        q.bind(SUMMARY, event.getSummary().getValue());
                        q.bind(START, event.getDateStart().getValue());
                    }
                };
            }
        }
    }


}
