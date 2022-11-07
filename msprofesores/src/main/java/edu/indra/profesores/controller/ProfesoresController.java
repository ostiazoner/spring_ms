package edu.indra.profesores.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import edu.indra.comun.entity.Curso;
import edu.indra.comun.entity.Profesor;
import edu.indra.profesores.service.ProfesorService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

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
	
	@GetMapping("/obtenerProfesorPorNombre/{nombreProfesor}")
	public ResponseEntity<?> obtenerProfesorPorNombre(@PathVariable String nombreProfesor) {
		Optional<Profesor> profesor = profesorService.ObtenerProfesorPorNombre(nombreProfesor);
		
		ResponseEntity<?> response = ResponseEntity.ok(profesor);
		return response;
	}
	
	@GetMapping("/obtenerCursosProfesor")
	public ResponseEntity<?> obtenerCursosProfesor(@Valid @RequestBody Profesor profesor) {
		Iterable<Curso> cursos = profesorService.obtenerCursosProfesor(profesor.getId());
		ResponseEntity<?> response = ResponseEntity.ok(cursos);
		
		return response;
	}
	
	@GetMapping("/obtenerCursosProfesorPorId/{idProfesor}")
	public ResponseEntity<?> obtenerCursosProfesorPorId(@PathVariable Long idProfesor) {
		Iterable<Curso> cursos = profesorService.obtenerCursosProfesor(idProfesor);
		ResponseEntity<?> response = ResponseEntity.ok(cursos);
		
		return response;
	}
	
	@PutMapping("/modificarProfesor")
	public ResponseEntity<?> modificarProfesor(@Valid @RequestBody Profesor profesorNuevo) {
		Optional<Profesor> optional = profesorService.actualizarProfesor(profesorNuevo);
		ResponseEntity<?> response = ResponseEntity.ok(optional);
		
		return response;
	}
	
	@PutMapping("/modificarCvProfesor/{idProfesor}")
	public ResponseEntity<?> modificarCvProfesor(@PathVariable Long idProfesor, MultipartFile archivo) {
		ResponseEntity<?> response = null;
		
		if(!archivo.isEmpty()) {
			try {
				ArrayList<Long> ids = new ArrayList<>();
				ids.add(idProfesor);
				Iterable<Profesor> profesores = profesorService.getProfesorByIds(ids);
				Profesor profesor = profesores.iterator().next();
				
				profesor.setCv_file(archivo.getBytes());
				
				response = response.ok(profesor);
			} catch (IOException e) {
				response = response.status(HttpStatus.BAD_REQUEST).body(null);
			}
		}
		
		return response;
	}
	
	@PostMapping("/insertarProfesor")
	public ResponseEntity<?> insertarProfesor(@Valid @RequestBody Profesor profesorNuevo) {
		ArrayList<Profesor> profesores = new ArrayList<>();
		profesores.add(profesorNuevo);
		
		profesorService.insertarProfesores(profesores);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(profesores);
	}
	
	@DeleteMapping("/eliminarProfesor")
	public ResponseEntity<?> eliminarProfesor(@Valid @RequestBody Profesor profesorNuevo) {
		ArrayList<Long> ids = new ArrayList<>();
		ids.add(profesorNuevo.getId());
		
		profesorService.deleteProfesorById(ids);
		
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/eliminarProfesorPorId/{idProfesor}")
	public ResponseEntity<?> eliminarProfesorPorId(@PathVariable Long idProfesor) {
		ArrayList<Long> ids = new ArrayList<>();
		ids.add(idProfesor);
		
		profesorService.deleteProfesorById(ids);
		
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/asignarCursosProfesor/{idProfesor}")
	public ResponseEntity<?> asignarCursosProfesor(@Valid @RequestBody List<Curso> cursos, @PathVariable Long idProfesor) {
		profesorService.asignarCursosProfesor(cursos, idProfesor);
		
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/eliminarCursosProfesor/{idProfesor}")
	public ResponseEntity<?> eliminarCursosProfesor(@Valid @RequestBody List<Curso> cursos, @PathVariable Long idProfesor) {
		profesorService.eliminarCursosProfesor(cursos, idProfesor);
		
		return ResponseEntity.ok().build();	
	}

}
