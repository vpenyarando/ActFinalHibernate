package ambDAO;

import org.hibernate.*;
import model.*;

import java.util.List;

public class CasellaImp implements CasellaDAO {

    private SessionFactory sessionFactory;

    public CasellaImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void guardarCasella(Casella casella) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(casella);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public Casella obtenerCasella(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Casella.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Casella> obtenerTodasLasCaselles() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Casella", Casella.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

