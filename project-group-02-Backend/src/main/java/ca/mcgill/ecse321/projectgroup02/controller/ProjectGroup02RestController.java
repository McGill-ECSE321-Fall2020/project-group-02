package ca.mcgill.ecse321.projectgroup02.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import ca.mcgill.ecse321.projectgroup02.dto.AddressDTO;
import ca.mcgill.ecse321.projectgroup02.dto.ApplicationUserDTO;
import ca.mcgill.ecse321.projectgroup02.dto.ArtGallerySystemDTO;
import ca.mcgill.ecse321.projectgroup02.dto.ArtistDTO;
import ca.mcgill.ecse321.projectgroup02.dto.CollectionDTO;
import ca.mcgill.ecse321.projectgroup02.dto.CustomerDTO;
import ca.mcgill.ecse321.projectgroup02.dto.DeliveryMethodDTO;
import ca.mcgill.ecse321.projectgroup02.dto.ItemDTO;
import ca.mcgill.ecse321.projectgroup02.dto.ItemOrderDTO;
import ca.mcgill.ecse321.projectgroup02.dto.PaymentCredentialsDTO;
import ca.mcgill.ecse321.projectgroup02.dto.ShoppingCartDTO;
import ca.mcgill.ecse321.projectgroup02.dto.UserRoleDTO;
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
import ca.mcgill.ecse321.projectgroup02.model.ShoppingCart;
import ca.mcgill.ecse321.projectgroup02.model.UserRole;
import ca.mcgill.ecse321.projectgroup02.service.ProjectGroup02Service;


@CrossOrigin(origins = "*")
@RestController
public class ProjectGroup02RestController {

  private ArtGallerySystemDTO agsDTO;

  @Autowired
  private ProjectGroup02Service service;

  /**
   * Creates an Art Gallery System 
   * @author ahmad
   * 
   * @param street
   * @param postalCode
   * @param province
   * @param country
   * @param city
   * @param adminUsername
   * @param adminPassword
   * @param adminEmail
   * @return agsDTO
   * @throws Exception
   */
  @PostMapping(value = {
      "/art-gallery-system",
      "/art-gallery-system/"})

  public ArtGallerySystemDTO createArtGallery(@RequestParam("street") String street,
		  @RequestParam("pc") String postalCode, @RequestParam("province") String province,
		  @RequestParam("country") String country, @RequestParam("city") String city,
		  @RequestParam("adminUsername") String adminUsername, @RequestParam("adminPassword") String adminPassword,
		  @RequestParam("adminEmail") String adminEmail) throws Exception {
    ArtGallerySystem ags =
        service.createGallery(street, postalCode, province, country, city, adminUsername, adminPassword, adminEmail);
    
    agsDTO = convertToDto(ags);

    return convertToDto(ags);
  }
  
  /**
   * Gets an Art Galery System
   * @author ahmad
   * @return agsDTO
   * @throws Exception
   */
  @GetMapping(value = {"/art-gallery-system", "/art-gallery-system/"})
  public ArtGallerySystemDTO getArtGallerySystem() throws Exception {
	 return convertToDto(service.getGallery());
  }

  /**
   * Creates an application user
   *
   * @author ahmad
   * @param username
   * @param email
   * @param password
   * @return userDTO
   * @throws Exception
   */
  @PostMapping(value = {"/create-user", "/create-user/"})
  public ApplicationUserDTO createUser(@RequestParam("name") String username, @RequestParam("email") String email,
		  @RequestParam("pw") String password) throws Exception {
    ApplicationUser user = service.createUser(username, email, password);
    convertToDto(service.getGallery()).getApplicationUsers().add(convertToDto(user));
    return convertToDto(user);
  }
  
  /**
   * Gets all the users from the Art Gallery System
   * 
   * @author ahmad
   * @return list of all userDTO
   */
  @GetMapping(value = {"/users", "/users/"})
  public List<ApplicationUserDTO> getAllUsers() {
    return service.getAllUsers().stream().map(u -> convertToDto(u)).collect(Collectors.toList());
  }
  
  /**
   * Returns the user from the Art Gallery
   * @author ahmad
   * @param username
   * @return userDTO
   */
  @GetMapping(value = {"/user-by-name/{username}", "/user-by-name/{username}/"})
  public ApplicationUserDTO getUserByUsername(@PathVariable("username") String username) {

    return convertToDto(service.getUser(username));
  }

