package com.sports.badmintonclub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sports.badmintonclub.models.User;
import com.sports.badmintonclub.repositories.UserRepository;
import com.sports.badmintonclub.security.SecurityConfig;


@SpringBootApplication
public class BadmintonClubApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(BadmintonClubApplication.class, args);
	}
	@Autowired
	PasswordEncoder passwordEncoder;

	@Bean
	CommandLineRunner commandLineRunner(UserRepository userRepository) {
		return args -> {
			User entity = new User();
			entity.setUserName("admin");
			entity.setPassword(passwordEncoder.encode("Admin@123"));
			entity.setActive(true);
			entity.setRoles("ROLE_ADMIN");
			userRepository.save(entity);
		};
	}

}
