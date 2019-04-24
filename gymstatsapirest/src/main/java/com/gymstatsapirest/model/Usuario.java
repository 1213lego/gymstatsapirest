package com.gymstatsapirest.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;


/**
 * The persistent class for the usuarios database table.
 * 
 */
@ApiModel(description = "Todos los detalles sobre los usuarios")
@Entity
@Table(name="usuarios")
public class Usuario implements Serializable
{
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(notes = "Documento del usuario" ,required = true)
	@NotNull
	@Id
	private Integer documento;

	@ApiModelProperty(notes = "apellidos del usuario" ,required = true)
	@NotBlank
	private String apellidos;

	@ApiModelProperty(notes = "Numero de celular del usuario" )
	private Integer celular;

	@ApiModelProperty(notes = "Direccion del usuario" )
	private String direccion;

	@ApiModelProperty(notes = "Correo del usuario",required = true)
	@NotBlank
	@Email
	private String email;

	@ApiModelProperty(notes = "foto del usuario" )
	private byte[] foto;

	@ApiModelProperty(notes = "nombres del usuario" ,required = true)
	@NotBlank
	private String nombres;

	//bi-directional many-to-one association to AsistenciasUsuario
	@ApiModelProperty(notes = "asistencias del usuario" )
	@OneToMany(mappedBy="usuario")
	private List<AsistenciasUsuario> asistenciasUsuarios;

	//bi-directional many-to-one association to AutenticacionUsuario
	@ApiModelProperty(notes = "datos para la autenticacion del usuario", required = true)
	@Valid
	@OneToOne(mappedBy="usuario")
	private AutenticacionUsuario autenticacionUsuarios;

	//bi-directional one-to-one association to Cliente
	@OneToOne(mappedBy="usuario")
	private Cliente cliente;

	//bi-directional one-to-one association to Empleado
	@OneToOne(mappedBy="usuario")
	private Empleado empleado;

	//bi-directional many-to-one association to EstadosUsuario
	@ManyToOne
	@JoinColumn(name="estado_persona")
	private EstadosUsuario estadosUsuario;

	//bi-directional many-to-one association to Genero
	@ManyToOne
	@JoinColumn(name="id_genero")
	private Genero genero;

	//bi-directional many-to-one association to Profesione
	@ManyToOne
	@JoinColumn(name="id_profesion")
	private Profesione profesione;

	//bi-directional many-to-one association to TipoDocumento
	@ApiModelProperty(notes = "Tipo de documento del usuario", required = true)
	@NotNull
	@Valid
	@ManyToOne
	@JoinColumn(name="tipodocumento")
	private TipoDocumento tipoDocumento;

	//bi-directional many-to-one association to TipoUsuario
	@ApiModelProperty(notes = "tipo de usuario")
	@ManyToOne
	@JoinColumn(name="id_tipo_persona")
	private TipoUsuario tipoUsuario;

	public Usuario() {
	}
	public Usuario(Integer documento) {
		this.documento=documento;
	}

	public Integer getDocumento() {
		return this.documento;
	}

	public void setDocumento(Integer documento) {
		this.documento = documento;
	}

	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Integer getCelular() {
		return this.celular;
	}

	public void setCelular(Integer celular) {
		this.celular = celular;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public byte[] getFoto() {
		return this.foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public List<AsistenciasUsuario> getAsistenciasUsuarios() {
		return this.asistenciasUsuarios;
	}

	public void setAsistenciasUsuarios(List<AsistenciasUsuario> asistenciasUsuarios) {
		this.asistenciasUsuarios = asistenciasUsuarios;
	}

	public AsistenciasUsuario addAsistenciasUsuario(AsistenciasUsuario asistenciasUsuario) {
		getAsistenciasUsuarios().add(asistenciasUsuario);
		asistenciasUsuario.setUsuario(this);

		return asistenciasUsuario;
	}

	public AsistenciasUsuario removeAsistenciasUsuario(AsistenciasUsuario asistenciasUsuario) {
		getAsistenciasUsuarios().remove(asistenciasUsuario);
		asistenciasUsuario.setUsuario(null);

		return asistenciasUsuario;
	}

	public AutenticacionUsuario getAutenticacionUsuarios() {
		return this.autenticacionUsuarios;
	}

	public void setAutenticacionUsuarios(AutenticacionUsuario autenticacionUsuarios) {
		this.autenticacionUsuarios = autenticacionUsuarios;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Empleado getEmpleado() {
		return this.empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public EstadosUsuario getEstadosUsuario() {
		return this.estadosUsuario;
	}

	public void setEstadosUsuario(EstadosUsuario estadosUsuario) {
		this.estadosUsuario = estadosUsuario;
	}

	public Genero getGenero() {
		return this.genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public Profesione getProfesione() {
		return this.profesione;
	}

	public void setProfesione(Profesione profesione) {
		this.profesione = profesione;
	}

	public TipoDocumento getTipoDocumento() {
		return this.tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public TipoUsuario getTipoUsuario() {
		return this.tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

}
