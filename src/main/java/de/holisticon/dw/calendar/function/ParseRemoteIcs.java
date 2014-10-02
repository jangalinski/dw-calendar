package de.holisticon.dw.calendar.function;

import com.google.common.base.Function;
import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;

import javax.annotation.Nullable;
import java.io.IOException;
import java.net.URL;

import static com.google.common.base.Throwables.propagate;

/**
 * Created by jangalinski on 03.10.14.
 */
public class ParseRemoteIcs implements Function<URL, Calendar> {
    @Nullable
    @Override
    public Calendar apply(URL url) {
        try {
            return new CalendarBuilder().build(url.openStream());
        } catch (IOException | ParserException e) {
            throw propagate(e);
        }
    }
}
