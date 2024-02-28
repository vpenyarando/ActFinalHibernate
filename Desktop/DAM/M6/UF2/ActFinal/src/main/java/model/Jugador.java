package model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Jugadors")
public class Jugador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdJugador")
    private Long idJugador;

    @Column(name = "Nom")
    private String nom;

    @Column(name = "Color")
    private String color;

    @Column(name = "Victories")
    private int victories;

    @OneToMany(mappedBy = "jugador")
    private Set<Fitxa> fitxes;

    @OneToMany(mappedBy = "guanyador")
    private Set<Partida> partidesGuanyades;

    public Jugador() {
    }

    public Jugador(String nom, String color, int victories) {
		this.nom = nom;
		this.color = color;
		this.victories = victories;
	}
    
    public Long getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(Long idJugador) {
        this.idJugador = idJugador;
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

    public int getVictories() {
        return victories;
    }

    public void setVictories(int victories) {
        this.victories = victories;
    }

    public Set<Fitxa> getFitxes() {
        return fitxes;
    }

    public void setFitxes(Set<Fitxa> fitxes) {
        this.fitxes = fitxes;
    }

    public Set<Partida> getPartidesGuanyades() {
        return partidesGuanyades;
    }

    public void setPartidesGuanyades(Set<Partida> partidesGuanyades) {
        this.partidesGuanyades = partidesGuanyades;
    }
}