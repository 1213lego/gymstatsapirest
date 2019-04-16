package com.gymstatsapirest.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipos_medida database table.
 * 
 */
@Entity
@Table(name="tipos_medida")
@NamedQuery(name="TiposMedida.findAll", query="SELECT t FROM TiposMedida t")
public class TiposMedida implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="id_medida")
	private Short idMedida;

	private byte[] descripcion;

	private String nombre;

	//bi-directional many-to-one association to MedidaCliente
	@OneToMany(mappedBy="tiposMedida")
	private List<MedidaCliente> medidaClientes;

	public TiposMedida() {
	}

	public Short getIdMedida() {
		return this.idMedida;
	}

	public void setIdMedida(Short idMedida) {
		this.idMedida = idMedida;
	}

	public byte[] getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(byte[] descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<MedidaCliente> getMedidaClientes() {
		return this.medidaClientes;
	}

	public void setMedidaClientes(List<MedidaCliente> medidaClientes) {
		this.medidaClientes = medidaClientes;
	}

	public MedidaCliente addMedidaCliente(MedidaCliente medidaCliente) {
		getMedidaClientes().add(medidaCliente);
		medidaCliente.setTiposMedida(this);

		return medidaCliente;
	}

	public MedidaCliente removeMedidaCliente(MedidaCliente medidaCliente) {
		getMedidaClientes().remove(medidaCliente);
		medidaCliente.setTiposMedida(null);

		return medidaCliente;
	}

}