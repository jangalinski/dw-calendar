package de.holisticon.dw.calendar.ical

import java.net.URL

import net.fortuna.ical4j.data.CalendarBuilder
import net.fortuna.ical4j.model.Calendar
import net.fortuna.ical4j.model.component.VEvent
import net.fortuna.ical4j.model.property.{Version, ProdId, CalScale}
import org.joda.time.DateTime

object Calendars {

    def toUrl(url: String): URL = new URL(url.replace("webcal://", "http://"))

    def load(url: URL): Calendar = new CalendarBuilder().build(url.openStream())

    def calendar(events:VEvent*) = {
        val calendar = new Calendar();
        calendar.getProperties().add(new ProdId("-//dw-calendar//iCal4j 1.0//EN"));
        calendar.getProperties().add(Version.VERSION_2_0);
        calendar.getProperties().add(CalScale.GREGORIAN);

        events.foreach(calendar.getComponents().add(_))
    }

    def vEvent(date:DateTime, title:String) = {
        new VEvent(new net.fortuna.ical4j.model.Date(date.toDate), title)
    }
}
