package InterfacesDAO;

import java.util.List;

import model.CasellaEspecial;

public interface CasellaEspecialDAO extends GenericDAO<CasellaEspecial, Integer> {

	int casellaSortida(String color);

	int casellaEntradaPasadisMeta(String color);

	CasellaEspecial getCasellaByColor(String color, String tipus);

	List<Integer> obtenerPosicionesCasillasEspecialesEnPartida(long idPartida);
}
