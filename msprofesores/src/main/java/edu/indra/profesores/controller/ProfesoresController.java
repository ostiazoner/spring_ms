package edu.indra.profesores.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.indra.comun.entity.Curso;
import edu.indra.comun.entity.Profesor;
import edu.indra.profesores.service.ProfesorService;

@RestController
@RequestMapping(name = "/profesores")
public class ProfesoresController {
	
	ProfesorService profesorService;
	
	public ResponseEntity<?> listarProfesores() {
		return null;
		
	}
	
	public ResponseEntity<?> obtenerProfesorPorId(Long idProfesor) {
		return null;
		
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
