package ca.mcgill.ecse321.projectgroup02.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
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

@ExtendWith(MockitoExtension.class)
public class TestProjectGroup02Service {

	@Mock
	private ArtGallerySystemRepository artGallerySystemRepository;
	@Mock
	private ApplicationUserRepository applicationUserRepository;
	@Mock
	private AddressRepository addressRepository;
	@Mock
	private ArtistRepository artistRepository;
	@Mock
	private ItemRepository itemRepository;
	@Mock
	private CollectionRepository collectionRepository;
	@Mock
	private CustomerRepository customerRepository;
	@Mock
	private ItemOrderRepository itemOrderRepository;
	@Mock
	private PaymentCredentialsRepository paymentCredentialsRepository;
	@Mock
	private ServiceProviderRepository serviceProviderRepository;
	@Mock
	private ShoppingCartRepository shoppingCartRepository;

	@InjectMocks
	private ProjectGroup02Service service;

	private final String USER_KEY = "applicationuser";
	private final String USER_EMAIL = "user@email";
	private final String USER_USERNAME = "username";
	private final String WRONG_USER_KEY = "appuser";
	private final String NONUSER_KEY = "nonapplicationuser";
	private final String USER_CUSTOMER_KEY = "customer";
	private final String BROKE_USER_CUSTOMER_KEY = "brokecustomer";
	private final String USER_NONCUSTOMER_KEY = "noncustomer";
	private final String USER_ARTIST_KEY = "artist";
	private final String PASSWORD_KEY = "password123";
	private final String WRONG_PASSWORD_KEY = "pass321";
	private final String ITEM_NAME_DISPOSABLE = "itemname";
	private final String ITEM_NAME = "itemname2";
	private final String USER_ADMIN_KEY = "adminkey";
	private final String NON_ADMIN_KEY = "notadminkey";

	private final int CUSTOMER_KEY = (USER_CUSTOMER_KEY + "customer").hashCode();
	private final int BROKE_CUSTOMER_KEY = (BROKE_USER_CUSTOMER_KEY + "customer").hashCode();
	private final int ARTIST_KEY = (USER_ARTIST_KEY + "artist").hashCode();
	private final int ADMIN_KEY = (USER_ADMIN_KEY + "admin").hashCode();
	private final int ITEM_KEY_DISPOSABLE = (USER_ARTIST_KEY + ITEM_NAME_DISPOSABLE).hashCode();
	private final int ITEM_KEY = (USER_ARTIST_KEY + ITEM_NAME).hashCode();

	// MOCKS

	@BeforeEach

