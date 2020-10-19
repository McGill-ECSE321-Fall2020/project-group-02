  
package ca.mcgill.ecse321.projectgroup02.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ca.mcgill.ecse321.projectgroup02.model.Artist;

@Repository
public interface ArtistRepository extends CrudRepository<Artist, String>{

	long count();
	
	Artist findByuserRoleId(int id);
	
	Iterable<Artist> findAll();
	
	void deleteByuserRoleId(int id);
	
	void deleteAll();
	
	boolean existsByuserRoleId(int id);
	
	Artist save(Artist artist);
	
}