package ambDAO;

import java.util.List;

import org.hibernate.*;

import InterfacesDAO.JugadorDAO;
import model.*;


public class JugadorDAOImp extends GenericDAOImpl<Jugador,Integer> implements JugadorDAO {

	@Override
	public boolean verificarVictoria(Jugador jugador) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Fitxa> llistaFitxesJugador(int id) {
		// TODO Auto-generated method stub
		return null;
	}






}
