package com.pingme.infrastructure.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "PingMe API", 
        version = "1.0.0", 
        description = "PingMe SNS API 문서"
    )
)
public class SwaggerConfig {
}

