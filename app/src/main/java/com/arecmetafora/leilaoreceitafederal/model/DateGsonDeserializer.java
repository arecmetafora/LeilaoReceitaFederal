package com.arecmetafora.leilaoreceitafederal.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Converts an api date to a {@link Date} instance.
 */
public class DateGsonDeserializer implements JsonDeserializer<Date> {

    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            return SimpleDateFormat.getDateTimeInstance().parse(json.getAsString());
        } catch (ParseException ignored) {
            // TODO: handle wrong formatted dates
            return null;
        }
    }
}
