package by.vdavdov.util;

import by.vdavdov.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

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

            session.getTransaction().commit();
        }
    }
}
