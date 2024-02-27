package model;

import javax.persistence.*;

@Entity
@Table(name = "Fitxes")
public class Fitxa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdFitxa")
    private Long idFitxa;

    @Column(name = "Posicio")
    private int posicio;

    @Column(name = "Activa")
    private boolean activa;

    @ManyToOne
    @JoinColumn(name = "IdJugador")
    private Jugador jugador;

    @ManyToOne
    @JoinColumn(name = "IdPartida")
    private Partida partida;

    public Fitxa() {
    }

    public Fitxa(int posicio, boolean activa, Jugador jugador, Partida partida) {
        this.posicio = posicio;
        this.activa = activa;
        this.jugador = jugador;
        this.partida = partida;
    }

    public Long getIdFitxa() {
        return idFitxa;
    }

    public void setIdFitxa(Long idFitxa) {
        this.idFitxa = idFitxa;
    }

    public int getPosicio() {
        return posicio;
    }

    public void setPosicio(int posicio) {
        this.posicio = posicio;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }
}
