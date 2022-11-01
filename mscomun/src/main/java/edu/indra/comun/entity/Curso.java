package edu.indra.comun.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "cursos")
public class Curso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//equivale a autoINC en MySql
	private Long id;
	
	private String nombre;
	
	@OneToMany(fetch = FetchType.LAZY) //para optimizar, hasta que no utilize la tabla alumnos no entrara...
	private List<Alumno> alumnos;
	
	@Column(name = "creado_en")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creadoEn;
	
	@PrePersist
	private void generarFechaCreacion ()
	{
		this.creadoEn = new Date();
	}

	
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

	public Date getCreadoEn() {
		return creadoEn;
	}

	public void setCreadoEn(Date creadoEn) {
		this.creadoEn = creadoEn;
	}

	public List<Alumno> getAlumnos() {
		return alumnos;
	}


	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}
	
	public void addAlumno (Alumno al) {
		this.alumnos.add(al);
	}
	
	public void eliminarAlumno (Alumno al) {
		this.alumnos.remove(al); //necesitamos en alumno el metodo equals
	}
	
	public int getNumAlumnos() {
		return this.alumnos.size();
	}

	@Override
	public String toString() {
		return "Curso [id=" + id + ", nombre=" + nombre + ", creadoEn=" + creadoEn + "]";
	}

	public Curso() {
		this.alumnos = new ArrayList<Alumno>();
	}

	public Curso(Long id, String nombre, List<Alumno> alumnos, Date creadoEn) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.alumnos = alumnos;
		this.creadoEn = creadoEn;
	}
}