  /**
   * Sets a user role of the user
   * 
   * @author ahmad
   * @param username
   * @param roles
   * @return userDTO
   */
  @PostMapping(value = {"/set-user-role/{username}", "/set-user-role/{username}/"})
  public ApplicationUserDTO setUserRole(@PathVariable("username") String username,
      @RequestParam("roles") String... roles) {
    return convertToDto(service.setUserRole(username, roles));
  }

  /**
   * Updates the payment credentials of a user
   * 
   * @author ahmad
   * @param name
   * @param cName
   * @param ccNumber
   * @param eDate
   * @param cvc
   * @return payementCredentialDTO
   * @throws Exception
   */
  @PostMapping(value = {"/update-payment-credentials",
      "/update-payment-credentials/"})
  public PaymentCredentialsDTO updateUserCredentials(@RequestParam("username") String name,
      @RequestParam("cardname") String cName, @RequestParam("ccnumber") String ccNumber,
      @RequestParam("expdate") String eDate, @RequestParam("cvc") String cvc) throws Exception {
    return convertToDto(service.updateUserCredentials(name, cName, ccNumber, eDate, cvc));
  }

  /**
   * Updates the address of the user
   * 
   * @author ahmad
   * @param username
   * @param street
   * @param postalcode
   * @param province
   * @param country
   * @param city
   * @return AddressDTO
   * @throws Exception
   */
  @PostMapping(value = {"/update-user-address/{username}",
      "/update-user-address//{username}/"})
  public AddressDTO updateUserAddress(@PathVariable("username") String username, @RequestParam("street") String street,
		  @RequestParam("postalcode") String postalcode, @RequestParam("province") String province,
		  @RequestParam("country") String country, @RequestParam("city") String city) throws Exception {
    return convertToDto(service.updateUserAddress(username, street, postalcode, province, country, city));
  }
  
  /**
   * Logs in a user
   * @author ahmad
   * @param username
   * @param pw
   * @return boolean
   */
  @PostMapping(value = {"/user-login", "/user-login/"})
  public boolean loginUser(@RequestParam("username") String username, @RequestParam("password") String pw) {
    return service.loginUser(username, pw);
  }
  
  /**
   * Logs out a user
   * 
   * @author Ahmad
   * @param username
   * @return boolean
   */
  @PostMapping(value = {"/user-logout/{username}", "/user-logout/{username}/"})
  public boolean logoutUser(@PathVariable("username") String username) {
    return service.logoutUser(username).isIsLoggedIn();
  }
  
  /**
   * Sets the balance of a given user
   * 
   * @author Vadim, Gurdashan, Ahmad
   * @param username
   * @param amount
   * @return ApplicationUserDTO
   * @throws Exception
   */
  @PostMapping(value = {"/set-balance/{username}/{amount}", "/set-balance/{username}/{amount}/"})
  public ApplicationUserDTO setUserBalance(@PathVariable("username") String username,
      @PathVariable("amount") Double amount) throws Exception {
    return convertToDto(service.setUserBalance(username, amount));
  }
  
  /*
   * Creates a collection
   * 
   * @author Vadim, Gurdashan, Ahmad
   * @return CollectionDTO
   */
  @PostMapping(value = {"/create-collection/{collectionName}", "/create-collection/{collectionName}/"})
  public CollectionDTO createCollection(@PathVariable("collectionName") String collectionName,
      @RequestParam("collectionDescription") String collectionDescription,
      @RequestParam("collectionImageUrl") String collectionImageUrl) throws Exception {
    return convertToDto((service.createCollection(collectionName, collectionDescription, collectionImageUrl)));
  }

  /**
   * Deletes the item from the art gallery
   * 
   * @author Vadim, Gurdashan, Ahmad 
   * @param username
   * @param itemName
   * @param artistUsername
   * @return boolean
   * @throws Exception
   */
  @PostMapping(value = {"/{username}/delete-item-from-gallery/{itemName}/{artistUsername}",
      "/{username}/delete-item-from-gallery/{itemName}/{artistUsername}/"})
  public boolean deleteItemFromGallery(@PathVariable("username") String username,
      @PathVariable("itemName") String itemName, @PathVariable("artistUsername") String artistUsername)
      throws Exception {
    return service.deleteItemFromGallery(username, itemName, artistUsername);
  }

