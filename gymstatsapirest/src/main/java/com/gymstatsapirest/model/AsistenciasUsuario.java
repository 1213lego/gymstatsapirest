package com.gymstatsapirest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the asistencias_usuario database table.
 * 
 */
@Entity
@Table(name="asistencias_usuario")
public class AsistenciasUsuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(name="fecha_ingreso")
	private Timestamp fechaIngreso;

	@Column(name="fecha_salida")
	private Timestamp fechaSalida;

	//bi-directional many-to-one association to Usuario
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="documento_usuario")
	private Usuario usuario;

	//bi-directional many-to-one association to AsistenciasUsuarioRutina
	@OneToMany(mappedBy="asistenciasUsuario")
	private List<AsistenciasUsuarioRutina> asistenciasUsuarioRutinas;

	public AsistenciasUsuario() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getFechaIngreso() {
		return this.fechaIngreso;
	}

	public void setFechaIngreso(Timestamp fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Timestamp getFechaSalida() {
		return this.fechaSalida;
	}

	public void setFechaSalida(Timestamp fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<AsistenciasUsuarioRutina> getAsistenciasUsuarioRutinas() {
		return this.asistenciasUsuarioRutinas;
	}

	public void setAsistenciasUsuarioRutinas(List<AsistenciasUsuarioRutina> asistenciasUsuarioRutinas) {
		this.asistenciasUsuarioRutinas = asistenciasUsuarioRutinas;
	}

	public AsistenciasUsuarioRutina addAsistenciasUsuarioRutina(AsistenciasUsuarioRutina asistenciasUsuarioRutina) {
		getAsistenciasUsuarioRutinas().add(asistenciasUsuarioRutina);
		asistenciasUsuarioRutina.setAsistenciasUsuario(this);

		return asistenciasUsuarioRutina;
	}

	public AsistenciasUsuarioRutina removeAsistenciasUsuarioRutina(AsistenciasUsuarioRutina asistenciasUsuarioRutina) {
		getAsistenciasUsuarioRutinas().remove(asistenciasUsuarioRutina);
		asistenciasUsuarioRutina.setAsistenciasUsuario(null);

		return asistenciasUsuarioRutina;
	}

}
