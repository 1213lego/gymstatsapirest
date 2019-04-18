package com.gymstatsapirest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipo_empleado database table.
 * 
 */
@Entity
@Table(name="tipo_empleado")
public class TipoEmpleado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_tipo")
	private Short idTipo;

	@Column(name="tipo_usuario")
	private String tipoUsuario;

	//bi-directional many-to-one association to Empleado
	/*@JsonIgnore
	@OneToMany(mappedBy="tipoEmpleado")
	private List<Empleado> empleados;*/

	public TipoEmpleado() {
	}

	public Short getIdTipo() {
		return this.idTipo;
	}

	public void setIdTipo(Short idTipo) {
		this.idTipo = idTipo;
	}

	public String getTipoUsuario() {
		return this.tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	/*public List<Empleado> getEmpleados() {
		return this.empleados;
	}

	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}

	public Empleado addEmpleado(Empleado empleado) {
		getEmpleados().add(empleado);
		empleado.setTipoEmpleado(this);

		return empleado;
	}

	public Empleado removeEmpleado(Empleado empleado) {
		getEmpleados().remove(empleado);
		empleado.setTipoEmpleado(null);

		return empleado;
	}*/

}
