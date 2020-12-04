package com.pizzeria.training.util.serializers;

import java.io.IOException;

import org.bson.types.ObjectId;
import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * Seralizes {@link org.bson.types.ObjectId} to show timestamp field and derived hex string.
 * @author stephen.gruver
 * 
 */
@JsonComponent
public class ObjectIdSerializer extends JsonSerializer<ObjectId> {
		@Override
		public void serialize(ObjectId value, JsonGenerator jgen,
		        SerializerProvider provider) throws IOException,
		        JsonProcessingException {
		    jgen.writeStartObject();
		    jgen.writeNumberField("timestamp", value.getTimestamp());
		    jgen.writeStringField("hexString", value.toHexString());
		    jgen.writeEndObject();
		}

}
