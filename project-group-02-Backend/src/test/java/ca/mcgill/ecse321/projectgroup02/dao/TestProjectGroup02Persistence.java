package ca.mcgill.ecse321.projectgroup02.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.AfterTestClass;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ca.mcgill.ecse321.projectgroup02.model.Address;
import ca.mcgill.ecse321.projectgroup02.model.ApplicationUser;
import ca.mcgill.ecse321.projectgroup02.model.ArtGallerySystem;
import ca.mcgill.ecse321.projectgroup02.model.Artist;
import ca.mcgill.ecse321.projectgroup02.model.Collection;
import ca.mcgill.ecse321.projectgroup02.model.Customer;
import ca.mcgill.ecse321.projectgroup02.model.Item;
import ca.mcgill.ecse321.projectgroup02.model.ItemOrder;
import ca.mcgill.ecse321.projectgroup02.model.NotificationHandler;
import ca.mcgill.ecse321.projectgroup02.model.OrderConfirmationEmail;
import ca.mcgill.ecse321.projectgroup02.model.PaymentCredentials;
import ca.mcgill.ecse321.projectgroup02.model.ServiceProvider;
import ca.mcgill.ecse321.projectgroup02.model.ShoppingCart;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestProjectGroup02Persistence {

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
	private OrderConfirmationEmailRepository orderConfirmationEmailRepository;
	@Autowired
	private PaymentCredentialsRepository paymentCredentialsRepository;
	@Autowired
	private ServiceProviderRepository serviceProviderRepository;
	@Autowired
	private ShoppingCartRepository shoppingCartRepository;
	@Autowired
	private NotificationHandlerRepository notificationHandlerRepository;


	@AfterTestClass
	public void clearDatabase() {
		artGallerySystemRepository.deleteAll();
		applicationUserRepository.deleteAll();
		addressRepository.deleteAll();
		artistRepository.deleteAll();
		itemRepository.deleteAll();
		collectionRepository.deleteAll();
		customerRepository.deleteAll();
		itemOrderRepository.deleteAll();
		orderConfirmationEmailRepository.deleteAll();
		paymentCredentialsRepository.deleteAll();
		serviceProviderRepository.deleteAll();
		shoppingCartRepository.deleteAll();
		notificationHandlerRepository.deleteAll();
	}

	@Test
	public void testPersistAndLoadArtGallerySystem() {

		ArtGallerySystem system = new ArtGallerySystem();

		ApplicationUser user = new ApplicationUser();

		system.setArtGalleryId(1);

		user.setApplicationUserId(2);

		HashSet<ApplicationUser> users = new HashSet<ApplicationUser>();

		users.add(user);

		/**
		 * system.setApplicationUsers(users) must be modified to link each user to the system
		 * See ArtGallerySystem.java line 28-33
		 */


		/**
		 * The user must be saved before the system
		 * The system does not have to be saved before the user
		 * See ApplicationUser.java line 73-74
		 */
		applicationUserRepository.save(user);

		system.setApplicationUsers(users);

		artGallerySystemRepository.save(system);

		system = artGallerySystemRepository.findByartGalleryId(1);

		ApplicationUser user2 = null;

		for(ApplicationUser userIter: system.getApplicationUsers()) {
			user2 = userIter;
		}
		assertEquals(user2.getApplicationUserId(),2);

		assertEquals(system.getArtGalleryId(),1);

	}

	@Test
	public void testPersistAndLoadApplicationUser() {

		ApplicationUser user = new ApplicationUser();

		Address address= new Address();

		address.setAddressId(2);

		HashSet<Address> addresses = new HashSet<Address>();

		addresses.add(address);

		user.setApplicationUserId(1);

		addressRepository.save(address);

		user.setAddress(addresses);

		applicationUserRepository.save(user);

		user = applicationUserRepository.findByapplicationUserId(1);

		Address testAddress = null;

		for(Address addressIter: user.getAddress()) {
			testAddress= addressIter;
		}

		assertEquals(testAddress.getAddressId(),2);

		assertEquals(user.getApplicationUserId(),1);

	}

	@Test
	public void testPersistAndLoadAddress() {

		int addressID = 15;

		Address adrs = new Address();

		adrs.setAddressId(addressID);

		addressRepository.save(adrs);

		adrs = addressRepository.findAddressByaddressId(addressID);

		int id = adrs.getAddressId();

		assertEquals(addressID, id);


	}

	@Test
	public void testPersistAndLoadArtist() {
		int artistId= 2;

		Artist artist= new Artist();

		artist.setUserRoleId(artistId);

		Item item = new Item();

		item.setItemId(3);

		HashSet<Item> items = new HashSet<Item>();

		items.add(item);

		itemRepository.save(item);

		artist.setItem(items);

		artistRepository.save(artist);


		artist = artistRepository.findByuserRoleId(artistId);

		Item itemTest = null;

		for(Item itemIter: artist.getItem()) {
			itemTest = itemIter;
		}

		int id= artist.getUserRoleId();

		assertEquals(itemTest.getItemId(),3);

		assertEquals(artistId,id);


	}

		@Test
		public void testPersistAndLoadCollection() {
			int collection_Id= 3;
	
			Collection collection = new Collection();
	
			collection.setCollectionId(collection_Id);
	
			Item item = new Item();
	
			item.setItemId(1);
	
			HashSet<Item> items = new HashSet<Item>();
	
			items.add(item);
	
			itemRepository.save(item);
			
			//assertNotNull(items);
			
			collection.setItem(items);
	
			collectionRepository.save(collection);
			
	
			collection = collectionRepository.findCollectionBycollectionId(collection_Id);
	
			Item itemTest = null;
			
			for(Item itemIter: collection.getItem()) {
				itemTest = itemIter;
			}
	
			int id= collection.getCollectionId();
	
			assertEquals(collection_Id,id);
			
			assertEquals(itemTest.getItemId(),1);
	
	
		}

	@Test
	public void testPersistAndLoadCustomer() {
		int customer_Id= 4;

		Customer customer = new Customer();

		customer.setUserRoleId(customer_Id);

		ItemOrder newOrder = new ItemOrder();

		newOrder.setItemOrderId(2);

		HashSet<ItemOrder> itemOrders = new HashSet<ItemOrder>();


		itemOrderRepository.save(newOrder);

		itemOrders.add(newOrder);

		customer.setItemOrder(itemOrders);

		customerRepository.save(customer);

		customer = customerRepository.findCustomerByuserRoleId(customer_Id);


		int id= customer.getUserRoleId();

		ItemOrder orderTest = null;

		for(ItemOrder orderIter: customer.getItemOrder()) {
			orderTest= orderIter;
		}

		assertEquals(orderTest.getItemOrderId(),2);

		assertEquals(customer_Id,id);

	}

	@Test
	public void testPersistAndLoadItem() {
		int item_Id= 6;

		Item item = new Item();

		item.setItemId(item_Id);

		Collection collection = new Collection();

		collection.setCollectionId(1);

		collectionRepository.save(collection);

		item.setCollection(collection);

		itemRepository.save(item);


		item = itemRepository.findItemByitemId(item_Id);

		int id= item.getItemId();

		assertEquals(item_Id,id);

		assertEquals(item.getCollection().getCollectionId(),1);
	}

		@Test
		public void testPersistAndLoadItemOrder() {
			int itemOrder_Id= 6;
	
			ItemOrder itemOrder = new ItemOrder();
	
			itemOrder.setItemOrderId(itemOrder_Id);
			
			Customer customer = new Customer();
			
			customer.setUserRoleId(10);
			
			customerRepository.save(customer);
			
			itemOrder.setCustomer(customer);
	
			itemOrderRepository.save(itemOrder);
	
			itemOrder = itemOrderRepository.findByitemOrderId(itemOrder_Id);
	
			int id= itemOrder.getItemOrderId();
	
			assertEquals(itemOrder_Id,id);
			
			assertEquals(itemOrder.getCustomer().getUserRoleId(),10);
	
		}

//		@Test
//	    public void testPersistAndLoadOrderConfirmationEmail() {
//	        
//			int orderConfirmationEmail_Id= 6;
//	        
//	        OrderConfirmationEmail orderConfirmationEmail = new OrderConfirmationEmail();
//	        
//			orderConfirmationEmail.setOrderConfirmationEmailId(orderConfirmationEmail_Id);
//	
//			//ItemOrder itemOrder= new ItemOrder();
//	
//			//itemOrder.setItemOrderId(69);
//	
//			//itemOrderRepository.save(itemOrder);
//			
//			//orderConfirmationEmail.setItemOrder(itemOrder);
//	
//			orderConfirmationEmailRepository.save(orderConfirmationEmail);
//	
//			orderConfirmationEmail = orderConfirmationEmailRepository.findByorderConfirmationEmailId(orderConfirmationEmail_Id);
//			
//			int id = orderConfirmationEmail.getOrderConfirmationEmailId();
//			
//			assertEquals(id,orderConfirmationEmail_Id);
//			
//			//assertEquals(orderConfirmationEmail.getItemOrder().getItemOrderId(),69);
//			
//		
//	    }

	@Test
	public void testPersistAndLoadPaymentCredentials() {
		int paymentCredentials_Id= 6;

		PaymentCredentials paymentCredentials = new PaymentCredentials();

		paymentCredentials.setPaymentCredentialsId(paymentCredentials_Id);

		paymentCredentialsRepository.save(paymentCredentials);

		paymentCredentials = paymentCredentialsRepository.findBypaymentCredentialsId(paymentCredentials_Id);

		int id = paymentCredentials.getPaymentCredentialsId();

		assertEquals(id, paymentCredentials_Id);

	}
	
	@Test
    public void testPersistAndLoadServiceProvider() {
        int serviceProvider_Id= 6;
        
        ServiceProvider serviceProvider = new ServiceProvider();
        
        serviceProvider.setUserRoleId(serviceProvider_Id);
        
        serviceProviderRepository.save(serviceProvider);

        serviceProvider = serviceProviderRepository.findByuserRoleId(serviceProvider_Id);
        
        assertEquals(serviceProvider.getUserRoleId(), serviceProvider_Id);
        
        
    }

	
	@Test
	public void testPersistAndLoadShoppingCart() {
		int shoppingCart_Id= 6;
		
		ShoppingCart shoppingCart = new ShoppingCart();
		
		Item item = new Item();
		
		item.setItemId(8);
		
		HashSet<Item> items = new HashSet<Item>();
		
		items.add(item);
		
		itemRepository.save(item);
		
		shoppingCart.setItem(items);

		shoppingCart.setShoppingCartId(shoppingCart_Id);

		shoppingCartRepository.save(shoppingCart);


		shoppingCart = shoppingCartRepository.findByshoppingCartId(shoppingCart_Id);

		int id= shoppingCart.getShoppingCartId();

		Item foundItem = null;
		
		for(Item itemIter: shoppingCart.getItem()) {
			foundItem = itemIter;
		}
		
		assertEquals(foundItem.getItemId(),8);
		
		assertEquals(shoppingCart_Id,id);

	}
	
	
//	@Test
//    public void testPersistAndLoadNotificationHandler() {
//        
//		ArtGallerySystem AGS = new ArtGallerySystem();
//		
//		AGS.setArtGalleryId(420);
//		
//		int notificationHandler_Id= 6;
//        
//        NotificationHandler notificationHandler = new NotificationHandler();
//        
//        notificationHandler.setNotificationHandlerId(notificationHandler_Id);
//		
//        notificationHandler.setArtGallerySystem(AGS);
//        
//		notificationHandlerRepository.save(notificationHandler);
//		
//		AGS.setNotificationHandler(notificationHandler);
//		
//		artGallerySystemRepository.save(AGS);
//		
//		//OrderConfirmationEmail orderConfirmationEmail= new OrderConfirmationEmail();
//
//		//orderConfirmationEmail.setOrderConfirmationId(71);
//		
//		//Set<OrderConfirmationEmail> orderConfirmationEmailSet= new HashSet<OrderConfirmationEmail>();
//		
//		//orderConfirmationEmailSet.add(orderConfirmationEmail);
//		
//		//setting the order to the user
//	//	notificationHandler.setOrderConfirmationEmail(orderConfirmationEmailSet);
//
//		// save the relational object
//		//orderConfirmationEmailRepository.save(orderConfirmationEmail);
//        
//        notificationHandler = notificationHandlerRepository.findBynotificationHandlerId(notificationHandler_Id);
//        
//        int id = notificationHandler.getNotificationHandlerId();
//        
//		assertEquals(notificationHandler_Id,id);
//		
//		// test the order confirmation email relation
//		//boolean retrievedOrderConfirmationEmail = notificationHandler.getOrderConfirmationEmail().isEmpty();
//		
//		// check if the order confirmation email was retrieved accordingly
//		//assertEquals(retrievedOrderConfirmationEmail,false);
//
//        
//    }







}
