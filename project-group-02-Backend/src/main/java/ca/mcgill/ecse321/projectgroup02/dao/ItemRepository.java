package ca.mcgill.ecse321.projectgroup02.dao;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.projectgroup02.model.Collection;
import ca.mcgill.ecse321.projectgroup02.model.Item;

@Repository
public interface ItemRepository extends CrudRepository<Item, Integer>{

	Item findItemByitemId(int id);

	
}