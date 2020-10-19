package ca.mcgill.ecse321.projectgroup02.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ca.mcgill.ecse321.projectgroup02.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, String>{

	long count();
	
	Customer findCustomerByuserRoleId(int id);
	
	Iterable<Customer> findAll();
	
	void deleteByuserRoleId(int id);
	
	void deleteAll();
	
	boolean existsByuserRoleId(int id);
	
	Customer save(Customer customer);
	
}