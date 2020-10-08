package ca.mcgill.ecse321.projectgroup02.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public class ArtGallerySystem{
private double totalProfit;
   
   public void setTotalProfit(double value) {
this.totalProfit = value;
    }
public double getTotalProfit() {
return this.totalProfit;
    }
private int artGalleryId;

public void setArtGalleryId(int value) {
this.artGalleryId = value;
    }
@Id
public int getArtGalleryId() {
return this.artGalleryId;
    }
private Address address;

@OneToOne(mappedBy="artGallerySystem", optional=false)
public Address getAddress() {
   return this.address;
}

public void setAddress(Address address) {
   this.address = address;
}

private NotificationHandler notificationHandler;

@OneToOne(mappedBy="artGallerySystem", cascade={CascadeType.ALL}, optional=false)
public NotificationHandler getNotificationHandler() {
   return this.notificationHandler;
}

public void setNotificationHandler(NotificationHandler notificationHandler) {
   this.notificationHandler = notificationHandler;
}

private ServiceProvider serviceProvider;

@OneToOne(mappedBy="artGallerySystem", cascade={CascadeType.ALL}, optional=false)
public ServiceProvider getServiceProvider() {
   return this.serviceProvider;
}

public void setServiceProvider(ServiceProvider serviceProvider) {
   this.serviceProvider = serviceProvider;
}

private Set<Item> items;

@OneToMany(mappedBy="artGallerySystem", cascade={CascadeType.ALL})
public Set<Item> getItems() {
   return this.items;
}

public void setItems(Set<Item> itemss) {
   this.items = itemss;
}

private ApplicationUser user;

@OneToOne(mappedBy="artGallerySystem", cascade={CascadeType.ALL}, optional=false)
public ApplicationUser getUser() {
   return this.user;
}

public void setUser(ApplicationUser user) {
   this.user = user;
}

}
