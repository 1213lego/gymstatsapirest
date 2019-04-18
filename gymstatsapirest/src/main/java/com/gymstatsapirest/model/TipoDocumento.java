package com.gymstatsapirest.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipo_documento database table.
 * 
 */
@Entity
@Table(name="tipo_documento")
public class TipoDocumento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Short tipodocumento;

	@Column(name="nombre_documento")
	private String nombreDocumento;

	//bi-directional many-to-one association to Usuario
	/*@OneToMany(mappedBy="tipoDocumento")
	private List<Usuario> usuarios;*/

	public TipoDocumento() {
	}

	public Short getTipodocumento() {
		return this.tipodocumento;
	}

	public void setTipodocumento(Short tipodocumento) {
		this.tipodocumento = tipodocumento;
	}

	public String getNombreDocumento() {
		return this.nombreDocumento;
	}

	public void setNombreDocumento(String nombreDocumento) {
		this.nombreDocumento = nombreDocumento;
	}

	/*public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setTipoDocumento(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setTipoDocumento(null);

		return usuario;
	}*/

}
