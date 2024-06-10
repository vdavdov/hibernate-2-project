package by.vdavdov.dao;

import by.vdavdov.entity.Address;
import by.vdavdov.entity.City;
import org.hibernate.SessionFactory;

public class CityDAO extends BaseDAO<City> {
    public CityDAO(SessionFactory sessionFactory) {
        super(City.class, sessionFactory);
    }
}
