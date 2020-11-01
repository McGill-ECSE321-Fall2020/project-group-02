package ca.mcgill.ecse321.projectgroup02.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import ca.mcgill.ecse321.projectgroup02.model.Collection;
import ca.mcgill.ecse321.projectgroup02.model.Customer;
import ca.mcgill.ecse321.projectgroup02.model.DeliveryMethod;
import ca.mcgill.ecse321.projectgroup02.model.Item;
import ca.mcgill.ecse321.projectgroup02.model.ItemOrder;
import ca.mcgill.ecse321.projectgroup02.model.PaymentCredentials;
import ca.mcgill.ecse321.projectgroup02.model.ServiceProvider;
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
  public final double taxPercentage = 0.15;

  /**
   * Creates an art gallery system. Inputs an address and the administrator registration information.
   * 
   * @author Ryad Ammar
   * @throws Exception
   */
  @Transactional
  public ArtGallerySystem createGallery(String street, String postalCode, String province, String country, String city,
      String adminUsername, String adminPassword, String adminEmail) throws Exception {
    ArtGallerySystem gallery = new ArtGallerySystem();

    Address address = new Address();
    address.setCity(city);
    address.setCountry(country);
    address.setPostalCode(postalCode);
    address.setProvince(province);
    address.setStreet(street);
    gallery.setArtGalleryId(1);

    HashSet<ApplicationUser> users = new HashSet<ApplicationUser>();
    gallery.setApplicationUsers(users);

    addressRepository.save(address);
    artGallerySystemRepository.save(gallery);

    ApplicationUser admin = createAdmin(adminUsername, adminEmail, adminPassword);

    users.add(admin);
    HashSet<Item> items = new HashSet<Item>();

    gallery.setItem(items);
    gallery.setApplicationUsers(users);
    gallery.setAddress(address);

    applicationUserRepository.save(admin);
    artGallerySystemRepository.save(gallery);

    return gallery;
  }

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

      if (user_.getEmail().equals(email)) {
        throw new Exception("Email already in use");
      }

    }
    user.setBalance(0);
    user.setUsername(username);
    user.setEmail(email);
    user.setPassword(password);
    user.setAccountCreationDate(java.time.LocalDate.now().toString());
    user.setUserRole(new HashSet<UserRole>());
    user.setAddress(new HashSet<Address>());
    user.setIsLoggedIn(false);
    applicationUserRepository.save(user);

    getGallery().getApplicationUsers().add(user);
    artGallerySystemRepository.save(getGallery());

    return user;
  }
  
  /**
   * Returns the user from the Art Gallery System
   * @author Ryad Ammar
   * @param username
   * @return user
   */
  @Transactional
  public ApplicationUser getUser(String username) {
    return applicationUserRepository.findByUsername(username);
  }
  
  /**
   * Returns all the users from the Art Gallery System
   * @author Ryad Ammar
   * @return users
   */
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
    Customer customer;

    try {
      customer = customerRepository.findCustomerByuserRoleId((username + "customer").hashCode());
    } catch (Exception e) {
      throw new Exception("User must be a customer");
    }

    PaymentCredentials paymentCredentials = new PaymentCredentials();
    paymentCredentials.setCardHolderName(cardHolderName);
    paymentCredentials.setCcNumber(ccNumber);
    paymentCredentials.setCvc(cvc);
    paymentCredentials.setExpirationDate(expirationDate);

    HashSet<PaymentCredentials> paycreds = new HashSet<PaymentCredentials>();
    paycreds.add(paymentCredentials);
    customer.setPaymentCredentials(paycreds);

    paymentCredentialsRepository.save(paymentCredentials);
    customerRepository.save(customer);
    applicationUserRepository.save(user);
    return paymentCredentials;
  }

  /**
   * Sets the role(s) of a user based on the role(s) inputed. If the role "customer" is assigned, assign a shopping cart
   * to the customer. Roles and shopping carts ID are generated based on the username.
   * 
   * @author Ryad Ammar
   * @param username
   * @param roles=
   */
  @Transactional
  public ApplicationUser setUserRole(String username, String... roles) {
    ApplicationUser user = applicationUserRepository.findByUsername(username);
    HashSet<UserRole> roles_ = new HashSet<UserRole>();

    for (String role : roles) {
      if (role.equalsIgnoreCase("customer")) {
        Customer customer = new Customer();
        ShoppingCart shoppingCart = new ShoppingCart();

        shoppingCart.setShoppingCartId((username + "sc").hashCode()); // Generates the ID using hashCode
                                                                      // encoding
        shoppingCart.setItem(new HashSet<Item>());
        customer.setUserRoleId((username + "customer").hashCode());
        customer.setShoppingCart(shoppingCart);
        customer.setApplicationUser(user);
        shoppingCartRepository.save(shoppingCart);
        customerRepository.save(customer);

        roles_.add(customer);
      }

      if (role.equalsIgnoreCase("artist")) {
        Artist artist = new Artist();
        artist.setUserRoleId((username + "artist").hashCode());
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
   * Logs out the user.
   * 
   * @author Ryad Ammar
   * @param username
   */
  @Transactional
  public ApplicationUser logoutUser(String uname) {
    ApplicationUser user = applicationUserRepository.findByUsername(uname);
    user.setIsLoggedIn(false);

    applicationUserRepository.save(user);

    return user;
  }

  /**
   * Logs in the user.
   * 
   * @author Ryad Ammar
   * @param username
   * @param password
   */
  @Transactional
  public boolean loginUser(String uname, String password) {
    ApplicationUser user = applicationUserRepository.findByUsername(uname);
    if (user.getPassword().equals(password)) {
      user.setIsLoggedIn(true);
    }
    applicationUserRepository.save(user);
    return user.isIsLoggedIn();
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

    Set<Address> addresses = new HashSet<Address>();

    if (user.getAddress() == null) {

      addresses.add(address);
      user.setAddress(addresses);
    }

    else {
      user.getAddress().add(address);
    }

    addressRepository.save(address);
    applicationUserRepository.save(user);
    return address;
  }

  /**
   * Creates an item based on the input, assigns it to the user. The user must be an artist. The item's name must be
   * unique in the artist's list of uploaded arts. The item's unique id is encoded based on its name and the artist's
   * username.
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
  public Item uploadArtwork(String username, String artworkName, double height, double width, double breadth,
      String creationDate, String description, double price, String imageUrl, String collection) throws Exception {
    Artist artist;

    try {
      artist = artistRepository.findByuserRoleId((username + "artist").hashCode());
    } catch (Exception e) {
      throw new Exception("User must be a artist");
    }

    for (Item item : artist.getItem()) {
      if (item.getName().equals(artworkName))
        throw new Exception("Artist's items' name must be unique");
    }

    Item item = new Item();

    item.setItemId((username + artworkName).hashCode()); // Generate the ID using hashCode encoding

    item.setName(artworkName);
    item.setHeight(height);
    item.setWidth(width);
    item.setBreadth(breadth);
    item.setCreationDate(creationDate);
    item.setDescription(description);
    item.setPrice(price);
    item.setPathToImage(imageUrl);
    item.setInStock(true);
    try {
      item.setCollection(collectionRepository.findByName(collection));
      collectionRepository.findByName(collection).getItem().add(item);
    } catch (Exception e) {
      throw new Exception("Collection+" + collection + " does not exist.");
    }

    artist.getItem().add(item);
    item.setArtist(artist);
    item.setArtGallerySystem(getGallery());
    getGallery().getItem().add(item);

    collectionRepository.save(collectionRepository.findByName(collection));
    itemRepository.save(item);
    artistRepository.save(artist);
    artGallerySystemRepository.save(getGallery());

    return item;
  }


  /**
   * Deletes an item from the database. Can only be executed if the user is a service provider.
   * 
   * @author Ryad Ammar
   * @param username
   * @param nameOfItem
   * @param usernameOfArtist
   * @throws Exception
   */
  @Transactional
  public boolean deleteItemFromGallery(String username, String nameOfItem, String usernameOfArtist) throws Exception {
    ServiceProvider admin = serviceProviderRepository.findByuserRoleId((username + "admin").hashCode());

    if (admin == null)
      throw new Exception("User must be a service provider to manage invertory");

    Item item = itemRepository.findItemByitemId((usernameOfArtist + nameOfItem).hashCode());
    Set<Item> items = getGallery().getItem();

    for (Item i : items) {
      if (i.getName().equalsIgnoreCase(item.getName()) && i.getArtist().getApplicationUser().getUsername()
          .equalsIgnoreCase(item.getArtist().getApplicationUser().getUsername())) {
        items.remove(i);
        break;
      }
    }

    for (ApplicationUser u : applicationUserRepository.findAll()) {
      Customer c = customerRepository.findCustomerByuserRoleId((u.getUsername() + "customer").hashCode());
      if (c != null)
        for (Item i : c.getShoppingCart().getItem()) {
          if (i.getName().equalsIgnoreCase(nameOfItem)
              && i.getArtist().getApplicationUser().getUsername().equalsIgnoreCase(usernameOfArtist)) {
            c.getShoppingCart().getItem().remove(i);
          }
        }
    }

    for (ApplicationUser u : applicationUserRepository.findAll()) {
      Artist a = artistRepository.findByuserRoleId((u.getUsername() + "artist").hashCode());
      if (a != null)
        for (Item i : a.getItem()) {
          if (i.getName().equalsIgnoreCase(nameOfItem)
              && i.getArtist().getApplicationUser().getUsername().equalsIgnoreCase(usernameOfArtist)) {
            a.getItem().remove(i);
          }
        }
    }

    itemRepository.delete(item);
    artGallerySystemRepository.save(getGallery());

    for (Item i : items) {
      if (i.getName().equalsIgnoreCase(item.getName()) && i.getArtist().getApplicationUser().getUsername()
          .equalsIgnoreCase(item.getArtist().getApplicationUser().getUsername())) {
        return false;
      }
    }

    return true;
  }

  public List<Collection> getCollections() {
    return toList(collectionRepository.findAll());
  }

  /**
   * Creates a collection based on input.
   * 
   * @author Ryad Ammar
   * @param name
   * @param description
   * @param imageUrl
   * @return Collection
   */
  @Transactional
  public Collection createCollection(String collectionName, String description, String imageUrl) {
    Collection collection = new Collection();

    collection.setName(collectionName);
    collection.setDescription(description);
    collection.setPathToImage(imageUrl);
    collection.setItem(new HashSet<Item>());

    collectionRepository.save(collection);

    return collection;
  }

  /**
   * Retrieves user's shopping cart and adds an item. User must be a customer.
   * 
   * @author Ryad Ammar
   * @param usernameOfClient
   * @param nameOfItem
   * @param usernameOfArtist
   * @throws Exception
   */
  @Transactional
  public ShoppingCart addToShoppingCart(String usernameOfClient, String nameOfItem, String usernameOfArtist)
      throws Exception {
    Item item = itemRepository.findItemByitemId((usernameOfArtist + nameOfItem).hashCode());
    ApplicationUser user = applicationUserRepository.findByUsername(usernameOfClient);
    Customer customer;

    try {
      customer = customerRepository.findCustomerByuserRoleId((usernameOfClient + "customer").hashCode());
      customer.getClass();
    } catch (Exception e) {
      throw new Exception("User must be a customer");
    }

    customer.getShoppingCart().getItem().add(item);

    shoppingCartRepository.save(customer.getShoppingCart());
    customerRepository.save(customer);
    applicationUserRepository.save(user);

    return customer.getShoppingCart();
  }

  /**
   * Retrieves user's shopping cart and removes an item. User must be a customer.
   * 
   * @author Ryad Ammar
   * @param usernameOfClient
   * @param nameOfItem
   * @param usernameOfArtist
   * @throws Exception
   */
  @Transactional
  public ShoppingCart removeFromShoppingCart(String usernameOfClient, String nameOfItem, String usernameOfArtist)
      throws Exception {
    Item item = itemRepository.findItemByitemId((usernameOfArtist + nameOfItem).hashCode());
    ApplicationUser user = applicationUserRepository.findByUsername(usernameOfClient);
    Customer customer;

    try {
      customer = customerRepository.findCustomerByuserRoleId((usernameOfClient + "customer").hashCode());
      customer.getClass();
    } catch (Exception e) {
      throw new Exception("User must be a customer");
    }

    for (Item item2 : customer.getShoppingCart().getItem())
      if (item2.getItemId() == item.getItemId())
        customer.getShoppingCart().getItem().remove(item2);

    shoppingCartRepository.save(customer.getShoppingCart());
    customerRepository.save(customer);
    applicationUserRepository.save(user);

    return customer.getShoppingCart();
  }

  /**
   * Returns a list of items from a shoppingCart.
   * @author Ryad Ammar
   * @param usernameOfClient
   * @throws Exception
   */
  @Transactional
  public List<Item> getItemsFromShoppingCart(String usernameOfClient) throws Exception {
    Customer customer;

    try {
      customer = customerRepository.findCustomerByuserRoleId((usernameOfClient + "customer").hashCode());
      customer.getClass();
    } catch (Exception e) {
      throw new Exception("User must be a customer");
    }

    return toList(customer.getShoppingCart().getItem());
  }

  /**
   * Creates a new order based on input information and customer's current shopping cart state. Adds commissions and
   * taxes to the system's total profit. Adds amount to the artists' balance(s). Removes amount from the customers total
   * balance. The customer must have enough balance. The bought items are removed from the shop and shopping cart.
   * 
   * @author Ryad Ammar
   * @param username
   * @param deliveryMethod
   * @throws Exception
   * @return ItemOrder
   */
  @Transactional
  public ItemOrder checkout(String username, String deliveryMethod) throws Exception {
    ArtGallerySystem artGallerySystem = getGallery();

    Customer customer;

    try {
      customer = customerRepository.findCustomerByuserRoleId((username + "customer").hashCode());
      customer.getClass();
    } catch (Exception e) {
      throw new Exception("User must be a customer");
    }

    // determine the delivery method
    DeliveryMethod delivMethod = null;

    switch (deliveryMethod.toString()) {
      case "INSTOREPICKUP":
        delivMethod = DeliveryMethod.INSTOREPICKUP;
        break;

      case "HOMEDELIVERY":
        delivMethod = DeliveryMethod.HOMEDELIVERY;
        break;

      default:
        throw new Exception("This delivery method does not exist");

    }

    ItemOrder order = new ItemOrder();
    order.setCustomer(customer);
    order.setDelivery(delivMethod);
    order.setItemOrderDate(java.time.LocalDate.now().toString());
    order.setItemOrderId((username + "order").hashCode());
    order.setItem(new HashSet<Item>());

    double totalPrice = 0;

    for (Item item : customer.getShoppingCart().getItem()) {
      totalPrice += item.getPrice();
    }
    if (customer.getApplicationUser().getBalance() < (1 + taxPercentage) * totalPrice)
      throw new Exception("Insufficient funds");

    for (Item item : customer.getShoppingCart().getItem()) {
      removeFromShoppingCart(username, item.getName(), item.getArtist().getApplicationUser().getUsername());
      item.setInStock(false);
      addToBalance(item.getArtist().getApplicationUser(), (1 - commissionPercentage) * item.getPrice());
      addToBalance(artGallerySystem, commissionPercentage * item.getPrice());
      artistRepository.save(item.getArtist());
      applicationUserRepository.save(item.getArtist().getApplicationUser());
      order.getItem().add(item);
      itemRepository.save(item);
    }

    addToBalance(customer.getApplicationUser(), -(1 + taxPercentage) * totalPrice); // reduce customer's balance

    customerRepository.save(customer);
    applicationUserRepository.save(customer.getApplicationUser());
    itemOrderRepository.save(order);
    artGallerySystemRepository.save(artGallerySystem);

    return order;
  }

  /**
   * Sorts items by price in ascending order.
   * 
   * @author Gurdarshan Singh
   * @return items
   */
  public List<Item> sortByPriceAsc(String collection) {

    List<Item> items = filterByCollection(collection);
    sortPrice(items, 0, items.size() - 1);

    return items;
  }

  /**
   * Sorts items by price in descending order.
   * 
   * @author Gurdarshan Singh
   * @return items
   */
  public List<Item> sortByPriceDesc(String collection) {

    List<Item> items = filterByCollection(collection);
    sortPrice(items, 0, items.size() - 1);
    Collections.reverse(items);

    return items;
  }

  /**
   * Filters items by their collection.
   * 
   * @author Gurdarshan Singh
   * @return filteredItems
   */
  public List<Item> filterByCollection(String collection) {

    List<Item> items = toList(itemRepository.findAll());

    List<Item> filteredItems = new ArrayList<Item>();

    for (Item i : items) {

      if (i.getCollection().getName().equalsIgnoreCase(collection)) {
        filteredItems.add(i);
      }
    }

    return filteredItems;
  }

  /**
   * Filters items by their artist.
   * 
   * @author Gurdarshan Singh
   * @return filteredItems
   */
  public List<Item> filterByArtist(String artist, String collection) {

    List<Item> items = toList(filterByCollection(collection));

    List<Item> filteredItems = new ArrayList<Item>();

    for (Item i : items) {

      if (i.getArtist().getApplicationUser().getUsername().equalsIgnoreCase(artist)) {
        filteredItems.add(i);
      }
    }

    return filteredItems;
  }

  /**
   * Filters items by their price.
   * 
   * @author Gurdarshan Singh
   * @return filteredItems
   */
  public List<Item> filterByPrice(int p1, int p2, String collection) {

    List<Item> items = toList(filterByCollection(collection));

    List<Item> filteredItems = new ArrayList<Item>();

    for (Item i : items) {

      if (i.getPrice() >= p1 && i.getPrice() <= p2) {
        filteredItems.add(i);
      }
    }

    return filteredItems;
  }



  /**
   * Returns the total profit of the gallery system.
   * 
   * @author Ryad Ammar
   * @throws Exception
   */
  @Transactional
  public double getArtGalleryProfit() throws Exception {
    return getGallery().getTotalProfit();
  }

  /**
   * Returns the total profit of a user.
   * 
   * @author Ryad Ammar
   * @throws Exception
   */
  @Transactional
  public double getUserBalance(String username) {
    return applicationUserRepository.findByUsername(username).getBalance();
  }

  /**
   * Returns the total profit of a user.
   * 
   * @author Ryad Ammar
   * @throws Exception
   */
  @Transactional
  public ApplicationUser setUserBalance(String username, double value) {
    ApplicationUser au = applicationUserRepository.findByUsername(username);
    au.setBalance(value);
    applicationUserRepository.save(au);
    return au;
  }
  //////////////////////
  // HELPER METHODS/////
  //////////////////////
  /**
   * Helper method to assist creation of a user Throws an exception for any user input that is not valid during
   * registration.
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
    // if (!email.contains("@"))
    // throw new Exception("Email is not valid");
    if (password.length() < 8)
      throw new Exception("Password must have at least 8 characters");
    if (applicationUserRepository.existsByUsername(username))
      throw new Exception("Username unavailable");
  }

  /**
   * Helper methods for checkout. Adds value to user's/system's balance.
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

  /**
   * Helper method to returns the ArtGallerySystem of the database.
   * 
   * @author Ryad Ammar
   * @throws Exception
   */
  @Transactional
  public ArtGallerySystem getGallery() throws Exception {
    for (ArtGallerySystem ags : artGallerySystemRepository.findAll())
      return ags;
    throw new Exception("ArtGallery does not exist");
  }

  /**
   * Helper method to assist creation of gallery system. Creates an administrator.
   * 
   * @author Ryad Ammar
   * @param username
   * @param email
   * @param password
   * @throws Exception
   */
  public ApplicationUser createAdmin(String username, String email, String password) throws Exception {

    ApplicationUser admin = createUser(username, email, password);
    ServiceProvider serviceprovider = new ServiceProvider();
    HashSet<UserRole> adminRoles = new HashSet<UserRole>();

    serviceprovider.setApplicationUser(admin);
    serviceprovider.setUserRoleId((username + "admin").hashCode());
    adminRoles.add(serviceprovider);
    admin.setUserRole(adminRoles);

    serviceProviderRepository.save(serviceprovider);
    applicationUserRepository.save(admin);

    return admin;
  }

  /**
   * Helper method. Converts iterables to lists.
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


  private int partitionPrice(List<Item> items, int low, int high) {
    Item pivot = items.get(high);
    int i = (low - 1); // index of smaller element
    for (int j = low; j < high; j++) {
      // If current element is smaller than the pivot
      if (items.get(j).getPrice() < pivot.getPrice()) {
        i++;

        // swap arr[i] and arr[j]
        Item temp = items.get(i);
        items.set(i, items.get(j));
        items.set(j, temp);
      }
    }

    // swap arr[i+1] and arr[high] (or pivot)
    Item temp = items.get(i + 1);
    items.set(i + 1, items.get(high));
    items.set(high, temp);

    return i + 1;
  }

  /**
   * Sorts items in ascending order according to their price. Implementation of QuickSort.
   * 
   * @author Gurdarshan Singh
   * @param items
   * @param low
   * @param high
   */
  private void sortPrice(List<Item> items, int low, int high) {
    if (low < high) {

      int pi = partitionPrice(items, low, high);


      sortPrice(items, low, pi - 1);
      sortPrice(items, pi + 1, high);
    }
  }



}
