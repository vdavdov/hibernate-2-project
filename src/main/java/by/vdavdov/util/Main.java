package by.vdavdov.util;


import by.vdavdov.dao.*;
import by.vdavdov.entity.*;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.hibernate.SessionFactory;

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
        sessionFactory = MySessionFactory.getSessionFactory();

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

        Country country = new Country();
        country.setName("Russia");
        countryDAO.save(country);

        City city = new City();
        city.setCountry(country);
        city.setName("San Francisco");
        cityDAO.save(city);

        Address address = new Address();
        address.setCity(city);
        address.setFirstAddress("Dzerzhinskogo 69");
        address.setPostalCode("924822");
        address.setDistrict("Samara");
        address.setPhone("+7 (987) - 947-89-62");
        addressDAO.save(address);

        Store store = new Store();
        store.setAddress(address);
        store.setStaff(new Staff());
        storeDAO.save(store);

        System.out.println(addNewCustomer(true, address, store, "Vsevolod", "Davydov", "vsevolod@gmail.com"));

    }

    public static void main(String[] args) {
        Main main = new Main();
    }

    public String addNewCustomer(Boolean active, Address address, Store store, String firstName, String lastName, String email) {
        try {
            Customer customer = new Customer();
            customer.setActive(active);
            customer.setAddress(address);
            customer.setStore(store);
            customer.setFirstName(firstName);
            customer.setLastName(lastName);
            customer.setEmail(email);
            customerDAO.save(customer);
            log.log(Level.INFO, "New customer created with id {}", customer.getId());
            return "Add new customer with first name " + firstName + " and last name " + lastName + " and email " + email;
        } catch (Exception e) {
            log.log(Level.ERROR, "Couldn't add new customer with name: {}", firstName);
        }
        return "Couldn't add new customer with name: " + firstName;
    }

}
