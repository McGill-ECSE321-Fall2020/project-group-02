package ca.mcgill.ecse321.projectgroup02.model;
import Collection;

import javax.persistence.Entity;
import javax.persistence.CascadeType;
import javax.persistence.OneToOne;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.Id;

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

public void setAddress(Address value) {
this.address = value;
    }
public Address getAddress() {
return this.address;
    }
private NotificationHandler notificationHandler;

@OneToOne(mappedBy="artGallerySystem", cascade={CascadeType.ALL}, optional=false)
public NotificationHandler getNotificationHandler() {
   return this.notificationHandler;
}

public void setNotificationHandler(NotificationHandler notificationHandler) {
   this.notificationHandler = notificationHandler;
}

private Set<Collection> collections;

@OneToMany(cascade={CascadeType.ALL})
public Set<Collection> getCollections() {
   return this.collections;
}

public void setCollections(Set<Collection> collectionss) {
   this.collections = collectionss;
}

private Set<User> users;

@OneToMany(mappedBy="artGallerySystem", cascade={CascadeType.ALL})
public Set<User> getUsers() {
   return this.users;
}

public void setUsers(Set<User> userss) {
   this.users = userss;
}

private Set<ServiceProvider> serviceProvider;

@OneToMany(mappedBy="artGallerySystem", cascade={CascadeType.ALL})
public Set<ServiceProvider> getServiceProvider() {
   return this.serviceProvider;
}

public void setServiceProvider(Set<ServiceProvider> serviceProviders) {
   this.serviceProvider = serviceProviders;
}

private int artGalleryId;

public void setArtGalleryId(int value) {
this.artGalleryId = value;
    }
@Id
public int getArtGalleryId() {
return this.artGalleryId;
       }
   }
