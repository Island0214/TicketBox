package com.example.demo;

import com.example.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class TicketBoxServerApplication {
	@RequestMapping("/")
	String home() {
		return "welcome";
	}

	public static void main(String[] args) {
		SpringApplication.run(TicketBoxServerApplication.class, args);
//		UserRepository userRepository
	}
}
