package by.vdavdov.dao;

import by.vdavdov.entity.Language;
import org.hibernate.SessionFactory;

public class LanguageDAO extends BaseDAO<Language> {
    public LanguageDAO(SessionFactory sessionFactory) {
        super(Language.class, sessionFactory);
    }
}
