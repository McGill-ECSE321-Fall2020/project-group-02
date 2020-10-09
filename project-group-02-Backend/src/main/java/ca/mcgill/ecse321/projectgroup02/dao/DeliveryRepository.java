package ca.mcgill.ecse321.projectgroup02.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.projectgroup02.model.Delivery;



public interface DeliveryRepository extends CrudRepository<Delivery, String>{

	long count();
	
	Delivery findDeliveryBydeliveryId(int id);
	
	Iterable<Delivery> findAll();
	
	void deleteBydeliveryId(int id);
	
	void deleteAll();
	
	boolean existsBydeliveryId(int id);
	
	Delivery save(Delivery delivery);
	
}