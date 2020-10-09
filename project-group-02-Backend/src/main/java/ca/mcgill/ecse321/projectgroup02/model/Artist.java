package ca.mcgill.ecse321.projectgroup02.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "artist")
public class Artist{
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
   }