  /**
   * Uploads Artwork to the art Gallery
   * 
   * @author Vadim, Gurdashan, Ahmad
   * @param username
   * @param collectionName
   * @param artworkName
   * @param height
   * @param width
   * @param breadth
   * @param creationDate
   * @param description
   * @param price
   * @param imageUrl
   * @return ItemDTO
   * @throws Exception
   */
  @PostMapping(value = {"/{username}/upload-artwork/{collection}/{artworkName}",
      "/{username}/upload-artwork/{collection}/{artworkName}/"})
  public ItemDTO uploadArtwork(@PathVariable("username") String username,
      @PathVariable("collection") String collectionName, @PathVariable("artworkName") String artworkName,
      @RequestParam("height") double height, @RequestParam("width") double width,
      @RequestParam("breadth") double breadth, @RequestParam("creationDate") String creationDate,
      @RequestParam("description") String description, @RequestParam("price") double price,
      @RequestParam("imageUrl") String imageUrl) throws Exception {
    return convertToDto(service.uploadArtwork(username, artworkName, height, width, breadth, creationDate, description,
        price, imageUrl, collectionName));
  }
  
  /**
   * Adds item to the shopping cart
   * 
   * @author Vadim, Gurdashan, Ahmad
   * @param username
   * @param itemName
   * @param artistUsername
   * @return
   * @throws Exception
   */
  @PostMapping(value = {"/{username}/shopping-cart/add-item/{itemName}/{artistUsername}",
      "/{username}/shopping-cart/add-item/{itemName}/{artistUsername}/"})
  public ShoppingCartDTO addToShoppingCart(@PathVariable("username") String username,
      @PathVariable("itemName") String itemName, @PathVariable("artistUsername") String artistUsername)
      throws Exception {
    return convertToDto(service.addToShoppingCart(username, itemName, artistUsername));
  }

  /**
   * @author Vadim, Gurdarshan, Ahmad
   * 
   * @param username
   * @param itemName
   * @param artistUsername
   * @return ShoppingCartDTO
   * @throws Exception
   */
  @PostMapping(value = {"/{username}/shopping-cart/remove-item/{itemName}/{artistUsername}",
      "/{username}/shopping-cart/remove-item/{itemName}/{artistUsername}/"})
  public ShoppingCartDTO removeFromShoppingCart(@PathVariable("username") String username,
      @PathVariable("itemName") String itemName, @PathVariable("artistUsername") String artistUsername)
      throws Exception {
    return convertToDto(service.removeFromShoppingCart(username, itemName, artistUsername));
  }

  /**
   * Checkout an order by a username
   * @param username customer's username
   * @param deliveryMethod delivery method selected
   * @return itemOrderDTO processed order
   * @throws Exception
   */
  @PostMapping(value = {"/{username}/checkout/{deliveryMethod}", "/{username}/checkout/{deliveryMethod}/"})
  public ItemOrderDTO checkout(@PathVariable("username") String username,
      @PathVariable("deliveryMethod") String deliveryMethod) throws Exception {
    return convertToDto(service.checkout(username, deliveryMethod));
  }

  /**
   * Returns all items present inside the shopping cart of a certain user that is a customer
   * @param username
   * @return itemsDTO items inside the user's (customer's) shopping
   * @throws Exception
   */
  @GetMapping(value = {"/{username}/shopping-cart", "/{username}/shopping-cart/"})
  public List<ItemDTO> getItemsFromShoppingCart(@PathVariable("username") String username) throws Exception {
    List<Item> items = service.getItemsFromShoppingCart(username);
    List<ItemDTO> itemsDTO = new ArrayList<ItemDTO>();

    for (Item myItem : items) {
      itemsDTO.add(convertToDto(myItem));
    }
    return itemsDTO;
  }

  /**
   * Returns all the collections present inside the art gallery
   * @return collectionDTOs all collections in the art gallery
   */
  @GetMapping(value = {"/collections", "/collections/"})
  public List<CollectionDTO> getAllCollections() {
    return service.getCollections().stream().map(u -> convertToDto(u)).collect(Collectors.toList());
  }

