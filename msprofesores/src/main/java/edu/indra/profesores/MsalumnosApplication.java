package edu.indra.profesores;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@EntityScan("edu.indra.comun") //Con esta anotacion, le ayudo a encontrar al entidad Alumno que la hemos movido a otro paquete.
@EnableFeignClients //Activamos FEIGN
//@ComponentScan Obligatorio usar si los componentes (servicios, repo o controller estan fuera del paquete raiz)
public class MsalumnosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsalumnosApplication.class, args);
	}

}
