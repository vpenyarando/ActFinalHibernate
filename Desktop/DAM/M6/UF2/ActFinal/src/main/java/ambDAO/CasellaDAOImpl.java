package ambDAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;


import InterfacesDAO.CasellaDAO;
import model.Casella;
import model.Partida;

public class CasellaDAOImpl extends GenericDAOImpl<Casella,Integer> implements CasellaDAO {
	private EntityManager em;

	public CasellaDAOImpl(EntityManager em) {
		this.em = em;
	}
	public void add(Casella c) throws Exception {
		try {
			em.getTransaction().begin();
			em.persist(c);
			em.getTransaction().commit();
		} catch (EntityNotFoundException ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}
	public EntityManager getEm() {
		return em;
	}
	public void setEm(EntityManager em) {
		this.em = em;
	}
	@Override
	public boolean verificarCasaSegura(int posicion) {
		Casella casilla = get(posicion);
		  if (casilla != null && casilla.isSeguro()) {
		        return true;
		    }
		  return false;
	}


	/*@Override
	public boolean verificarCasaSegura(int posicion) {
		Casella casilla = get(posicion);
		  if (casilla != null && casilla.isSeguro()) {
		        return true;
		    }
		  return false;
	}*/


	
	
}
