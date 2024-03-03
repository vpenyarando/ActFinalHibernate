
import model.Fitxa;


public class MovimentsPossibles {
	
	private Fitxa fitxa;
	private int posicioFinal;
	public MovimentsPossibles(Fitxa fitxa, int posicioFinal) {
		super();
		this.fitxa = fitxa;
		this.posicioFinal = posicioFinal;
	}
	public Fitxa getFitxa() {
		return fitxa;
	}
	public void setFitxa(Fitxa fitxa) {
		this.fitxa = fitxa;
	}
	public int getPosicioFinal() {
		return posicioFinal;
	}
	public void setPosicioFinal(int posicioFinal) {
		this.posicioFinal = posicioFinal;
	}
	
	

}
