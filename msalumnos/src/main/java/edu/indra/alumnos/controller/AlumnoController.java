package edu.indra.alumnos.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.ClientCodecConfigurer.MultipartCodecs;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import edu.indra.alumnos.dto.FraseChuckNorris;
import edu.indra.alumnos.service.AlumnoService;
import edu.indra.comun.entity.Alumno;
import edu.indra.comun.entity.Curso;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//REST http://localhost:8081/alumno
@CrossOrigin(originPatterns = {"*"}, methods = {RequestMethod.GET})
@RestController
@RequestMapping("/alumno") //Todo lo que sea /alumno se metera en esta clase
public class AlumnoController {
	
	@Value("${instancia}")
	String instancia;
	
	@Autowired
	Environment environment;

	Logger log = LoggerFactory.getLogger(AlumnoController.class);
	
	@Autowired
	AlumnoService alumnoService;
	
	//GET - Leer todos (Le vamos a poder pasar qualquier tipo de datos en este caso seria una lista de alumnos)
	@GetMapping
	public ResponseEntity<?> listarAlumnos() {
		ResponseEntity<?> respEntity = null;
		log.debug("en listarAlumnos");
		log.debug("***Atendido por la instancia: " + instancia);
		log.debug("***Atendido en el puerto: " + environment.getProperty("local.server.port"));
		Iterable<Alumno> listadoAlumnos = this.alumnoService.findAll();
		respEntity = ResponseEntity.ok(listadoAlumnos);
		log.debug("en listarAlumnos" + listadoAlumnos.toString());
		
		return respEntity;
	}
	
	//GET - Leer un alumno 
	@GetMapping("/{id}")
	public ResponseEntity<?> getAlumno(@PathVariable Long id) {
		ResponseEntity<?> respEntity = null;
		
		Optional<Alumno> alumno = this.alumnoService.findById(id);
		if(alumno.isPresent()) {
			Alumno alumno_leido = alumno.get();
			respEntity = ResponseEntity.ok(alumno_leido);
		} else {
			respEntity = ResponseEntity.noContent().build();
		}
		
		return respEntity;
	}
	
	//POST - Guardar un alumno
	@PostMapping
	public ResponseEntity<?> insertarAlumno(@Valid @RequestBody Alumno alumno, BindingResult resultado) {
		ResponseEntity<?> respEntity = null;
		
		if(resultado.hasErrors()) {
			//Tiene errores de validación
			obtenerErrores(resultado);
		} else {
			Alumno alumno_insertado = this.alumnoService.save(alumno);
			respEntity = ResponseEntity.status(HttpStatus.CREATED).body(alumno_insertado);
		}

		return respEntity;
	}
	
	//PUT - Modificar un alumno
	@PutMapping("/{id}")
	public ResponseEntity<?> modificarAlumno(@Valid @RequestBody Alumno alumno, @PathVariable Long id, BindingResult resultado) {
		ResponseEntity<?> respEntity = null;
		
		if(resultado.hasErrors()) {
			//Tiene errores de validación
			obtenerErrores(resultado);
		} else {
			Optional<Alumno> alumno_update = this.alumnoService.update(alumno, id);
			if(alumno_update.isPresent()) {
				Alumno alumno_modificado = alumno_update.get();
				respEntity = ResponseEntity.ok(alumno_modificado);
			} else {
				respEntity = ResponseEntity.noContent().build();
			}
		}
		
		return respEntity;
	}
	
	//DELETE - Borrar un alumno
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarAlumno(@PathVariable Long id) {
		ResponseEntity<?> respEntity = null;
		
		//var saludo = "HOLA";
		//saludo.charAt(5);
		
		this.alumnoService.deleteById(id);
		respEntity = ResponseEntity.ok().build();
			
		return respEntity;
	}
	
