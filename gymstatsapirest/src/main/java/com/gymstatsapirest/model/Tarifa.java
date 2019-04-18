package com.gymstatsapirest.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tarifas database table.
 * 
 */
@Entity
@Table(name="tarifas")
public class Tarifa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="id_tarifa")
	private Short idTarifa;

	@Column(name="nombre_tarifa")
	private String nombreTarifa;

	private float precio;

	//bi-directional many-to-one association to Suscripcione
	@OneToMany(mappedBy="tarifa")
	private List<Suscripcione> suscripciones;

	public Tarifa() {
	}

	public Short getIdTarifa() {
		return this.idTarifa;
	}

	public void setIdTarifa(Short idTarifa) {
		this.idTarifa = idTarifa;
	}

	public String getNombreTarifa() {
		return this.nombreTarifa;
	}

	public void setNombreTarifa(String nombreTarifa) {
		this.nombreTarifa = nombreTarifa;
	}

	public float getPrecio() {
		return this.precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public List<Suscripcione> getSuscripciones() {
		return this.suscripciones;
	}

	public void setSuscripciones(List<Suscripcione> suscripciones) {
		this.suscripciones = suscripciones;
	}

	public Suscripcione addSuscripcione(Suscripcione suscripcione) {
		getSuscripciones().add(suscripcione);
		suscripcione.setTarifa(this);

		return suscripcione;
	}

	public Suscripcione removeSuscripcione(Suscripcione suscripcione) {
		getSuscripciones().remove(suscripcione);
		suscripcione.setTarifa(null);

		return suscripcione;
	}

}
