package ca.mcgill.ecse321.projectgroup02.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ca.mcgill.ecse321.projectgroup02.model.ServiceProvider;

@Repository
public interface ServiceProviderRepository extends CrudRepository<ServiceProvider, String> {

	ServiceProvider findByuserRoleId(int serviceProvider);

	
}