	//DELETE ALL - Borrar todos los alumnos
	@DeleteMapping
	public ResponseEntity<?> eliminarAlumnos() {
		ResponseEntity<?> respEntity = null;
		Iterable<Alumno> alumnos = this.alumnoService.findAll();
		
		for (Alumno alumno : alumnos) {
			this.alumnoService.deleteById(alumno.getId());
		}
		
		Iterable<Alumno> listadoAlumnos = this.alumnoService.findAll();
		respEntity = ResponseEntity.ok(listadoAlumnos);

		return respEntity;
	}
	
	private ResponseEntity<?> obtenerErrores(BindingResult resultado) {
		ResponseEntity<?> responseEntity = null;
		List<ObjectError> listaErrores = resultado.getAllErrors();
		//Recorremos todos los errores y los pintamos
		listaErrores.forEach(o_error -> log.error(o_error.toString()));
		responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(listaErrores);
		
		return responseEntity;
	}
	
	//PRUEBA
	@GetMapping("/obtenerAlumnoTest") //Si llega una llamada a este servicio ejecutara el metodo obtenrAlumnoPruebas GET http://localhost:8081/obtenerAlumnoTest
	public Alumno obtenerAlumnoPruebas() {
		Alumno alumno_devuelto = null;
		alumno_devuelto = new Alumno(3L, "Paco", "Lopez", "paco@correo.es", 32, new Date()); //Objeto estado transcient(No tiene relacion con la BD)
		return alumno_devuelto;
	}
	
	//OBTENER FRASE CHUCK ALEATORIA
	@GetMapping("/obtenerFraseAleatoriaChuckNorris") //GET http://localhost:8081/alumno/obtenerFraseAleatoriaChuckNorris
	public ResponseEntity<?> obtenerFraseAleatoriaChuckNorris ()
	{
		ResponseEntity<?> responseEntity = null;
		Optional<FraseChuckNorris> o_frase = null;
		
				log.debug("en obtenerFraseAleatoriaChuckNorris()");
				o_frase = this.alumnoService.obtenerFraseAleatoriaChuckNorris();
				if (o_frase.isPresent())
				{
					FraseChuckNorris frase = o_frase.get();
					responseEntity = ResponseEntity.ok(frase);
					log.debug("FRASE recuperada " + frase);
				} else {
					//no ha obtenido una frase
					log.debug("no ha obtenido una frase");
					responseEntity = ResponseEntity.noContent().build();
				}
				
		return responseEntity;
	}
	
	//OBTENER ALUMNOS EN UN RANGO DE EDAD (service - serviceImpl - controller - repository)
	@GetMapping("/obtenerAlumnosPorRangoDeEdad") //GET http://localhost:8081/alumno/obtenerAlmonosPorRangoDeEdad?edadmin=10&edadmax=40
	public ResponseEntity<?> findByEdadBetween (@RequestParam(name="edadmin") int edadmin, @RequestParam(name="edadmax") int edadmax)
	{
		ResponseEntity<?> responseEntity = null;
		Iterable<Alumno> alumnos = this.alumnoService.findByEdadBetween(edadmin, edadmax);
		responseEntity = ResponseEntity.ok(alumnos);
				
		return responseEntity;
	}
	
	//OBTENER ALUMNOS EN UN RANGO DE EDAD (service - serviceImpl - controller - repository)
	@GetMapping("/obtenerAlumnosLike") //GET http://localhost:8081/alumno/obtenerAlumnosLike?patron=PaC
	public ResponseEntity<?> findByNombreIgnoreCaseContaining (@RequestParam(name="patron") String patron)
	{
		ResponseEntity<?> responseEntity = null;
		Iterable<Alumno> alumnos = this.alumnoService.findByNombreIgnoreCaseContaining(patron);
		responseEntity = ResponseEntity.ok(alumnos);
				
		return responseEntity;
	}
	
	//OBTENER ALUMNOS POR NOMBRE O APELLIDOS (PQL)
	@GetMapping("/busquedaNombreApellidosJPQL/{patron}") //GET http://localhost:8081/alumno/busquedaNombreApellidosJPQL/Pa
	public ResponseEntity<?> busquedaNombreApellidosJPQL (String patron)
	{
		ResponseEntity<?> responseEntity = null;
		Iterable<Alumno> alumnos = this.alumnoService.busquedaNombreApellidosJPQL(patron);
		responseEntity = ResponseEntity.ok(alumnos);
				
		return responseEntity;
	}
	
