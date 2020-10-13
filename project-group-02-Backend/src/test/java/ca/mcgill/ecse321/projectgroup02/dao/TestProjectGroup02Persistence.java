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

import ca.mcgill.ecse321.projectgroup02.model.ApplicationUser;
import ca.mcgill.ecse321.projectgroup02.model.ArtGallerySystem;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestProjectGroup02Persistence {
	
	@Autowired
	private ArtGallerySystemRepository artGallerySystemRepository;
	@Autowired
	private ApplicationUserRepository applicationUserRepository;
	
	@AfterEach
	public void clearDatabase() {
		artGallerySystemRepository.deleteAll();
		applicationUserRepository.deleteAll();
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
		//system.setApplicationUsers(users);

		/**
		 * The user must be saved before the system
		 * The system does not have to be saved before the user
		 * See ApplicationUser.java line 73-74
		 */
		applicationUserRepository.save(user);
	
		artGallerySystemRepository.save(system);
		
		system = artGallerySystemRepository.findByartGalleryId(1);
		
		assertEquals(system.getArtGalleryId(),1);
		
	}
	
	@Test
	public void testPersistAndLoadApplicationUser() {
		
		ApplicationUser user = new ApplicationUser();

		user.setApplicationUserId(1);
		
		applicationUserRepository.save(user);
		
		user = applicationUserRepository.findByapplicationUserId(1);
		
		assertEquals(user.getApplicationUserId(),1);

	}
	
}
