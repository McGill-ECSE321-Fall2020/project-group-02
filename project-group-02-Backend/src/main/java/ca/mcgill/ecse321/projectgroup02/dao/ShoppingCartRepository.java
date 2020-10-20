package ca.mcgill.ecse321.projectgroup02.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ca.mcgill.ecse321.projectgroup02.model.ShoppingCart;

@Repository
public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, String> {
	

	ShoppingCart findByshoppingCartId(int shoppingCartId);
	

	
}