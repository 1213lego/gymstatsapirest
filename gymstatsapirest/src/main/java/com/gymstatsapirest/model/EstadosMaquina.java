package com.gymstatsapirest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;


/**
 * The persistent class for the estados_maquina database table.
 * 
 */
@ApiModel("Detalles del estado de una maquina")
@Entity
@Table(name="estados_maquina")
public class EstadosMaquina implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(name = "identificador del estado generado por la base de datos",required = true)
	@NotNull
	@Id
	@GeneratedValue
	@Column(name="id_estado_maquina")
	private Short idEstadoMaquina;

	@ApiModelProperty(name = "descripcion del estado actual de la maquina", required = true)
	@NotEmpty
	@Column(name="estado_maquina")
	private String estadoMaquina;

	//bi-directional many-to-one association to Maquina
	@JsonIgnore
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
