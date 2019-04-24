package com.gymstatsapirest.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.Date;


/**
 * The persistent class for the suscripciones database table.
 * 
 */
@Entity
@Table(name="suscripciones")
public class Suscripcione implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="id_subscripcion")
	private Long idSubscripcion;

	@Column(name="fecha_fin")
	private Date fechaFin;

	@Column(name="fecha_inicio")
	private Date fechaInicio;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	private Cliente cliente;

	//bi-directional many-to-one association to EstadoSuscripcionRepository
	@ManyToOne
	@JoinColumn(name="estado_suscripcon")
	private EstadoSuscripcion estadoSuscripcion;

	//bi-directional many-to-one association to Tarifa
	@ManyToOne
	@JoinColumn(name="tipo_tarifa")
	private Tarifa tarifa;

	public Suscripcione() {
	}

	public Long getIdSubscripcion() {
		return this.idSubscripcion;
	}

	public void setIdSubscripcion(Long idSubscripcion) {
		this.idSubscripcion = idSubscripcion;
	}

	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public EstadoSuscripcion getEstadoSuscripcion() {
		return this.estadoSuscripcion;
	}

	public void setEstadoSuscripcion(EstadoSuscripcion estadoSuscripcion) {
		this.estadoSuscripcion = estadoSuscripcion;
	}

	public Tarifa getTarifa() {
		return this.tarifa;
	}

	public void setTarifa(Tarifa tarifa) {
		this.tarifa = tarifa;
	}

}
