package com.example.gatewayservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Map;

@Configuration
public class GatewayWelcomeConfig {

    @Bean
    public RouterFunction<?> welcomeRoutes() {
        return RouterFunctions.route()
                .GET("/actuator/info", request -> ServerResponse.ok().body(Mono.just(Map.of(
                        "app", "gateway-service",
                        "status", "UP",
                        "available_routes", Map.of(
                                "clients", "/clients",
                                "voitures", "/voitures"
                        )
                )), Map.class))
                .GET("/", request -> ServerResponse.ok().body(Mono.just(Map.of(
                        "service", "Spring Cloud Gateway",
                        "routes", Map.of(
                                "clients", "http://localhost:8888/clients",
                                "voitures", "http://localhost:8888/voitures"
                        )
                )), Map.class))
                .build();
    }

}

