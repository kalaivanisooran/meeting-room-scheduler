package cts.rabobank.glassdoorscheduler.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * It has Swagger configuration details. 
 * 
 * @author 400640
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	/**
	 * It creates {@link Docket} object and register into spring application context.
	 * @return
	 */
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("cts.rabobank.glassdoorscheduler"))
				.paths(PathSelectors.any()).build()
				.apiInfo(metaData());
	}

	
	/**
	 * It sets the Swagger Meta data information and returns {@link ApiInfo} 
	 * 
	 * @return
	 */
	private ApiInfo metaData() {
		return new ApiInfoBuilder()
				.title("Glass Door Swagger App")
				.description("\"Glass Door Swagger Server App\"")
				.version("1.0.0").license("Cognizant Glass Door Version 2.0")
				.licenseUrl("https://www.ctsrebo.com/licenses/LICENSE-2.0\"")
				.build();

	}

}
