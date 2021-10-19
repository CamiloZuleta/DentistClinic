package com.DH.DentistClinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class DentistClinicApplication {

	public static void main(String[] args) {
		SpringApplication.run(DentistClinicApplication.class, args);
	}

}
