package de.holisticon.dw.calendar.ical;

import biweekly.component.VEvent;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import de.holisticon.dw.calendar.ical.test.H2MemoryRule;
import org.junit.Rule;
import org.junit.Test;

import javax.annotation.Nullable;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class ICalendarDaoTest {

    @Rule
    public final H2MemoryRule<ICalendarDao> h2 = new H2MemoryRule(ICalendarDao.class);

    private final ICalendarDao dao = h2.get();

    @Test
    public void creates_event_entry() {
        final VEvent event = new VEvent();
        event.setSummary("summary");
        event.setDateStart(new Date());

        dao.insert(event);

        final VEvent read = dao.findNameById(event.getUid().getValue());

        assertThat(read.getSummary().getValue()).isEqualTo(event.getSummary().getValue());
    }

    @Test
    public void finds_all_events() {
        dao.insert(vEvent("a", new Date()));
        dao.insert(vEvent("b", new Date()));

        final List<VEvent> events = dao.findAll();

        assertThat(events).isNotEmpty().hasSize(2);

        assertThat(FluentIterable.from(events).transform(new Function<VEvent, String>() {
            @Nullable
            @Override
            public String apply(VEvent input) {
                return input.getSummary().getValue();
            }
        })).contains("a","b");
    }

    private VEvent vEvent(String summary, Date start) {
        final VEvent event = new VEvent();
        event.setSummary(summary);
        event.setDateStart(start);
        return event;
    }
}
