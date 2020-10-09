package ca.mcgill.ecse321.projectgroup02.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public class ApplicationUser{
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
private int applicationUserId;

public void setApplicationUserId(int value) {
this.applicationUserId = value;
    }
@Id
public int getApplicationUserId() {
return this.applicationUserId;
    }
private ShoppingCart shoppingCart;

@OneToOne(mappedBy="applicationUser", optional=false)
public ShoppingCart getShoppingCart() {
   return this.shoppingCart;
}

public void setShoppingCart(ShoppingCart shoppingCart) {
   this.shoppingCart = shoppingCart;
}

private Set<ItemOrder> itemOrder;

@OneToMany(mappedBy="applicationUser")
public Set<ItemOrder> getItemOrder() {
   return this.itemOrder;
}

public void setItemOrder(Set<ItemOrder> itemOrders) {
   this.itemOrder = itemOrders;
}

private Set<OrderConfirmationEmail> orderConfirmationEmail;

@OneToMany(mappedBy="applicationUser")
public Set<OrderConfirmationEmail> getOrderConfirmationEmail() {
   return this.orderConfirmationEmail;
}

public void setOrderConfirmationEmail(Set<OrderConfirmationEmail> orderConfirmationEmails) {
   this.orderConfirmationEmail = orderConfirmationEmails;
}

private Set<UserRole> userRole;

@OneToMany(mappedBy="applicationUser")
public Set<UserRole> getUserRole() {
   return this.userRole;
}

public void setUserRole(Set<UserRole> userRoles) {
   this.userRole = userRoles;
}

private Set<Address> address;

@OneToMany(mappedBy="applicationUser")
public Set<Address> getAddress() {
   return this.address;
}

public void setAddress(Set<Address> addresss) {
   this.address = addresss;
}

private ArtGallerySystem artGallerySystem;

@OneToOne(optional=false)
public ArtGallerySystem getArtGallerySystem() {
   return this.artGallerySystem;
}

public void setArtGallerySystem(ArtGallerySystem artGallerySystem) {
   this.artGallerySystem = artGallerySystem;
}

}
