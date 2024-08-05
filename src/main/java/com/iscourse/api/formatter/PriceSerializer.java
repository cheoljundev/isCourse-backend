package com.iscourse.api.formatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

public class PriceSerializer extends JsonSerializer<Integer> {
    @Override
    public void serialize(Integer value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value != null) {
            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.KOREA);
            String formattedPrice = currencyFormat.format(value);
            gen.writeString(formattedPrice);
        } else {
            gen.writeNull();
        }
    }
}
