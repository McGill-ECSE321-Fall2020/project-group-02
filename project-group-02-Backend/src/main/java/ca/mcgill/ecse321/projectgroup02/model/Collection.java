package ca.mcgill.ecse321.projectgroup02.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Set;
import javax.persistence.ManyToMany;
import javax.persistence.Id;

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
private Order order;

@ManyToOne(optional=false)
public Order getOrder() {
   return this.order;
}

public void setOrder(Order order) {
   this.order = order;
}

private Set<Item> item;

@ManyToMany
public Set<Item> getItem() {
   return this.item;
}

public void setItem(Set<Item> items) {
   this.item = items;
}

private int collectionId;

public void setCollectionId(int value) {
this.collectionId = value;
    }
@Id
public int getCollectionId() {
return this.collectionId;
       }
   }
