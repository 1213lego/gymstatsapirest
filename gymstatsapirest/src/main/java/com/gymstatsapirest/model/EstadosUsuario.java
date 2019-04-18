package com.gymstatsapirest.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the estados_usuario database table.
 * 
 */
@Entity
@Table(name="estados_usuario")
public class EstadosUsuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_estado_usuario")
	@GeneratedValue
	private Short idEstadoUsuario;

	@Column(name="estado_cliente")
	private String estadoCliente;

	//bi-directional many-to-one association to Usuario
	/*@OneToMany(mappedBy="estadosUsuario")
	private List<Usuario> usuarios;*/

	public EstadosUsuario() {
	}

    public EstadosUsuario(String estadoCliente) {
	    this.estadoCliente=estadoCliente;
    }

    public Short getIdEstadoUsuario() {
		return this.idEstadoUsuario;
	}

	public void setIdEstadoUsuario(Short idEstadoUsuario) {
		this.idEstadoUsuario = idEstadoUsuario;
	}

	public String getEstadoCliente() {
		return this.estadoCliente;
	}

	public void setEstadoCliente(String estadoCliente) {
		this.estadoCliente = estadoCliente;
	}

	/*public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setEstadosUsuario(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setEstadosUsuario(null);

		return usuario;
	}*/

}
