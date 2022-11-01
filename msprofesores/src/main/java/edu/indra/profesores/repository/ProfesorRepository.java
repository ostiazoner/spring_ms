package edu.indra.profesores.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.indra.comun.entity.Curso;
import edu.indra.comun.entity.Profesor;

@Repository
public interface ProfesorRepository extends CrudRepository<Profesor, Long>{
	
	@Query(value = "select * from cursos where id in (select cursos_id from profesores_cursos where profesor_id = ?1)", nativeQuery = true)
	public Iterable<Curso> ObtenerCursosProfesor(Long idProfesor);
	
}
