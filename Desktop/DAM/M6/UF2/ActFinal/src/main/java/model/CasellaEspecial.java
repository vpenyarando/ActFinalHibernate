package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "casellaEspecials")
public class CasellaEspecial extends Casella {



	public CasellaEspecial(Long id, String tipusCasella, int posicio, Partida partida) {
		super(id, tipusCasella, posicio, partida);
		// TODO Auto-generated constructor stub
	}

	// sortida/entrada
	@Column(name = "nom")
	private String nom;
	// colors jugadors les farem autogenerar
	@Column(name = "color")
	private String color;
	


	public CasellaEspecial(String tipusCasella, int posicio, Partida partida, String nom, String color) {
		super(tipusCasella, posicio, partida);
		this.nom = nom;
		this.color = color;
	}

	public CasellaEspecial(Long id, String tipusCasella, int posicio, Partida partida, String nom, String color) {
		super(id, tipusCasella, posicio, partida);
		this.nom = nom;
		this.color = color;
	}

	public CasellaEspecial(String tipusCasella, int posicio, String nom, String color) {
		super(tipusCasella, posicio);
		this.nom = nom;
		this.color = color;
	}

	public CasellaEspecial(String tipusCasella, int posicio, Partida partida) {
		super(tipusCasella, posicio, partida);
		// TODO Auto-generated constructor stub
	}

	

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	
}
