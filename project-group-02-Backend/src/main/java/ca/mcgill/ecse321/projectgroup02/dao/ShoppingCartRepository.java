package ca.mcgill.ecse321.projectgroup02.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ca.mcgill.ecse321.projectgroup02.model.ShoppingCart;

@Repository
public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, String> {
	
	long count();
	
	ShoppingCart findByshoppingCartId(int shoppingCartId);
	
	Iterable<ShoppingCart> findAll();
	
	void deleteByshoppingCartId(int shoppingCartId);
	
	void deleteAll();
	
	boolean existsByshoppingCartId(int shoppingCartId);
	
	ShoppingCart save(ShoppingCart shoppingCart);
	
	
}