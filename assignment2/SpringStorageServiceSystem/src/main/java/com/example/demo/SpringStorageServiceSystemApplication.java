package com.example.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class SpringStorageServiceSystemApplication {
	
	 public static void main(String[] args) {

	        
	        AnnotationConfigApplicationContext context =
	                new AnnotationConfigApplicationContext(SpringStorageServiceSystemApplication.class);

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
