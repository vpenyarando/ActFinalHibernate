package InterfacesDAO;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<T, ID extends Serializable> {
	// especificado en el operador Diamante
	void saveOrUpdate(T entity);

	// get devuelve el generico especificado
	T get(ID id);

	// delete le pasas la id
	void delete(ID id);

	// delete le pasas la entidad
	void delete(T entity);

	// puedes especificar que devuelva una lista de genericos
	List<T> list();

}
