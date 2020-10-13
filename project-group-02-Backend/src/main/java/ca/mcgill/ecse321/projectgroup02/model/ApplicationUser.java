package ca.mcgill.ecse321.projectgroup02.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

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
private ArtGallerySystem artGallerySystem;

@ManyToOne(optional=true)
@NotFound(action = NotFoundAction.IGNORE) 
public ArtGallerySystem getArtGallerySystem() {
   return this.artGallerySystem;
}

public void setArtGallerySystem(ArtGallerySystem artGallerySystem) {
   this.artGallerySystem = artGallerySystem;
}
@Column(nullable = false) //Tell JPA users must be non-null
@ElementCollection //Resolves "Failed to load ApplicationContext"
private Set<UserRole> userRole;

@OneToMany
@NotFound(action = NotFoundAction.IGNORE) 
public Set<UserRole> getUserRole() {
   return this.userRole;
}

public void setUserRole(Set<UserRole> userRoles) {
   this.userRole = userRoles;
}

@Column(nullable = false) //Tell JPA users must be non-null
@ElementCollection //Resolves "Failed to load ApplicationContext"
private Set<ItemOrder> itemOrder;

@OneToMany
@NotFound(action = NotFoundAction.IGNORE) 
public Set<ItemOrder> getItemOrder() {
   return this.itemOrder;
}

public void setItemOrder(Set<ItemOrder> itemOrders) {
   this.itemOrder = itemOrders;
}

@Column(nullable = false) //Tell JPA users must be non-null
@ElementCollection //Resolves "Failed to load ApplicationContext"
private Set<Address> address;

@OneToMany
@NotFound(action = NotFoundAction.IGNORE) 
public Set<Address> getAddress() {
   return this.address;
}

public void setAddress(Set<Address> addresss) {
   this.address = addresss;
}



}
