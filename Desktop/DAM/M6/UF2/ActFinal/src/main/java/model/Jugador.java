package model;

import javax.persistence.*;

@Entity
public class Jugador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String color;

    private int victories;
    
    public Jugador() {
    }

    public Jugador(String nom, String color, int victories) {
        this.nom = nom;
        this.color = color;
        this.victories = victories;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void incrementarVictories() {
        this.victories++;
    }

    @Override
    public String toString() {
        return "Jugador [id=" + id + ", nom=" + nom + ", color=" + color + ", victories=" + victories + "]";
    }
}


