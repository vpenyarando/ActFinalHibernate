package ambDAO;

import java.util.List;

import InterfacesDAO.FitxaDAO;
import model.Fitxa;
import model.Partida;

public class FitxaDAOImpl extends GenericDAOImpl<Fitxa,Integer> implements FitxaDAO {



	@Override
	public List<Fitxa> getAllFitxesPartida(Partida partida) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void moureFitxa(Fitxa fitxa, int resultatDau) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void capturarFitxa(Fitxa fitxa) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void entradaFitxaTauler(Fitxa fitxa) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean finalitzatRecorregut(Fitxa fitxa, int quantitat) {
		// TODO Auto-generated method stub
		return false;
	}

}
