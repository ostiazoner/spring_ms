package edu.indra.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MsgatewayApplication {

	
	/**
	 * 
	 * 1- Creamos proyecto spring starter, añadimos dependencias de eureka client y gateway
	 * 2- clase main con @EnableEurekaClient
	 * 3- Configurar properties, canviar extension a .yml
	 * 
	 * 
	 */
	
	
	public static void main(String[] args) {
		SpringApplication.run(MsgatewayApplication.class, args);
	}

}
