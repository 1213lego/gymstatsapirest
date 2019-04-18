package com.gymstatsapirest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;


/**
 * The persistent class for the empleados database table.
 * 
 */
@ApiModel(description = "Todos los detalles sobre los empleados")
@Entity
@Table(name="empleados")
public class Empleado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer documento;

	private Integer salario;

	//bi-directional many-to-one association to TipoEmpleado
	@ApiModelProperty(notes = "tipo de empleado" ,required = true)
	@ManyToOne
	@JoinColumn(name="id_tipo_empleado")
	private TipoEmpleado tipoEmpleado;

	//bi-directional one-to-one association to Usuario
	@JsonIgnore
	@OneToOne
	@JoinColumn(name="documento")
	private Usuario usuario;

	public Empleado() {
	}
	public Empleado(Integer documento)
	{
		this.documento=documento;
	}
	public Integer getDocumento() {
		return this.documento;
	}

	public void setDocumento(Integer documento) {
		this.documento = documento;
	}

	public Integer getSalario() {
		return this.salario;
	}

	public void setSalario(Integer salario) {
		this.salario = salario;
	}

	public TipoEmpleado getTipoEmpleado() {
		return this.tipoEmpleado;
	}

	public void setTipoEmpleado(TipoEmpleado tipoEmpleado) {
		this.tipoEmpleado = tipoEmpleado;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
