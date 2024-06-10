package by.vdavdov.dao;

import by.vdavdov.entity.Staff;
import org.hibernate.SessionFactory;

public class StaffDAO extends BaseDAO<Staff> {
    public StaffDAO(SessionFactory sessionFactory) {
        super(Staff.class, sessionFactory);
    }
}
