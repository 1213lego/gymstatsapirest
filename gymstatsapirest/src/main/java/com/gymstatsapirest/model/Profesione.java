package com.gymstatsapirest.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the profesiones database table.
 * 
 */
@Entity
@Table(name="profesiones")
@NamedQuery(name="Profesione.findAll", query="SELECT p FROM Profesione p")
public class Profesione implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="id_profesion")
	private Short idProfesion;

	private String profesion;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="profesione")
	private List<Usuario> usuarios;

	public Profesione() {
	}

	public Short getIdProfesion() {
		return this.idProfesion;
	}

	public void setIdProfesion(Short idProfesion) {
		this.idProfesion = idProfesion;
	}

	public String getProfesion() {
		return this.profesion;
	}

	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario addUsuario(Usuario usuario) {
		getUsuarios().add(usuario);
		usuario.setProfesione(this);

		return usuario;
	}

	public Usuario removeUsuario(Usuario usuario) {
		getUsuarios().remove(usuario);
		usuario.setProfesione(null);

		return usuario;
	}

}