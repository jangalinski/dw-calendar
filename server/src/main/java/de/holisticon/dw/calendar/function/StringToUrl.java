package de.holisticon.dw.calendar.function;

import com.google.common.base.Function;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Nullable;
import java.net.MalformedURLException;
import java.net.URL;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Throwables.propagate;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * Created by jangalinski on 03.10.14.
 */
public class StringToUrl implements Function<String, URL> {
    @Nullable
    @Override
    public URL apply(final String url) {
        checkArgument(isNotBlank(url), "url must not be null or blank");
        try {
            return new URL(url.replace("webcal://", "http://"));
        } catch (MalformedURLException e) {
            throw propagate(e);
        }
    }
}

