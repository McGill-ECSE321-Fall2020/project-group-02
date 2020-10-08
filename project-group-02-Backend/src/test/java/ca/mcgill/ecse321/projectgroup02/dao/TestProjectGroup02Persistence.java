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

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestProjectGroup02Persistence {

	@Autowired
	private ApplicationUserRepository userRepository;
	@Autowired
	private AddressRepository addressRepository;

	
	@AfterEach
	public void clearDatabase() {
		// First, we clear registrations to avoid exceptions due to inconsistencies
		userRepository.deleteAll();
		addressRepository.deleteAll();
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
}
