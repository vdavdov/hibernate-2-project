package by.vdavdov.dao;

import by.vdavdov.entity.Store;
import org.hibernate.SessionFactory;

public class StoreDAO extends BaseDAO<Store> {
    public StoreDAO(SessionFactory sessionFactory) {
        super(Store.class, sessionFactory);
    }
}
