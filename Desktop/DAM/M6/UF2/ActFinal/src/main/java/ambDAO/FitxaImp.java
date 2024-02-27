package ambDAO;

import org.hibernate.*;
import model.*;

import java.util.List;

public class FitxaImp implements FitxaDAO {

    private SessionFactory sessionFactory;

    public FitxaImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void guardarFitxa(Fitxa fitxa) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(fitxa);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public Fitxa obtenerFitxa(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Fitxa.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Fitxa> obtenerTodasLesFitxes() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Fitxa", Fitxa.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // de momento esto
}

