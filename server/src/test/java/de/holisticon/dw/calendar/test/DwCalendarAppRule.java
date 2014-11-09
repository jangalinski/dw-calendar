package de.holisticon.dw.calendar.test;

import de.holisticon.dw.calendar.DwCalendarApplication;
import io.dropwizard.Application;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.ConfigOverride;
import io.dropwizard.testing.junit.DropwizardAppRule;

public class DwCalendarAppRule extends DropwizardAppRule<DwCalendarApplication.Configuration> {
    public DwCalendarAppRule() {
        super(DwCalendarApplication.class, ResourceHelpers.resourceFilePath("configuration.yml"));
    }



    public String getEndpoint() {
        return String.format("http://localhost:%s/", getLocalPort());
    }


}
