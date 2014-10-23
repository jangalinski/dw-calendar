package de.holisticon.dw.calendar.ical.rest;

import biweekly.ICalendar;
import biweekly.component.VEvent;
import de.holisticon.dw.calendar.api.CalendarResource;
import de.holisticon.dw.calendar.api.RemoteCalendar;
import de.holisticon.dw.calendar.ical.Calendars;
import de.holisticon.dw.calendar.ical.ICalendarDao;

import java.io.Serializable;
import java.util.List;


public class CalendarResourceBean implements Serializable, CalendarResource {
    private static final long serialVersionUID = 1L;

    private final ICalendarDao dao;

    public CalendarResourceBean(final ICalendarDao dao) {
        this.dao = dao;
    }

    @Override
    public String getCalendar() {
        ICalendar calendar = new ICalendar();
        for (VEvent event : dao.findAll()) {
            calendar.addEvent(event);
        }

        return calendar.write();
    }

    @Override
    public void addEvents(final RemoteCalendar remoteCalendar) {
        final List<VEvent> events = Calendars.load(remoteCalendar.getUrl()).getEvents();

        for (VEvent event : events) {
            dao.insert(event);
        }
    }
}
