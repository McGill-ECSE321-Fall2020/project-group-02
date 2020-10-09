package ca.mcgill.ecse321.projectgroup02.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.projectgroup02.model.ItemOrder;
import ca.mcgill.ecse321.projectgroup02.model.OrderConfirmationEmail;

public interface ItemOrderRepository extends CrudRepository<ItemOrder, String> {
	
	long count();
	
	ItemOrder findByitemOrderId(int itemOrderId);
	
	Iterable<ItemOrder> findAll();
	
	void deleteByitemOrderId(int itemOrderId);
	
	void deleteAll();
	
	boolean existsByitemOrderId(int itemOrderId);
	
	ItemOrder save(ItemOrder itemOrderId);
	
	
}
