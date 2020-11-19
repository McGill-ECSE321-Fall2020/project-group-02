  
package ca.mcgill.ecse321.projectgroup02.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ca.mcgill.ecse321.projectgroup02.model.Artist;

@Repository
public interface ArtistRepository extends CrudRepository<Artist, String>{

	Artist findByuserRoleId(int id);
	

	
}