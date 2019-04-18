package com.gymstatsapirest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;


/**
 * The persistent class for the tarifas database table.
 * 
 */
@ApiModel("Detalles de una tarifa")
@Entity
@Table(name="tarifas")
public class Tarifa implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(name = "identificador de la tarifa generado por la base de datos")
	@Id
	@GeneratedValue
	@Column(name="id_tarifa")
	private Short idTarifa;
	@ApiModelProperty(name = "Nombre de la tarifa", required = true)
	@NotBlank
	@Column(name="nombre_tarifa")
	private String nombreTarifa;

	@ApiModelProperty(name = "precio de la tarifa", required = true)
	@Positive
	@Column(name = "precio")
	private double precio;

	//bi-directional many-to-one association to Suscripcione
	@JsonIgnore
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

	public double getPrecio() {
		return this.precio;
	}

	public void setPrecio(double precio) {
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