	//OBTENER ALUMNOS POR NOMBRE O APELLIDOS (SQL)
	@GetMapping("/busquedaNombreApellidosComo/{patron}") //GET http://localhost:8081/alumno/busquedaNombreApellidosComo/p
	public ResponseEntity<?> busquedaNombreApellidosComo (String patron)
	{
		ResponseEntity<?> responseEntity = null;
		Iterable<Alumno> alumnos = this.alumnoService.busquedaNombreApellidosComo(patron);
		responseEntity = ResponseEntity.ok(alumnos);
				
		return responseEntity;
	}
	
	@GetMapping("/prodecimientoAlumnosAltaHoy") //GET http://localhost:8081/alumno/prodecimientoAlumnosAltaHoy
	public ResponseEntity<?> prodecimientoAlumnosAltaHoy ()
	{
		ResponseEntity<?> responseEntity = null;
		Iterable<Alumno> alumnos = this.alumnoService.prodecimientoAlumnosAltaHoy();
		responseEntity = ResponseEntity.ok(alumnos);
				
		return responseEntity;
	}
	
	@GetMapping("/prodecimientoEstadisticosEdad") //GET http://localhost:8081/alumno/prodecimientoEstadisticosEdad
	public ResponseEntity<?> prodecimientoEstadisticosEdad ()
	{
		ResponseEntity<?> responseEntity = null;
		Map<String, Number> edades = this.alumnoService.prodecimientoEstadisticosEdad();
		responseEntity = ResponseEntity.ok(edades);
				
		return responseEntity;
	}
	
	@GetMapping("/prodecimientoAlumnosNombreComo/{patron}") //GET http://localhost:8081/alumno/prodecimientoAlumnosNombreComo
	public ResponseEntity<?> prodecimientoAlumnosNombreComo (@PathVariable String patron)
	{
		ResponseEntity<?> responseEntity = null;
		Iterable<Alumno> alumnos = this.alumnoService.prodecimientoAlumnosNombreComo(patron);
		responseEntity = ResponseEntity.ok(alumnos);
				
		return responseEntity;
	}
	
	@GetMapping("/obtenerAlumnosPorPagina") // GET http://localhost:8081/alumno/obtenerAlumnosPorPagina?page=0&size=3&sort=edad,ASC
	public ResponseEntity<?> obtenerAlumnosPorPagina(Pageable pageable ) {
		Iterable<Alumno>  listado_alumnos = this.alumnoService.findAll(pageable);
		ResponseEntity<?> responseEntity = ResponseEntity.ok(listado_alumnos);

		return responseEntity;
	}
	
	@GetMapping("/obtenerAlumnosPorRangoDeEdadPaginable") //GET http://localhost:8081/alumno/obtenerAlumnosPorRangoDeEdadPaginable?edadmin=10&edadmax=40&page=0&size=3
	public ResponseEntity<?> findByEdadBetweenPaginado (@RequestParam(name="edadmin") int edadmin, @RequestParam(name="edadmax") int edadmax, Pageable pag)
	{
		ResponseEntity<?> responseEntity = null;
		Iterable<Alumno> alumnos = this.alumnoService.findByEdadBetween(edadmin, edadmax, pag);
		responseEntity = ResponseEntity.ok(alumnos);
				
		return responseEntity;
	}
	
