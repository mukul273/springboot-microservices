package com.spring.boot.springbootmaster;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		Set<String> produces = new HashSet<String>(Arrays.asList("application/json", "application/xml"));
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(new ApiInfo("Chimps Swagger Demo", "Chimp says Learn", "1.0",
						"This is the URL, What else it can be..", "Chimp Nation", "Free world", "again..really?"))
				.produces(produces)
				.consumes(produces);

	}
}
