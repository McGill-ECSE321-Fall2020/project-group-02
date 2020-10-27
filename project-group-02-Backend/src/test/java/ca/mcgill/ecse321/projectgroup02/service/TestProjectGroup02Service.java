package ca.mcgill.ecse321.projectgroup02.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.incrementer.SybaseAnywhereMaxValueIncrementer;

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

	private static final int AGS_KEY = 1;
	private static final int NONEXISTING__AGS_KEY = 0;


	@BeforeEach
	public void setMockOutput() {
		// mock for ArtGallerySystem
		lenient().when(artGallerySystemRepository.findByartGalleryId(anyInt()))
				.thenAnswer((InvocationOnMock invocation) -> {
					if (invocation.getArgument(0).equals(AGS_KEY)) {
						ArtGallerySystem ags = new ArtGallerySystem();
						ags.setArtGalleryId(AGS_KEY);
						return ags;
					} else {
						return null;
					}
				});
		

		// Return the parameter object when anything is saved
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

	@Test
	public void testCreateUser() throws Exception {
		String error = null;

		ApplicationUser user = null;
		try {
			user = service.createUser("asmaajerroumi", "asmaa@gmail.com", "asmaamcgill");
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNotNull(user);
		assertEquals("asmaajerroumi", user.getUsername());
		assertEquals("asmaa@gmail.com", user.getEmail());
		assertEquals("asmaamcgill", user.getPassword());

	}


	@Test
	public void testCreateAdmin() throws Exception {
		String error = null;

		ApplicationUser admin = null;
		try {
			admin = service.createAdmin("asmaajerroumi", "asmaa@gmail.com", "asmaamcgill");
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertNotNull(admin);
		assertEquals("asmaajerroumi", admin.getUsername());
		assertEquals("asmaa@gmail.com", admin.getEmail());
		assertEquals("asmaamcgill", admin.getPassword());

	}

	@Test
	public void testCreateArtGallery() throws Exception {
		// assertEquals(null,service.getGallery());
		String street = "sherbroke";
		String postalCode = "H4J4H8";
		String province = "Quebec";
		String country = "Canada";
		String city = "Montreal";
		String adminUsername = "Asmaajerroumi";
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
				if (ur.equals((adminUsername + "admin").hashCode())) {
					assertEquals(adminUsername, ap.getUsername());
					assertEquals(adminEmail, ap.getEmail());
				}
			}
		}
	}
	
	@Test
	public void testGetUser() throws Exception {
		String error = null;

		ApplicationUser user = null;
		try {
		user = service.createUser("asmaajerroumi", "asmaa@gmail.com", "asmaamcgill");
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals(user, service.getUser("asmaajerroumi"));
		
	}
	
	@Test
	public void testGetAllUsers() throws Exception {
		String error = null;

		ApplicationUser user1 = null;
		ApplicationUser user2 = null;

		try {
		user1 = service.createUser("asmaajerroumi1", "asmaa1@gmail.com", "asmaamcgill1");
		user2 = service.createUser("asmaajerroumi2", "asmaa2@gmail.com", "asmaamcgill2");

		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		assertEquals(2, service.getAllUsers().size());
		
	}
	
	
	@Test
	public void testUpdateUserCredentials() throws Exception {
		String error = null;
		
		try {
		ApplicationUser us = service.createUser("asmaajerroumi", "asmaa@gmail.com", "asmaamcgill");
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		// NEED TO SET USER CREDENTIALS IN SERVICE LAYER
		
	}
	
	@Test
	public void testSetUserRole() throws Exception {
		String error = null;
		ApplicationUser user = null;
		
		try {
		user = service.createUser("asmaajerroumi", "asmaa@gmail.com", "asmaamcgill");
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		service.setUserRole("asmaajerroumi", "customer");
		for(UserRole ur : user.getUserRole()) {
				assertTrue(ur.equals("customer"));	
		}
		}

	@Test
	public void testLogoutUser() throws Exception {
		String error = null;
		ApplicationUser user = null;
		
		try {
		user = service.createUser("asmaajerroumi", "asmaa@gmail.com", "asmaamcgill");
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		service.logoutUser("asmaajerroumi");
		assertEquals(user.isIsLoggedIn(), false);
	}
	
	@Test
	public void testLoginUser() throws Exception {
		String error = null;
		ApplicationUser user = null;
		
		try {
		user = service.createUser("asmaajerroumi", "asmaa@gmail.com", "asmaamcgill");
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		service.loginUser("asmaajerroumi", "asmaamcgill");
		assertEquals(user.isIsLoggedIn(), true);
	}
	
	@Test
	public void testUpdateUserAddress() throws Exception {
		String error = null;
		ApplicationUser user = null;
		
		try {
		user = service.createUser("asmaajerroumi", "asmaa@gmail.com", "asmaamcgill");
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		
		service.updateUserAddress("asmaajerroumi", "sherbrook", "H4E8H8", "Quebec", "Canada", "Montreal");
		assertEquals("asmaajerroumi", user.getUsername());
		for(Address address : user.getAddress()) {
			assertEquals("sherbrook", address.getStreet());
			assertEquals("H4E8H8", address.getPostalCode());
			assertEquals("Quebec", address.getProvince());
			assertEquals("Canada", address.getCountry());
			assertEquals("Montreal", address.getCity());

		}
	}
}
