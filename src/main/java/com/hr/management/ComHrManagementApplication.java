package com.hr.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class ComHrManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComHrManagementApplication.class, args);
	}

}
