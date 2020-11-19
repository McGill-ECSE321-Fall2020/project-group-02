package ca.mcgill.ecse321.projectgroup02.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ca.mcgill.ecse321.projectgroup02.model.Address;

@Repository
public interface AddressRepository extends CrudRepository<Address, String>{
	
	Address findByPostalCode(String postalCode);
	
}