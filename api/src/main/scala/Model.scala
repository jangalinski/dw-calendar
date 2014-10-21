package de.holisticon.dw.calendar.api

import java.net.URL

import com.fasterxml.jackson.annotation.{JsonCreator, JsonProperty}

import scala.beans.BeanProperty


case class RemoteCalendar(@BeanProperty url: URL) {

    @JsonCreator
    def this(@JsonProperty("url") url: String) = this(new URL(url.replace("webcal://", "http://")))

}
