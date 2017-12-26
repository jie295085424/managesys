package com.jj.managesys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class ManagesysApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManagesysApplication.class, args);
	}
}
