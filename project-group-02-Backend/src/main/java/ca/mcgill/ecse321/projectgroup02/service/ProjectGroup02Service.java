package ca.mcgill.ecse321.projectgroup02.service;

import java.util.HashSet;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ca.mcgill.ecse321.projectgroup02.dao.AddressRepository;
import ca.mcgill.ecse321.projectgroup02.dao.ApplicationUserRepository;
import ca.mcgill.ecse321.projectgroup02.dao.ArtGallerySystemRepository;
import ca.mcgill.ecse321.projectgroup02.dao.ArtistRepository;
import ca.mcgill.ecse321.projectgroup02.dao.CollectionRepository;
import ca.mcgill.ecse321.projectgroup02.dao.CustomerRepository;
import ca.mcgill.ecse321.projectgroup02.dao.ItemOrderRepository;
import ca.mcgill.ecse321.projectgroup02.dao.ItemRepository;
import ca.mcgill.ecse321.projectgroup02.dao.PaymentCredentialsRepository;
import ca.mcgill.ecse321.projectgroup02.dao.ServiceProviderRepository;
import ca.mcgill.ecse321.projectgroup02.dao.ShoppingCartRepository;
import ca.mcgill.ecse321.projectgroup02.model.Address;
import ca.mcgill.ecse321.projectgroup02.model.ApplicationUser;
import ca.mcgill.ecse321.projectgroup02.model.Artist;
import ca.mcgill.ecse321.projectgroup02.model.Collection;
import ca.mcgill.ecse321.projectgroup02.model.Customer;
import ca.mcgill.ecse321.projectgroup02.model.Item;
import ca.mcgill.ecse321.projectgroup02.model.PaymentCredentials;
import ca.mcgill.ecse321.projectgroup02.model.UserRole;

@Service
public class ProjectGroup02Service {
  @Autowired
  private ArtGallerySystemRepository artGallerySystemRepository;
  @Autowired
  private ApplicationUserRepository applicationUserRepository;
  @Autowired
  private AddressRepository addressRepository;
  @Autowired
  private ArtistRepository artistRepository;
  @Autowired
  private ItemRepository itemRepository;
  @Autowired
  private CollectionRepository collectionRepository;
  @Autowired
  private CustomerRepository customerRepository;
  @Autowired
  private ItemOrderRepository itemOrderRepository;
  @Autowired
  private PaymentCredentialsRepository paymentCredentialsRepository;
  @Autowired
  private ServiceProviderRepository serviceProviderRepository;
  @Autowired
  private ShoppingCartRepository shoppingCartRepository;

  /**
   * Registers user based on information inputed. Verifies the validity of the information inputed.
   * 
   * @author ryadammar1
   * @param username
   * @param email
   * @param password
   * @throws Exception
   */
  @Transactional
  public ApplicationUser createUser(String username, String email, String password) throws Exception {

    try {
      validateRegistrationInput(username, email, password);
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }

    ApplicationUser user = new ApplicationUser();
    Iterable<ApplicationUser> users = applicationUserRepository.findAll();
    for (ApplicationUser user_ : users) {
      if (user_.getEmail().equals(email) || user_.getUsername().equals(username)) {
        throw new Exception("Username/Email already in use");
      }
    }
    user.setUsername(username);
    user.setEmail(email);
    user.setPassword(password);
    user.setAccountCreationDate(java.time.LocalDate.now().toString());
    applicationUserRepository.save(user);
    
    return user;
  }

  /**
   * Throws an exception for any user input that is not valid during registration.
   * 
   * @author ryadammar1
   * @param username
   * @param email
   * @param password
   * @throws Exception
   */
  private void validateRegistrationInput(String username, String email, String password) throws Exception {
    if (username.length() < 8)
      throw new Exception("Username must have at least 8 characters");
    if (!email.contains("@"))
      throw new Exception("Email is not valid");
    if (password.length() < 8)
      throw new Exception("Password must have at least 8 characters");
    if (applicationUserRepository.existsByUsername(username))
      ;
    throw new Exception("Username unavailable");
  }

  @Transactional
  public ApplicationUser getUser(String username) {
    return applicationUserRepository.findByUsername(username);
  }

  @Transactional
  public Iterable<ApplicationUser> getAllUsers() {
    return (applicationUserRepository.findAll());
  }

