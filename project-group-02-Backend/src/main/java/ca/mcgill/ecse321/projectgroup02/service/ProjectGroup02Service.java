package ca.mcgill.ecse321.projectgroup02.service;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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
import ca.mcgill.ecse321.projectgroup02.model.ApplicationUser;
import ca.mcgill.ecse321.projectgroup02.model.Customer;
import ca.mcgill.ecse321.projectgroup02.model.PaymentCredentials;
import ca.mcgill.ecse321.projectgroup02.model.ShoppingCart;
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
    
    /**
     * @param username
     * @param email
     * @param password
     * @return Instance of ApplicationUser if the method successfully created the user
     * @throws Exception 
     */
    @Transactional
    public ApplicationUser createUser(String username, String email, String password) throws Exception {
        
        try {
          validateRegistrationInput(username, email, password);
        }catch(Exception e) {
          throw new Exception(e.getMessage());
        }
      
        ApplicationUser user = new ApplicationUser();
        Iterable<ApplicationUser> users = applicationUserRepository.findAll();
        for (ApplicationUser user_ : users) {
          if (user_.getEmail().equals(email) || user_.getUsername().equals(username)) {
            throw new Exception("Username/Email already in use");
          }
        }
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setAccountCreationDate(java.time.LocalDate.now().toString());
        applicationUserRepository.save(user);
        return user;
    }
    
    private void validateRegistrationInput(String username, String email, String password) throws Exception {
        if (username.length() < 8)
          throw new Exception("Username must have at least 8 characters");
        if (!email.contains("@"))
          throw new Exception("Email is not valid");
        if (password.length() < 8)
          throw new Exception("Password must have at least 8 characters");
    }
    
    @Transactional
    public ApplicationUser getUser(String username) {
        return applicationUserRepository.findByUsername(username);
    }
    
    @Transactional
    public Iterable<ApplicationUser> getAllUsers() {
        return (applicationUserRepository.findAll());
    }
    
    @Transactional
    public ApplicationUser updateUserCredentials(String username, 
            String cardHolderName, String ccNumber, String expirationDate, String cvc) throws Exception {
        ApplicationUser user = applicationUserRepository.findByUsername(username);
        Customer customer = null;
        
        for (UserRole role : user.getUserRole()) {
          if (role instanceof Customer)
            customer = (Customer) role;
        }
        
        if (customer == null) 
          throw new Exception("User must be a customer");
        
        PaymentCredentials paymentCredentials = new PaymentCredentials();
        paymentCredentials.setCardHolderName(cardHolderName);
        paymentCredentials.setCcNumber(ccNumber);
        paymentCredentials.setCvc(cvc);
        paymentCredentials.setExpirationDate(expirationDate);
        
        customer.getPaymentCredentials().add(paymentCredentials);

        paymentCredentialsRepository.save(paymentCredentials);
        customerRepository.save(customer);
        applicationUserRepository.save(user);
        return user;
    }
    
    @Transactional
    public ApplicationUser updateUserRole(String username, 
            HashSet<UserRole> role) {
        ApplicationUser user = applicationUserRepository.findByUsername(username);
        
        user.setUserRole(role);
        applicationUserRepository.save(user);
        return user;
    }
    
    @Transactional
    public void logoutUser(String username) {
        ApplicationUser user = applicationUserRepository.findByUsername(username);
        user.setIsLoggedIn(false);
    }
    
    @Transactional
    public boolean loginUser(String username, String password) {
        ApplicationUser user = applicationUserRepository.findByUsername(username);
        if (user.getPassword().equals(password)) {
          user.setIsLoggedIn(true);
          return true;
          }        
        return false;
    }
    
    @Transactional
    public ShoppingCart createShoppingCart(Customer customer) {
    	ShoppingCart shoppingCart = new ShoppingCart();
    	HashSet<ShoppingCart> shoppingCarts = new HashSet<ShoppingCart>();
    	customer.setShoppingCart(shoppingCarts);
    	shoppingCartRepository.save(shoppingCart);
    	return shoppingCart;
    }
    
    @Transactional
    public ShoppingCart getShoppingCart(Customer customer) {
    	ShoppingCart shoppingCart = (ShoppingCart) customer.getShoppingCart();
    	return shoppingCart;
    }
    
    
    @Transactional
    public List<ShoppingCart> getAllShoppingCarts() {
    	shoppingCartRepository.findAll();
    	return toList(shoppingCartRepository.findAll());
    }
    

	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
    
}
