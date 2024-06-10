package by.vdavdov.dao;

import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

@Transactional
public class BaseDAO<T> {
    private final Class<T> clazz;
    private final SessionFactory sessionFactory;

    public BaseDAO(final Class<T> clazz, final SessionFactory sessionFactory) {
        this.clazz = clazz;
        this.sessionFactory = sessionFactory;
    }

    public T getById(final Long id) {
        return getCurrentSession().get(clazz, id);
    }

    public T save(final T entity) {
        getCurrentSession().persist(entity);
        return entity;
    }

    public void delete(final T entity) {
        getCurrentSession().remove(entity);
    }

    public List<T> getAll() {
        return getCurrentSession().createQuery("from" + clazz.getName(), clazz).list();
    }

    public T update(final T entity) {
        return getCurrentSession().merge(entity);
    }


    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }


}
