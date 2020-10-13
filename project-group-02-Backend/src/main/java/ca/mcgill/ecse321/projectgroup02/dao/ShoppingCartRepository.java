package ca.mcgill.ecse321.projectgroup02.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.projectgroup02.model.ShoppingCart;


public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, String> {
	
	long count();
	
	ShoppingCart findByshoppingCartId(int shoppingCartId);
	
	Iterable<ShoppingCart> findAll();
	
	void deleteByshoppingCartId(int shoppingCartId);
	
	void deleteAll();
	
	boolean existsByshoppingCartId(int shoppingCartId);
	
	ShoppingCart save(ShoppingCart shoppingCart);
	
	
}