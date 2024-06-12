package by.vdavdov.util;


import by.vdavdov.constants.LogConstants;
import by.vdavdov.dao.*;
import by.vdavdov.entity.*;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import by.vdavdov.constants.FeaturesEnum;
import by.vdavdov.constants.RatingEnum;
import org.apache.logging.log4j.Level;
import org.hibernate.Session;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Log4j2
@Getter
public class InitTestData {
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

    public InitTestData() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(Film.class);
        configuration.addAnnotatedClass(Language.class);
        configuration.addAnnotatedClass(Actor.class);
        configuration.addAnnotatedClass(City.class);
        configuration.addAnnotatedClass(Country.class);
        configuration.addAnnotatedClass(Address.class);
        configuration.addAnnotatedClass(Staff.class);
        configuration.addAnnotatedClass(Store.class);
        configuration.addAnnotatedClass(Customer.class);
        configuration.addAnnotatedClass(Inventory.class);
        configuration.addAnnotatedClass(Rental.class);
        configuration.addAnnotatedClass(FilmText.class);
        configuration.addAnnotatedClass(Payment.class);
        configuration.addAnnotatedClass(Category.class);
        sessionFactory = configuration.buildSessionFactory();

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

    public void init() {
        Customer customer = addNewCustomer();
        System.out.println(customer);
        rentalReturn();
        addNewRental();
        Film film = addNewFilm();
        System.out.println(film);
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

            log.log(Level.INFO, LogConstants.SUCCESSFUL_CREATE_CUSTOMER, customer.getId());
            session.getTransaction().commit();
            return customer;
        } catch (Exception e) {
            log.log(Level.ERROR, LogConstants.FAILED_CREATE_CUSTOMER, e);
        }
        return null;
    }

    public void rentalReturn() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Customer customer = customerDAO.findById(64L);
            Rental rental = rentalDAO.findById(13333L);

            rental.setReturnDate(LocalDateTime.now());
            rentalDAO.save(rental);

            log.log(Level.INFO, LogConstants.SUCCESSFUL_RETURN_RENTAL, customer.getId(), rental.getId());
            session.getTransaction().commit();
        } catch (Exception e) {
            log.log(Level.ERROR, LogConstants.FAILED_RETURN_RENTAL, e);
        }
    }

    public void addNewRental() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Store store = storeDAO.getById(1L);

            Staff staff = store.getStaff();
            Customer customer = customerDAO.findById(554L);
            Inventory inventory = new Inventory();
            inventory.setFilm(filmDAO.getFirstAvailableFilm());
            inventory.setStore(store);
            inventoryDAO.save(inventory);

            Rental rental = new Rental();
            rental.setStaff(staff);
            rental.setCustomer(customer);
            rental.setInventory(inventory);
            rental.setRentalDate(LocalDateTime.now());

            rentalDAO.save(rental);

            log.log(Level.INFO, LogConstants.SUCCESSFUL_TAKE_NEW_RENTAL, rental.getId());
            session.getTransaction().commit();
        } catch (Exception e) {
            log.log(Level.ERROR, LogConstants.FAILED_TAKE_NEW_RENTAL, e);
        }
    }

    public Film addNewFilm() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Language language = languageDAO.findById(1L);

            List<Category> categories = categoryDAO.getItems(0, 5);
            List<Actor> actors = actorDAO.getItems(0, 10);


            Film film = new Film();
            film.setTitle("Very new film");
            film.setDescription("It is so new film, u need to see this...");
            film.setYear(Year.now());
            film.setLanguage(language);
            film.setRentalDuration((byte) 9);
            film.setRentalRate(BigDecimal.valueOf(0.99));
            film.setLength((short) 120);
            film.setReplacementCost(BigDecimal.valueOf(30.99));
            film.setRating(RatingEnum.PG);
            film.setActors(new HashSet<>(actors));
            film.setCategories(new HashSet<>(categories));
            film.setSpecialFeatures(Set.of(FeaturesEnum.Commentaries, FeaturesEnum.Trailers, FeaturesEnum.Deleted_Scenes));
            filmDAO.save(film);

            FilmText filmText = new FilmText();
            filmText.setFilm(film);
            filmText.setDescription("welcome to very new film");
            filmText.setTitle("Very new film");
            filmTextDAO.save(filmText);


            session.getTransaction().commit();
            log.log(Level.INFO, LogConstants.SUCCESSFUL_CREATE_NEW_FILM, film.getId());
            return film;
        } catch (Exception e) {
            log.log(Level.ERROR, LogConstants.FAILED_CREATE_FILM, e);
        }
        return null;
    }
}
