package edu.indra.alumnos.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import edu.indra.alumnos.dto.FraseChuckNorris;
import edu.indra.comun.entity.Alumno;
import edu.indra.comun.entity.Curso;

public interface AlumnoService {
	
	//ABMC ALUMNOS
	public Iterable<Alumno> findAll ();
	
	public Iterable<Alumno> findAll (Pageable pag);
	
	public Iterable<Alumno> findAll (Pageable pag, Sort orden);
	
	public Optional<Alumno> findById (Long id);
	
	public Alumno save (Alumno alumno);
	
	public void deleteById (Long id);
	
	public Optional<Alumno> update (Alumno alumno, Long id);
	
	public Optional<FraseChuckNorris> obtenerFraseAleatoriaChuckNorris();
	
	public Iterable<Alumno> findByEdadBetween(int edadMin, int edadMax);
	
	public Iterable<Alumno> findByEdadBetween(int edadMin, int edadMax, Pageable pag);
	
	public Iterable<Alumno> findByNombreIgnoreCaseContaining(String patron);
	
	public Iterable<Alumno> busquedaNombreApellidosJPQL(String patron);
	
	public Iterable<Alumno> busquedaNombreApellidosComo(String patron);
	
	public Iterable<Alumno> prodecimientoAlumnosAltaHoy();
	
	public Map<String, Number> prodecimientoEstadisticosEdad();

	public Iterable<Alumno> prodecimientoAlumnosNombreComo(String patron);
	
	public Optional<Curso> obtenerCursoAlumno(Long idalumno);
}
