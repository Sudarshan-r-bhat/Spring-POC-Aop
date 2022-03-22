package com.practise.aop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class PractiseAopApplication {

	public static void main(String[] args) {
		SpringApplication.run(PractiseAopApplication.class, args);
	}

}