	@GetMapping("/obtenerAlumnosPorPaginaOrdenados") // GET http://localhost:8081/alumno/obtenerAlumnosPorPaginaOrdenados?page=0&size=3&campo=nombre&orden=ASC
	public ResponseEntity<?> obtenerAlumnosPorPaginaOrdenados(Pageable pageable, @RequestParam(name="campo") String campoOrden, @RequestParam(name="orden") String orden) {
		Iterable<Alumno>  listado_alumnos = null;
		
		if("ASC".equals(orden)) {
			listado_alumnos = this.alumnoService.findAll(pageable, Sort.by(campoOrden).ascending());
		} else if("DESC".equals(orden)) {
			listado_alumnos = this.alumnoService.findAll(pageable, Sort.by(campoOrden).descending());
		}

		ResponseEntity<?> responseEntity = ResponseEntity.ok(listado_alumnos);

		return responseEntity;
	}
	
	@GetMapping("/obtenerCursoAlumnoViaFeign/{idalumno}") // GET http://localhost:8081/alumno/obtenerCursoAlumnoViaFeign/1
	public ResponseEntity<?> obtenerCursoAlumnoViaFeign(@PathVariable Long idalumno) {
		ResponseEntity<?> responseEntity = null;
		Optional<Curso> o_curso = null;

			o_curso = this.alumnoService.obtenerCursoAlumno(idalumno);
			if (o_curso.isPresent()) {
				Curso curso_leido = o_curso.get();
				responseEntity = ResponseEntity.ok(curso_leido);
			} else {
				// no había un alumno con ese ID
				responseEntity = ResponseEntity.noContent().build();
			}

		return responseEntity;
	}
	
	@PostMapping("/crear-con-foto") // POST http://localhost:8081/alumno/crear-con-foto
	public ResponseEntity<?> insertarAlumnoConFoto(@Valid Alumno alumno, BindingResult resultado, MultipartFile archivo) {
		ResponseEntity<?> respEntity = null;
		
		if(resultado.hasErrors()) {
			//Tiene errores de validación
			obtenerErrores(resultado);
		} else {
			if(!archivo.isEmpty()) {
				try {
					alumno.setFoto(archivo.getBytes());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			Alumno alumno_insertado = this.alumnoService.save(alumno);
			respEntity = ResponseEntity.status(HttpStatus.CREATED).body(alumno_insertado);
		}

		return respEntity;
	}
	
	@GetMapping("/obtenerFotoAlumno/{id}") // GET http://localhost:8081/alumno/obtenerFoto/5
	public ResponseEntity<?> obtenerFotoAlumno(@PathVariable Long id) {
		ResponseEntity<?> respEntity = null;
		Resource imagen = null;
		
		Optional<Alumno> alumno = this.alumnoService.findById(id);
		if(alumno.isPresent() && alumno.get().getFoto() != null) {
			Alumno alumno_leido = alumno.get();
			imagen = new ByteArrayResource(alumno_leido.getFoto());
			respEntity = ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imagen);
		} else {
			//No dispone de foto
			respEntity = ResponseEntity.noContent().build();
		}
		
		return respEntity;
	}
	
	@PutMapping("/editar-con-foto/{id}") //PUT localhost:8081/alumno/id
	public ResponseEntity<?> modificarAlumnoConFoto (@Valid Alumno alumno, BindingResult bindingResult, MultipartFile archivo, @PathVariable Long id) throws IOException {
		 ResponseEntity<?> responseEntity = null;
		 Optional<Alumno> optional_alumno = null;
		 
		 	if (bindingResult.hasErrors())
		 	{
		 		//viene con errores
		 		//devolver un mensaje de error //BAD REQUEST -le enviamos los fallos
		 	
		 		responseEntity = obtenerErrores(bindingResult);
		 	} else {
		 		//sin fallos- seguimos con el update
		 		if (!archivo.isEmpty())
		 		{
		 			try {
						alumno.setFoto(archivo.getBytes());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						throw e;
					}
		 		}
		 		
		 		optional_alumno = this.alumnoService.update(alumno, id);
			 	if (optional_alumno.isPresent())
			 	{
			 		responseEntity = ResponseEntity.ok(optional_alumno.get());
			 	} else 
			 	{
			 		//no existía
			 		responseEntity = ResponseEntity.notFound().build();
			 	}
		 	}
		 
		 return responseEntity; //este será el HTTP de vuelta
	}

}
