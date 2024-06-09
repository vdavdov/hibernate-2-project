package by.vdavdov.util;

import by.vdavdov.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MySessionFactory {
    private final SessionFactory sessionFactory;
    private static MySessionFactory instance;

    private MySessionFactory() {
        sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Language.class)
                .addAnnotatedClass(Actor.class)
                .addAnnotatedClass(City.class)
                .addAnnotatedClass(Country.class)
                .addAnnotatedClass(Address.class)
                .addAnnotatedClass(Staff.class)
                .addAnnotatedClass(Store.class)
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Film.class)
                .addAnnotatedClass(Inventory.class)
                .addAnnotatedClass(Rental.class)
                .addAnnotatedClass(FilmText.class)
                .addAnnotatedClass(Payment.class)
                .addAnnotatedClass(Category.class)
                .buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        if (instance == null) {
            instance = new MySessionFactory();
        }
        return instance.sessionFactory;
    }
}
