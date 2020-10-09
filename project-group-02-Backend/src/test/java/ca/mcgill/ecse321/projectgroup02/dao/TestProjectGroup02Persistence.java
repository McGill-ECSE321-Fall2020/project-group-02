package ca.mcgill.ecse321.projectgroup02.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
		
		collectionRepository.save(collection);
		
		collection = null;
		
		collection = collectionRepository.findCollectionBycollectionId(collection_Id);
		
		assertNotNull(collection);
		
		int id= collection.getCollectionId();
		
		assertEquals(collection_Id,id);
		
		
	}
	
	@Test
	public void testPersistAndLoadCustomer() {
		int customer_Id= 4;
		
		Customer customer = new Customer();
		
		customer.setCustomerId(customer_Id);
		
		customerRepository.save(customer);
		
		customer = null;
		
		customer = customerRepository.findCustomerBycustomerId(customer_Id);
		
		assertNotNull(customer);
		
		int id= customer.getCustomerId();
		
		assertEquals(customer_Id,id);
		
	}
	
	@Test
	public void testPersistAndLoadDelivery() {
		int delivery_Id= 5;
		
		Delivery delivery = new Delivery();
		
		delivery.setDeliveryId(delivery_Id);
		
		deliveryRepository.save(delivery);
		
		delivery = null;
		
		delivery = deliveryRepository.findDeliveryBydeliveryId(delivery_Id);
		
		assertNotNull(delivery);
		
		int id= delivery.getDeliveryId();
		
		assertEquals(delivery_Id,id);
		
	}
	
	@Test
	public void testPersistAndLoadItem() {
		int item_Id= 6;
		
		Item item = new Item();
		
		item.setItemId(item_Id);
		
		itemRepository.save(item);
		
		item = null;
		
		item = itemRepository.findItemByitemId(item_Id);
		
		assertNotNull(item);
		
		int id= item.getItemId();
		
		assertEquals(item_Id,id);
		
	}
	
	
	
	
}
