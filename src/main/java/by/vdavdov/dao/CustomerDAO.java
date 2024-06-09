package by.vdavdov.dao;


import by.vdavdov.entity.Customer;
import org.hibernate.SessionFactory;

public class CustomerDAO extends BaseDAO<Customer> {

    public CustomerDAO(SessionFactory sessionFactory) {
        super(Customer.class, sessionFactory);
    }

}
