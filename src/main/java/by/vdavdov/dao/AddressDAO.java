package by.vdavdov.dao;

import by.vdavdov.entity.Address;
import org.hibernate.SessionFactory;

public class AddressDAO extends BaseDAO<Address> {
    public AddressDAO(SessionFactory sessionFactory) {
        super(Address.class, sessionFactory);
    }
}
