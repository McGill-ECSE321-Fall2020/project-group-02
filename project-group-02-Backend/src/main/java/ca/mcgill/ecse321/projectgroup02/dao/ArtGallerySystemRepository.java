package ca.mcgill.ecse321.projectgroup02.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ca.mcgill.ecse321.projectgroup02.model.ArtGallerySystem;

@Repository
public interface ArtGallerySystemRepository extends CrudRepository<ArtGallerySystem, String> {

	ArtGallerySystem findByartGalleryId(int artGalleryID);

	
}
