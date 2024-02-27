package ambDAO;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import InterfacesDAO.CasellaEspecialDAO;

import model.CasellaEspecial;

public class CasellaEspecialImp extends GenericDAOImpl<CasellaEspecial, Integer> implements CasellaEspecialDAO {

	@Override
	public int casellaSortida(String color) {
		String tipus = "sortida";
		CasellaEspecial casella = getCasellaByColor(color, tipus);
		// Si la casilla es de tipo "pasadisMeta", devolver su posición
		if (casella != null && casella.getTipusCasella().equals(tipus)) {
			return casella.getPosicio();
		}
		// Si no hay casilla de entrada al pasadizo de meta para ese color, devolver -1
		return -1;
	}

	@Override
	public int casellaEntradaPasadisMeta(String color) {
		String tipus = "entradaMeta";
		// Recuperar la casilla actual asociada al color dado
		CasellaEspecial casella = getCasellaByColor(color, tipus);
		// Si la casilla es de tipo "pasadisMeta", devolver su posición
		if (casella != null && casella.getTipusCasella().equals(tipus)) {
			return casella.getPosicio();
		}
		// Si no hay casilla de entrada al pasadizo de meta para ese color, devolver -1
		return -1;
	}

	@Override
	public CasellaEspecial getCasellaByColor(String color, String tipus) {
		Session session = Utils.getSessionFactory().getCurrentSession();
	        try {
	            session.beginTransaction();
	            // Realizar la consulta para obtener la casilla con el color y tipo dados
	            Query<CasellaEspecial> query = session.createQuery("FROM CasellaEspecial WHERE color = :color AND tipusCasella = :tipus", CasellaEspecial.class);
	            query.setParameter("color", color);
	            query.setParameter("tipus", tipus);
	            CasellaEspecial casella = query.uniqueResult();
	            session.getTransaction().commit();
	            return casella;
	        } catch (HibernateException e) {
	            e.printStackTrace();
	            if (session != null && session.getTransaction() != null) {
	                System.out.println("\n.......Transaction Is Being Rolled Back.......");
	                session.getTransaction().rollback();
	            }
	            return null;
	        }
	}

}
