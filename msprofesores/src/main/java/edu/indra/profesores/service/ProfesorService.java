package edu.indra.profesores.service;

import java.util.List;
import java.util.Optional;

import edu.indra.comun.entity.Alumno;
import edu.indra.comun.entity.Curso;
import edu.indra.comun.entity.Profesor;

public interface ProfesorService {

	public Iterable<Profesor> getProfesorByIds(Iterable<Long> id);
	
	public Iterable<Profesor> getProfesores();
	
	public void deleteProfesorById(Iterable<Long> id);
	
	public void insertarProfesores(Iterable<Profesor> profesores);
	
	public Optional<Profesor> actualizarProfesor (Profesor profesor);
	
	public Iterable<Curso> obtenerCursosProfesor(Long idPorfesor);
	
	public void asignarCursosProfesor(List<Curso> cursos, Long idProfesor);
	
	public void eliminarCursosProfesor(List<Curso> cursos, Long idProfesor);
}
