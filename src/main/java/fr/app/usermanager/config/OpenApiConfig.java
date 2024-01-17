package fr.app.usermanager.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
/**
 * OpenApiConfig
 *
 * @author Amine HADIDI
 */
@Configuration
public class OpenApiConfig {

    @Bean
    GroupedOpenApi api() {
		return GroupedOpenApi.builder().group("user-manager").pathsToMatch("/**", "/**").build();
	}

    @Bean
    OpenAPI springOpenAPI() {
		return new OpenAPI().info(new Info().title("USER-MANAGER Swagger")
				.description("Swagger configuration for user-manager microservice").version("v1.0.0"));
	}

}
