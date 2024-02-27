package InterfacesDAO;


import java.util.List;

import model.*;

public interface JugadorDAO extends GenericDAO<Jugador, Integer> {

		
	//llistat de fitxes en una partida
	List<Fitxa> llistaFitxesJugador(int id);

	boolean verificarVictoria(Jugador jugador);



	
	// falta gestionar en el futuro las elecciones que tenga el jugador
	// en situaciones determinadas de la partida

}
