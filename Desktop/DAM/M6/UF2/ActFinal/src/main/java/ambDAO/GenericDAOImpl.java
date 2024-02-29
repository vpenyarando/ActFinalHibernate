package ambDAO;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;


import java.lang.reflect.ParameterizedType;


import javax.persistence.*;


import org.hibernate.*;

import InterfacesDAO.GenericDAO;

public class GenericDAOImpl <T, ID extends Serializable> implements GenericDAO<T, ID> {
	SessionFactory sessionFactory;

	// private final static Logger LOGGER =
	// Logger.getLogger(GenericDao.class.getName());

	public GenericDAOImpl() {
		sessionFactory = Utils.getSessionFactory();
	}

	@Override
	public void saveOrUpdate(T entity) {
		// TODO Auto-generated method stub

		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(entity);
			session.getTransaction().

		} catch (HibernateException e) {
			e.printStackTrace();
			if (session != null && session.getTransaction() != null) {
				System.out.println("\n.......Transaction Is Being Rolled Back.......");
				session.getTransaction().rollback();
			}
			e.printStackTrace();

		}
	}

	@Override
	public T get(ID id) {
		// TODO Auto-generated method stub

		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			T entity = (T) session.get(getEntityClass(), id);
			session.getTransaction().commit();

			return entity;
		} catch (HibernateException e) {
			e.printStackTrace();
			if (session != null && session.getTransaction() != null) {
				System.out.println("\n.......Transaction Is Being Rolled Back.......");
				session.getTransaction().rollback();
			}
			e.printStackTrace();
			return null;

		}

	}

	private Class<T> getEntityClass() {
		return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public void delete(Serializable id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			T entity = (T) session.get(getEntityClass(), id);
			session.delete(entity);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (session != null && session.getTransaction() != null) {
				System.out.println("\n.......Transaction Is Being Rolled Back.......");
				session.getTransaction().rollback();
			}
			e.printStackTrace();

		}
	}

	public void delete(Object entity) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			session.delete(entity);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (session != null && session.getTransaction() != null) {
				System.out.println("\n.......Transaction Is Being Rolled Back.......");
				session.getTransaction().rollback();
			}
			e.printStackTrace();

		}
	}

	public List<T> list() {
		Session session = sessionFactory.getCurrentSession();
		// TODO Auto-generated method stubSession session =
		// sessionFactory.getCurrentSession();
		try {

			List<T> entities = session.createQuery("SELECT e FROM " + getEntityClass().getName() + " e").list();

			return entities;
		} catch (HibernateException e) {
			e.printStackTrace();
			if (session != null && session.getTransaction() != null) {
				System.out.println("\n.......Transaction Is Being Rolled Back.......");
				session.getTransaction().rollback();
			}
			e.printStackTrace();
			return null;

		}
	}

}