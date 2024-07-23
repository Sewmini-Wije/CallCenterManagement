package com.call_center.Call.Center;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CallCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(CallCenterApplication.class, args);
	}

}
