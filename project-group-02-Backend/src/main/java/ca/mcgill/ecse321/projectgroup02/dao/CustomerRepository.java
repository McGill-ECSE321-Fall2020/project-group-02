package ca.mcgill.ecse321.projectgroup02.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.projectgroup02.model.Customer;


public interface CustomerRepository extends CrudRepository<Customer, String>{

	long count();
	
	Customer findCustomerBycustomerId(int id);
	
	Iterable<Customer> findAll();
	
	void deleteBycustomerId(int id);
	
	void deleteAll();
	
	boolean existsBycustomerId(int id);
	
	Customer save(Customer customer);
	
}