package com.terasoft.servicesbc;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition
@SpringBootApplication
public class ServicesBcApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServicesBcApplication.class, args);
    }

}
