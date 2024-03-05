package InterfacesDAO;

import model.Partida;

public interface PartidaDAO extends GenericDAO<Partida, Integer> {
	void iniciarPartida();
}
