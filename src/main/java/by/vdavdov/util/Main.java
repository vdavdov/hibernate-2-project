package by.vdavdov.util;

import by.vdavdov.constants.FeaturesEnum;
import by.vdavdov.constants.RatingEnum;
import by.vdavdov.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = MySessionFactory.getSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Country country = new Country();
            country.setName("Russia");
            session.persist(country);

            City city = new City();
            city.setCountry(country);
            city.setName("Togliatti");
            session.persist(city);

            Address address = new Address();
            address.setCity(city);
            address.setFirstAddress("Dzerchinskogo 69");
            address.setDistrict("Samarskaya");
            address.setPostalCode("90210");
            address.setPhone("+7(987)-947-89-62");
            session.persist(address);

            Store store = new Store();
            store.setAddress(address);
            session.persist(store);

            Customer customer = new Customer();
            customer.setFirstName("Vsevolod");
            customer.setLastName("Davydov");
            customer.setActive(true);
            customer.setEmail("vsevolod@gmail.com");
            customer.setAddress(address);
            customer.setStore(store);
            session.persist(customer);

            Staff staff = new Staff();
            staff.setStore(store);
            staff.setAddress(address);
            staff.setFirstName("Vsevolod");
            staff.setLastName("Davydov");
            staff.setEmail("sevadavydov.06z@mail.ru");
            staff.setPassword("sevva6363");
            staff.setUsername("vdavdov");
            staff.setActive(true);
            session.persist(staff);

            store.setStaff(staff);
            session.persist(store);

            Actor actor = new Actor();
            actor.setFirstName("Vsevolod");
            actor.setLastName("Davydov");
            session.persist(actor);

            Language language = new Language();
            language.setName("Russian");
            session.persist(language);

            Film film = new Film();
            film.setDescription("dad");
            film.setLanguage(language);
            film.setRating(RatingEnum.R);
            film.setTitle("BigBoss");
            film.setLength((short) 124);
            film.setRentalDuration(123);
            film.setReplacementCost(BigDecimal.valueOf(12.00));
            film.setRentalRate(BigDecimal.valueOf(4.00));
            film.setSpecialFeatures(FeaturesEnum.Behind_the_Scenes);
            session.persist(film);

            session.getTransaction().commit();
        } catch (Exception e) {
            sessionFactory.getCurrentSession().getTransaction().rollback();
        }
    }
}
