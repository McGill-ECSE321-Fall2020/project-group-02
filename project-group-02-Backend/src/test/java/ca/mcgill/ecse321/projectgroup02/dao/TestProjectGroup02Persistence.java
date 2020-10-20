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
	private PaymentCredentialsRepository paymentCredentialsRepository;
	@Autowired
	private ServiceProviderRepository serviceProviderRepository;
	@Autowired
	private ShoppingCartRepository shoppingCartRepository;


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
		paymentCredentialsRepository.deleteAll();
		serviceProviderRepository.deleteAll();
		shoppingCartRepository.deleteAll();
	}

	@Test
	public void testPersistAndLoadArtGallerySystem() {

		ArtGallerySystem system = new ArtGallerySystem();

		ApplicationUser user = new ApplicationUser();

		system.setArtGalleryId(1);

		user.setUsername("2");

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
		assertEquals(user2.getUsername(),"2");

		assertEquals(system.getArtGalleryId(),1);

	}

	@Test
	public void testPersistAndLoadApplicationUser() {

		ApplicationUser user = new ApplicationUser();

		Address address= new Address();

		address.setPostalCode("2");

		HashSet<Address> addresses = new HashSet<Address>();

		addresses.add(address);

		user.setUsername("1");

		addressRepository.save(address);

		user.setAddress(addresses);

		applicationUserRepository.save(user);

		user = applicationUserRepository.findByUsername("1");

		Address testAddress = null;

		for(Address addressIter: user.getAddress()) {
			testAddress= addressIter;
		}

		assertEquals(testAddress.getPostalCode(),"2");

		assertEquals(user.getUsername(),"1");

	}

	@Test
	public void testPersistAndLoadAddress() {

		String addressID = "15";

		Address adrs = new Address();

		adrs.setPostalCode(addressID);

		addressRepository.save(adrs);

		adrs = addressRepository.findByPostalCode(addressID);

		String id = adrs.getPostalCode();

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
			String collection_Id= "3";
	
			Collection collection = new Collection();
	
			collection.setName(collection_Id);
	
			Item item = new Item();
	
			item.setItemId(1);
	
			HashSet<Item> items = new HashSet<Item>();
	
			items.add(item);
	
			itemRepository.save(item);
			
			//assertNotNull(items);
			
			collection.setItem(items);
	
			collectionRepository.save(collection);
			
	
			collection = collectionRepository.findByName(collection_Id);
	
			Item itemTest = null;
			
			for(Item itemIter: collection.getItem()) {
				itemTest = itemIter;
			}
	
			String id= collection.getName();
	
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

		collection.setName("1");

		collectionRepository.save(collection);

		item.setCollection(collection);

		itemRepository.save(item);


		item = itemRepository.findItemByitemId(item_Id);

		int id= item.getItemId();

		assertEquals(item_Id,id);

		assertEquals(item.getCollection().getName(),"1");
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

	@Test
	public void testPersistAndLoadPaymentCredentials() {
		String paymentCredentials_Id= "6";

		PaymentCredentials paymentCredentials = new PaymentCredentials();

		paymentCredentials.setCcNumber(paymentCredentials_Id);

		paymentCredentialsRepository.save(paymentCredentials);

		paymentCredentials = paymentCredentialsRepository.findByCcNumber(paymentCredentials_Id);

		String id = paymentCredentials.getCcNumber();

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
	
	






}
