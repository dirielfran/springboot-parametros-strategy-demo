package com.example.parametrosdemo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI parametrosDemoOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("API ABM Parametros - Demo Strategy")
                        .description("Ejemplo de ABM con tablas separadas y resolucion por Strategy")
                        .version("v1.0.0")
                        .contact(new Contact().name("Equipo Demo"))
                        .license(new License().name("Uso educativo")));
    }
}
