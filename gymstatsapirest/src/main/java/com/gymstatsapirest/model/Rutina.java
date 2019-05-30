package com.gymstatsapirest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;


/**
 * The persistent class for the rutinas database table.
 * 
 */
@Entity
@Table(name="rutinas")
public class Rutina implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;
	@NotBlank
	@Column(name="descripcion_rutina")
	private String descripcionRutina;
	@NotBlank
	@Column(name="nombre_rutina")
	private String nombreRutina;

	//bi-directional many-to-one association to AsistenciasUsuarioRutina
	@OneToMany(mappedBy="rutina")
	@JsonIgnore
	private List<AsistenciasUsuarioRutina> asistenciasUsuarioRutinas;
	//bi-directional many-to-one association to MaquinaRutina
	@OneToMany(mappedBy="rutina")
	@JsonIgnore
	private List<MaquinaRutina> maquinaRutinas;

	public Rutina() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcionRutina() {
		return this.descripcionRutina;
	}

	public void setDescripcionRutina(String descripcionRutina) {
		this.descripcionRutina = descripcionRutina;
	}

	public String getNombreRutina() {
		return this.nombreRutina;
	}

	public void setNombreRutina(String nombreRutina) {
		this.nombreRutina = nombreRutina;
	}

	public List<AsistenciasUsuarioRutina> getAsistenciasUsuarioRutinas() {
		return this.asistenciasUsuarioRutinas;
	}

	public void setAsistenciasUsuarioRutinas(List<AsistenciasUsuarioRutina> asistenciasUsuarioRutinas) {
		this.asistenciasUsuarioRutinas = asistenciasUsuarioRutinas;
	}

	public AsistenciasUsuarioRutina addAsistenciasUsuarioRutina(AsistenciasUsuarioRutina asistenciasUsuarioRutina) {
		getAsistenciasUsuarioRutinas().add(asistenciasUsuarioRutina);
		asistenciasUsuarioRutina.setRutina(this);

		return asistenciasUsuarioRutina;
	}

	public AsistenciasUsuarioRutina removeAsistenciasUsuarioRutina(AsistenciasUsuarioRutina asistenciasUsuarioRutina) {
		getAsistenciasUsuarioRutinas().remove(asistenciasUsuarioRutina);
		asistenciasUsuarioRutina.setRutina(null);

		return asistenciasUsuarioRutina;
	}

	public List<MaquinaRutina> getMaquinaRutinas() {
		return this.maquinaRutinas;
	}

	public void setMaquinaRutinas(List<MaquinaRutina> maquinaRutinas) {
		this.maquinaRutinas = maquinaRutinas;
	}

	public MaquinaRutina addMaquinaRutina(MaquinaRutina maquinaRutina) {
		getMaquinaRutinas().add(maquinaRutina);
		maquinaRutina.setRutina(this);

		return maquinaRutina;
	}

	public MaquinaRutina removeMaquinaRutina(MaquinaRutina maquinaRutina) {
		getMaquinaRutinas().remove(maquinaRutina);
		maquinaRutina.setRutina(null);

		return maquinaRutina;
	}

}
