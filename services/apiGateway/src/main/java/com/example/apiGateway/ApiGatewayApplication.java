package com.example.apiGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("Product", r -> r.path("/produits/**")
						.uri("lb://PRODUCT-SERVICE"))
				.route("Credit", r -> r.path("/credits/**")
						.uri("lb://CREDIT-SERVICE"))
				.route("Contract", r -> r.path("/contracts/**")
						.uri("lb://CONTRACT-SERVICE"))
				.route("Sinistre", r -> r.path("/sinistres/**")
						.uri("lb://SINISTRE-SERVICE"))
				.route("Devis", r -> r.path("/api/devis/**")
						.uri("lb://DEVIS-SERVICE"))
				.route("User", r -> r.path("/api/v1/auth/**")
						.uri("lb://USER-SERVICE"))
				.build();
	}
}