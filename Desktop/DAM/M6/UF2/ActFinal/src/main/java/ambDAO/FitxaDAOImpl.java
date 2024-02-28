package ambDAO;

import java.util.Collections;
import java.util.List;

import org.hibernate.*;

import InterfacesDAO.FitxaDAO;
import model.Fitxa;
import model.Partida;

public class FitxaDAOImpl extends GenericDAOImpl<Fitxa,Integer> implements FitxaDAO {



	@Override
	public List<Fitxa> getAllFitxesPartida(Partida partida) {
		    try (Session session = Utils.getSessionFactory().openSession()) {
        String hql = "SELECT f FROM Fitxa f WHERE f.partida = :partida";
        Query<Fitxa> query = session.createQuery(hql, Fitxa.class);
        query.setParameter("partida", partida);
        return query.list();
    } catch (Exception e) {
        e.printStackTrace();
        return Collections.emptyList();
    }
	}

	@Override
	public void moureFitxa(Fitxa fitxa, int resultatDau) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void capturarFitxa(Fitxa fitxa) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void entradaFitxaTauler(Fitxa fitxa) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean finalitzatRecorregut(Fitxa fitxa, int quantitat) {
		// TODO Auto-generated method stub
		return false;
	}

}
