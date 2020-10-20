package ca.mcgill.ecse321.projectgroup02.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ca.mcgill.ecse321.projectgroup02.model.ItemOrder;

@Repository
public interface ItemOrderRepository extends CrudRepository<ItemOrder, String> {
	
  ItemOrder findByitemOrderId(int itemOrderId);

	
	
}