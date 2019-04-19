package com.gymstatsapirest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the maquinas database table.
 * 
 */
@ApiModel("Detalles de una maquina")
@Entity
@Table(name="maquinas")
public class Maquina implements Serializable {
	private static final long serialVersionUID = 1L;

    @ApiModelProperty(name = "identificador de la maquina generado por la base de datos")
	@Id
	@GeneratedValue
	private Integer id;

    @ApiModelProperty(name = "descripcion de la maquina",required = true)
    @NotBlank
	private String descripcion;
    @ApiModelProperty(name = "fecha de compra de la maquina",required = true)
    @NotNull
    @PastOrPresent
    @Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_compra")
	private Date fechaCompra;

    @ApiModelProperty(name = "marca de la maquina",required = true)
    @NotBlank
	private String marca;
    @ApiModelProperty(name = "nombre de la maquina",required = true)
    @NotBlank
	private String nombre;

	//bi-directional many-to-one association to MaquinaRutina
	@JsonIgnore
	@OneToMany(mappedBy="maquina")
	private List<MaquinaRutina> maquinaRutinas;

	//bi-directional many-to-one association to EstadosMaquina
    @ApiModelProperty(name = "estado de la maquina",required = true)
    @Valid
    @NotNull
	@ManyToOne
	@JoinColumn(name="estado_maquina")
	private EstadosMaquina estadosMaquina;

	public Maquina() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaCompra() {
		return this.fechaCompra;
	}

	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public String getMarca() {
		return this.marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<MaquinaRutina> getMaquinaRutinas() {
		return this.maquinaRutinas;
	}

	public void setMaquinaRutinas(List<MaquinaRutina> maquinaRutinas) {
		this.maquinaRutinas = maquinaRutinas;
	}

	public MaquinaRutina addMaquinaRutina(MaquinaRutina maquinaRutina) {
		getMaquinaRutinas().add(maquinaRutina);
		maquinaRutina.setMaquina(this);

		return maquinaRutina;
	}

	public MaquinaRutina removeMaquinaRutina(MaquinaRutina maquinaRutina) {
		getMaquinaRutinas().remove(maquinaRutina);
		maquinaRutina.setMaquina(null);

		return maquinaRutina;
	}

	public EstadosMaquina getEstadosMaquina() {
		return this.estadosMaquina;
	}

	public void setEstadosMaquina(EstadosMaquina estadosMaquina) {
		this.estadosMaquina = estadosMaquina;
	}

}
