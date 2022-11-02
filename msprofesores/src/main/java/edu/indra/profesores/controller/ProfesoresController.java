package edu.indra.profesores.controller;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.indra.comun.entity.Curso;
import edu.indra.comun.entity.Profesor;
import edu.indra.profesores.service.ProfesorService;

@RestController
@RequestMapping(name = "/profesores")
public class ProfesoresController {
	
	ProfesorService profesorService;
	
	@GetMapping("/listarProfesores")
	public ResponseEntity<?> listarProfesores() {
		Iterable<Profesor> profesores =  profesorService.getProfesores();
		
		ResponseEntity<?> response = ResponseEntity.ok(profesores);
		return response;
	}
	
	@GetMapping("/listarProfesores/{idProfesor}")
	public ResponseEntity<?> obtenerProfesorPorId(@PathVariable Long idProfesor) {
		ArrayList<Long> ids = new ArrayList<>();
		ids.add(idProfesor);
		
		Iterable<Profesor> profesores =  profesorService.getProfesorByIds(ids);
		
		ResponseEntity<?> response = ResponseEntity.ok(profesores);
		return response;		
	}
	
	public ResponseEntity<?> obtenerProfesorPorNombre(String nombreProfesor) {
		//TODO REPOSITORY CONSULTA, SERVICE, SERVICE_IMPL
		return null;
		
	}
	
	public ResponseEntity<?> obtenerCursosProfesor(Profesor profesor) {
		return null;
		
	}
	
	public ResponseEntity<?> obtenerCursosProfesorPorId(Long idProfesor) {
		return null;
		
	}
	
	public ResponseEntity<?> modificarProfesor(Profesor profesorNuevo) {
		return null;
		
	}
	
	public ResponseEntity<?> modificarProfesorPorId(Long idProfesor) {
		return null;
		
	}
	
	public ResponseEntity<?> modificarCvProfesor(Long idProfesor) {
		return null;
		
	}
	
	public ResponseEntity<?> añadirProfesor(Profesor profesorNuevo) {
		return null;
		
	}
	
	public ResponseEntity<?> eliminarProfesor(Profesor profesorNuevo) {
		return null;
		
	}
	
	public ResponseEntity<?> eliminarProfesorPorId(Long idProfesor) {
		return null;
		
	}
	
	public ResponseEntity<?> asignarCursosProfesor(Iterable<Curso> cursos, Long idProfesor) {
		return null;
		
	}
	
	public ResponseEntity<?> eliminarCursosProfesor(Iterable<Curso> cursos, Long idProfesor) {
		return null;
		
	}
	
	

}
