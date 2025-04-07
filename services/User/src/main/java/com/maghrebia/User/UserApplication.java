package com.maghrebia.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication(scanBasePackages = "com.maghrebia.User")

@EnableDiscoveryClient
public class UserApplication implements CommandLineRunner {
	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);


	}
	public void run(String...args){
		User adminaccount = userRepository.findByRole(ERole.ADMIN);
		if(null==adminaccount){

			User user=new User();
			user.setCin(123456);
			user.setNom("maher");
			user.setPrenom("selmi");
			user.setAdresse("tabarka");
			user.setEmail("maher@gmil.com");
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));
		}

	}

}
