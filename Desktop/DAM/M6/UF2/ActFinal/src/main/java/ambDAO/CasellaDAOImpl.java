package ambDAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import InterfacesDAO.CasellaDAO;
import model.Casella;

public class CasellaDAOImpl extends GenericDAOImpl<Casella, Integer> implements CasellaDAO {

	@Override
	public boolean verificarCasaSegura(int posicion) {
		Session session = Utils.getSessionFactory().getCurrentSession();

		try {
			session.beginTransaction();

			// HQL query para verificar si la casilla en la posición dada es casa o casilla
			// segura
			String hql = "SELECT c.tipusCasella FROM Casella c WHERE c.posicio = :posicio";
			String tipusCasella = (String) session.createQuery(hql).setParameter("posicio", posicion).uniqueResult();

			session.getTransaction().commit();

			// Verificar si la casilla es casa o casilla segura
			return tipusCasella != null && (tipusCasella.equals("casa") || tipusCasella.equals("seguro")
					|| tipusCasella.equals("pasadisMeta") || tipusCasella.equals("meta"));
		} catch (HibernateException e) {
			if (session != null && session.getTransaction() != null) {
				session.getTransaction().rollback();
			}
			e.printStackTrace();
			return false;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}



	/*
	 * @Override public boolean verificarCasaSegura(int posicion) { Casella casilla
	 * = get(posicion); if (casilla != null && casilla.isSeguro()) { return true; }
	 * return false; }
	 */

}
