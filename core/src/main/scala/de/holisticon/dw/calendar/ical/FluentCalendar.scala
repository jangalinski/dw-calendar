package de.holisticon.dw.calendar.ical

import biweekly.ICalendar
import biweekly.component.VEvent
import com.google.common.base.Supplier
import org.joda.time.DateTime


class FluentCalendar extends Supplier[ICalendar] {

    private val cal = new ICalendar
    private val properties = cal.getProperties
    private val components = cal.getComponents


    override def get(): ICalendar = cal

    def event(date:DateTime, summary:String) = {
        cal.addEvent(new VEvent())
        this
    }

    def event(event:VEvent) = {
        cal.addEvent(event)
        this
    }
}

object FluentCalendar {
    def calendar = new FluentCalendar

    def calendar(event: VEvent): FluentCalendar = calendar event(event)

    def calendar(date:DateTime, summary:String): FluentCalendar = calendar event(date,summary)
}
