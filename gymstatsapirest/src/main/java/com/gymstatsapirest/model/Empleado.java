package com.gymstatsapirest.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the empleados database table.
 * 
 */
@Entity
@Table(name="empleados")
@NamedQuery(name="Empleado.findAll", query="SELECT e FROM Empleado e")
public class Empleado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer documento;

	private Integer salario;

	//bi-directional many-to-one association to TipoEmpleado
	@ManyToOne
	@JoinColumn(name="id_tipo_empleado")
	private TipoEmpleado tipoEmpleado;

	//bi-directional one-to-one association to Usuario
	@OneToOne
	@JoinColumn(name="documento")
	private Usuario usuario;

	public Empleado() {
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