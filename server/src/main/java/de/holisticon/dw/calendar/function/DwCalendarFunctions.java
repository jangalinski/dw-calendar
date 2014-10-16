package de.holisticon.dw.calendar.function;


import java.net.URL;

public class DwCalendarFunctions {

    private final static DwCalendarFunctions INSTANCE = new DwCalendarFunctions();

    private final StringToUrl stringToUrl = new StringToUrl();


    public static URL stringToUrl(String url) {
        return INSTANCE.stringToUrl.apply(url);
    }
}
