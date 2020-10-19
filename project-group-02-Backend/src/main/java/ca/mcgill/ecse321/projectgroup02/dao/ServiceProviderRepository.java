package ca.mcgill.ecse321.projectgroup02.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ca.mcgill.ecse321.projectgroup02.model.ServiceProvider;

@Repository
public interface ServiceProviderRepository extends CrudRepository<ServiceProvider, String> {
	
	long count();
	
	ServiceProvider findByuserRoleId(int serviceProvider);
	
	Iterable<ServiceProvider> findAll();
	
	void deleteByuserRoleId(int serviceProvider);
	
	void deleteAll();
	
	boolean existsByuserRoleId(int serviceProvider);
	
	ServiceProvider save(ServiceProvider serviceProvider);
	
}