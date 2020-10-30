package ca.mcgill.ecse321.projectgroup02.controller;

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

import ca.mcgill.ecse321.projectgroup02.dao.AddressRepository;
import ca.mcgill.ecse321.projectgroup02.dao.ArtGallerySystemRepository;
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

	@Autowired
	private ProjectGroup02Service service;

	@PostMapping(value = { "/art-gallery-system/{street}/{pc}/{province}/{country}/{city}/{adminUsername}/{adminPassword}/{adminEmail}", 
	"/art-gallery-system/{street}/{pc}/{province}/{country}/{city}/{adminUsername}/{adminPassword}/{adminEmail}/" })

	public ArtGallerySystemDTO createArtGallery( @PathVariable("street") String street, @PathVariable("pc") String postalCode, @PathVariable("province") String province,
			@PathVariable("country") String country, @PathVariable("city") String city,@PathVariable("adminUsername") String adminUsername,@PathVariable("adminPassword") String adminPassword,@PathVariable("adminEmail") String adminEmail) 
					throws Exception {
		ArtGallerySystem ags = service.createGallery(street, postalCode, province, country, city, adminUsername, adminPassword, adminEmail);

		ArtGallerySystemDTO agsDTO = convertToDto(ags);

		return agsDTO;
	}

	@GetMapping(value = { "/art-gallery-system", "/art-gallery-system/" })
	public ArtGallerySystemDTO getArtGallerySystem() throws Exception {
		return convertToDto(service.getGallery());
	}

	@PostMapping(value = { "/create-user/{name}/{email}/{pw}", "/create-user/{name}/{email}/{pw}/" })
	public ApplicationUserDTO createUser(@PathVariable("name") String username,@PathVariable("email") String email, @PathVariable("pw") String password) 
			throws Exception {
		ApplicationUser user = service.createUser(username, email, password);
		return convertToDto(user);
	}

	@GetMapping(value = { "/users", "/users/" })
	public List<ApplicationUserDTO> getAllUsers() {
		return service.getAllUsers().stream().map(u -> convertToDto(u)).collect(Collectors.toList());
	}

	@GetMapping(value = { "/user-by-name/{username}", "/user-by-name/{username}/" })
	public ApplicationUserDTO getUserByUsername(@PathVariable("username") String username) {

		return convertToDto(service.getUser(username));
	}

	
	@PostMapping(value = { "/set-user-role/{username}" , "/set-user-role/{username}/"})
	public ApplicationUserDTO setUserRole(@PathVariable("username") String username, @PathVariable("roles") String... role) {
		return convertToDto(service.setUserRole(username, role));
	}


	@PostMapping(value = { "/update-payment-credentials/{username}/{cardname}/{ccnumber}/{expdate}/{cvc}", "/update-payment-credentials/{username}/{cardname}/{ccnumber}/{expdate}/{cvc}/" })
	public PaymentCredentialsDTO updateUserCredentials(@PathVariable("username") String name,@PathVariable("cardname") String cName, @PathVariable("ccnumber") String ccNumber, @PathVariable("expdate") String eDate, @PathVariable("cvc") String cvc) throws Exception {
		return convertToDto(service.updateUserCredentials(name, cName, ccNumber, eDate, cvc));
	}


	@PostMapping(value = { "/update-user-address/{username}/{street}/{postalcode}/{province}/{country}/{city}", "/update-user-address/{username}/{street}/{postalcode}/{province}/{country}/{city}/"})
	public AddressDTO updateUserAddress(@PathVariable("username") String username, @PathVariable("street") String street, @PathVariable("postalcode") String postalcode, @PathVariable("province") String province, @PathVariable("country") String country, @PathVariable("city") String city) throws Exception {
		return convertToDto(service.updateUserAddress(username, street, postalcode, province, country, city));
	}

	@PostMapping(value = {"/user-login/{username}/{password}" , "/user-login/{username}/{password}/"})
	public boolean loginUser(@PathVariable("username") String username, @PathVariable("password") String pw) {
		return service.loginUser(username, pw);
	}

	@PostMapping(value = {"/user-logout/{username}" , "/user-logout/{username}/"})
	public void logoutUser(@PathVariable("username") String username) {
		service.logoutUser(username);
	}

	@GetMapping(value = {"/{username}/shopping-cart', '/{username}/shopping-cart/"})
	public List<ItemDTO> getItemsFromShoppingCart(@PathVariable("username") String username) throws Exception {
		return service.getItemsFromShoppingCart(username).stream().map(item -> convertToDto(item)).collect(Collectors.toList());
	}

	@PostMapping(value = {"/{username}/checkout/{deliveryMethod}", "/{username}/checkout/{deliveryMethod}/"})
	public ItemOrderDTO checkout(@PathVariable("username") String username, @PathVariable("deliveryMethod") String deliveryMethod) throws Exception {
		return convertToDto(service.checkout(username, deliveryMethod));
	}

	@PostMapping(value = {"/{username}/shopping-cart/add-item/{itemName}/{artistusername}", "/{username}/shopping-cart/remove-item/{itemName}/{artistUsername}/"})
	public ShoppingCartDTO addToShoppingCart(@PathVariable("username") String username, @PathVariable("itemName") String itemName, @PathVariable("artistUsername") String artistUsername) throws Exception {
		return convertToDto(service.addToShoppingCart(username, itemName, artistUsername));
	}

	@PostMapping(value = {"/{username}/shopping-cart/remove-item/{itemName}/{artistusername}", "/{username}/shopping-cart/remove-item/{itemName}/{artistUsername}/"})
	public ShoppingCartDTO removeFromShoppingCart(@PathVariable("username") String username, @PathVariable("itemName") String itemName, @PathVariable("artistUsername") String artistUsername) throws Exception {
		return convertToDto(service.removeFromShoppingCart(username, itemName, artistUsername));
	}

	@PostMapping(value = {"/create-collection/{collectionName}", "/create-collection/{collectionName}/"})
	public CollectionDTO createCollection(@PathVariable("collectionName") String collectionName, @RequestParam("collectionDescription") String collectionDescription, @RequestParam("collectionImageUrl") String collectionImageUrl) throws Exception {
		return convertToDto((service.createCollection(collectionName, collectionDescription, collectionImageUrl)));
	}

	@PostMapping(value = {"/{username}/delete-item-from-gallery/{itemName}/{artistusername}", "/{username}/delete-item-from-gallery/{itemName}/{artistUsername}/"})
	public boolean deleteItemFromGallery(@PathVariable("username") String username, @PathVariable("itemName") String itemName, @PathVariable("artistUsername") String artistUsername) throws Exception {
		return service.deleteItemFromGallery(username, itemName, artistUsername);
	}

	@PostMapping(value = {"/{username}/upload-artwork/{collection}/{artworkName}/"})
	public ItemDTO uploadArtwork(@PathVariable("username") String username, @PathVariable("artworkName") String artworkName, @RequestParam("height") double height, @RequestParam("width") double width, @RequestParam("breadth") double breadth,
			@RequestParam("creationDate") String creationDate, @RequestParam("description") String description, @RequestParam("price")  double price, @RequestParam("iamgeUrl") String imageUrl,  @PathVariable("collection") String collectionName) throws Exception {
		return convertToDto(service.uploadArtwork(username, artworkName, height, width, breadth, creationDate, description, price, imageUrl, collectionName));
	}

	/**
	 * Converts an ApplicationUser object into an ApplicationUserDTO object and 
	 * makes all the necessary links with the associations.
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
			for(Address ad : u.getAddress()) {
				AddressDTO adDTO = convertToDto(ad);
				adSetDTO.add(adDTO);
			}
		} catch(Exception e) {

		}

		Set<UserRoleDTO> usSetDTO = new HashSet<UserRoleDTO>();

		try {

			for(UserRole ur : u.getUserRole()) {
				UserRoleDTO usDTO = new UserRoleDTO(ur.getUserRoleId());
				usSetDTO.add(usDTO);
			}
		} catch(Exception e) {

		}

		ApplicationUserDTO userDto;

		if(u.getAddress() != null && u.getUserRole() != null) {
			userDto = new ApplicationUserDTO(u.getAccountCreationDate(), u.getUsername(), u.getEmail(), u.getPassword(), u.getPhoneNumber(), adSetDTO, usSetDTO);
		} else if(u.getAddress() != null) {
			userDto = new ApplicationUserDTO(u.getAccountCreationDate(), u.getUsername(), u.getEmail(), u.getPassword(), u.getPhoneNumber(), adSetDTO, 1);
		} else if(u.getUserRole() != null) {
			userDto = new ApplicationUserDTO(u.getAccountCreationDate(), u.getUsername(), u.getEmail(), u.getPassword(), u.getPhoneNumber(), usSetDTO);
		} else {
			userDto = new ApplicationUserDTO(u.getAccountCreationDate(), u.getUsername(), u.getEmail(), u.getPassword(), u.getPhoneNumber());
		}
		return userDto;
	}

	/**
	 * Converts an Address object into an AddressDTO object and 
	 * makes all the necessary links with the associations.
	 * 
	 * @author Gurdarshan Singh, Ahmad Siddiqi,
	 * @param a
	 * @throws Exception
	 * @return adDTO
	 */

	private AddressDTO convertToDto(Address a) {
		if(a == null) {
			throw new IllegalArgumentException("There is no such Address!");
		}
		AddressDTO adDTO = new AddressDTO(a.getStreet(), a.getPostalCode(), a.getProvince(), a.getCity(), a.getCountry());

		return adDTO;
	}

	/**
	 * Converts a PaymentCredentials object into a PaymentCredentialsDTO object and 
	 * makes all the necessary links with the associations.
	 * 
	 * @author Gurdarshan Singh, Ahmad Siddiqi,
	 * @param pc
	 * @throws Exception
	 * @return pcDTO
	 */

	private PaymentCredentialsDTO convertToDto(PaymentCredentials pc) {
		if(pc == null) {
			throw new IllegalArgumentException("There is no such Payment Credentials!");
		}
		PaymentCredentialsDTO pcDTO = new PaymentCredentialsDTO(pc.getCardHolderName(), pc.getCcNumber(), pc.getExpirationDate(), pc.getCvc());

		return pcDTO;
	}

	/**
	 * Converts a ShoppingCart object into a ShoppingCartDTO object and 
	 * makes all the necessary links with the associations.
	 * 
	 * @author Gurdarshan Singh, Ahmad Siddiqi,
	 * @param sc
	 * @throws Exception
	 * @return scDTO
	 */

	private ShoppingCartDTO convertToDto(ShoppingCart sc) {
		if(sc == null) {
			throw new IllegalArgumentException("There is no such Shopping Cart!");
		}

		Set<Item> iSet = sc.getItem();
		Set<ItemDTO> iDTOSet = new HashSet<ItemDTO>();

		for(Item i : iSet) {
			ItemDTO iDTO = convertToDto(i);
			iDTOSet.add(iDTO);

		}

		ShoppingCartDTO scDTO = new ShoppingCartDTO(sc.getShoppingCartId(), iDTOSet);

		return scDTO;
	}

	/**
	 * Converts an Artist object into an ArtistDTO object and 
	 * makes all the necessary links with the associations.
	 * 
	 * @author Gurdarshan Singh, Ahmad Siddiqi,
	 * @param a
	 * @throws Exception
	 * @return aDTO
	 */
	//TODO infinite loop with item
	private ArtistDTO convertToDto(Artist a) {
		if(a == null) {
			throw new IllegalArgumentException("There is no such Artist");
		}

		Set<Item> iSet = a.getItem();
		Set<ItemDTO> iDTOSet = new HashSet<ItemDTO>();

		for(Item i : iSet) {
			ItemDTO iDTO = convertToDto(i);
			iDTOSet.add(iDTO);

		}

		ArtistDTO aDTO = new ArtistDTO(a.getDescription(), iDTOSet);

		return aDTO;
	}

	/**
	 * Converts a Collection object into a CollectionDTO object and 
	 * makes all the necessary links with the associations.
	 * 
	 * @author Gurdarshan Singh, Ahmad Siddiqi,
	 * @param c
	 * @throws Exception
	 * @return cDTO
	 */

	//TODO infinite loop with item
	private CollectionDTO convertToDto(Collection c) {
		if(c == null) {
			throw new IllegalArgumentException("There is no such Collection");
		}

		Set<Item> iSet = c.getItem();
		Set<ItemDTO> iDTOSet = new HashSet<ItemDTO>();

		for(Item i : iSet) {
			ItemDTO iDTO = convertToDto(i);
			iDTOSet.add(iDTO);

		}

		CollectionDTO cDTO = new CollectionDTO(c.getName(), c.getDescription(), c.getPathToImage(), iDTOSet);

		return cDTO;
	}


	/**
	 * Converts a Customer object into a CustomerDTO object and 
	 * makes all the necessary links with the associations.
	 * 
	 * @author Gurdarshan Singh, Ahmad Siddiqi,
	 * @param c
	 * @throws Exception
	 * @return cDTO
	 */

	//TODO	ADD ITEM ORDER ASSOCIATION


	private CustomerDTO convertToDto(Customer c) {
		if(c == null) {
			throw new IllegalArgumentException("There is no such Customer");
		}

		Set<PaymentCredentials> pcSet = c.getPaymentCredentials();
		Set<PaymentCredentialsDTO> pcDTOSet = new HashSet<PaymentCredentialsDTO>();

		for(PaymentCredentials pc : pcSet) {
			PaymentCredentialsDTO pcdto = convertToDto(pc);
			pcDTOSet.add(pcdto);

		}

		ShoppingCart sp = c.getShoppingCart();

		ShoppingCartDTO spDTO = convertToDto(sp);


		CustomerDTO cDTO = new CustomerDTO(pcDTOSet, spDTO );

		return cDTO;
	}

	/**
	 * Converts an ArtGallerySystem object into an ArtGallerySystemDTO object and 
	 * makes all the necessary links with the associations.
	 * 
	 * @author Gurdarshan Singh, Ahmad Siddiqi,
	 * @param ags
	 * @throws Exception
	 * @return agsDTO
	 */

	private ArtGallerySystemDTO convertToDto(ArtGallerySystem ags) {
		if(ags == null) {
			throw new IllegalArgumentException("There is no such Art Gallery System");
		}

		Set<ApplicationUser> uSet = ags.getApplicationUsers();
		Set<ApplicationUserDTO> uDTOSet = new HashSet<ApplicationUserDTO>();

		for(ApplicationUser u : uSet) {
			ApplicationUserDTO udto = convertToDto(u);
			uDTOSet.add(udto);

		}

		Set<Item> iSet = ags.getItem();
		Set<ItemDTO> iDTOSet = new HashSet<ItemDTO>();

		for(Item i : iSet) {
			ItemDTO iDTO = convertToDto(i);
			iDTOSet.add(iDTO);

		}

		Address a = ags.getAddress();

		AddressDTO aDTO = convertToDto(a);


		ArtGallerySystemDTO agsDTO = new ArtGallerySystemDTO(uDTOSet, ags.getTotalProfit(), ags.getArtGalleryId(), aDTO, iDTOSet);

		return agsDTO;
	}

	/**
	 * Converts an Item object into an ItemDTO object and 
	 * makes all the necessary links with the associations.
	 * 
	 * @author Gurdarshan Singh, Ahmad Siddiqi, Vadim Tuchila
	 * @param i
	 * @throws Exception
	 * @return iDTO
	 */

	private ItemDTO convertToDto(Item i) {
		if(i == null) {
			throw new IllegalArgumentException("There is no such Item");
		}


		Artist a = i.getArtist();

		ArtistDTO aDTO = convertToDto(a);

		Collection c = i.getCollection();

		CollectionDTO cDTO = convertToDto(c);

		ArtGallerySystem ags = i.getArtGallerySystem();

		ArtGallerySystemDTO agsDTO = convertToDto(ags);


		ItemDTO iDTO = new ItemDTO(i.getItemId(), i.getName(), i.getHeight(), i.getWidth(), i.getBreadth(), i.getCreationDate(),
				i.getDescription(), i.getPrice(), i.isInStock(), aDTO, cDTO, agsDTO);

		return iDTO;
	}

	/**
	 * Converts an ItemOrder object into an ItemOrderDTO object and 
	 * makes all the necessary links with the associations.
	 * 
	 * @author Gurdarshan Singh, Ahmad Siddiqi, Vadim Tuchila
	 * @param io
	 * @throws Exception
	 * @return ioDTO
	 */

	private ItemOrderDTO convertToDto(ItemOrder io) throws Exception {
		if(io == null) {
			throw new IllegalArgumentException("There is no such Item");
		}

		Customer c = io.getCustomer();

		CustomerDTO cDTO = convertToDto(c);

		Set<Item> iSet = io.getItem();
		Set<ItemDTO> iDTOSet = new HashSet<ItemDTO>();

		for(Item i : iSet) {
			ItemDTO idto = convertToDto(i);
			iDTOSet.add(idto);

		}

		DeliveryMethod dm = io.getDelivery();
		DeliveryMethodDTO dmDTO;

		String Sdm = dm.toString();

		switch(Sdm) {

		case "INSTOREPICKUP": 
			dmDTO = DeliveryMethodDTO.INSTOREPICKUP;
			break;

		case "HOMEDELIVERY":
			dmDTO = DeliveryMethodDTO.HOMEDELIVERY;
			break;

		default:
			throw new Exception("Wrong delivery method");

		}

		ItemOrderDTO ioDTO = new ItemOrderDTO(io.getItemOrderId(),io.getItemOrderDate(), cDTO, dmDTO, iDTOSet);

		return ioDTO;
	}

	/**
	 * Converts a collection object to a collection DTO object and makes all the 
	 * necessary association links with other objects
	 * @author Vadim Tuchila
	 * @param createCollection
	 * @return collectionDTO
	 */
	private CollectionDTO convertToDTO(Collection collection) {
		if (collection == null) {
			throw new IllegalArgumentException("An error occured with collection management");
		}

		CollectionDTO collectionDTO = new CollectionDTO(collection.getName(), collection.getDescription(), collection.getPathToImage());

		return collectionDTO;
	}





}
