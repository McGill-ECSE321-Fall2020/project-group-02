import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public class Artist extends UserRole{
private String description;
   
   public void setDescription(String value) {
this.description = value;
    }
public String getDescription() {
return this.description;
    }
private String artistId;

public void setArtistId(String value) {
this.artistId = value;
    }
@Id
public String getArtistId() {
return this.artistId;
    }
private Set<Item> item;

@OneToMany(mappedBy="creator")
public Set<Item> getItem() {
   return this.item;
}

public void setItem(Set<Item> items) {
   this.item = items;
}

}
