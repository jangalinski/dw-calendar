package de.holisticon.dw.calendar.api

import java.net.URL

import com.fasterxml.jackson.annotation.JsonProperty

import scala.beans.BeanProperty


case class RemoteCalendar(@BeanProperty url: URL) {

    def this(@JsonProperty("url") url: String) = this(new URL(url.replace("webcal://", "http://")))

}
