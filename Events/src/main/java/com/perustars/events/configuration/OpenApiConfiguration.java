package com.perustars.events.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
    @Bean(name = "peruStarsOpenApi")
    public OpenAPI peruStarsOpenApi(){
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("PeruStars Application API")
                        .description("PeruStars API implemented with Spring Boot RESTful service and documented using spingdoc_openapi and OpenAPI 3.0"));
    }
}
