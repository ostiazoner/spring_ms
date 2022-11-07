package edu.indra.profesores.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.indra.comun.entity.Curso;
import edu.indra.comun.entity.Profesor;
import edu.indra.profesores.repository.ProfesorRepository;

@Service
public class ProfesorServiceImpl implements ProfesorService{

	@Autowired
	ProfesorRepository profesorRespository;
	
	@Transactional(readOnly = true)
	public Iterable<Profesor> getProfesorByIds(Iterable<Long> id){
		return profesorRespository.findAllById(id);
	}
	
	@Transactional(readOnly = true)
	public Iterable<Profesor> getProfesores() {
		return profesorRespository.findAll();
	}
	
	@Transactional
	public void deleteProfesorById(Iterable<Long> id) {
		profesorRespository.deleteAllById(id);
	}
	
	@Transactional
	public void insertarProfesores(Iterable<Profesor> profesores) {
		profesorRespository.saveAll(profesores);
	}
	
	@Transactional
	public Optional<Profesor> actualizarProfesor (Profesor profesor) {
		Optional<Profesor> profesor_ant = profesorRespository.findById(profesor.getId());
		
		if(profesor_ant.isPresent()) {
			Profesor profesor_leido = profesor_ant.get();
			BeanUtils.copyProperties(profesor, profesor_leido, "id", "creadoEn");
			profesor_ant = Optional.of(profesor_leido);
		}
		
		return profesor_ant;
	}
	
	@Transactional(readOnly = true)
	public Iterable<Curso> obtenerCursosProfesor(Long idPorfesor) {
		return profesorRespository.ObtenerCursosProfesor(idPorfesor);
	}

	@Transactional
	public void asignarCursosProfesor(List<Curso> cursos, Long idProfesor) {
		Optional<Profesor> profesor = profesorRespository.findById(idProfesor);
		profesor.get().setCursos(cursos);
	}

	@Transactional
	public void eliminarCursosProfesor(List<Curso> cursos, Long idProfesor) {
		Optional<Profesor> profesor = profesorRespository.findById(idProfesor);
		cursos.forEach(curso -> (profesor.get()).eliminarCurso(curso));
	}

	@Transactional(readOnly = true)
	public Optional<Profesor> ObtenerProfesorPorNombre(String nombreProfesor) {
		return profesorRespository.ObtenerProfesorPrNombre(nombreProfesor);
	}
	
	
	
}