	public void setMockOutput() {
		lenient().when(artGallerySystemRepository.findAll()).thenReturn(createArtGallerySystemsStub());
		lenient().when(applicationUserRepository.findAll()).thenReturn(createApplicationUsersStub());
		lenient().when(applicationUserRepository.existsByUsername(USER_USERNAME)).thenReturn(true);
		lenient().when(itemRepository.findAll()).thenReturn(createItemsStub());
		lenient().when(applicationUserRepository.findByUsername(anyString()))
				.thenAnswer((InvocationOnMock invocation) -> {
					if (invocation.getArgument(0).equals(USER_KEY)) {
						ApplicationUser user = new ApplicationUser();
						user.setUsername(USER_KEY);
						user.setPassword(PASSWORD_KEY);
						return user;
					} else {
						return null;
					}
				});
		lenient().when(itemRepository.findItemByitemId(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(ITEM_KEY_DISPOSABLE)) {
				Item item = new Item();
				Artist artist = new Artist();
				ApplicationUser user = new ApplicationUser();
				user.setUsername(USER_ARTIST_KEY);
				item.setArtist(artist);
				item.setName(ITEM_NAME_DISPOSABLE);
				item.setPrice(50);
				item.setItemId(ITEM_KEY_DISPOSABLE);
				return item;
			} else if (invocation.getArgument(0).equals(ITEM_KEY)) {
				Item item = new Item();
				Artist artist = new Artist();
				ApplicationUser user = new ApplicationUser();
				user.setUsername(USER_ARTIST_KEY);
				item.setArtist(artist);
				item.setName(ITEM_NAME);
				item.setItemId(ITEM_KEY);
				item.setPrice(50);
				return item;
			} else {
				return null;
			}
		});
		lenient().when(customerRepository.findCustomerByuserRoleId(anyInt()))
				.thenAnswer((InvocationOnMock invocation) -> {
					if (invocation.getArgument(0).equals(CUSTOMER_KEY)) {
						Customer customer = new Customer();
						customer.setUserRoleId((USER_CUSTOMER_KEY + "customer").hashCode());
						ShoppingCart shoppingCart = new ShoppingCart();
						HashSet<Item> items = new HashSet<Item>();
						Item item = new Item();
						item.setName(ITEM_NAME);
						item.setItemId(ITEM_KEY);
						item.setPrice(99);
						ApplicationUser user = new ApplicationUser();
						user.setUsername(USER_ARTIST_KEY);
						Artist artist = new Artist();
						artist.setApplicationUser(user);
						item.setArtist(artist);
						items.add(item);
						shoppingCart.setItem(items);
						customer.setShoppingCart(shoppingCart);
						ApplicationUser user2 = new ApplicationUser();
						user2.setUsername(USER_CUSTOMER_KEY);
						user2.setBalance(9999);
						customer.setApplicationUser(user2);
						return customer;
					} else if (invocation.getArgument(0).equals(BROKE_CUSTOMER_KEY)) {
						Customer customer = new Customer();
						customer.setUserRoleId((BROKE_USER_CUSTOMER_KEY + "customer").hashCode());
						ShoppingCart shoppingCart = new ShoppingCart();
						HashSet<Item> items = new HashSet<Item>();
						Item item = new Item();
						item.setName(ITEM_NAME);
						item.setItemId(ITEM_KEY);
						item.setPrice(99);
						ApplicationUser user = new ApplicationUser();
						user.setUsername(USER_ARTIST_KEY);
						Artist artist = new Artist();
						artist.setApplicationUser(user);
						item.setArtist(artist);
						items.add(item);
						shoppingCart.setItem(items);
						customer.setShoppingCart(shoppingCart);
						ApplicationUser user2 = new ApplicationUser();
						user2.setUsername(BROKE_USER_CUSTOMER_KEY);
						user2.setBalance(0);
						customer.setApplicationUser(user2);
						return customer;
					} else {
						return null;
					}
				});
		lenient().when(artistRepository.findByuserRoleId(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(ARTIST_KEY)) {
				Artist artist = new Artist();
				artist.setUserRoleId((USER_ARTIST_KEY + "artist").hashCode());
				HashSet<Item> items = new HashSet<Item>();
				Item item = new Item();
				item.setName(ITEM_NAME_DISPOSABLE);
				items.add(item);
				artist.setItem(items);
				return artist;
			} else {
				return null;
			}
		});
		lenient().when(serviceProviderRepository.findByuserRoleId(anyInt()))
				.thenAnswer((InvocationOnMock invocation) -> {
					if (invocation.getArgument(0).equals(ADMIN_KEY)) {
						ServiceProvider admin = new ServiceProvider();
						admin.setUserRoleId((USER_ADMIN_KEY + "amin").hashCode());
						return admin;
					} else {
						return null;
					}
				});
		lenient().when(collectionRepository.findByName(anyString())).thenAnswer((InvocationOnMock invocation) -> {
			Collection collection = new Collection();
			collection.setName(invocation.getArgument(0));
			return collection;
		});

		// Whenever anything is saved, just return the parameter object
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(artGallerySystemRepository.save(any(ArtGallerySystem.class)))
				.thenAnswer(returnParameterAsAnswer);
		lenient().when(applicationUserRepository.save(any(ApplicationUser.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(addressRepository.save(any(Address.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(artistRepository.save(any(Artist.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(itemRepository.save(any(Item.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(collectionRepository.save(any(Collection.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(customerRepository.save(any(Customer.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(itemOrderRepository.save(any(ItemOrder.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(paymentCredentialsRepository.save(any(PaymentCredentials.class)))
				.thenAnswer(returnParameterAsAnswer);
		lenient().when(serviceProviderRepository.save(any(ServiceProvider.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(shoppingCartRepository.save(any(ShoppingCart.class))).thenAnswer(returnParameterAsAnswer);
	}

	private HashSet<ArtGallerySystem> createArtGallerySystemsStub() {
		HashSet<ArtGallerySystem> agss = new HashSet<ArtGallerySystem>();
		agss.add(new ArtGallerySystem());
		return agss;
	}

	private HashSet<ApplicationUser> createApplicationUsersStub() {
		HashSet<ApplicationUser> appusers = new HashSet<ApplicationUser>();
		ApplicationUser user = new ApplicationUser();
		user.setEmail(USER_EMAIL);
		user.setUsername(USER_USERNAME);
		appusers.add(user);
		return appusers;
	}

	private HashSet<Item> createItemsStub() {
		HashSet<Item> items = new HashSet<Item>();

		Item item = new Item();
		item.setPrice(100);
		Collection c1 = new Collection();
		c1.setName("testing");
		item.setCollection(c1);
		ApplicationUser au1 = new ApplicationUser();
		au1.setUsername("Ahmad");
		Artist a1 = new Artist();
		a1.setApplicationUser(au1);
		item.setArtist(a1);

		Item item2 = new Item();
		item2.setPrice(150);
		Collection c2 = new Collection();
		c2.setName("testing");
		item2.setCollection(c2);
		ApplicationUser au2 = new ApplicationUser();
		au2.setUsername("Ahmad");
		Artist a2 = new Artist();
		a2.setApplicationUser(au2);
		item2.setArtist(a2);

		Item item3 = new Item();
		item3.setPrice(200);
		Collection c3 = new Collection();
		c3.setName("testing123");
		item3.setCollection(c3);
		ApplicationUser au3 = new ApplicationUser();
		au3.setUsername("Ryad");
		Artist a3 = new Artist();
		a3.setApplicationUser(au3);
		item3.setArtist(a3);

		items.add(item2);
		items.add(item3);
		items.add(item);

		return items;
	}

	// TESTS

	@Test
	void testGetGallery() throws Exception {
		ArtGallerySystem gallery = service.getGallery();
		assertNotNull(gallery);
	}

	@Test
	public void testCreateArtGallery() throws Exception {
		// assertEquals(null,service.getGallery());
		String street = "sherbroke";
		String postalCode = "H4J4H8";
		String province = "Quebec";
		String country = "Canada";
		String city = "Montreal";
		String adminUsername = USER_KEY;
		String adminPassword = "asmaamcgill";
		String adminEmail = "asmaa@gmail.com";

		ArtGallerySystem ags = null;

		try {
			ags = service.createGallery(street, postalCode, province, country, city, adminUsername, adminPassword,
					adminEmail);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertNotNull(ags);
		assertEquals(street, ags.getAddress().getStreet());
		assertEquals(postalCode, ags.getAddress().getPostalCode());
		assertEquals(province, ags.getAddress().getProvince());
		assertEquals(country, ags.getAddress().getCountry());
		assertEquals(postalCode, ags.getAddress().getPostalCode());
		assertEquals(city, ags.getAddress().getCity());

		for (ApplicationUser ap : ags.getApplicationUsers()) {
			for (UserRole ur : ap.getUserRole()) {
				if (ur.getUserRoleId() == ((adminUsername + "admin").hashCode())) {
					assertEquals(adminUsername, ap.getUsername());
					assertEquals(adminEmail, ap.getEmail());
				}
			}
		}
	}

	@Test
	public void testCreateUser() throws Exception {
		ApplicationUser user = null;
		try {
			user = service.createUser(USER_KEY, "asmaa@gmail.com", "asmaamcgill");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

		assertNotNull(user);
		assertEquals(USER_KEY, user.getUsername());
		assertEquals("asmaa@gmail.com", user.getEmail());
		assertEquals("asmaamcgill", user.getPassword());
	}

	@Test
	public void testCreateUserAlreadyUsedEmail() throws Exception {
		assertThrows(Exception.class, () -> service.createUser(USER_KEY, USER_EMAIL, "asmaamcgill"),
				"Email already in use");
	}

	@Test
	public void testCreateUserAlreadyUsedUsername() throws Exception {
		assertThrows(Exception.class, () -> service.createUser(USER_USERNAME, "asmaa@gmail.com", "asmaamcgill"),
				"Username unavailable");
	}

	@Test
	public void testCreateUserWrongUsername() throws Exception {
		assertThrows(Exception.class, () -> service.createUser(WRONG_USER_KEY, "asmaa@gmail.com", "asmaamcgill"),
				"Username must have at least 8 characters");
	}

	@Test
	public void testCreateUserWrongPassword() throws Exception {
		assertThrows(Exception.class, () -> service.createUser(USER_KEY, "asmaa@gmail.com", WRONG_PASSWORD_KEY),
				"Username must have at least 8 characters");
	}

	@Test
	public void testCreateAdmin() throws Exception {
		ApplicationUser admin = null;
		try {
			admin = service.createAdmin(USER_KEY, "asmaa@gmail.com", "asmaamcgill");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

		assertNotNull(admin);
		assertEquals(USER_KEY, admin.getUsername());
		assertEquals("asmaa@gmail.com", admin.getEmail());
		assertEquals("asmaamcgill", admin.getPassword());
	}

	@Test
	public void testGetExistingUser() throws Exception {
		ApplicationUser user = null;
		try {
			user = service.createUser(USER_KEY, "asmaa@gmail.com", "asmaamcgill");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

		assertEquals(user.getUsername(), service.getUser(USER_KEY).getUsername());

	}

	@Test
	public void testGetNonExistingUser() throws Exception {
		assertNull(service.getUser(NONUSER_KEY));

	}

	@Test
	public void testUpdateUserCredentialsOnCustomer() throws Exception {
		PaymentCredentials paycreds = service.updateUserCredentials(USER_CUSTOMER_KEY, "asma", "5555", "03/02/21",
				"9565238");
		assertNotNull(paycreds);
		assertEquals(paycreds.getCardHolderName(), "asma");
		assertEquals(paycreds.getCcNumber(), "5555");
		assertEquals(paycreds.getCvc(), "9565238");
		assertEquals(paycreds.getExpirationDate(), "03/02/21");
	}

	@Test
	public void testUpdateUserCredentialsOnNonCustomer() throws Exception {
		assertThrows(Exception.class,
				() -> service.updateUserCredentials(USER_NONCUSTOMER_KEY, "asma", "5555", "03/02/21", "9565238"),
				"User must be a customer");
	}

	@Test
	public void testSetUserRoleForArtist() {
		ApplicationUser user = service.setUserRole(USER_KEY, "Artist");
		for (UserRole role : user.getUserRole())
			assertTrue(role instanceof Artist);
	}

	@Test
	public void testSetUserRoleForCustomer() {
		ApplicationUser user = service.setUserRole(USER_KEY, "Customer");
		for (UserRole role : user.getUserRole())
			assertTrue(role instanceof Customer);
	}

	@Test
	public void logoutUser() {
		assertTrue(!service.logoutUser(USER_KEY).isIsLoggedIn());
	}

	@Test
	public void loginUserCorrectPassword() {
		assertTrue(service.loginUser(USER_KEY, PASSWORD_KEY));
	}

	@Test
	public void loginUserWrongPassword() {
		assertTrue(!service.loginUser(USER_KEY, WRONG_PASSWORD_KEY));
	}

	@Test
	public void testUpdateUserAddress() throws Exception {
		Address address = service.updateUserAddress(USER_KEY, "sherbrooke", "H4E8H8", "Quebec", "Canada", "Montreal");
		assertEquals("sherbrooke", address.getStreet());
		assertEquals("H4E8H8", address.getPostalCode());
		assertEquals("Quebec", address.getProvince());
		assertEquals("Canada", address.getCountry());
		assertEquals("Montreal", address.getCity());

	}

	@Test
	public void testUploadArtwork() throws Exception {

		Item item = service.uploadArtwork(USER_ARTIST_KEY, "MonaLisa", 66.0, 2.0, 4.0, "September 2", "my artwork",
				600.0, "documents/", "Fall 2020");

		assertEquals("MonaLisa", item.getName());
		assertEquals(66.0, item.getHeight());
		assertEquals(2.0, item.getWidth());
		assertEquals("September 2", item.getCreationDate());
		assertEquals("my artwork", item.getDescription());
		assertEquals(600.0, item.getPrice());
		assertEquals("documents/", item.getPathToImage());
		assertEquals("Fall 2020", item.getCollection().getName());
	}

	@Test
	public void testUploadArtworkSameName() throws Exception {
		assertThrows(
				Exception.class, () -> service.uploadArtwork(USER_ARTIST_KEY, ITEM_NAME_DISPOSABLE, 66.0, 2.0, 4.0,
						"September 2", "my artwork", 600.0, "documents/", "Fall 2020"),
				"Artist's items' name must be unique");
	}

	@Test
	public void testDeleteItemFromGalleryIsAdmin() throws Exception {
		assertEquals(true, service.deleteItemFromGallery(USER_ADMIN_KEY, "MonaLisa", "asmaajerroumi"));
	}

	@Test
	public void testDeleteItemFromGalleryIsNotAdmin() throws Exception {
		assertThrows(Exception.class, () -> service.deleteItemFromGallery(NON_ADMIN_KEY, "MonaLisa", "Artist"),
				"User must be a service provider to manage invertory");
	}

	@Test
	public void testCreateCollection() {
		Collection collection = service.createCollection("Fall 2020", "my collection", "documents/");

		assertEquals("Fall 2020", collection.getName());
		assertEquals("my collection", collection.getDescription());
		assertEquals("documents/", collection.getPathToImage());
	}

	@Test
	public void testAddToShoppingCart() throws Exception {
		ShoppingCart shopcart = service.addToShoppingCart(USER_CUSTOMER_KEY, ITEM_NAME_DISPOSABLE, USER_ARTIST_KEY);
		for (Item item : shopcart.getItem()) {
			if (!item.getName().equals(ITEM_NAME_DISPOSABLE)) { // Ignore if it is not the item were are looking for
				// do nothing
			} else
				assertEquals(item.getName(), ITEM_NAME_DISPOSABLE);
		}
	}

	@Test
	public void testAddToShoppingCartNotCustomer() throws Exception {
		assertThrows(Exception.class, () -> service.addToShoppingCart(USER_NONCUSTOMER_KEY, "MonaLisa", "elonmusk"),
				"User must be a customer");

	}

	@Test
	public void testRemoveFromShoppingCart() throws Exception {
		ShoppingCart shopcart = service.removeFromShoppingCart(USER_CUSTOMER_KEY, ITEM_NAME, USER_ARTIST_KEY);
		for (Item item : shopcart.getItem())
			assertNotEquals(item.getName(), ITEM_NAME);
	}

	@Test
	public void testGetItemsFromShoppingCart() throws Exception {
		List<Item> items = service.getItemsFromShoppingCart(USER_CUSTOMER_KEY);
		assertEquals(items.get(0).getName(), ITEM_NAME);
	}

	@Test
	public void testCheckoutInStorePickUp() throws Exception {
		ItemOrder order = service.checkout(USER_CUSTOMER_KEY, "INSTOREPICKUP");
		assertEquals(order.getCustomer().getApplicationUser().getUsername(), USER_CUSTOMER_KEY);
		assertEquals(order.getDelivery(), DeliveryMethod.INSTOREPICKUP);
		assertEquals(order.getItemOrderId(), (USER_CUSTOMER_KEY + "order").hashCode());
		Set<Item> items = order.getItem();
		for (Item item : items)
			assertEquals(item.getName(), ITEM_NAME);
	}

	@Test
	public void testCheckoutHomeDelivery() throws Exception {
		ItemOrder order = service.checkout(USER_CUSTOMER_KEY, "HOMEDELIVERY");
		assertEquals(order.getCustomer().getApplicationUser().getUsername(), USER_CUSTOMER_KEY);
		assertEquals(order.getDelivery(), DeliveryMethod.HOMEDELIVERY);
		assertEquals(order.getItemOrderId(), (USER_CUSTOMER_KEY + "order").hashCode());
		Set<Item> items = order.getItem();
		for (Item item : items)
			assertEquals(item.getName(), ITEM_NAME);
	}

	@Test
	public void testCheckoutBrokeBoy() throws Exception {
		assertThrows(Exception.class, () -> service.checkout(BROKE_USER_CUSTOMER_KEY, "INSTOREPICKUP"),
				"Insufficient funds");
	}

	@Test
	public void testSortedByPriceAsc() throws Exception {

		List<Item> sortedItems = service.sortByPriceAsc();
		int itemPrice = 100;
		for (Item i : sortedItems) {

			assertEquals(i.getPrice(), itemPrice);
			itemPrice = itemPrice + 50;
		}
	}

	@Test
	public void testSortedByPriceDesc() throws Exception {

		List<Item> sortedItems = service.sortByPriceDesc();
		int itemPrice = 200;
		for (Item i : sortedItems) {

			assertEquals(i.getPrice(), itemPrice);
			itemPrice = itemPrice - 50;
		}
	}

	@Test
	public void testFilteredByCollection() throws Exception {

		List<Item> filteredItems = service.filterByCollection("testing");

		for (Item i : filteredItems) {

			assertEquals(i.getCollection().getName(), "testing");
		}
	}

	@Test
	public void testFilteredByArtist() throws Exception {

		List<Item> filteredItems = service.filterByArtist("Ahmad");

		for (Item i : filteredItems) {

			assertEquals(i.getArtist().getApplicationUser().getUsername(), "Ahmad");
		}
	}

	@Test
	public void testFilteredByPrice() throws Exception {

		List<Item> filteredItems = service.filterByPrice(100, 150);

		for (Item i : filteredItems) {

			assertTrue(i.getPrice() >= 100 && i.getPrice() <= 150);

		}

	}

}
