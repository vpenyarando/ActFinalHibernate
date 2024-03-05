package ambDAO;

import java.util.Collections;
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
		try (Session session = Utils.getSessionFactory().openSession()) {
			String hql = "SELECT f FROM Fitxa f WHERE f.jugador.id = :idJugador";
			Query<Fitxa> query = session.createQuery(hql, Fitxa.class);
			query.setParameter("idJugador", id);
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
}