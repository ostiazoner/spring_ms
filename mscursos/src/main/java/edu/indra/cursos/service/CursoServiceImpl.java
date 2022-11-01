package edu.indra.cursos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import edu.indra.comun.entity.Alumno;
import edu.indra.comun.entity.Curso;
import edu.indra.cursos.repository.CursoRepository;


@Service
public class CursoServiceImpl implements CursoService {

	@Autowired
	CursoRepository cursoRepository;
	
	@Override
	@Transactional(readOnly = true)
	public Iterable<Curso> findAll() {
		return this.cursoRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Curso> findById(Long id) {
		return this.cursoRepository.findById(id);
	}

	@Override
	@Transactional
	public Curso save(Curso curso) {
		return this.cursoRepository.save(curso);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		this.cursoRepository.deleteById(id);
		
	}

	@Override
	@Transactional
	public Optional<Curso> update(Curso curso, Long id) {
		Optional<Curso> optional = Optional.empty();
		
		//1 leer 
		optional = this.cursoRepository.findById(id);
		if (optional.isPresent())
		{
			Curso curso_leido = optional.get();//PERSISTENCE
			//curso_leido.setNombre(curso.getNombre());
			//le actualizo las propiedades
			//2 modificar
			BeanUtils.copyProperties(curso, curso_leido, "id", "creadoEn");
			//this.cursoRepository.save(curso_leido);//NO ES NECESARIO por el estado de la Entidad
			optional = Optional.of(curso_leido);
		}
		
	
	return optional;
	}

	@Override
	@Transactional
	public Optional<Curso> asignarAlumnosCurso(List<Alumno> alumnos, Long idCurso) {
		Optional<Curso> optional = Optional.empty();
		optional = this.cursoRepository.findById(idCurso);
		if(optional.isPresent()) {
			Curso cursoLeido = optional.get();
			alumnos.forEach(alumno -> cursoLeido.addAlumno(alumno));
			optional = Optional.of(cursoLeido);
		}
		
		return optional;
	}

	@Override
	@Transactional
	public Optional<Curso> eliminarAlumnoDeUnCurso(Alumno alumno, Long idCurso) {
		Optional<Curso> optional = Optional.empty();
		optional = this.cursoRepository.findById(idCurso);
		if(optional.isPresent()) {
			Curso cursoLeido = optional.get();
			cursoLeido.eliminarAlumno(alumno);
			optional = Optional.of(cursoLeido);
		}
		
		return optional;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Curso> obtenerCursoAlumno(Long id_alumno) {
		return this.cursoRepository.obtenerCursoAlumnoNativa(id_alumno);
	}
	
}
