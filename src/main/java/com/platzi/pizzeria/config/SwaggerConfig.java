package com.platzi.pizzeria.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Clase de configuración para Swagger.
 */
@Configuration
public class SwaggerConfig {

    /**
     * Bean para proporcionar una configuración personalizada de OpenAPI.
     *
     * @return OpenAPI: La configuración personalizada de OpenAPI.
     */
    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info().title("Pizzeria")
                        .description("Api para estudiar Spring data jpa")
                        .version("v0.0.1")
                        .termsOfService("")
                );
    }

}
