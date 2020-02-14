package com.example.demo.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@Configuration
public class SwaggerConfig {


    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.techprimers.springboot.swaggerexample"))
                .paths(regex("/rest.*"))
                .build()
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo() {


        ApiInfo apiInfo = new ApiInfo(
                "Photo app RESTful Web Service documentation",
                "This pages documents Photo app RESTful Web Service endpoints", "1.0",
                "http://www.appsdeveloperblof.com/service.html", new Contact("Gong CHEN", "www.example.com", "GONG.CHEN.2011@UTT.FR") ,
                "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", Collections.emptyList());

        return apiInfo;
    }
}