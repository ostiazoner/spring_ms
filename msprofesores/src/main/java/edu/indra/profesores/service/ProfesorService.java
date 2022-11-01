package edu.indra.profesores.service;

import java.util.Optional;

import edu.indra.comun.entity.Profesor;

public interface ProfesorService {

	public Iterable<Profesor> getProfesorByIds(Iterable<Long> id);
	
	public Iterable<Profesor> getProfesores();
	
	public void deleteProfesorById(Iterable<Long> id);
	
	public void insertarProfesores(Iterable<Profesor> profesores);
	
	public Optional<Profesor> actualizarProfesor (Profesor profesor);
}
