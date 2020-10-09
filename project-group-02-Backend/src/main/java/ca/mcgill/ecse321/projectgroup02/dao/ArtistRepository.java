package ca.mcgill.ecse321.projectgroup02.dao;

import org.springframework.data.repository.CrudRepository;


import ca.mcgill.ecse321.projectgroup02.model.Artist;

public interface ArtistRepository extends CrudRepository<Artist, String>{

	long count();
	
	Artist findByartistId(int id);
	
	Iterable<Artist> findAll();
	
	void deleteByartistId(int id);
	
	void deleteAll();
	
	boolean existsByartistId(int id);
	
	Artist save(Artist artist);
	
}