  /**
   * Sort item by ascending price
   * @author Vadim Tuchila
   * @param collection collection 
   * @return itemsDTO items sorted by price in ascending order
   * @throws Exception
   */
  @GetMapping(value = {"/{collection}/sort-by-price-asc", "/{collection}/sort-by-price-asc/"})
  public List<ItemDTO> sortByPriceAsc(@PathVariable("collection") String collection) throws Exception {
    List<Item> items = service.sortByPriceAsc(collection);
    List<ItemDTO> itemsDTO = new ArrayList<ItemDTO>();

    for (Item myItem : items) {
      itemsDTO.add(convertToDto(myItem));
    }
    return itemsDTO;
  }

  /**
   * Sort item by descending price
   * @author Vadim Tuchila
   * @param collection collection 
   * @return itemsDTO items sorted by price in descending order
   * @throws Exception
   */
  @GetMapping(value = {"/{collection}/sort-by-price-desc", "/{collection}/sort-by-price-desc/"})
  public List<ItemDTO> sortByPriceDesc(@PathVariable("collection") String collection) throws Exception {
    List<Item> items = service.sortByPriceDesc(collection);
    List<ItemDTO> itemsDTO = new ArrayList<ItemDTO>();

    for (Item myItem : items) {
      itemsDTO.add(convertToDto(myItem));
    }
    return itemsDTO;
  }

  /**
   * Filter items by collection (show all items inside of a collection)
   * @author Vadim Tuchila
   * @param collection collection 
   * @return itemsDTO items inside of the collection
   * @throws Exception
   */
  @GetMapping(value = {"/{collection}", "/{collection}/"})
  public List<ItemDTO> filterByCollection(@PathVariable("collection") String collection) throws Exception {
    List<Item> items = service.filterByCollection(collection);
    List<ItemDTO> itemsDTO = new ArrayList<ItemDTO>();

    for (Item myItem : items) {
      itemsDTO.add(convertToDto(myItem));
    }
    return itemsDTO;
  }

  /**
   * All items in the specified price range
   * @author Vadim Tuchila
   * @param collection collection 
   * @param priceFrom starting price of price range
   * @param priceTo end price of price range
   * @return itemsDTO items inside the specified price range
   * @throws Exception
   */
  @GetMapping(value = {"/{collection}/{price-from}/{price-to}", "/{collection}/{price-from}/{price-to}/"})
  public List<ItemDTO> filterByPrice(@PathVariable("collection") String collection,
      @PathVariable("price-from") int priceFrom, @PathVariable("price-to") int priceTo) throws Exception {
    List<Item> items = service.filterByPrice(priceFrom, priceTo, collection);
    List<ItemDTO> itemsDTO = new ArrayList<ItemDTO>();

    for (Item myItem : items) {
      itemsDTO.add(convertToDto(myItem));
    }
    return itemsDTO;
  }

  /**
   * Returns all items uploaded by an artist
   * @param collection collection
   * @param artist artist
   * @return itemsDTO items filtered by artist
   * @throws Exception
   */
  @GetMapping(value = {"/{collection}/{artist}", "/{collection}/{artist}/"})
  public List<ItemDTO> filterByArtist(@PathVariable("collection") String collection,
      @PathVariable("artist") String artist) throws Exception {
    List<Item> items = service.filterByArtist(artist, collection);
    List<ItemDTO> itemsDTO = new ArrayList<ItemDTO>();

    for (Item myItem : items) {
      itemsDTO.add(convertToDto(myItem));
    }
    return itemsDTO;
  }

  /**
   * Converts an ApplicationUser object into an ApplicationUserDTO object and makes all the necessary links with the
   * associations.
   * 
   * @author Gurdarshan Singh, Ahmad Siddiqi, Vadim Tuchila
   * @param u
   * @throws Exception
   * @return userDTO
   */

