package ca.mcgill.ecse321.projectgroup02.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.projectgroup02.model.Address;


public interface AddressRepository extends CrudRepository<Address, String>{

	long count();
	
	Address findAddressByaddressId(int id);
	
	Iterable<Address> findAll();
	
	void deleteByaddressId(int id);
	
	void deleteAll();
	
	boolean existsByaddressId(int id);
	
	Address save(Address address);
	
}