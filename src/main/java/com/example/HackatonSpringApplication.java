package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class HackatonSpringApplication {

	public static void main(String[] args) {
		System.setProperty("server.servlet.context-path", "/hackaton-springboot");
		SpringApplication.run(HackatonSpringApplication.class, args);
	}

}
