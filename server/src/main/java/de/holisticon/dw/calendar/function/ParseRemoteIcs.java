package de.holisticon.dw.calendar.function;

import biweekly.ICalendar;
import com.google.common.base.Function;
import de.holisticon.dw.calendar.ical.Calendars;

import javax.annotation.Nullable;
import java.io.IOException;
import java.net.URL;

import static com.google.common.base.Throwables.propagate;

/**
 * Created by jangalinski on 03.10.14.
 */
public class ParseRemoteIcs implements Function<URL, ICalendar> {
    @Nullable
    @Override
    public ICalendar apply(URL url) {
        return Calendars.load(url);
    }
}
