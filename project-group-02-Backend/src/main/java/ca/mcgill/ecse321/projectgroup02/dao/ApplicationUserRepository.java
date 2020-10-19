package ca.mcgill.ecse321.projectgroup02.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ca.mcgill.ecse321.projectgroup02.model.ApplicationUser;

@Repository
public interface ApplicationUserRepository extends CrudRepository<ApplicationUser, String> {
	
	long count();
	
	ApplicationUser findByapplicationUserId(int userID);
	
	Iterable<ApplicationUser> findAll();
	
	void deleteByapplicationUserId(int userID);
	
	void deleteAll();
	
	boolean existsByapplicationUserId(int userID);
	
	ApplicationUser save(ApplicationUser user);
	
}