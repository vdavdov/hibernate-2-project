package by.vdavdov.dao;

import by.vdavdov.entity.Rental;
import org.hibernate.SessionFactory;

public class RentalDAO extends BaseDAO<Rental> {
    public RentalDAO(SessionFactory sessionFactory) {
        super(Rental.class, sessionFactory);
    }
}
