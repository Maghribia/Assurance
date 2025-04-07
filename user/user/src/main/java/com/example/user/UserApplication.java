package com.example.user;


import com.example.user.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableDiscoveryClient
public class UserApplication {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);


	}
	@Bean
	public JavaMailSender javaMailSender() {
		return new JavaMailSenderImpl();
	}
	/*
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

	}*/

}
