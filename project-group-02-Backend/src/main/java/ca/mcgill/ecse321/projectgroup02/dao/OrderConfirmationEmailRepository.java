package ca.mcgill.ecse321.projectgroup02.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.projectgroup02.model.OrderConfirmationEmail;

public interface OrderConfirmationEmailRepository extends CrudRepository<OrderConfirmationEmailRepository, String> {
	
	long count();
	
	OrderConfirmationEmail findByorderConfirmationId(int orderConfirmationId);
	
	Iterable<OrderConfirmationEmail> findAll();
	
	void deleteByorderConfirmationId(int orderConfirmationId);
	
	void deleteAll();
	
	boolean existsByorderConfirmationId(int orderConfirmationId);
	
	OrderConfirmationEmail save(OrderConfirmationEmail orderConfirmationEmail);
	
	
}
