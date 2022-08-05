package airbus.sunspotanalyser.api;

import java.io.IOException;

import org.modelmapper.ModelMapper;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;

@Configuration
public class MappingConfig {

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setAmbiguityIgnored(true);

		return modelMapper;
	}

	@JsonComponent
	public class CustomStringDeserializer extends StringDeserializer {

		private static final long serialVersionUID = -9034169224643266102L;

		@Override
		public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
			String value = super.deserialize(p, ctxt);
			return value != null ? value.trim() : null;
		}
	}

}
