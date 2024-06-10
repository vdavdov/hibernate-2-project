package by.vdavdov.dao;

import by.vdavdov.entity.Actor;
import org.hibernate.SessionFactory;

public class ActorDAO  extends BaseDAO<Actor> {
    public ActorDAO(SessionFactory sessionFactory) {
        super(Actor.class, sessionFactory);
    }
}
