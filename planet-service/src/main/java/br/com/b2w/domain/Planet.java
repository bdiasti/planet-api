package br.com.b2w.domain;

import javax.validation.constraints.NotBlank;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mangofactory.swagger.annotations.ApiIgnore;
import com.wordnik.swagger.annotations.ApiModelProperty;



/**
 * @author bdiasti
 * 
 * Classe que define um planeta 
 *
 */
public class Planet{
	
	public ObjectId _id;
	
	@NotBlank(message = "nome é mandatório")
	private String nome;
	
	@NotBlank(message = "clima é mandatório")
	private String clima;
	
	@NotBlank(message = "terreno é mandatório")
	private String terreno;
	
	private int countFilms;
	

	public String get_id() {
		return _id.toHexString();
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getClima() {
		return clima;
	}

	public void setClima(String clima) {
		this.clima = clima;
	}

	public String getTerreno() {
		return terreno;
	}

	public void setTerreno(String terreno) {
		this.terreno = terreno;
	}

	public int getCountFilms() {
		return countFilms;
	}

	public void setCountFilms(int countFilms) {
		this.countFilms = countFilms;
	}
	

	

	
}
