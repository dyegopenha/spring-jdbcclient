package com.example.springjdbcclient;

import com.example.springjdbcclient.dto.User;
import com.example.springjdbcclient.repository.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringJdbcclientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJdbcclientApplication.class, args);
	}

	@Bean
	ApplicationRunner runner(UserRepository userRepository) {
		return args -> {
			var user = new User(1L, "john", "john@email.com", "123");
			userRepository.save(user);
			user = user.withPassword("newpassword123");
			userRepository.update(user);
			// userRepository.deleteById(user.id());
			userRepository.findAll().forEach(System.out::println);
		};
	}
}
