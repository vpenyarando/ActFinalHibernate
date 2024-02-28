package model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Partides")
public class Partida {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdPartida")
	private Long idPartida;

	@Column(name = "FechaInicio")
	private Date fechaInicio;

	@Column(name = "FechaFin")
	private Date fechaFin;

	@ManyToOne
	@JoinColumn(name = "IdGanador")
	private Jugador ganador;

	@Column(name = "EnCurso")
	private boolean enCurso;

	public Partida() {
		this.fechaInicio = new Date();
	}

	public Partida(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Long getIdPartida() {
		return idPartida;
	}

	public void setIdPartida(Long idPartida) {
		this.idPartida = idPartida;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Jugador getGanador() {
		return ganador;
	}

	public void setGanador(Jugador ganador) {
		this.ganador = ganador;
	}

	public boolean isEnCurso() {
		return enCurso;
	}

	public void setEnCurso(boolean enCurso) {
		this.enCurso = enCurso;
	}

	@Override
	public String toString() {
		return "Partida{" + "idPartida=" + idPartida + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin
				+ ", ganador=" + ganador + ", enCurso=" + enCurso + '}';
	}

}
