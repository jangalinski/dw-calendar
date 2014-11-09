package de.holisticon.dw.calendar;

import com.google.common.base.Function;
import com.google.common.io.Resources;

import javax.annotation.Nullable;
import java.net.URISyntaxException;

import static com.google.common.base.Throwables.propagate;
import static io.dropwizard.testing.ResourceHelpers.resourceFilePath;

public class DwCalendarApplicationRunner {

    public static void main(String... args) throws Exception {
        DwCalendarApplication.main(new String[]{"server", resourceFilePath("configuration.yml")});
    }
}
