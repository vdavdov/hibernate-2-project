package by.vdavdov.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public abstract class BaseDAO<T> {
    private final Class<T> clazz;
    private final SessionFactory sessionFactory;

    public BaseDAO(final Class<T> clazz, SessionFactory sessionFactory) {
        this.clazz = clazz;
        this.sessionFactory = sessionFactory;
    }

    public List<T> getItems(int offset, int limit) {
        final Session session = sessionFactory.getCurrentSession();
        Query<T> query = session.createQuery("from " + clazz.getSimpleName(), clazz);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.list();
    }

    public T findById(final Long id) {
        return getCurrentSession().find(clazz, id);
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

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

}
