package ca.mcgill.ecse321.projectgroup02.model;

import javax.persistence.Entity;
import Address;
import javax.persistence.OneToOne;
import User;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import Collection;
import Conversation;
import Item;

@Entity
public class ArtGallerySystem{
private double totalProfit;
   
   public void setTotalProfit(double value) {
this.totalProfit = value;
    }
public double getTotalProfit() {
return this.totalProfit;
    }
private Address address;

@OneToOne(optional=false)
public Address getAddress() {
   return this.address;
}

public void setAddress(Address address) {
   this.address = address;
}

private Set<User> user;

@OneToMany(mappedBy="artGallerySystem", cascade={CascadeType.ALL})
public Set<User> getUser() {
   return this.user;
}

public void setUser(Set<User> users) {
   this.user = users;
}

private Collection collection;

@OneToOne(mappedBy="artGallerySystem", cascade={CascadeType.ALL}, optional=false)
public Collection getCollection() {
   return this.collection;
}

public void setCollection(Collection collection) {
   this.collection = collection;
}

private Set<Conversation> conversation;

@OneToMany(mappedBy="artGallerySystem")
public Set<Conversation> getConversation() {
   return this.conversation;
}

public void setConversation(Set<Conversation> conversations) {
   this.conversation = conversations;
}

private Set<Item> galleryItem;

@OneToMany(mappedBy="artGallerySystem")
public Set<Item> getGalleryItem() {
   return this.galleryItem;
}

public void setGalleryItem(Set<Item> galleryItems) {
   this.galleryItem = galleryItems;
}

}
