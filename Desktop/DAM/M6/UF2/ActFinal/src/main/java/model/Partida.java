package model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Partides")
public class Partida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdPartida")
    private int idPartida;

    @Column(name = "DataInici")
    private Date dataInici;

    @Column(name = "DataFi")
    private Date dataFi;

    @ManyToOne
    @JoinColumn(name = "IdGuanyador")
    private Jugador guanyador;

    @Column(name = "EnCurs")
    private boolean enCurs;

    @OneToMany(mappedBy = "partida")
    private Set<Fitxa> fitxes;

    @OneToMany(mappedBy = "partida")
    private Set<Casella> caselles;

    public Partida() {
    }

    public Partida(Date dataInici, boolean enCurs) {
        this.dataInici = dataInici;
        this.enCurs = enCurs;
    }

    public Partida(Date dataInici, Date dataFi, Jugador guanyador, boolean enCurs) {
        this.dataInici = dataInici;
        this.dataFi = dataFi;
        this.guanyador = guanyador;
        this.enCurs = enCurs;
    }

    public int getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(int idPartida) {
        this.idPartida = idPartida;
    }

    public Date getDataInici() {
        return dataInici;
    }

    public void setDataInici(Date dataInici) {
        this.dataInici = dataInici;
    }

    public Date getDataFi() {
        return dataFi;
    }

    public void setDataFi(Date dataFi) {
        this.dataFi = dataFi;
    }

    public Jugador getGuanyador() {
        return guanyador;
    }

    public void setGuanyador(Jugador guanyador) {
        this.guanyador = guanyador;
    }

    public boolean isEnCurs() {
        return enCurs;
    }

    public void setEnCurs(boolean enCurs) {
        this.enCurs = enCurs;
    }

    public Set<Fitxa> getFitxes() {
        return fitxes;
    }

    public void setFitxes(Set<Fitxa> fitxes) {
        this.fitxes = fitxes;
    }

    public Set<Casella> getCaselles() {
        return caselles;
    }

    public void setCaselles(Set<Casella> caselles) {
        this.caselles = caselles;
    }
}
