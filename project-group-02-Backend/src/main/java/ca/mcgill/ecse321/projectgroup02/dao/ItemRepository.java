package ca.mcgill.ecse321.projectgroup02.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ca.mcgill.ecse321.projectgroup02.model.Item;

@Repository
public interface ItemRepository extends CrudRepository<Item, String>{

	Item findItemByitemId(int id);
	
}