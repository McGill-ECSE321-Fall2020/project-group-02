package ca.mcgill.ecse321.projectgroup02.service;
import java.util.HashSet;

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
import ca.mcgill.ecse321.projectgroup02.model.PaymentCredentials;
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
	
	@Transactional
	public ApplicationUser createUser(int id, String username, String email, String password, HashSet<UserRole> role, HashSet<Address> address) {
		ApplicationUser user = new ApplicationUser();
		user.setUserRole(role);
		user.setApplicationUserId(id);
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(password);
		user.setAccountCreationDate(java.time.LocalDate.now().toString());
		user.setAddress(address);
		return user;
	}
	
	@Transactional
	public ApplicationUser getUser(int id) {
		return applicationUserRepository.findByapplicationUserId(id);
	}
	
	@Transactional
	public Iterable<ApplicationUser> getAllUsers() {
		return (applicationUserRepository.findAll());
	}
	
	@Transactional
	public ApplicationUser updateUserCredentials(ApplicationUser user, HashSet<PaymentCredentials> paymentCredentials) {
		user.setPaymentCredentials(paymentCredentials);
		return user;
	}
	
	@Transactional
	public ApplicationUser updateUser(ApplicationUser user, int id, String username, String email, String password, 
			HashSet<UserRole> role, HashSet<Address> address, HashSet<PaymentCredentials> paymentCredentials) {
		user.setUserRole(role);
		user.setApplicationUserId(id);
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(password);
		user.setAccountCreationDate(java.time.LocalDate.now().toString());
		user.setAddress(address);
		user.setPaymentCredentials(paymentCredentials);
		return user;
	}
	
}
