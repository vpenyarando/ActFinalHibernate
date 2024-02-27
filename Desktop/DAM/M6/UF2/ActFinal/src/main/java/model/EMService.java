package model;

import javax.persistence.*;

public class EMService {

    @PersistenceContext
    private EntityManager entityManager;

    public void realizarOperacion() {
        // Utilizar entityManager para interactuar con la base de datos
        // entityManager.persist(objeto); // Ejemplo de persistencia de un objeto
    }
}
