package ca.mcgill.ecse321.projectgroup02.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
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

	private static final String APPLICATION_USER_KEY = "TestApplicationUser";
	private static final String NONEXISTING_USER_KEY = "NotAnApplicationUser";

	@BeforeEach
	public void setMockOutput() {
		// mock for ArtGallerySystem
		lenient().when(artGallerySystemRepository.findByartGalleryId(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(AGS_KEY)) {
				ArtGallerySystem ags = new ArtGallerySystem();
				ags.setArtGalleryId(1);
				return ags;
			} else {
				return null;
			}
		});

		// mock for ApplicationUser
		lenient().when(applicationUserRepository.findByUsername(anyString())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(AGS_KEY)) {
				ApplicationUser applicationUser = new ApplicationUser();
				applicationUser.setUsername(APPLICATION_USER_KEY);
				return applicationUser;
			} else {
				return null;
			}
		});

		// Return the parameter object when anything is saved
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};



		lenient().when(addressRepository.save(any(Address.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(artistRepository.save(any(Artist.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(itemRepository.save(any(Item.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(collectionRepository.save(any(Collection.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(customerRepository.save(any(Customer.class))).thenAnswer(returnParameterAsAnswer);	
		lenient().when(itemOrderRepository.save(any(ItemOrder.class))).thenAnswer(returnParameterAsAnswer);	
		lenient().when(paymentCredentialsRepository.save(any(PaymentCredentials.class))).thenAnswer(returnParameterAsAnswer);	
		lenient().when(serviceProviderRepository.save(any(ServiceProvider.class))).thenAnswer(returnParameterAsAnswer);	
		lenient().when(shoppingCartRepository.save(any(ShoppingCart.class))).thenAnswer(returnParameterAsAnswer);	

	}

	public void testCreateArtGallery() {

	}
}
