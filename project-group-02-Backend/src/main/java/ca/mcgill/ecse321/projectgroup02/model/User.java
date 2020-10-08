package ca.mcgill.ecse321.projectgroup02.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;
import javax.persistence.OneToOne;
import javax.persistence.Id;

@Entity
public class User{
private String accountCreationDate;
   
   public void setAccountCreationDate(String value) {
this.accountCreationDate = value;
    }
public String getAccountCreationDate() {
return this.accountCreationDate;
    }
private String username;

public void setUsername(String value) {
this.username = value;
    }
public String getUsername() {
return this.username;
    }
private String email;

public void setEmail(String value) {
this.email = value;
    }
public String getEmail() {
return this.email;
    }
private String password;

public void setPassword(String value) {
this.password = value;
    }
public String getPassword() {
return this.password;
    }
private String phoneNumber;

public void setPhoneNumber(String value) {
this.phoneNumber = value;
    }
public String getPhoneNumber() {
return this.phoneNumber;
    }
private boolean isLoggedIn;

public void setIsLoggedIn(boolean value) {
this.isLoggedIn = value;
    }
public boolean isIsLoggedIn() {
return this.isLoggedIn;
    }
private Set<OrderConfirmationEmail> orderConfirmationEmail;

@OneToMany(mappedBy="receiver")
public Set<OrderConfirmationEmail> getOrderConfirmationEmail() {
   return this.orderConfirmationEmail;
}

public void setOrderConfirmationEmail(Set<OrderConfirmationEmail> orderConfirmationEmails) {
   this.orderConfirmationEmail = orderConfirmationEmails;
}

private Set<Address> address;

@ManyToMany
public Set<Address> getAddress() {
   return this.address;
}

public void setAddress(Set<Address> addresss) {
   this.address = addresss;
}

private ArtGallerySystem artGallerySystem;

@ManyToOne(optional=false)
public ArtGallerySystem getArtGallerySystem() {
   return this.artGallerySystem;
}

public void setArtGallerySystem(ArtGallerySystem artGallerySystem) {
   this.artGallerySystem = artGallerySystem;
}

private ShoppingCart shoppingCart1;

@OneToOne(mappedBy="user", cascade={CascadeType.ALL}, optional=false)
public ShoppingCart getShoppingCart1() {
   return this.shoppingCart1;
}

public void setShoppingCart1(ShoppingCart shoppingCart1) {
   this.shoppingCart1 = shoppingCart1;
}

private Set<Order> orders;

@OneToMany(mappedBy="user", cascade={CascadeType.ALL})
public Set<Order> getOrders() {
   return this.orders;
}

public void setOrders(Set<Order> orderss) {
   this.orders = orderss;
}

private int userId;

public void setUserId(int value) {
this.userId = value;
    }
@Id
public int getUserId() {
return this.userId;
    }
private Set<UserRole> userRole;

@OneToMany(mappedBy="user")
public Set<UserRole> getUserRole() {
   return this.userRole;
}

public void setUserRole(Set<UserRole> userRoles) {
   this.userRole = userRoles;
}

}
