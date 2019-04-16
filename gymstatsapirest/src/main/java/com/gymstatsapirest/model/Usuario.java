package com.gymstatsapirest.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the usuarios database table.
 * 
 */
@Entity
@Table(name="usuarios")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer documento;

	private String apellidos;

	private Integer celular;

	private String direccion;

	private String email;

	private byte[] foto;

	private String nombres;

	//bi-directional many-to-one association to AsistenciasUsuario
	@OneToMany(mappedBy="usuario")
	private List<AsistenciasUsuario> asistenciasUsuarios;

	//bi-directional many-to-one association to AutenticacionUsuario
	@OneToMany(mappedBy="usuario")
	private List<AutenticacionUsuario> autenticacionUsuarios;

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
	@ManyToOne
	@JoinColumn(name="tipodocumento")
	private TipoDocumento tipoDocumento;

	//bi-directional many-to-one association to TipoUsuario
	@ManyToOne
	@JoinColumn(name="id_tipo_persona")
	private TipoUsuario tipoUsuario;

	public Usuario() {
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

	public List<AutenticacionUsuario> getAutenticacionUsuarios() {
		return this.autenticacionUsuarios;
	}

	public void setAutenticacionUsuarios(List<AutenticacionUsuario> autenticacionUsuarios) {
		this.autenticacionUsuarios = autenticacionUsuarios;
	}

	public AutenticacionUsuario addAutenticacionUsuario(AutenticacionUsuario autenticacionUsuario) {
		getAutenticacionUsuarios().add(autenticacionUsuario);
		autenticacionUsuario.setUsuario(this);

		return autenticacionUsuario;
	}

	public AutenticacionUsuario removeAutenticacionUsuario(AutenticacionUsuario autenticacionUsuario) {
		getAutenticacionUsuarios().remove(autenticacionUsuario);
		autenticacionUsuario.setUsuario(null);

		return autenticacionUsuario;
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