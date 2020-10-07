package ca.mcgill.ecse321.projectgroup02.model;

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
private int artistId;

public void setArtistId(int value) {
this.artistId = value;
    }
@Id
public int getArtistId() {
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
