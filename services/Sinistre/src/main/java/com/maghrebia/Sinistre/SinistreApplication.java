package com.maghrebia.Sinistre;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SinistreApplication {

	public static void main(String[] args) {
		SpringApplication.run(SinistreApplication.class, args);
	}

}
