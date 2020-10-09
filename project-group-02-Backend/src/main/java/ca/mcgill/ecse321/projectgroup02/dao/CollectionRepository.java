package ca.mcgill.ecse321.projectgroup02.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.projectgroup02.model.Collection;



public interface CollectionRepository extends CrudRepository<Collection, String>{

	long count();
	
	Collection findCollectionBycollectionId(int id);
	
	Iterable<Collection> findAll();
	
	void deleteBycollectionId(int id);
	
	void deleteAll();
	
	boolean existsBycollectionId(int id);
	
	Collection save(Collection collection);
	
}