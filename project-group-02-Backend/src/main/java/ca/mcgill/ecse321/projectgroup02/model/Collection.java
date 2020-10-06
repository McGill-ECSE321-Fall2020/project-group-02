import javax.persistence.Entity;
import javax.persistence.Id;
import ca.mcgill.ecse321.projectgroup02.model.ArtGallerySystem;
import javax.persistence.OneToOne;
import java.util.Set;
import javax.persistence.ManyToMany;

@Entity
public class Collection{
private String description;
   
   public void setDescription(String value) {
this.description = value;
    }
public String getDescription() {
return this.description;
    }
private int numberOfItems;

public void setNumberOfItems(int value) {
this.numberOfItems = value;
    }
public int getNumberOfItems() {
return this.numberOfItems;
    }
private String name;

public void setName(String value) {
this.name = value;
    }
@Id
public String getName() {
return this.name;
    }
private String pathToImage;

public void setPathToImage(String value) {
this.pathToImage = value;
    }
public String getPathToImage() {
return this.pathToImage;
    }
private ArtGallerySystem artGallerySystem;

@OneToOne(optional=false)
public ArtGallerySystem getArtGallerySystem() {
   return this.artGallerySystem;
}

public void setArtGallerySystem(ArtGallerySystem artGallerySystem) {
   this.artGallerySystem = artGallerySystem;
}

private Set<Item> item;

@ManyToMany(mappedBy="collection")
public Set<Item> getItem() {
   return this.item;
}

public void setItem(Set<Item> items) {
   this.item = items;
}

}
