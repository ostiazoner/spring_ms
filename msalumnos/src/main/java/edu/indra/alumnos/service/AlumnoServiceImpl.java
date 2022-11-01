package edu.indra.alumnos.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import edu.indra.alumnos.client.CursoFeignClient;
import edu.indra.alumnos.dto.FraseChuckNorris;
import edu.indra.alumnos.repository.AlumnoRepository;
import edu.indra.comun.entity.Alumno;
import edu.indra.comun.entity.Curso;

@Service
public class AlumnoServiceImpl implements AlumnoService {
	
	@Autowired
	AlumnoRepository alumnoRepository;

	@Autowired
	CursoFeignClient cursoFeignClient;
	
	@Override
	@Transactional(readOnly = true)
	public Iterable<Alumno> findAll() {
		return this.alumnoRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Alumno> findById(Long id) {
		return this.alumnoRepository.findById(id);
	}

	@Override
	@Transactional
	public Alumno save(Alumno alumno) {
		return this.alumnoRepository.save(alumno);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		this.alumnoRepository.deleteById(id);
		
	}

	@Override
	@Transactional
	public Optional<Alumno> update(Alumno alumno, Long id) {
		Optional<Alumno> optional = Optional.empty();
		
			//1 leer 
			optional = this.alumnoRepository.findById(id);
			if (optional.isPresent())
			{
				Alumno alumno_leido = optional.get();//PERSISTENCE
				//alumno_leido.setNombre(alumno.getNombre());
				//le actualizo las propiedades
				BeanUtils.copyProperties(alumno, alumno_leido, "id", "creadoEn");
				//this.alumnoRepository.save(alumno_leido);//NO ES NECESARIO por el estado de la Entidad
				optional = Optional.of(alumno_leido);
			}
			//2 modificar
		
		return optional;
	}

	@Override
	//No lo hacemos transacional porque no interactua con la BD
	public Optional<FraseChuckNorris> obtenerFraseAleatoriaChuckNorris() {
		Optional<FraseChuckNorris> optional = Optional.empty();
		FraseChuckNorris frase = null;
		RestTemplate restTmp = new RestTemplate();
		
		//Pase JSON a java
		frase = restTmp.getForObject("https://api.chucknorris.io/jokes/random", FraseChuckNorris.class);
		optional = Optional.of(frase);
		
		return Optional.empty();
	}
	
	@Override
	@Transactional
	public Iterable<Alumno> findByEdadBetween(int edadMin, int edadMax){
		return alumnoRepository.findByEdadBetween(edadMin, edadMax);
	}

	@Override
	@Transactional
	public Iterable<Alumno> findByNombreIgnoreCaseContaining(String patron) {
		return alumnoRepository.findByNombreIgnoreCaseContaining(patron);
	}
	
	@Override
	@Transactional
	public Iterable<Alumno> busquedaNombreApellidosJPQL(String patron){
		return alumnoRepository.busquedaNombreApellidosJPQL(patron);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Iterable<Alumno> busquedaNombreApellidosComo(String patron){
		return alumnoRepository.busquedaNombreApellidosComo(patron);
	}

	@Override
	@Transactional(readOnly = false) //Aun que no modifique la BD hay que indicarlo (peculariedad de los procedimientos)
	public Iterable<Alumno> prodecimientoAlumnosAltaHoy() {
		return this.alumnoRepository.prodecimientoAlumnosAltaHoy();
	}

	@Override
	@Transactional(readOnly = false)
	public Map<String, Number> prodecimientoEstadisticosEdad() {
		return this.alumnoRepository.prodecimientoEstadisticosEdad(0, 0, 0f); //Añadimos estos parametros para que funcione
	}

	@Override
	@Transactional(readOnly = false)
	public Iterable<Alumno> prodecimientoAlumnosNombreComo(String patron) {
		return this.alumnoRepository.prodecimientoAlumnosNombreComo("%" + patron + "%");
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Alumno> findAll(Pageable pag) {
		return this.alumnoRepository.findAll(pag);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Iterable<Alumno> findByEdadBetween(int edadMin, int edadMax, Pageable pag){
		return alumnoRepository.findByEdadBetween(edadMin, edadMax, pag);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Alumno> findAll(Pageable pag, Sort orden) {
		return this.alumnoRepository.findAll(orden);
	}

	@Override
	//No lo hacemos transacional porque no interactua con la BD
	public Optional<Curso> obtenerCursoAlumno(Long idalumno) {
		//Mi fuente de datos va a ser feign
		Optional<Curso> ocurso = null;
		
		try {
			ocurso = cursoFeignClient.obtenerCursoAlumno(idalumno);
			if (ocurso.isPresent())
			{
				//log.debug("Alumno matriculado en el curso " + ocurso.get());
			}else {
				//log.debug("Alumno NO matriculado en Ningún curso");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return ocurso;
	}
	
	

	
}
