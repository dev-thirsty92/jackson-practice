package com.practice.custom;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class CustomItemSerializer extends StdSerializer<Item> {


    public CustomItemSerializer(){
        this(null);
    }

    protected CustomItemSerializer(Class<Item> t) {
        super(t);
    }

    @Override
    public void serialize(Item item, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("id", item.id);
            jsonGenerator.writeStringField("itemName", item.name);
            jsonGenerator.writeNumberField("ownerId", item.owner.id);
            jsonGenerator.writeEndObject();
    }
}
