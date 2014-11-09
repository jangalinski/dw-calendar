package de.holisticon.dw.calendar.ical

import com.fasterxml.jackson.databind.ObjectMapper
import de.holisticon.dw.calendar.api.RemoteCalendar
import io.dropwizard.testing.FixtureHelpers
import org.scalatest.{FunSpec, GivenWhenThen}

class RemoteCalendarSpec extends FunSpec with GivenWhenThen {

    val mapper = new ObjectMapper
    val url = "webcal://mycalendar.com/abc.ics"
    val urlReplaced = url.replace("webcal://", "http://")
    val fixture = FixtureHelpers.fixture("fixtures/remoteCalendar.json")

    describe("A remote calendar") {

        it("replaces the webcal prefix with http") {

            Given("a calendar with url " + url)
            val cal = new RemoteCalendar(url)

            Then("the url is created with http instead")
            assert(cal.url.toExternalForm === urlReplaced)

        }

        it("serializes to json") {
            Given("a remote calendar " + url)
            val cal = new RemoteCalendar(url)

            When("converted to json")
            val json = mapper.writeValueAsString(cal)

            Then("the result equals fixture 'remoteCalendar.json'")
            assert(json === fixture)
        }

        it("deserializes from json") {
            Given("a json string 'remoteCalendar.json'")
            When("converted to instance")
            val cal = mapper.readValue(fixture, classOf[RemoteCalendar])

            Then("has the correct url")
            assert(cal.url.toExternalForm === urlReplaced)
        }

    }
}
