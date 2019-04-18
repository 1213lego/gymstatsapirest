package com.gymstatsapirest.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the estado_suscripcion database table.
 * 
 */
@Entity
@Table(name="estado_suscripcion")
public class EstadoSuscripcion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_estado_suscripcon")
	@GeneratedValue
	private Short idEstadoSuscripcon;

	@Column(name="estado_suscripcion")
	private String estadoSuscripcion;

	//bi-directional many-to-one association to Suscripcione
	@OneToMany(mappedBy="estadoSuscripcion")
	private List<Suscripcione> suscripciones;

	public EstadoSuscripcion() {
	}

	public Short getIdEstadoSuscripcon() {
		return this.idEstadoSuscripcon;
	}

	public void setIdEstadoSuscripcon(Short idEstadoSuscripcon) {
		this.idEstadoSuscripcon = idEstadoSuscripcon;
	}

	public String getEstadoSuscripcion() {
		return this.estadoSuscripcion;
	}

	public void setEstadoSuscripcion(String estadoSuscripcion) {
		this.estadoSuscripcion = estadoSuscripcion;
	}

	public List<Suscripcione> getSuscripciones() {
		return this.suscripciones;
	}

	public void setSuscripciones(List<Suscripcione> suscripciones) {
		this.suscripciones = suscripciones;
	}

	public Suscripcione addSuscripcione(Suscripcione suscripcione) {
		getSuscripciones().add(suscripcione);
		suscripcione.setEstadoSuscripcion(this);

		return suscripcione;
	}

	public Suscripcione removeSuscripcione(Suscripcione suscripcione) {
		getSuscripciones().remove(suscripcione);
		suscripcione.setEstadoSuscripcion(null);

		return suscripcione;
	}

}
