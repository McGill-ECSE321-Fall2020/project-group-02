package ca.mcgill.ecse321.projectgroup02.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.projectgroup02.model.OrderConfirmationEmail;

public interface OrderConfirmationEmailRepository extends CrudRepository<OrderConfirmationEmail, String> {
	
	long count();
	
	OrderConfirmationEmail findByorderConfirmationEmailId(int orderConfirmationEmailId);
	
	Iterable<OrderConfirmationEmail> findAll();
	
	void deleteByorderConfirmationEmailId(int orderConfirmationEmailId);
	
	void deleteAll();
	
	boolean existsByorderConfirmationEmailId(int orderConfirmationEmailId);
	
	OrderConfirmationEmail save(OrderConfirmationEmail orderConfirmationEmail);
	
	
}