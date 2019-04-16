package com.gymstatsapirest.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the estados_maquina database table.
 * 
 */
@Entity
@Table(name="estados_maquina")
@NamedQuery(name="EstadosMaquina.findAll", query="SELECT e FROM EstadosMaquina e")
public class EstadosMaquina implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="id_estado_maquina")
	private Short idEstadoMaquina;

	@Column(name="estado_maquina")
	private String estadoMaquina;

	//bi-directional many-to-one association to Maquina
	@OneToMany(mappedBy="estadosMaquina")
	private List<Maquina> maquinas;

	public EstadosMaquina() {
	}

	public Short getIdEstadoMaquina() {
		return this.idEstadoMaquina;
	}

	public void setIdEstadoMaquina(Short idEstadoMaquina) {
		this.idEstadoMaquina = idEstadoMaquina;
	}

	public String getEstadoMaquina() {
		return this.estadoMaquina;
	}

	public void setEstadoMaquina(String estadoMaquina) {
		this.estadoMaquina = estadoMaquina;
	}

	public List<Maquina> getMaquinas() {
		return this.maquinas;
	}

	public void setMaquinas(List<Maquina> maquinas) {
		this.maquinas = maquinas;
	}

	public Maquina addMaquina(Maquina maquina) {
		getMaquinas().add(maquina);
		maquina.setEstadosMaquina(this);

		return maquina;
	}

	public Maquina removeMaquina(Maquina maquina) {
		getMaquinas().remove(maquina);
		maquina.setEstadosMaquina(null);

		return maquina;
	}

}