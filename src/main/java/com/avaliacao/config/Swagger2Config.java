package com.avaliacao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {
	
	
	@Bean
    public Docket restApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.avaliacao.controller.rest"))
                .build()
                .apiInfo(getApiInfo());
    }
	 private ApiInfo getApiInfo() {
	        return new ApiInfoBuilder()
	                .title("API - AVALIACAO CODE GROUP")
	                .description("")
	                .version("1.0.0")
	                .build();
	    }

}
