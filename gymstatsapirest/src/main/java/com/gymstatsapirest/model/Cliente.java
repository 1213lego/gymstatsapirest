package com.gymstatsapirest.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the clientes database table.
 * 
 */
@Entity
@Table(name="clientes")
@NamedQuery(name="Cliente.findAll", query="SELECT c FROM Cliente c")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer documento;

	//bi-directional one-to-one association to Usuario
	@OneToOne
	@JoinColumn(name="documento")
	private Usuario usuario;

	//bi-directional many-to-one association to MedidaCliente
	@OneToMany(mappedBy="cliente")
	private List<MedidaCliente> medidaClientes;

	//bi-directional many-to-one association to Suscripcione
	@OneToMany(mappedBy="cliente")
	private List<Suscripcione> suscripciones;

	public Cliente() {
	}

	public Integer getDocumento() {
		return this.documento;
	}

	public void setDocumento(Integer documento) {
		this.documento = documento;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<MedidaCliente> getMedidaClientes() {
		return this.medidaClientes;
	}

	public void setMedidaClientes(List<MedidaCliente> medidaClientes) {
		this.medidaClientes = medidaClientes;
	}

	public MedidaCliente addMedidaCliente(MedidaCliente medidaCliente) {
		getMedidaClientes().add(medidaCliente);
		medidaCliente.setCliente(this);

		return medidaCliente;
	}

	public MedidaCliente removeMedidaCliente(MedidaCliente medidaCliente) {
		getMedidaClientes().remove(medidaCliente);
		medidaCliente.setCliente(null);

		return medidaCliente;
	}

	public List<Suscripcione> getSuscripciones() {
		return this.suscripciones;
	}

	public void setSuscripciones(List<Suscripcione> suscripciones) {
		this.suscripciones = suscripciones;
	}

	public Suscripcione addSuscripcione(Suscripcione suscripcione) {
		getSuscripciones().add(suscripcione);
		suscripcione.setCliente(this);

		return suscripcione;
	}

	public Suscripcione removeSuscripcione(Suscripcione suscripcione) {
		getSuscripciones().remove(suscripcione);
		suscripcione.setCliente(null);

		return suscripcione;
	}

}