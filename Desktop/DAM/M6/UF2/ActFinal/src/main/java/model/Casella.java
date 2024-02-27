package model;

import javax.persistence.*;

@Entity
@Table(name = "Caselles")
public class Casella {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdCasella")
    private Long idCasella;

    @Column(name = "TipusCasella")
    private String tipusCasella;

    @Column(name = "Posicio")
    private int posicio;

    @ManyToOne
    @JoinColumn(name = "IdPartida")
    private Partida partida;

    public Casella() {
    }

    public Long getIdCasella() {
        return idCasella;
    }

    public void setIdCasella(Long idCasella) {
        this.idCasella = idCasella;
    }

    public String getTipusCasella() {
        return tipusCasella;
    }

    public void setTipusCasella(String tipusCasella) {
        this.tipusCasella = tipusCasella;
    }

    public int getPosicio() {
        return posicio;
    }

    public void setPosicio(int posicio) {
        this.posicio = posicio;
    }

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }
}
