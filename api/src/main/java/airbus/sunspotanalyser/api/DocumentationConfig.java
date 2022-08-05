package airbus.sunspotanalyser.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class DocumentationConfig {

	@Bean
	public OpenAPI openApi() {
		OpenAPI openAPI = new OpenAPI()
			.info(new Info()
				.title("Sunspot Analyser Test API")
				.version("v1"));

		return openAPI;
	}

}
