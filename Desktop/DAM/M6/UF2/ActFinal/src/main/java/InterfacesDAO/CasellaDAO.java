package InterfacesDAO;

import model.Casella;

public interface CasellaDAO extends GenericDAO<Casella, Integer> {
	
	boolean verificarCasaSegura(int posicio);

}