  private ApplicationUserDTO convertToDto(ApplicationUser u) {
    if (u == null) {
      throw new IllegalArgumentException("There is no such Application User!");
    }

    Set<AddressDTO> adSetDTO = new HashSet<AddressDTO>();

    try {
      for (Address ad : u.getAddress()) {
        AddressDTO adDTO = convertToDto(ad);
        adSetDTO.add(adDTO);
      }
    } catch (Exception e) {

    }

    Set<UserRoleDTO> usSetDTO = new HashSet<UserRoleDTO>();

    try {

      for (UserRole ur : u.getUserRole()) {
        UserRoleDTO usDTO = new UserRoleDTO(ur.getUserRoleId());
        usSetDTO.add(usDTO);
      }
    } catch (Exception e) {

    }

    ApplicationUserDTO userDto;
    if (agsDTO != null) {
      if (u.getAddress() != null && u.getUserRole() != null) {
        userDto = new ApplicationUserDTO(u.getAccountCreationDate(), u.getUsername(), u.getEmail(), u.getPassword(),
            u.getPhoneNumber(), adSetDTO, usSetDTO, u.isIsLoggedIn(), agsDTO, u.getBalance());
      } else if (u.getAddress() != null) {
        userDto = new ApplicationUserDTO(u.getAccountCreationDate(), u.getUsername(), u.getEmail(), u.getPassword(),
            u.getPhoneNumber(), adSetDTO, 1, u.isIsLoggedIn(), agsDTO, u.getBalance());
      } else if (u.getUserRole() != null) {
        userDto = new ApplicationUserDTO(u.getAccountCreationDate(), u.getUsername(), u.getEmail(), u.getPassword(),
            u.getPhoneNumber(), usSetDTO, u.isIsLoggedIn(), agsDTO, u.getBalance());
      } else {
        userDto = new ApplicationUserDTO(u.getAccountCreationDate(), u.getUsername(), u.getEmail(), u.getPassword(),
            u.getPhoneNumber(), u.isIsLoggedIn(), agsDTO, u.getBalance());
      }
    } else {
      if (u.getAddress() != null && u.getUserRole() != null) {
        userDto = new ApplicationUserDTO(u.getAccountCreationDate(), u.getUsername(), u.getEmail(), u.getPassword(),
            u.getPhoneNumber(), adSetDTO, usSetDTO, u.isIsLoggedIn(), u.getBalance());
      } else if (u.getAddress() != null) {
        userDto = new ApplicationUserDTO(u.getAccountCreationDate(), u.getUsername(), u.getEmail(), u.getPassword(),
            u.getPhoneNumber(), adSetDTO, 1, u.isIsLoggedIn(), u.getBalance());
      } else if (u.getUserRole() != null) {
        userDto = new ApplicationUserDTO(u.getAccountCreationDate(), u.getUsername(), u.getEmail(), u.getPassword(),
            u.getPhoneNumber(), usSetDTO, u.isIsLoggedIn(), u.getBalance());
      } else {
        userDto = new ApplicationUserDTO(u.getAccountCreationDate(), u.getUsername(), u.getEmail(), u.getPassword(),
            u.getPhoneNumber(), u.isIsLoggedIn(), u.getBalance());
      }

    }

    return userDto;
  }

  /**
   * Converts an Address object into an AddressDTO object and makes all the necessary links with the associations.
   * 
   * @author Gurdarshan Singh, Ahmad Siddiqi,Vadim Tuchila
   * @param a
   * @throws Exception
   * @return adDTO
   */

  private AddressDTO convertToDto(Address a) {
    if (a == null) {
      throw new IllegalArgumentException("There is no such Address!");
    }
    AddressDTO adDTO = new AddressDTO(a.getStreet(), a.getPostalCode(), a.getProvince(), a.getCity(), a.getCountry());

    return adDTO;
  }

  /**
   * Converts a PaymentCredentials object into a PaymentCredentialsDTO object and makes all the necessary links with the
   * associations.
   * 
   * @author Gurdarshan Singh, Ahmad Siddiqi,Vadim Tuchila
   * @param pc
   * @throws Exception
   * @return pcDTO
   */

  private PaymentCredentialsDTO convertToDto(PaymentCredentials pc) {
    if (pc == null) {
      throw new IllegalArgumentException("There is no such Payment Credentials!");
    }
    PaymentCredentialsDTO pcDTO =
        new PaymentCredentialsDTO(pc.getCardHolderName(), pc.getCcNumber(), pc.getExpirationDate(), pc.getCvc());

    return pcDTO;
  }

  /**
   * Converts a ShoppingCart object into a ShoppingCartDTO object and makes all the necessary links with the
   * associations.
   * 
   * @author Gurdarshan Singh, Ahmad Siddiqi,Vadim Tuchila
   * @param sc
   * @throws Exception
   * @return scDTO
   */

