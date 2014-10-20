package de.holisticon.dw.calendar.ical;

import biweekly.component.VEvent;
import de.holisticon.dw.calendar.ical.test.H2MemoryRule;
import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

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
}
