package cts.rabobank.glassdoorscheduler.exception;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;


public class CustomOauthExceptionSerializer extends StdSerializer<CustomOauthException> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomOauthExceptionSerializer() {
		super(CustomOauthException.class);
	}

	@Override
	public void serialize(CustomOauthException value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
		jsonGenerator.writeStartObject();
		jsonGenerator.writeNumberField("code", value.getHttpErrorCode());
		jsonGenerator.writeBooleanField("status", true);
		jsonGenerator.writeObjectField("message", value.getMessage());
		jsonGenerator.writeObjectField("errors", Arrays.asList(value.getOAuth2ErrorCode(), value.getMessage()));
		if (value.getAdditionalInformation() != null) {
			for (Map.Entry<String, String> entry : value.getAdditionalInformation().entrySet()) {
				String key = entry.getKey();
				String add = entry.getValue();
				jsonGenerator.writeStringField(key, add);
			}
		}
		jsonGenerator.writeEndObject();
	}
}