  private ShoppingCartDTO convertToDto(ShoppingCart sc) {
    if (sc == null) {
      throw new IllegalArgumentException("There is no such Shopping Cart!");
    }

    Set<Item> iSet = sc.getItem();
    Set<ItemDTO> iDTOSet = new HashSet<ItemDTO>();

    for (Item i : iSet) {
      ItemDTO iDTO = convertToDto(i);
      iDTOSet.add(iDTO);

    }

    ShoppingCartDTO scDTO = new ShoppingCartDTO(sc.getShoppingCartId(), iDTOSet);

    return scDTO;
  }

  /**
   * Converts an Artist object into an ArtistDTO object and makes all the necessary links with the associations.
   * 
   * @author Gurdarshan Singh, Ahmad Siddiqi,Vadim Tuchila
   * @param a
   * @throws Exception
   * @return aDTO
   */

  private ArtistDTO convertToDto(Artist a) {
    if (a == null) {
      throw new IllegalArgumentException("There is no such Artist");
    }

    ArtistDTO aDTO = new ArtistDTO();

    return aDTO;
  }

  /**
   * Converts a Collection object into a CollectionDTO object and makes all the necessary links with the associations.
   * 
   * @author Gurdarshan Singh, Ahmad Siddiqi,Vadim Tuchila
   * @param c
   * @throws Exception
   * @return cDTO
   */

  
  private CollectionDTO convertToDto(Collection c) {
    if (c == null) {
      throw new IllegalArgumentException("There is no such Collection");
    }

    CollectionDTO cDTO = new CollectionDTO(c.getName(), c.getDescription(), c.getPathToImage());

    return cDTO;
  }


  /**
   * Converts a Customer object into a CustomerDTO object and makes all the necessary links with the associations.
   * 
   * @author Gurdarshan Singh, Ahmad Siddiqi,Vadim Tuchila
   * @param c
   * @throws Exception
   * @return cDTO
   */
  private CustomerDTO convertToDto(Customer c) {
    if (c == null) {
      throw new IllegalArgumentException("There is no such Customer");
    }

    Set<PaymentCredentials> pcSet = c.getPaymentCredentials();
    Set<PaymentCredentialsDTO> pcDTOSet = new HashSet<PaymentCredentialsDTO>();

    for (PaymentCredentials pc : pcSet) {
      PaymentCredentialsDTO pcdto = convertToDto(pc);
      pcDTOSet.add(pcdto);

    }

    ShoppingCart sp = c.getShoppingCart();

    ShoppingCartDTO spDTO = convertToDto(sp);


    CustomerDTO cDTO = new CustomerDTO(pcDTOSet, spDTO);

    return cDTO;
  }

  /**
   * Converts an ArtGallerySystem object into an ArtGallerySystemDTO object and makes all the necessary links with the
   * associations.
   * 
   * @author Gurdarshan Singh, Ahmad Siddiqi,Vadim Tuchila
   * @param ags
   * @throws Exception
   * @return agsDTO
   */

  private ArtGallerySystemDTO convertToDto(ArtGallerySystem ags) {
    if (ags == null) {
      throw new IllegalArgumentException("There is no such Art Gallery System");
    }

    Set<ApplicationUser> uSet = ags.getApplicationUsers();
    Set<ApplicationUserDTO> uDTOSet = new HashSet<ApplicationUserDTO>();

    for (ApplicationUser u : uSet) {
      ApplicationUserDTO udto = convertToDto(u);
      uDTOSet.add(udto);

    }

    Address a = ags.getAddress();

    AddressDTO aDTO = convertToDto(a);


    ArtGallerySystemDTO agsDTO = new ArtGallerySystemDTO(uDTOSet, ags.getTotalProfit(), ags.getArtGalleryId(), aDTO);

    return agsDTO;
  }

  /**
   * Converts an Item object into an ItemDTO object and makes all the necessary links with the associations.
   * 
   * @author Gurdarshan Singh, Ahmad Siddiqi, Vadim Tuchila
   * @param i
   * @throws Exception
   * @return iDTO
   */

