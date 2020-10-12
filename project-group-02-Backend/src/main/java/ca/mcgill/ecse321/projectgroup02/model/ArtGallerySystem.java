package ca.mcgill.ecse321.projectgroup02.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;

import java.util.Set;
import javax.persistence.OneToMany;

@Entity
@Table(name="art_gallery_system")
public class ArtGallerySystem{
@Column(nullable = false) //Tell JPA users must be non-null
@ElementCollection //Resolves "Failed to load ApplicationContext"
private Set<ApplicationUser> applicationUsers;

@OneToMany(mappedBy="artGallerySystem")
@NotFound(action = NotFoundAction.IGNORE) 
public Set<ApplicationUser> getApplicationUsers() {
   return this.applicationUsers;
}

public void setApplicationUsers(Set<ApplicationUser> applicationUserss) {
   this.applicationUsers = applicationUserss;
   for (ApplicationUser user : applicationUserss){
   user.setArtGallerySystem(this);
   }
}

private double totalProfit;
   
   public void setTotalProfit(double value) {
this.totalProfit = value;
    }
public double getTotalProfit() {
return this.totalProfit;
    }

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private int artGalleryId;

public void setArtGalleryId(int value) {
this.artGalleryId = value;
    }

public int getArtGalleryId() {
return this.artGalleryId;
    }

@OneToOne(optional=true)
@JoinColumn(name = "addressId") //Tell JPA users must be non-null
private Address address;

@NotFound(action = NotFoundAction.IGNORE) 
public Address getAddress() {
   return this.address;
}

public void setAddress(Address address) {
   this.address = address;
}

@OneToOne
@JoinColumn(name = "notificationHandlerId")
private NotificationHandler notificationHandler;

@NotFound(action = NotFoundAction.IGNORE) 
public NotificationHandler getNotificationHandler() {
   return this.notificationHandler;
}

public void setNotificationHandler(NotificationHandler notificationHandler) {
   this.notificationHandler = notificationHandler;
}

@Column(nullable = false) //Tell JPA users must be non-null
@ElementCollection //Resolves "Failed to load ApplicationContext"
private Set<Item> item;

@OneToMany(mappedBy="artGallerySystem")
@NotFound(action = NotFoundAction.IGNORE) 
public Set<Item> getItem() {
   return this.item;
}

public void setItem(Set<Item> items) {
   this.item = items;
}

}