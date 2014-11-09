package de.holisticon.dw.calendar.ical;

import biweekly.component.VEvent;
import biweekly.property.Created;
import biweekly.property.Uid;
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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.Closeable;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Dao taking care of persistence.
 */
public abstract class ICalendarDao implements Closeable {

    public static final String UID = "uid";
    public static final String SUMMARY = "summary";
    public static final String START = "start";
    public static final String END = "end";
    public static final String CREATED = "created";

    /**
     * Stores a VEvent in the db.
     *
     * @param event the event to save
     */
    @SqlUpdate("insert into EVENT (uid,summary,start,end,created) values(:uid,:summary,:start,:end, :created)")
    public abstract void insert(@BindVEvent VEvent event);

    @SqlUpdate("update EVENT set summary=:summary,start=:start,end=:end,created=:created where uid = :uid")
    public abstract void update(@BindVEvent VEvent event);


    /**
     * Reads a VEvent from db.
     *
     * @param uid the uid of the event
     * @return the found event (or null).
     */
    @Nullable
    @SqlQuery("select * from EVENT where uid = :uid")
    @Mapper(VEventMapper.class)
    public abstract VEvent find(@Bind(UID) String uid);

    public VEvent find(final Uid uid) {
        return find(uid.getValue());
    }

    public boolean exists(final VEvent event) {
        return find(event.getUid()) != null;
    }

    /**
     * @return all found events (or empty list)
     */
    @Nonnull
    @SqlQuery("select * from EVENT")
    @Mapper(VEventMapper.class)
    public abstract List<VEvent> findAll();

    public VEvent createOrUpdate(final VEvent event) {
        if (!exists(event)) {
            insert(event);
        } else {
            update(event);
        }
        return find(event.getUid());
    }

    /**
     * Mapping to read events from result.
     */
    public static class VEventMapper implements ResultSetMapper<VEvent> {

        @Override
        public VEvent map(int index, ResultSet r, StatementContext ctx) throws SQLException {
            final VEvent event = new VEvent();
            event.setUid(r.getString(UID));
            event.setSummary(r.getString(SUMMARY));
            event.setDateStart(r.getTimestamp(START));
            event.setDateEnd(r.getTimestamp(END));
            event.setCreated(new Date());

            return event;
        }
    }

    /**
     * Bind insert event.
     */
    @BindingAnnotation(BindVEvent.VEventBinderFactory.class)
    @Retention(RUNTIME)
    @Target({PARAMETER})
    public static @interface BindVEvent {

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
                        q.bind(END, event.getDateEnd() != null ? event.getDateEnd().getValue() : null);

                        final Created created = event.getCreated();
                        final Date createValue = created != null ? created.getValue() : null;

                        q.bind(CREATED, createValue != null ? createValue : new Date());
                    }
                };
            }
        }
    }


}
