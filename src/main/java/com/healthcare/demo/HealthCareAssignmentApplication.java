package com.healthcare.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
@SpringBootApplication
@ComponentScan(basePackages  = {"com.healthcare"})
@EntityScan(basePackages  = {"com.healthcare.domain"})
@EnableSwagger2
@EnableJpaRepositories(basePackages  = {"com.healthcare.repository","com.healthcare.service","com.healthcare.controller","com.healthcare.DTO","com.healthcare.domain"})
public class HealthCareAssignmentApplication {

	public static void main(String[] args) {
		//SpringApplication.run(HealthCareAssignmentApplication.class, args);
		SpringApplication app = new SpringApplication(HealthCareAssignmentApplication.class);
		System.out.println("started-------------");
		Environment env = app.run(args).getEnvironment();
		System.out.println("application ** "+env.getProperty("spring.datasource.url"));
		env.getProperty("url");
		System.out.println("ended-------------");
	}

}

