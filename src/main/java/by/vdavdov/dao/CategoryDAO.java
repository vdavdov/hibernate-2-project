package by.vdavdov.dao;

import by.vdavdov.entity.Category;
import org.hibernate.SessionFactory;

public class CategoryDAO extends BaseDAO<Category> {
    public CategoryDAO(SessionFactory sessionFactory) {
        super(Category.class, sessionFactory);
    }
}
