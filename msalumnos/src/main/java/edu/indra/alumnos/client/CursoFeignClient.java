package edu.indra.alumnos.client;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import edu.indra.comun.entity.Curso;

@FeignClient(name = "mscursos")
public interface CursoFeignClient {
	//TODOS los metodos del curso controller o los que queremos
	
	@GetMapping("/curso/obtenerCursoAlumno/{idalumno}") // GET http://localhost:8081/curso/obtenerCursoAlumno/5
	public Optional<Curso> obtenerCursoAlumno(@PathVariable Long idalumno);
	
}
