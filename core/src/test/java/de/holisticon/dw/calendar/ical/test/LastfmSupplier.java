package de.holisticon.dw.calendar.ical.test;

import biweekly.ICalendar;
import com.google.common.base.Supplier;
import com.google.common.io.Resources;
import de.holisticon.dw.calendar.ical.Calendars;

import java.net.URL;

public class LastfmSupplier implements Supplier<ICalendar> {

    public static URL CALENDAR_URL = Resources.getResource("lastfm.ics");

    private final ICalendar lastfm = Calendars.load(CALENDAR_URL);

    @Override
    public ICalendar get() {
        return lastfm;
    }
}
