package com.gymstatsapirest.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the maquina_rutinas database table.
 * 
 */
@Entity
@Table(name="maquina_rutinas")
public class MaquinaRutina implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;

	//bi-directional many-to-one association to Maquina
	@ManyToOne
	@JoinColumn(name="maquinasid")
	private Maquina maquina;

	//bi-directional many-to-one association to Rutina
	@ManyToOne
	@JoinColumn(name="rutinasid")
	private Rutina rutina;

	public MaquinaRutina() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Maquina getMaquina() {
		return this.maquina;
	}

	public void setMaquina(Maquina maquina) {
		this.maquina = maquina;
	}

	public Rutina getRutina() {
		return this.rutina;
	}

	public void setRutina(Rutina rutina) {
		this.rutina = rutina;
	}

}
