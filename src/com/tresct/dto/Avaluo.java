package com.tresct.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="Avaluo")
public class Avaluo {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idAvaluo;
	private String lugarAvaluo;
	
	//La clase / Tabla que contiene la llave foranea de otra, debera implementar las siguientes anotaciones:
	@OneToOne
	@JoinColumn(name="Tramite_idTramite")
	private Tramite tramite;

	
	
	public Avaluo(String lugarAvaluo) {		
		this.lugarAvaluo = lugarAvaluo;		
	}
	
	

	public Avaluo() {
	}



	public int getIdAvaluo() {
		return idAvaluo;
	}

	public void setIdAvaluo(int idAvaluo) {
		this.idAvaluo = idAvaluo;
	}

	public String getLugarAvaluo() {
		return lugarAvaluo;
	}

	public void setLugarAvaluo(String lugarAvaluo) {
		this.lugarAvaluo = lugarAvaluo;
	}

	public Tramite getTramite() {
		return tramite;
	}

	public void setTramite(Tramite tramite) {
		this.tramite = tramite;
	}



	@Override
	public String toString() {
		return "Avaluo [idAvaluo=" + idAvaluo + ", lugarAvaluo=" + lugarAvaluo + ", tramite=" + tramite + "]";
	}
	
}
