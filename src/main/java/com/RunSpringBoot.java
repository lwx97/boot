package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication

@MapperScan(basePackages = {"com.boot.dao","com.boot.service"})
public class RunSpringBoot {
	public static void main(String[] args){
		SpringApplication.run(RunSpringBoot.class, args);
	}
}
