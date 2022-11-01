package edu.indra.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer //Activamos eureka
public class MseurekaCloudApplication {
	
	/**
	 * PASOS PARA CONFIGURAR EUREKA
	 * 
	 * 1 Creamos un proyecto starter y add Eureka Server
	 * 2 Add JAXB Glassfish
	 * 3 Anotamos el main con @EnableEurekaServer
	 * 4 Configurar las properties porque va a ser un servidor
	 * 5 sobre el microservicio alumnos le añadimos las dependencias del eureka client
	 * 6 Anotamos en el main de msalumnos @EnableEurekaClient
	 * 7 gateway
	 * 
	 */

	public static void main(String[] args) {
		SpringApplication.run(MseurekaCloudApplication.class, args);
	}

}
