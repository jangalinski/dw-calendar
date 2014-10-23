package de.holisticon.dw.calendar.ical

import java.net.URL

import biweekly.component.VEvent
import biweekly.{Biweekly, ICalendar}

object Calendars {

    def url(url: String) = new URL(url.replace("webcal://", "http://"))

    def load(url: URL): ICalendar = Biweekly.parse(url.openStream()).first()

    def calendar(events:VEvent*) = {
        val calendar = new ICalendar();

        events.foreach(calendar.addEvent(_))
    }
}
