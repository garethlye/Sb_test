package com.my.sb_test.api.Gson;

/**
 * Created by G-Man garethlye on 2019-05-11
 */

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.math.BigDecimal;

public class BigDecimalDeserializer implements JsonDeserializer<BigDecimal> {

    @Override
    public BigDecimal deserialize(final JsonElement jsonElement, final Type typeOf,
                                  final JsonDeserializationContext context) throws JsonParseException {
        try {
            if (jsonElement != null) {
                return new BigDecimal(jsonElement.getAsString());
            }
        } catch (final NumberFormatException e) {
            // ignore this
        }

        throw new JsonParseException("Unparseable BigDecimal: " + (jsonElement == null ? null :
                jsonElement.getAsString()));
    }
}
