package edu.indra.alumnos.repository;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.indra.comun.entity.Alumno;

@Repository
//public interface AlumnoRepository extends CrudRepository<Alumno, Long> {
public interface AlumnoRepository extends PagingAndSortingRepository<Alumno, Long> { //Dispondremos de CRUD + PagingAndSortingRepository

	//1 KEY WORD SQL
	
		//1 Obtener un listado de alumnos por rango de edad
		//https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
		public Iterable<Alumno> findByEdadBetween(int edadMin, int edadMax);
		
		//2 Obtener un listado de alumnos que cumpla un patron
		public Iterable<Alumno> findByNombreIgnoreCaseContaining(String patron);
	
	//2 JPQL (lenguaje que seria apto para todo tipo de BD)
		
		//1 BUSCAR ALUMNOS QUE SE LLAMEN O APELLIDEN COMO EL PATRON (No hacemos referencia a la tabla sino hacemos referencia a la entidad)
		@Query("SELECT a FROM Alumno a WHERE (a.nombre LIKE %?1 OR a.apellido LIKE %?1)")
		public Iterable<Alumno> busquedaNombreApellidosJPQL(String patron);
	
	//3 SQLs NATIVAS (select contra la tabla)
		
		@Query(value = "SELECT * FROM ALUMNOS a WHERE (a.nombre LIKE %?1 OR a.apellido LIKE %?1)", nativeQuery = true)
		public Iterable<Alumno> busquedaNombreApellidosComo(String patron);
	
	//4 PROCEDIMIENTOS ALMACENADOS (PLSQL por ejemplo)
		
		//1 DEFINIRLOS EN LA BASE DE DATOS
		/**
		 *  use indra24;e
			DELIMITER $$
			CREATE DEFINER=`root`@`localhost` PROCEDURE `obtenerAlumnosConNombreComo`( IN patron VARCHAR(50))
			BEGIN
			    SELECT * FROM indra24.alumnos
			    WHERE nombre like patron;
			END
			$$

		* use indra24;
			DELIMITER $$
			CREATE DEFINER=`root`@`localhost` PROCEDURE `calcular_max_min_media_edad`(
				OUT edadmax INT(11),
			    OUT edadmin INT(11),
			    OUT edadmedia DECIMAL(15, 2)
			)
			BEGIN
			  SELECT 
			    MAX(edad),
			    MIN(edad),
			    AVG(edad)
			    FROM indra24.alumnos
			    INTO edadmax, edadmin, edadmedia;
			END
			$$
		
		* use indra24;
			DELIMITER $$
			CREATE DEFINER=`root`@`localhost` PROCEDURE `obtenerAlumnosRegistradosHoy`()
			BEGIN
			    SELECT * FROM indra24.alumnos 
			    WHERE DATE(`creado_en`) = CURDATE();
			END
			$$
		 * 
		 */
		//2 REFERENCIASLOS EN LA ENTIDAD ALUMNOS (en la clase alumno)
		//3 HACER METODOS EN ALUMNO REPOSITORY @PROCEDURE QUE REFERENCIAN AL 2
		@Procedure(name="Alumno.alumnosRegistradosHoy")
		public Iterable<Alumno> prodecimientoAlumnosAltaHoy();
		
		@Procedure(name="Alumno.alumnosEdadMediaMinMax")
		public Map<String, Number> prodecimientoEstadisticosEdad(int edadMax, int edadMin, float edadMedia);
		
		@Procedure(name="Alumno.alumnosNombreComo")
		public Iterable<Alumno> prodecimientoAlumnosNombreComo(@Param("patron") String patron);
		
	//5 CRITERIA API (No lo veremos)
	
	//6 PAGINACIÓN - CONSULTAS
		
		//1.1 Obtener un listado de alumnos por rango de edad paginado
		public Page<Alumno> findByEdadBetween(int edadMin, int edadMax, Pageable pag);
	
	
}
