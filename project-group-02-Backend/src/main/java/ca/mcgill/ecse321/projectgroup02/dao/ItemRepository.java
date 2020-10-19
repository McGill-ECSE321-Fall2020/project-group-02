package ca.mcgill.ecse321.projectgroup02.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ca.mcgill.ecse321.projectgroup02.model.Item;

@Repository
public interface ItemRepository extends CrudRepository<Item, String>{

	long count();
	
	Item findItemByitemId(int id);
	
	Iterable<Item> findAll();
	
	void deleteByitemId(int id);
	
	void deleteAll();
	
	boolean existsByitemId(int id);
	
	Item save(Item item);
	
}