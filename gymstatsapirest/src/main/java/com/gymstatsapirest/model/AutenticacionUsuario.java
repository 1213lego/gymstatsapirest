package com.gymstatsapirest.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the autenticacion_usuario database table.
 * 
 */
@Entity
@Table(name="autenticacion_usuario")
@NamedQuery(name="AutenticacionUsuario.findAll", query="SELECT a FROM AutenticacionUsuario a")
public class AutenticacionUsuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String username;

	private byte[] password;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="documento_usuario")
	private Usuario usuario;

	public AutenticacionUsuario() {
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public byte[] getPassword() {
		return this.password;
	}

	public void setPassword(byte[] password) {
		this.password = password;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}