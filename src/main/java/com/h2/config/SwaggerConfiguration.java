package com.h2.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Value("${info.title}")
    private String title;

    @Value("${info.description}")
    private String description;

    @Value("${info.version}")
    private String version;

    @Bean
    public OpenAPI swaggerConfig() {

        Info info = new Info()
                .title(title)
                .description(description)
                .version(version);

        return new OpenAPI().info(info);

    }
}
