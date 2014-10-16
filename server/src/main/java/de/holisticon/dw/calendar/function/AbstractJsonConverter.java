package de.holisticon.dw.calendar.function;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Converter;
import io.dropwizard.jackson.Jackson;

import java.io.IOException;
import java.io.Serializable;

/**
 * Created by jangalinski on 03.10.14.
 */
public class AbstractJsonConverter<T extends Serializable> extends Converter<T, String> {

    private final ObjectMapper mapper = Jackson.newObjectMapper();
    private final Class<T> type;

    protected AbstractJsonConverter(Class<T> type) {
        this.type = type;
    }


    @Override
    protected String doForward(final T value) {
        try {
            return mapper.writeValueAsString(value);
        } catch (NullPointerException | JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    protected T doBackward(String json) {
        try {
            return mapper.readValue(json, type);
        } catch (NullPointerException | IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
