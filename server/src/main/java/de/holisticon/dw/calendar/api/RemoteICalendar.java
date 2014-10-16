package de.holisticon.dw.calendar.api;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.net.URL;

import static com.google.common.base.Throwables.propagate;
import static de.holisticon.dw.calendar.function.DwCalendarFunctions.stringToUrl;
import static org.apache.commons.lang3.builder.ToStringBuilder.reflectionToString;
import static org.apache.commons.lang3.builder.ToStringStyle.SHORT_PREFIX_STYLE;

public class RemoteICalendar implements Serializable {

    @JsonProperty
    private final String name;

    @JsonProperty
    private final URL url;

    public RemoteICalendar(String name, String url) {
        this(name, stringToUrl(url));
    }



    @JsonCreator
    public RemoteICalendar(@JsonProperty("name") String name, @JsonProperty("url") URL url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public URL getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return reflectionToString(this, SHORT_PREFIX_STYLE);
    }
}
