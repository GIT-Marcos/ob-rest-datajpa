package com.example.ob_rest_datajpa.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

/**
 * DOC WEB: http://localhost:8081/doc/swagger-ui/index.html
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().title("DOC DE LIBRERÍA")
                        .description("DOCS de proyecto librería")
                        .version("0.0.1")
                        .contact(new Contact().name("BAAL").email("fake.mail@gmail.com"))
                        .license(new License().name("IPET").url("https://springdoc.org/#swagger-ui-properties"))
                        .termsOfService("https://springdoc.org/#swagger-ui-properties"));
    }
/*
    private ApiInfo apiDetails(){
        return new ApiInfo("Spring Boot Books API REST",
                "Api rest docs for books proyect",
                "1", "https://www.cia.gov/",
                new Contact("BAAL", "https://swagger.io/docs/",
                        "fake.mail@hotmail.com"),
                "IPET", "https://www.ipet379molet.edu.ar/", Collections.emptyList());
    }*/
}
