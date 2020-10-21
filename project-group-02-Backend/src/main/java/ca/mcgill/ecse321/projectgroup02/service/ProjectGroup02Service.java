package ca.mcgill.ecse321.projectgroup02.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
import ca.mcgill.ecse321.projectgroup02.model.ArtGallerySystem;
import ca.mcgill.ecse321.projectgroup02.model.Artist;
import ca.mcgill.ecse321.projectgroup02.model.Customer;
import ca.mcgill.ecse321.projectgroup02.model.DeliveryMethod;
import ca.mcgill.ecse321.projectgroup02.model.Item;
import ca.mcgill.ecse321.projectgroup02.model.ItemOrder;
import ca.mcgill.ecse321.projectgroup02.model.PaymentCredentials;
import ca.mcgill.ecse321.projectgroup02.model.ShoppingCart;
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

  // CONSTANT VARIABLES
  public final double commissionPercentage = 0.05;
  public final double taxePercentage = 0.15;
  
  /**
   * Registers user based on information inputed. Verifies the validity of the information inputed.
   * 
   * @author Ryad Ammar
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
   * @author Ryad Ammar
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
    if (applicationUserRepository.existsByUsername(username));
    
    throw new Exception("Username unavailable");
  }

  @Transactional
  public ApplicationUser getUser(String username) {
    return applicationUserRepository.findByUsername(username);
  }

  @Transactional
  public List<ApplicationUser> getAllUsers() {
    return toList(applicationUserRepository.findAll());
  }

  /**
   * Adds payment credentials information to a user. Verifies that the user is a customer.
   * 
   * @author Ryad Ammar
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
   * Sets the role(s) of a user based on the role(s) inputed.
   * If the role "customer" is assigned, assign a shopping cart to the customer.
   * Roles and shopping carts ID are generated based on the username.
   * 
   * @author Ryad Ammar
   * @param username
   * @param roles=
   */
  @Transactional
  public ApplicationUser updateUserRole(String username, String... roles) {
    ApplicationUser user = applicationUserRepository.findByUsername(username);
    HashSet<UserRole> roles_ = new HashSet<UserRole>();
    
    for (String role : roles) {
      if(role.equalsIgnoreCase("customer")) {
        Customer customer = new Customer();
        ShoppingCart shoppingCart = new ShoppingCart();
        
        shoppingCart.setShoppingCartId((username+"sc").hashCode()); //Generates the ID using hashCode encoding
        customer.setUserRoleId((username+"customer").hashCode());
        customer.setShoppingCart(shoppingCart);
        customer.setApplicationUser(user);
        
        shoppingCartRepository.save(shoppingCart);
        customerRepository.save(customer);
        
        roles_.add(customer);
      }
      
      if(role.equalsIgnoreCase("artist")) {
        Artist artist = new Artist();
        artist.setUserRoleId((username+"artist").hashCode());
        artist.setApplicationUser(user);
        
        artistRepository.save(artist);
        
        roles_.add(artist);
      }
    }

    user.setUserRole(roles_);
    applicationUserRepository.save(user);
    return user;
  }

  /**
   * @author Ryad Ammar
   * @param username
   */
  @Transactional
  public void logoutUser(String username) {
    ApplicationUser user = applicationUserRepository.findByUsername(username);
    user.setIsLoggedIn(false);

    applicationUserRepository.save(user);
  }

  /**
   * @author Ryad Ammar
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
   * @author Ryad Ammar
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
   * The user must be an artist.
   * The item's name must be unique in the artist's list of uploaded art.
   * The item's unique id is encoded based on its name and the artist's username.
   * 
   * @author Ryad Ammar
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
  public boolean UploadArtwork(String username, String name, double height, double width, double breadth, String creationDate, String description, double price, String imageUrl, String collection) throws Exception {
    Artist artist;
    
    try {
      artist = artistRepository.findByuserRoleId((username+"artist").hashCode());
    } catch(Exception e) {
      throw new Exception("User must be a customer");
    }
    
    for (Item item : artist.getItem()) {
      if (item.getName().equals(name))
        throw new Exception("Artist's items' name must be unique");
    }
    
    Item item = new Item();
    
    item.setItemId((username+name).hashCode()); // Generate the ID using hashCode encoding
    
    item.setName(name);
    item.setHeight(height);
    item.setWidth(width);
    item.setBreadth(breadth);
    item.setCreationDate(creationDate);
    item.setDescription(description);
    item.setPrice(price);
    item.setPathToImage(imageUrl);
    try {
    item.setCollection(collectionRepository.findByName(collection));
    }catch (Exception e) {
      throw new Exception("Collection+" +collection+" does not exist.");
    }
    
    artist.getItem().add(item);
    item.setArtist(artist);
    
    itemRepository.save(item);
    artistRepository.save(artist);
    
    return true;
  }
  
  // Does not support filtered & sorted item list queries yet
  
  /**
   * @author Ryad Ammar
   * @return all items
   */
  @Transactional
  public List<Item> getAllItems() {
    return toList(itemRepository.findAll());
  }
  
  /**
   * @author Ryad Ammar
   * @return all items filtered
   */
  @Transactional
  public List<Item> getAllItemsFiltered(String filter) {
    return Filter(itemRepository.findAll(), filter);
  }
  
  /**
   * @author Ryad Ammar
   * @return all items sorted
   */
  @Transactional
  public List<Item> getAllItemsSorted(String sort) {
    return Sort(itemRepository.findAll(), sort);
  }
  
  /**
   * Helper methods for getAllItemsFiltered & getAllItemsSorted
   * 
   * @author Ryad Ammar
   */
  private List<Item> Sort(Iterable<Item> items, String sort) {
    return null;
  }

  private List<Item> Filter(Iterable<Item> items, String filter) {
    return null;
  }

  /**
   * Retrieves user's shopping cart and adds an item.
   * User must be a customer.
   * 
   * @author Ryad Ammar
   * @param usernameOfClient
   * @param nameOfItem
   * @param usernameOfArtist
   * @throws Exception
   */
  @Transactional
  public boolean addToShoppingCart (String usernameOfClient, String nameOfItem, String usernameOfArtist) throws Exception {
    Item item = itemRepository.findItemByitemId((usernameOfArtist+nameOfItem).hashCode());
    ApplicationUser user = applicationUserRepository.findByUsername(usernameOfClient);
    Customer customer;
    
    try {
      customer = customerRepository.findCustomerByuserRoleId((usernameOfClient+"customer").hashCode());
    } catch(Exception e) {
      throw new Exception("User must be a customer");
    }
    
    customer.getShoppingCart().getItem().add(item);

    customerRepository.save(customer);
    applicationUserRepository.save(user);
    
    return true;
  }
  
  /**
   * Retrieves user's shopping cart and removes an item.
   * User must be a customer.
   * 
   * @author Ryad Ammar
   * @param usernameOfClient
   * @param nameOfItem
   * @param usernameOfArtist
   * @throws Exception
   */
  @Transactional
  public boolean removeFromShoppingCart (String usernameOfClient, String nameOfItem, String usernameOfArtist) throws Exception {
    Item item = itemRepository.findItemByitemId((usernameOfArtist+nameOfItem).hashCode());
    ApplicationUser user = applicationUserRepository.findByUsername(usernameOfClient);
    Customer customer;
    
    try {
      customer = customerRepository.findCustomerByuserRoleId((usernameOfClient+"customer").hashCode());
    } catch(Exception e) {
      throw new Exception("User must be a customer");
    }
    
    customer.getShoppingCart().getItem().remove(item);
    
    customerRepository.save(customer);
    applicationUserRepository.save(user);
    
    return true;
  }
  
  public List<Item> getItemsFromShoppingCart(String usernameOfClient) throws Exception{
    Customer customer;
    
    try {
      customer = customerRepository.findCustomerByuserRoleId((usernameOfClient+"customer").hashCode());
    } catch(Exception e) {
      throw new Exception("User must be a customer");
    }
     
    return toList(customer.getShoppingCart().getItem());
  }
  
  /**
   * Creates new order based on input information and customer's current shopping cart state.
   * Adds commissions to the system's total profit.
   * Adds to the artists' balance.
   * Removes from the customers total balance.
   * The customer must have enough balance.
   * The bought items are removed from the shop
   * 
   * @param username
   * @param deliveryMethod
   * @throws Exception
   * @return ItemOrder
   */
  @Transactional
  public ItemOrder checkout(String username, DeliveryMethod deliveryMethod) throws Exception {
    
    ArtGallerySystem artGallerySystem = null;
     
    for (ArtGallerySystem ags : artGallerySystemRepository.findAll())
      artGallerySystem = ags;
    
    if (artGallerySystem == null)
      throw new Exception("ArtGallerySystem is null");
   
    Customer customer;
    
    try {
      customer = customerRepository.findCustomerByuserRoleId((username+"customer").hashCode());
    } catch(Exception e) {
      throw new Exception("User must be a customer");
    }
    
    ItemOrder order = new ItemOrder();
    order.setCustomer(customer);
    order.setDelivery(deliveryMethod);
    order.setItemOrderDate(java.time.LocalDate.now().toString());
    order.setItemOrderId((username+"order").hashCode());
    
    double totalPrice = 0;
    
    for (Item item : customer.getShoppingCart().getItem())
      totalPrice += item.getPrice();
    
    if (customer.getApplicationUser().getBalance() < (1+taxePercentage)*totalPrice)
      throw new Exception("Insufisant funds");
    
    for (Item item : customer.getShoppingCart().getItem()) {
      addToBalance(item.getArtist().getApplicationUser(), (1-commissionPercentage) * item.getPrice());
      addToBalance(artGallerySystem, commissionPercentage * item.getPrice());
      
      order.getItem().add(item);
      itemRepository.delete(item); // Item is removed from the shop after its bought
    }
    
    addToBalance(customer.getApplicationUser(), (1+taxePercentage)*totalPrice);
   
    itemOrderRepository.save(order);
    
    return order;
  }
  
  /**
   * Helper methods for checkout. 
   * Adds value to user's/system's balance.
   * 
   * @author Ryad Ammar
   * @param applicationUser
   * @param value
   */
  public void addToBalance(ApplicationUser applicationUser, double value) {
    double balance = applicationUser.getBalance() + value;
    applicationUser.setBalance(balance);
    
    applicationUserRepository.save(applicationUser);
  }
  
  public void addToBalance(ArtGallerySystem system, double value) {
    double balance = system.getTotalProfit() + value;
    system.setTotalProfit(balance);
    
    artGallerySystemRepository.save(system);
  }
  
  //public add balance
  
  /**
   * Helper method.
   * Converts iterables to lists.
   * 
   * @author Ryad Ammar
   * @param <T>
   * @param iterable
   * @return list 
   */
  private <T> List<T> toList(Iterable<T> iterable) {
    List<T> resultList = new ArrayList<T>();
    for (T t : iterable) {
      resultList.add(t);
    }
    return resultList;
  }
}
