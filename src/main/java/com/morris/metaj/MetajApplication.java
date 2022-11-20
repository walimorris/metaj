package com.morris.metaj;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class MetajApplication {
	private static final Logger logger = LoggerFactory.getLogger(MetajApplication.class);

	public static void main(String[] args) throws IOException {
		SpringApplication.run(MetajApplication.class, args);
		logger.info("metaj running at: http://localhost:8080");
	}
}
