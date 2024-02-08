package ambDAO;

import org.hibernate.*;
import model.*;
import java.util.List;

public class JugadorImp implements JugadorDAO {

    private SessionFactory sf;

    public JugadorImp(SessionFactory sessionFactory) {
        this.sf = sessionFactory;
    }

    @Override
    public void guardarJugador(Jugador jugador) {
        Transaction t = null;
        try (Session s = sf.openSession()) {
            t = s.beginTransaction();
            s.save(jugador);
            t.commit();
        } catch (Exception e) {
            if (t != null) {
                t.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public Jugador obtenerJugador(Long id) {
        try (Session s = sf.openSession()) {
            return s.get(Jugador.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Jugador> obtenerTodosLosJugadores() {
        try (Session s = sf.openSession()) {
            return s.createQuery("FROM Jugador", Jugador.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

