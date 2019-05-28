package com.gymstatsapirest.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 * The persistent class for the medida_clientes database table.
 * 
 */
@Entity
@Table(name="medida_clientes")
public class MedidaCliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name="fecha_toma_medida")
	private Date fechaTomaMedida;

	@Column(name="valor_medida")
	private Integer valorMedida;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="clientesdocumento")
	private Cliente cliente;

	//bi-directional many-to-one association to TiposMedida
	@ManyToOne
	@JoinColumn(name="tipos_medidaid_medida")
	private TiposMedida tiposMedida;

	public MedidaCliente() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFechaTomaMedida() {
		return this.fechaTomaMedida;
	}

	public void setFechaTomaMedida(Date fechaTomaMedida) {
		this.fechaTomaMedida = fechaTomaMedida;
	}

	public Integer getValorMedida() {
		return this.valorMedida;
	}

	public void setValorMedida(Integer valorMedida) {
		this.valorMedida = valorMedida;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public TiposMedida getTiposMedida() {
		return this.tiposMedida;
	}

	public void setTiposMedida(TiposMedida tiposMedida) {
		this.tiposMedida = tiposMedida;
	}

}
