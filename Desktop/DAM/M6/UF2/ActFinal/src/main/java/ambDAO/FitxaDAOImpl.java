package ambDAO;

import java.util.ArrayList;
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
		fitxa.setPosicio(resultatDau);
		this.saveOrUpdate(fitxa);
		
	}

	@Override
	public void capturarFitxa(Fitxa fitxa) {
		fitxa.setActiva(false);
		fitxa.setPosicio(0);
		this.saveOrUpdate(fitxa);
		
	}

	@Override
	public void entradaFitxaTauler(Fitxa fitxa) {
		this.saveOrUpdate(fitxa);
		
	}

	@Override
	public boolean finalitzatRecorregut(Fitxa fitxa, int quantitat) {
		boolean meta=false;
		int posicio =fitxa.getPosicio();
		//posicio meta 76
		if(posicio==76) {
			meta= true;
		}
		return meta;
	}

	@Override
	public List<Fitxa> getAllFitxesByPosicio(int posicio, Partida partida) {
		List<Fitxa> fitxesPartida=this.getAllFitxesPartida(partida);
		List<Fitxa> fitxesPosicio = new ArrayList<>();
		for (Fitxa tip :fitxesPartida){
			if(tip.getPosicio()==posicio){
				fitxesPosicio.add(tip);
			}
		}
		return fitxesPosicio;
	}

}