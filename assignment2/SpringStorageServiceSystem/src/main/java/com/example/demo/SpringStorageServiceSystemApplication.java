package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringStorageServiceSystemApplication {
	
	 public static void main(String[] args) {

		    
	        ConfigurableApplicationContext context = SpringApplication.run(SpringStorageServiceSystemApplication.class, args);

	        System.out.println("---- Container Started ----");

	        StorageService storageService = context.getBean(StorageService.class);
	        storageService.storeFile("data.txt");

	        System.out.println("---- Prototype Bean Calls ----");

	        StorageService local1 = (StorageService) context.getBean("localStorage");
	        StorageService local2 = (StorageService) context.getBean("localStorage");

	        local1.storeFile("local1.txt");
	        local2.storeFile("local2.txt");

	        context.close();
	    }
}
