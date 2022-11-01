package edu.indra.cursos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EntityScan("edu.indra.comun") //Con esta anotacion, le ayudo a encontrar al entidad Curso que la hemos movido a otro paquete.
//@ComponentScan Obligatorio usar si los componentes (servicios, repo o controller estan fuera del paquete raiz)
public class MscursosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MscursosApplication.class, args);
	}

}