  private ItemDTO convertToDto(Item i) {
    if (i == null) {
      throw new IllegalArgumentException("There is no such Item");
    }

    Artist a = i.getArtist();
    ArtistDTO aDTO = convertToDto(a);

    Collection c = i.getCollection();
    CollectionDTO cDTO = convertToDto(c);


    ItemDTO iDTO = new ItemDTO(i.getItemId(), i.getName(), i.getHeight(), i.getWidth(), i.getBreadth(),
        i.getCreationDate(), i.getDescription(), i.getPrice(), i.isInStock(), i.getPathToImage());

    HashSet<ItemDTO> itemsA = new HashSet<ItemDTO>();
    for (Item item : i.getArtist().getItem()) {
      ItemDTO oldItemDTO =
          new ItemDTO(item.getItemId(), item.getName(), item.getHeight(), item.getWidth(), item.getBreadth(),
              item.getCreationDate(), item.getDescription(), item.getPrice(), item.isInStock(), item.getPathToImage());
      itemsA.add(oldItemDTO);
    }

    itemsA.add(iDTO);
    aDTO.setItems(itemsA);

    HashSet<ItemDTO> itemsC = new HashSet<ItemDTO>();
    for (Item item : i.getCollection().getItem()) {
      if (item != null) {
        ItemDTO oldItemDTO = new ItemDTO(item.getItemId(), item.getName(), item.getHeight(), item.getWidth(),
            item.getBreadth(), item.getCreationDate(), item.getDescription(), item.getPrice(), item.isInStock(),
            item.getPathToImage());
        itemsC.add(oldItemDTO);
      }
    }
    itemsC.add(iDTO);
    cDTO.setItems(itemsC);

    HashSet<ItemDTO> itemsags = new HashSet<ItemDTO>();
    for (Item item : i.getArtGallerySystem().getItem()) {
      if (item != null) {
        ItemDTO oldItemDTO = new ItemDTO(item.getItemId(), item.getName(), item.getHeight(), item.getWidth(),
            item.getBreadth(), item.getCreationDate(), item.getDescription(), item.getPrice(), item.isInStock(),
            item.getPathToImage());
        itemsags.add(oldItemDTO);
      }
    }
    itemsags.add(iDTO);
    try {
		convertToDto(service.getGallery()).setItem(itemsags);
	} catch (Exception e) {
		e.printStackTrace();
	}

    return iDTO;
  }

  /**
   * Converts an ItemOrder object into an ItemOrderDTO object and makes all the necessary links with the associations.
   * 
   * @author Gurdarshan Singh, Ahmad Siddiqi, Vadim Tuchila
   * @param io
   * @throws Exception
   * @return ioDTO
   */

  private ItemOrderDTO convertToDto(ItemOrder io) throws Exception {
    if (io == null) {
      throw new IllegalArgumentException("There is no such Item");
    }

    Customer c = io.getCustomer();

    CustomerDTO cDTO = convertToDto(c);

    Set<Item> iSet = io.getItem();
    Set<ItemDTO> iDTOSet = new HashSet<ItemDTO>();

    for (Item i : iSet) {
      ItemDTO idto = convertToDto(i);
      iDTOSet.add(idto);

    }

    DeliveryMethod dm = io.getDelivery();
    DeliveryMethodDTO dmDTO;

    String Sdm = dm.toString();

    switch (Sdm) {

      case "INSTOREPICKUP":
        dmDTO = DeliveryMethodDTO.INSTOREPICKUP;
        break;

      case "HOMEDELIVERY":
        dmDTO = DeliveryMethodDTO.HOMEDELIVERY;
        break;

      default:
        throw new Exception("Wrong delivery method");

    }

    ItemOrderDTO ioDTO = new ItemOrderDTO(io.getItemOrderId(), io.getItemOrderDate(), cDTO, dmDTO, iDTOSet);

    return ioDTO;
  }

  /**
   * Converts a collection object to a collection DTO object and makes all the necessary association links with other
   * objects
   * 
   * @author Vadim Tuchila
   * @param createCollection
   * @return collectionDTO
   */
  private CollectionDTO convertToDTO(Collection collection) {
    if (collection == null) {
      throw new IllegalArgumentException("An error occured with collection management");
    }

    CollectionDTO collectionDTO =
        new CollectionDTO(collection.getName(), collection.getDescription(), collection.getPathToImage());

    return collectionDTO;
  }



}
