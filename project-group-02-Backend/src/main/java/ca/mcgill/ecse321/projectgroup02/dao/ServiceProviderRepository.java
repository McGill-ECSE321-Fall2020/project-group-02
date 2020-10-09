package ca.mcgill.ecse321.projectgroup02.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.projectgroup02.model.ServiceProvider;


public interface ServiceProviderRepository extends CrudRepository<ServiceProviderRepository, String> {
	
	long count();
	
	ServiceProvider findByserviceProvider(int serviceProvider);
	
	Iterable<ServiceProvider> findAll();
	
	void deleteByserviceProvider(int serviceProvider);
	
	void deleteAll();
	
	boolean existsByserviceProvider(int serviceProvider);
	
	ServiceProvider save(ServiceProvider serviceProvider);
	
}
