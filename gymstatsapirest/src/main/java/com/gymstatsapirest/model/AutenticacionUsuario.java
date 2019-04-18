package com.gymstatsapirest.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


/**
 * The persistent class for the autenticacion_usuario database table.
 * 
 */
@ApiModel(description = "Datos para la autenticacion de un usuario")
@Entity
@Table(name="autenticacion_usuario")
public class AutenticacionUsuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(notes = "username debe tener como minino 4 caracteres" ,required = true)
	@Size(min = 4 ,message = "El username debe deber al menos 4 caraceres")
	@Id
	private String username;

	@ApiModelProperty(notes = "password debe tener como minino 4 caracteres"  ,required = true)
	@Size(min = 4,message = "La contraseña debe tener al menos 4 caracteres")
	private String password;

	//bi-directional many-to-one association to Usuario
	@JsonIgnore
	@OneToOne
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

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
