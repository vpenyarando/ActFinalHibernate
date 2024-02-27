package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "casellaEspecials")
public class CasellaEspecial extends Casella {



	public CasellaEspecial(Long id, String tipusCasella, int posicio, Partida partida) {
		super(id, tipusCasella, posicio, partida);
		// TODO Auto-generated constructor stub
	}

	// sortida/entrada
	@Column(name = "nom", nullable = false, length = 50)
	private String nom;
	// colors jugadors les farem autogenerar
	@Column(name = "color", nullable = false, length = 50)
	private String color;
	




	

	public CasellaEspecial(Long id, String tipusCasella, int posicio, Partida partida, String nom, String color) {
		super(id, tipusCasella, posicio, partida);
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
