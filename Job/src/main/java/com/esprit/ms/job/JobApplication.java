package com.esprit.ms.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class JobApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobApplication.class, args);
    }
    @Autowired
    private JobRepository jobRepository;
    @Bean
    ApplicationRunner init() {
        return (args) -> {

            if (jobRepository.count() == 0) {
                // save
                jobRepository.save(new Job("Service Financier", true));
                jobRepository.save(new Job("Service Info", false));
                jobRepository.save(new Job("Service RH", false));

                // fetch
                jobRepository.findAll().forEach(System.out::println);
            }else {
                System.out.println("Les données existent déjà !");
            }
        };
    }
}
