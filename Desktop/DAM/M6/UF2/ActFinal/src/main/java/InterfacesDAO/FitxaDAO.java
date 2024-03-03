package InterfacesDAO;

import java.util.List;

import model.Fitxa;
import model.Partida;

public interface FitxaDAO extends GenericDAO<Fitxa, Integer> {


	

	List<Fitxa> getAllFitxesPartida(Partida partida);

	void moureFitxa(Fitxa fitxa, int resultatDau);

	void capturarFitxa(Fitxa fitxa);

	void entradaFitxaTauler(Fitxa fitxa);

	boolean finalitzatRecorregut(Fitxa fitxa, int quantitat);
	List<Fitxa> getAllFitxesByPosicio(int posicio, Partida partida);

}
