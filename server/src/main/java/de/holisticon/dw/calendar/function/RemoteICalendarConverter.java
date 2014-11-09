package de.holisticon.dw.calendar.function;


import de.holisticon.dw.calendar.api.RemoteCalendar;

/**
 * Created by jangalinski on 03.10.14.
 */
public class RemoteICalendarConverter extends AbstractJsonConverter<RemoteCalendar> {

    public RemoteICalendarConverter() {
        super(RemoteCalendar.class);
    }

}
