package de.holisticon.dw.calendar.ical.test;

import biweekly.ICalendar;
import com.google.common.base.Supplier;
import com.google.common.io.Resources;
import de.holisticon.dw.calendar.ical.Calendars;

public class LastfmSupplier implements Supplier<ICalendar> {

    private final ICalendar lastfm = Calendars.load(Resources.getResource("lastfm.ics"));

    @Override
    public ICalendar get() {
        return lastfm;
    }
}
