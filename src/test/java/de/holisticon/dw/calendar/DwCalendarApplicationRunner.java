package de.holisticon.dw.calendar;

import com.google.common.base.Function;
import com.google.common.io.Resources;

import javax.annotation.Nullable;
import java.net.URISyntaxException;

import static com.google.common.base.Throwables.propagate;

public class DwCalendarApplicationRunner {

    private static final Function<String, String> PATH_TO_RESOURCE = new Function<String, String>() {

        @Nullable
        @Override
        public String apply(final String resourceName) {
            try {
                return Resources.getResource(resourceName).toURI().getPath();
            } catch (URISyntaxException e) {
                throw propagate(e);
            }
        }
    };

    public static void main(String... args) throws Exception {
        DwCalendarApplication.main(new String[]{"server", PATH_TO_RESOURCE.apply("configuration.yml")});
    }
}