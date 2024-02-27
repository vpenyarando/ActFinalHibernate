package ambDAO;

import model.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;


public class PartidaImp implements PartidaDAO {

    private SessionFactory sessionFactory;

    public PartidaImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public void iniciarPartida() {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            // Lógica para iniciar una nueva partida
            Partida nuevaPartida = new Partida();
            // Configura otras propiedades de la nueva partida si es necesario

            // Configura la posición inicial de las fichas de cada jugador
            List<Jugador> jugadores = obtenerTodosLosJugadores(); // Implementa este método según tu lógica
            for (Jugador jugador : jugadores) {
                for (int i = 0; i < 4; i++) {
                    Fitxa fitxa = new Fitxa();
                    fitxa.setJugador(jugador);
                    fitxa.setPosicio(0); // Posición inicial, ajusta según tu lógica
                    session.save(fitxa);
                }
            }

            // Configura otras condiciones iniciales o lógica de inicialización según tu juego

            // Guarda la nueva partida en la base de datos
            session.save(nuevaPartida);

            // Confirma la transacción
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Jugador> obtenerTodosLosJugadores() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Jugador", Jugador.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void guardarPartida(Partida partida) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(partida);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public Partida obtenerPartida(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Partida.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Partida> obtenerTodasLasPartidas() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Partida", Partida.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // de momento estos
}

