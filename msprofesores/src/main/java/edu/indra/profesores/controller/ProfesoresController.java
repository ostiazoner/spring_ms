package edu.indra.profesores.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	
	@Autowired
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
		Iterable<Curso> cursos = profesorService.obtenerCursosProfesor(profesor.getId());
		ResponseEntity<?> response = ResponseEntity.ok(cursos);
		
		return response;
	}
	
	public ResponseEntity<?> obtenerCursosProfesorPorId(Long idProfesor) {
		Iterable<Curso> cursos = profesorService.obtenerCursosProfesor(idProfesor);
		ResponseEntity<?> response = ResponseEntity.ok(cursos);
		
		return response;
	}
	
	public ResponseEntity<?> modificarProfesor(Profesor profesorNuevo) {
		Optional<Profesor> optional = profesorService.actualizarProfesor(profesorNuevo);
		ResponseEntity<?> response = ResponseEntity.ok(optional);
		
		return response;
	}
	
	public ResponseEntity<?> modificarCvProfesor(Long idProfesor) {
		return null;
		
	}
	
	public ResponseEntity<?> añadirProfesor(Profesor profesorNuevo) {
		ArrayList<Profesor> profesores = new ArrayList<>();
		profesores.add(profesorNuevo);
		
		profesorService.insertarProfesores(profesores);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(profesores);
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
