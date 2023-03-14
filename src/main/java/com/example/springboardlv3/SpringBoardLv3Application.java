package com.example.springboardlv3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringBoardLv3Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBoardLv3Application.class, args);
	}

}

// 이거 싸없새 새끼다...