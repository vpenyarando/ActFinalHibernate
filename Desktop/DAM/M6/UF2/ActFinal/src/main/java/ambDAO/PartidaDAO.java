package ambDAO;

import java.util.List;
import model.*;

public interface PartidaDAO {

	void iniciarPartida();
	
    void guardarPartida(Partida partida);

    Partida obtenerPartida(Long id);

    List<Partida> obtenerTodasLasPartidas();
    
    List<Jugador> obtenerTodosLosJugadores();

    // de momento esto
}

