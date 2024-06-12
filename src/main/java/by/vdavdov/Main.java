package by.vdavdov;


import by.vdavdov.dao.*;
import by.vdavdov.entity.*;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.LocalDateTime;

@Log4j2
@Getter
public class Main {
    private final SessionFactory sessionFactory;
    private final ActorDAO actorDAO;
    private final AddressDAO addressDAO;
    private final CategoryDAO categoryDAO;
    private final CityDAO cityDAO;
    private final CountryDAO countryDAO;
    private final CustomerDAO customerDAO;
    private final FilmDAO filmDAO;
    private final FilmTextDAO filmTextDAO;
    private final InventoryDAO inventoryDAO;
    private final LanguageDAO languageDAO;
    private final PaymentDAO paymentDAO;
    private final RentalDAO rentalDAO;
    private final StaffDAO staffDAO;
    private final StoreDAO storeDAO;

    public Main() {
        sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Film.class)
                .addAnnotatedClass(Language.class)
                .addAnnotatedClass(Actor.class)
                .addAnnotatedClass(City.class)
                .addAnnotatedClass(Country.class)
                .addAnnotatedClass(Address.class)
                .addAnnotatedClass(Staff.class)
                .addAnnotatedClass(Store.class)
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Inventory.class)
                .addAnnotatedClass(Rental.class)
                .addAnnotatedClass(FilmText.class)
                .addAnnotatedClass(Payment.class)
                .addAnnotatedClass(Category.class)
                .buildSessionFactory();

        actorDAO = new ActorDAO(sessionFactory);
        addressDAO = new AddressDAO(sessionFactory);
        categoryDAO = new CategoryDAO(sessionFactory);
        cityDAO = new CityDAO(sessionFactory);
        countryDAO = new CountryDAO(sessionFactory);
        customerDAO = new CustomerDAO(sessionFactory);
        filmDAO = new FilmDAO(sessionFactory);
        filmTextDAO = new FilmTextDAO(sessionFactory);
        inventoryDAO = new InventoryDAO(sessionFactory);
        languageDAO = new LanguageDAO(sessionFactory);
        paymentDAO = new PaymentDAO(sessionFactory);
        rentalDAO = new RentalDAO(sessionFactory);
        staffDAO = new StaffDAO(sessionFactory);
        storeDAO = new StoreDAO(sessionFactory);

    }

    public static void main(String[] args) {
        Main main = new Main();
//        Customer customer = main.addNewCustomer();
//        System.out.println(customer);
        System.out.println(main.returnFilm());
    }

    public Customer addNewCustomer() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Store store = storeDAO.getById(1L);
            City city = cityDAO.getById(1L);

            Address address = new Address();

            address.setFirstAddress("Dzerzhinskogo 69");
            address.setPhone("+7 (987) - 947-89-12");
            address.setDistrict("Samara");
            address.setPostalCode("924822");
            address.setCity(city);
            addressDAO.save(address);

            Customer customer = new Customer();

            customer.setFirstName("Vsevolod");
            customer.setLastName("Vdavdov");
            customer.setAddress(address);
            customer.setStore(store);
            customer.setActive(true);
            customerDAO.save(customer);

            log.log(Level.INFO, "-----------New customer created with id {}", customer.getId() + "---------------");
            session.getTransaction().commit();
            return customer;
        } catch (Exception e) {
            log.log(Level.ERROR, "Couldn't add new customer with", e);
        }
        return null;
    }

    public Customer returnFilm() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Customer customer = customerDAO.findById(229L);
            Rental rental = rentalDAO.findById(13295L);

            rental.setReturnDate(LocalDateTime.now());
            rentalDAO.save(rental);

            log.log(Level.INFO, "-----------Customer {} successfully return film", customer.getId() + "---------------");
            session.getTransaction().commit();
            return customer;
        } catch (Exception e) {
            log.log(Level.ERROR, "Couldn't return film with", e);
        }
        return null;
    }
}
