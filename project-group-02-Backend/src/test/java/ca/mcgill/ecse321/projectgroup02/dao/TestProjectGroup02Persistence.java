package ca.mcgill.ecse321.projectgroup02.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.projectgroup02.model.Address;
import ca.mcgill.ecse321.projectgroup02.model.ApplicationUser;
import ca.mcgill.ecse321.projectgroup02.model.ArtGallerySystem;
import ca.mcgill.ecse321.projectgroup02.model.Artist;
import ca.mcgill.ecse321.projectgroup02.model.Collection;
import ca.mcgill.ecse321.projectgroup02.model.Customer;
import ca.mcgill.ecse321.projectgroup02.model.Delivery;
import ca.mcgill.ecse321.projectgroup02.model.Item;
import ca.mcgill.ecse321.projectgroup02.model.ItemOrder;
import ca.mcgill.ecse321.projectgroup02.model.NotificationHandler;
import ca.mcgill.ecse321.projectgroup02.model.OrderConfirmationEmail;
import ca.mcgill.ecse321.projectgroup02.model.PaymentCredentials;
import ca.mcgill.ecse321.projectgroup02.model.ServiceProvider;
import ca.mcgill.ecse321.projectgroup02.model.ShoppingCart;
import ca.mcgill.ecse321.projectgroup02.model.UserRole;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestProjectGroup02Persistence {

	@Autowired
	private ApplicationUserRepository userRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private ArtGallerySystemRepository artGallerySystemRepository;
	@Autowired
	private ArtistRepository artistRepository;
	@Autowired
	private CollectionRepository collectionRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private DeliveryRepository deliveryRepository;
	@Autowired
	private ItemRepository itemRepository;
	@Autowired
    private ItemOrderRepository itemOrderRepository;
	@Autowired
    private NotificationHandlerRepository notificationHandlerRepository;
	@Autowired
    private OrderConfirmationEmailRepository orderConfirmationEmailRepository;
	@Autowired
    private PaymentCredentialsRepository paymentCredentialsRepository;
	@Autowired
    private ServiceProviderRepository serviceProviderRepository;
	@Autowired
    private ShoppingCartRepository shoppingCartRepository;
	@Autowired
    private UserRoleRepository userRoleRepository;
	
	
	
	@AfterEach
	public void clearDatabase() {
		// First, we clear registrations to avoid exceptions due to inconsistencies
		userRepository.deleteAll();
		addressRepository.deleteAll();
		artGallerySystemRepository.deleteAll();
		artistRepository.deleteAll();
		collectionRepository.deleteAll();
		customerRepository.deleteAll();
		deliveryRepository.deleteAll();
		itemRepository.deleteAll();
		userRepository.deleteAll();
		shoppingCartRepository.deleteAll();
		serviceProviderRepository.deleteAll();
		paymentCredentialsRepository.deleteAll();
		orderConfirmationEmailRepository.deleteAll();
		notificationHandlerRepository.deleteAll();
		itemOrderRepository.deleteAll();
		// Then we can clear the other tables
		
	}
	
	@Test
	public void testPersistAndLoadUser() {
		int userID = 14231;
		
		ApplicationUser user = new ApplicationUser();
		//Address address = new Address();
		
		//address.setAddressId(123);
		//Set<Address> addresses = null;
		//addresses.add(address);
		
		// First example for attribute save
		user.setApplicationUserId(userID);
		// First example for reference save
		//user.setAddress(addresses);
		// First example for object save
		userRepository.save(user);

		user = null;
		// First example for object load
		user = userRepository.findByapplicationUserId(userID);
		assertNotNull(user);
		// First example for attribute load
		int id = user.getApplicationUserId();
		//assertNotNull(id);
		assertEquals(userID, id);
		// First example of reference load
		//Set<Address> checkaddress = user.getAddress();
		//assertEquals(addresses, checkaddress);
	}
	
	@Test
	public void testPersistAndLoadAddress() {
		
		int addressID = 15;
		
		Address adrs = new Address();
		
		adrs.setAddressId(addressID);
		
		addressRepository.save(adrs);
		
		adrs = null;
		
		adrs = addressRepository.findAddressByaddressId(addressID);
		
		assertNotNull(adrs);
		
		int id = adrs.getAddressId();
		
		assertEquals(addressID, id);
		
		
	}
	
	@Test
	public void testPersistAndLoadArtGallerySystem() {
		int AG_id= 1;
		
		ArtGallerySystem AG_sys= new ArtGallerySystem();
		
		AG_sys.setArtGalleryId(AG_id);
		
		artGallerySystemRepository.save(AG_sys);
		
		AG_sys= null;
		
		AG_sys= artGallerySystemRepository.findByartGalleryId(AG_id);
		
		assertNotNull(AG_sys);
		
		int id= AG_sys.getArtGalleryId();
		
		assertEquals(AG_id,id);
		
		
	}
	
	@Test
	public void testPersistAndLoadArtist() {
		int artistId= 2;
		
		Artist artist= new Artist();
		
		artist.setArtistId(artistId);
		
		artistRepository.save(artist);
		
		artist = null;
		
		artist = artistRepository.findByartistId(artistId);
		
		assertNotNull(artist);
		
		int id= artist.getArtistId();
		
		assertEquals(artistId,id);
		
		
	}
	
	@Test
	public void testPersistAndLoadCollection() {
		int collection_Id= 3;
		
		Collection collection = new Collection();
		
		collection.setCollectionId(collection_Id);
		
		Item item = new Item();
		
		item.setItemId(84);
		
		Set<Item> itemset = new HashSet<Item>();
		
		itemset.add(item);
		
		collection.setItem(itemset);
		
		collectionRepository.save(collection);
		
		collection = null;
		
		collection = collectionRepository.findCollectionBycollectionId(collection_Id);
		
		assertNotNull(collection);
		
		int id= collection.getCollectionId();
		
		assertEquals(collection_Id,id);
		
		assertEquals(collection.getItem().contains(item),true);
		
	}
	
	@Test
	public void testPersistAndLoadCustomer() {
		int customer_Id= 4;
		
		Customer customer = new Customer();
		
		PaymentCredentials paymentCredentials = new PaymentCredentials();
		
		paymentCredentials.setPaymentCredentialsId(85);
		
		customer.setCustomerId(customer_Id);
		
		customer.setPaymentCredentials(paymentCredentials);
		
		customerRepository.save(customer);
		
		customer = null;
		
		customer = customerRepository.findCustomerBycustomerId(customer_Id);
		
		assertNotNull(customer);
		
		int id= customer.getCustomerId();
		
		assertEquals(customer_Id,id);
		
		assertEquals(customer.getPaymentCredentials().getPaymentCredentialsId(),85);
		
	}
	
	@Test
	public void testPersistAndLoadDelivery() {
		int delivery_Id= 5;
		
		Delivery delivery = new Delivery();
		
		ItemOrder itemOrder = new ItemOrder();
		
		itemOrder.setItemOrderId(86);
		
		delivery.setDeliveryId(delivery_Id);
		
		delivery.setItemOrder(itemOrder);
		
		deliveryRepository.save(delivery);
		
		delivery = null;
		
		delivery = deliveryRepository.findDeliveryBydeliveryId(delivery_Id);
		
		assertNotNull(delivery);
		
		int id= delivery.getDeliveryId();
		
		assertEquals(delivery_Id,id);
		
		assertEquals(delivery.getItemOrder().getItemOrderId(),86);
		
	}
	
	@Test
	public void testPersistAndLoadItem() {
		int item_Id= 6;
		
		Item item = new Item();
		
		Collection collection = new Collection();
		
		collection.setCollectionId(87);
		
		item.setItemId(item_Id);
		
		item.setCollection(collection);
		
		itemRepository.save(item);
		
		item = null;
		
		item = itemRepository.findItemByitemId(item_Id);
		
		assertNotNull(item);
		
		int id= item.getItemId();
		
		assertEquals(item_Id,id);
		
		assertEquals(item.getCollection().getCollectionId(),87);
		
	}
	
	@Test
    public void testPersistAndLoadItemOrder() {
        int itemOrder_Id= 6;
        
        ItemOrder itemOrder = new ItemOrder();
        
        itemOrder.setItemOrderId(itemOrder_Id);
        
        itemOrderRepository.save(itemOrder);
        
        itemOrder = null;
        
        itemOrder = itemOrderRepository.findByitemOrderId(itemOrder_Id);
        
        assertNotNull(itemOrder);
        
        int id= itemOrder.getItemOrderId();
        
        assertEquals(itemOrder_Id,id);
        
    }
	
	@Test
    public void testPersistAndLoadNotificationHandler() {
        int notificationHandler_Id= 6;
        
        NotificationHandler notificationHandler = new NotificationHandler();
        
        notificationHandler.setNotificationHandlerId(notificationHandler_Id);
        
        notificationHandlerRepository.save(notificationHandler);
        
        notificationHandler = null;
        
        notificationHandler = notificationHandlerRepository.findBynotificationHandlerId(notificationHandler_Id);
        
        assertNotNull(notificationHandler);
        
        int id= notificationHandler.getNotificationHandlerId();
        
        assertEquals(notificationHandler_Id,id);
        
    }
	
	@Test
    public void testPersistAndLoadOrderConfirmationEmail() {
        int orderConfirmationEmail_Id= 6;
        
        OrderConfirmationEmail orderConfirmationEmail = new OrderConfirmationEmail();
        
        orderConfirmationEmail.setOrderConfirmationId(orderConfirmationEmail_Id);
        
        orderConfirmationEmailRepository.save(orderConfirmationEmail);
        
        orderConfirmationEmail = null;
        
        orderConfirmationEmail = orderConfirmationEmailRepository.findByorderConfirmationId(orderConfirmationEmail_Id);
        
        assertNotNull(orderConfirmationEmail);
        
        int id= orderConfirmationEmail.getOrderConfirmationId();
        
        assertEquals(orderConfirmationEmail_Id,id);
        
    }
	
	@Test
    public void testPersistAndLoadPaymentCredentials() {
        int paymentCredentials_Id= 6;
        
        PaymentCredentials paymentCredentials = new PaymentCredentials();
        
        paymentCredentials.setPaymentCredentialsId(paymentCredentials_Id);
        
        paymentCredentialsRepository.save(paymentCredentials);

        Customer customer = new Customer();
        
        customer.setCustomerId(123);
        
        paymentCredentials.setCustomer(customer);
        
        paymentCredentials = null;
        
        paymentCredentials = paymentCredentialsRepository.findBypaymentCredentialsId(paymentCredentials_Id);
        
        assertNotNull(paymentCredentials);
        
        int id= paymentCredentials.getPaymentCredentialsId();
        
        assertEquals(paymentCredentials_Id,id);
        
        assertEquals(paymentCredentials.getCustomer(), customer);        
    }
	
	
	@Test
    public void testPersistAndLoadServiceProvider() {
        int serviceProvider_Id= 6;
        
        ServiceProvider serviceProvider = new ServiceProvider();
        
        serviceProvider.setServiceProviderId(serviceProvider_Id);
        
        serviceProviderRepository.save(serviceProvider);
        
        ArtGallerySystem gallery = new ArtGallerySystem();
        
        gallery.setArtGalleryId(123);
        
        serviceProvider.setArtGallerySystem(gallery);
        
        serviceProvider = null;
        
        serviceProvider = serviceProviderRepository.findByserviceProviderId(serviceProvider_Id);
        
        assertNotNull(serviceProvider);
        
        int id= serviceProvider.getServiceProviderId();
        
        assertEquals(serviceProvider_Id,id);
        
        assertEquals(serviceProvider.getArtGallerySystem(), gallery);
        
    }
	
	@Test
    public void testPersistAndLoadShoppingCart() {
        int shoppingCart_Id= 6;
        
        ShoppingCart shoppingCart = new ShoppingCart();
        
        shoppingCart.setShoppingCartId(shoppingCart_Id);
        
        shoppingCartRepository.save(shoppingCart);
        
        Customer customer = new Customer();
        
        customer.setCustomerId(123);
        
        shoppingCart.setCustomer(customer);
        
        shoppingCart = null;
        
        shoppingCart = shoppingCartRepository.findByshoppingCartId(shoppingCart_Id);
        
        assertNotNull(shoppingCart);
        
        int id= shoppingCart.getShoppingCartId();
        
        assertEquals(shoppingCart_Id,id);
        
        assertEquals(shoppingCart.getCustomer(), customer);
        
    }
	
    /* UserRole class cannot be instantiated
     *
     * @Test public void testPersistAndLoadUserRole() { int userRole_Id= 6;
     * 
     * UserRole userRole = new UserRole();
     * 
     * userRole.setUserRoleId(userRole_Id);
     * 
     * userRoleRepository.save(userRole);
     * 
     * userRole = null;
     * 
     * userRole = userRoleRepository.findByuserRoleId(userRole_Id);
     * 
     * assertNotNull(userRole);
     * 
     * int id= userRole.getUserRoleId();
     * 
     * assertEquals(userRole_Id,id);
     * 
     * }
     */
	
	
	
}
