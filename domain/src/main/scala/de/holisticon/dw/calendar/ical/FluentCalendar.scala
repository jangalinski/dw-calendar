package de.holisticon.dw.calendar.ical

import com.google.common.base.Supplier
import net.fortuna.ical4j.model.Calendar
import net.fortuna.ical4j.model.component.VEvent
import net.fortuna.ical4j.model.property.{CalScale, ProdId, Version}
import org.joda.time.DateTime


class FluentCalendar extends Supplier[Calendar] {

    private val cal = new Calendar
    private val properties = cal.getProperties
    private val components = cal.getComponents

    properties.add(new ProdId("-//dw-calendar//iCal4j 1.0//EN"))
    properties.add(Version.VERSION_2_0)
    properties.add(CalScale.GREGORIAN)

    override def get(): Calendar = cal

    def event(date:DateTime, summary:String) = {
        components.add(new VEvent(new net.fortuna.ical4j.model.Date(date.toDate), summary))
        this
    }

    def event(event:VEvent) = {
        components.add(event)
        this
    }
}

object FluentCalendar {
    def calendar = new FluentCalendar

    def calendar(event: VEvent): FluentCalendar = calendar event(event)

    def calendar(date:DateTime, summary:String): FluentCalendar = calendar event(date,summary)
}
