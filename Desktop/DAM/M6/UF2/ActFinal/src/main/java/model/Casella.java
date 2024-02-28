package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "casella")
public class Casella {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "TipusCasella", nullable = false, length = 50)
	private String tipusCasella;
	// casa/casella/seguro/meta/pasadisMeta/Meta
	// tenim definir sortides de COlors i entrades de pasadisMeta;

	@Column(name = "Posicio")
	private int posicio;
	// caselles numeriques/ casa =0 /69-70-71-72-73-74-75 pasadis meta/ 76 Meta

	@ManyToOne
	@JoinColumn(name = "partida_id")
	private Partida partida;

	public Casella(Long id, String tipusCasella, int posicio, Partida partida) {
		super();
		this.id = id;
		this.tipusCasella = tipusCasella;
		this.posicio = posicio;
		this.partida = partida;
	}

	public Casella(String tipusCasella, int posicio) {
		super();
		this.tipusCasella = tipusCasella;
		this.posicio = posicio;
	}

	public Casella(String tipusCasella, int posicio, Partida partida) {
		super();
		this.tipusCasella = tipusCasella;
		this.posicio = posicio;
		this.partida = partida;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public boolean isSeguro() {
		switch (this.tipusCasella) {
		case "casa":
		case "seguro":
		case "pasadisMeta":
		case "meta":
			return true;
		default:
			return false;
		}

	}

}
