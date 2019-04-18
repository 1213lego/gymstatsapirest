package com.gymstatsapirest.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the maquinas database table.
 * 
 */
@Entity
@Table(name="maquinas")
public class Maquina implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;

	private Integer descripcion;

	@Column(name="fecha_compra")
	private Timestamp fechaCompra;

	private String marca;

	private String nombre;

	//bi-directional many-to-one association to MaquinaRutina
	@OneToMany(mappedBy="maquina")
	private List<MaquinaRutina> maquinaRutinas;

	//bi-directional many-to-one association to EstadosMaquina
	@ManyToOne
	@JoinColumn(name="estado_maquina")
	private EstadosMaquina estadosMaquina;

	public Maquina() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(Integer descripcion) {
		this.descripcion = descripcion;
	}

	public Timestamp getFechaCompra() {
		return this.fechaCompra;
	}

	public void setFechaCompra(Timestamp fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public String getMarca() {
		return this.marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<MaquinaRutina> getMaquinaRutinas() {
		return this.maquinaRutinas;
	}

	public void setMaquinaRutinas(List<MaquinaRutina> maquinaRutinas) {
		this.maquinaRutinas = maquinaRutinas;
	}

	public MaquinaRutina addMaquinaRutina(MaquinaRutina maquinaRutina) {
		getMaquinaRutinas().add(maquinaRutina);
		maquinaRutina.setMaquina(this);

		return maquinaRutina;
	}

	public MaquinaRutina removeMaquinaRutina(MaquinaRutina maquinaRutina) {
		getMaquinaRutinas().remove(maquinaRutina);
		maquinaRutina.setMaquina(null);

		return maquinaRutina;
	}

	public EstadosMaquina getEstadosMaquina() {
		return this.estadosMaquina;
	}

	public void setEstadosMaquina(EstadosMaquina estadosMaquina) {
		this.estadosMaquina = estadosMaquina;
	}

}
