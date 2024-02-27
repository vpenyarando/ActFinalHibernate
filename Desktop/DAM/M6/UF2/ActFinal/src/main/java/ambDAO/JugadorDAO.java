package ambDAO;

import model.*;

public interface JugadorDAO {

    void guardarJugador(Jugador jugador);

    Jugador obtenerJugador(Long id);
    
    //falta gestionar en el futuro las elecciones que tenga el jugador
    //en situaciones determinadas de la partida
    
}









