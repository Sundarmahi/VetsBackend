package com.bt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.CrossOrigin;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication
@CrossOrigin(origins= {"*"},allowCredentials= "false")
//@OpenAPIDefinition(info = @Info(title = "Vets-API's", version = "1.0", description = "API for VET Service")
//,servers = {
//		@Server(url = "https://vetservice.bt.skillassure.com", description = "Default Server URL"),
//		@Server(url = "https://apigateway.bt.skillassure.com", description = "Eureka Server URL")
//		})
@EnableEurekaClient
public class VetPetzyApplication {

	public static void main(String[] args) {
		SpringApplication.run(VetPetzyApplication.class, args);
	}

}