  /**
   * Adds payment credentials information to a user. Verifies that the user is a customer.
   * 
   * @author ryadammar1
   * @param username
   * @param cardHolderName
   * @param ccNumber
   * @param expirationDate
   * @param cvc
   * @throws Exception
   */
  @Transactional
  public PaymentCredentials updateUserCredentials(String username, String cardHolderName, String ccNumber,
      String expirationDate, String cvc) throws Exception {
    ApplicationUser user = applicationUserRepository.findByUsername(username);
    Customer customer = null;

    for (UserRole role : user.getUserRole()) {
      if (role instanceof Customer)
        customer = (Customer) role;
    }

    if (customer == null)
      throw new Exception("User must be a customer");

    PaymentCredentials paymentCredentials = new PaymentCredentials();
    paymentCredentials.setCardHolderName(cardHolderName);
    paymentCredentials.setCcNumber(ccNumber);
    paymentCredentials.setCvc(cvc);
    paymentCredentials.setExpirationDate(expirationDate);

    customer.getPaymentCredentials().add(paymentCredentials);

    paymentCredentialsRepository.save(paymentCredentials);
    customerRepository.save(customer);
    applicationUserRepository.save(user);
    return paymentCredentials;
  }

  /**
   * Sets the role(s) of a user based on the role(s) inputed
   * 
   * @author ryadammar1
   * @param username
   * @param roles=
   */
  @Transactional
  public ApplicationUser updateUserRole(String username, UserRole... roles) {
    ApplicationUser user = applicationUserRepository.findByUsername(username);
    HashSet<UserRole> roles_ = new HashSet<UserRole>();
    for (UserRole role : roles)
      roles_.add(role);

    user.setUserRole(roles_);
    applicationUserRepository.save(user);
    return user;
  }

  /**
   * @author ryadammar1
   * @param username
   */
  @Transactional
  public void logoutUser(String username) {
    ApplicationUser user = applicationUserRepository.findByUsername(username);
    user.setIsLoggedIn(false);

    applicationUserRepository.save(user);
  }

  /**
   * @author ryadammar1
   * @param username
   * @param password
   */
  @Transactional
  public boolean loginUser(String username, String password) {
    ApplicationUser user = applicationUserRepository.findByUsername(username);
    if (user.getPassword().equals(password)) {
      user.setIsLoggedIn(true);
      applicationUserRepository.save(user);
      return true;
    }
    return false;
  }

  /**
   * Adds address information to a user.
   * 
   * @author ryadammar1
   * @param username
   * @param street
   * @param postalCode
   * @param province
   * @param country
   * @param city
   * @throws Exception
   */
  @Transactional
  public Address updateUserAddress(String username, String street, String postalCode, String province, String country,
      String city) throws Exception {
    ApplicationUser user = applicationUserRepository.findByUsername(username);
    Address address = new Address();
    address.setCity(city);
    address.setCountry(country);
    address.setPostalCode(postalCode);
    address.setProvince(province);
    address.setStreet(street);
    user.getAddress().add(address);

    addressRepository.save(address);
    applicationUserRepository.save(user);
    return address;
  }

  /**
   * Creates an item based on the input, assigns it to the user.
   * The use must be an artist.
   * The item's name must be unique in the artist's list of uploaded art.
   * The item's unique id is encoded based on its name and the artist's username.
   * 
   * @param username
   * @param name
   * @param height
   * @param width
   * @param breadth
   * @param creationDate
   * @param description
   * @param price
   * @param link
   * @param collection
   * @throws Exception
   */
  @Transactional
  public void UploadArtwork(String username, String name, double height, double width, double breadth, String creationDate, String description, double price, String link, Collection collection) throws Exception {
    ApplicationUser user = applicationUserRepository.findByUsername(username);
    Artist artist = null;
    for (UserRole role : user.getUserRole()) {
      if (role instanceof Artist)
        artist = (Artist) role;
    }

    if (artist == null)
      throw new Exception("User must be an artist");
    
    for (Item item : artist.getItem()) {
      if (item.getName().equals(name))
        throw new Exception("Artist's items' name must be unique");
    }
    
    Item item = new Item();
    
    String id = username+name;
    item.setItemId(id.hashCode()); // Encoding the id
    
    item.setName(name);
    item.setHeight(height);
    item.setWidth(width);
    item.setBreadth(breadth);
    item.setCreationDate(creationDate);
    item.setDescription(description);
    item.setPrice(price);
    item.setPathToImage(link);
    item.setCollection(collection);
    
    artist.getItem().add(item);
    item.setArtist(artist);
    
    itemRepository.save(item);
    artistRepository.save(artist);
    
  }

}
