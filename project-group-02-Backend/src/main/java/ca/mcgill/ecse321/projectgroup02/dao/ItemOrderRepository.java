package ca.mcgill.ecse321.projectgroup02.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ca.mcgill.ecse321.projectgroup02.model.ItemOrder;

@Repository
public interface ItemOrderRepository extends CrudRepository<ItemOrder, String> {
	
	long count();
	
	ItemOrder findByitemOrderId(int itemOrderId);
	
	Iterable<ItemOrder> findAll();
	
	void deleteByitemOrderId(int itemOrderId);
	
	void deleteAll();
	
	boolean existsByitemOrderId(int itemOrderId);
	
	ItemOrder save(ItemOrder itemOrderId);
	
	
}