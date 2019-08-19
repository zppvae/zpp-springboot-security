package org.zpp.security.core.component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import lombok.SneakyThrows;
import org.zpp.security.core.exception.MyOAuth2Exception;
import org.zpp.security.core.properties.SecurityConstants;

/**
 *
 * @author zpp
 */
public class MyOAuth2ExceptionSerializer extends StdSerializer<MyOAuth2Exception> {
	public MyOAuth2ExceptionSerializer() {
		super(MyOAuth2Exception.class);
	}

	@Override
	@SneakyThrows
	public void serialize(MyOAuth2Exception value, JsonGenerator gen, SerializerProvider provider) {
		gen.writeStartObject();
		gen.writeObjectField("code", SecurityConstants.FAIL);
		gen.writeStringField("msg", value.getMessage());
		gen.writeEndObject();
	}
}
