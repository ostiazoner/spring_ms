package edu.indra.comun.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "profesores")
public class Profesor {
	
	@Id
	private Long id;
	
	@Size(min = 3, max = 20)
	private String nombre;
	
	@NotEmpty
	private String apellido;
	
	@Min(0)
	@Max(100)
	private int edad;
	
	@Email
	private String email;
	
	@Lob
	@JsonIgnore
	private byte[] cv_file;
	
	@Column(name = "creado_en")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creadoEn;
	
	@OneToMany(fetch = FetchType.LAZY)
	private List<Curso> cursos;

	
	/**
	 * GETTERS / SETTERS
	 */
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public Date getCreadoEn() {
		return creadoEn;
	}

	public void setCreadoEn(Date creadoEn) {
		this.creadoEn = creadoEn;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos.addAll(cursos);
	}
	
	public void eliminarCurso (Curso cu) {
		this.cursos.remove(cu);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public byte[] getCv_file() {
		return cv_file;
	}

	public void setCv_file(byte[] cv_file) {
		this.cv_file = cv_file;
	}

	/**
	 * CONTRUCTORES + TO_STRING
	 */
	
	@Override
	public String toString() {
		return "Profesor [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", edad=" + edad + ", email="
				+ email + ", creadoEn=" + creadoEn + ", cursos=" + cursos + "]";
	}
	
	public Profesor() {
		super();
	}

	public Profesor(Long id, @Size(min = 3, max = 20) String nombre, @NotEmpty String apellido, @Min(0) @Max(100) int edad, @Email String email, Date creadoEn, List<Curso> cursos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.email = email;
		this.creadoEn = creadoEn;
		this.cursos = cursos;
	}
	
}
