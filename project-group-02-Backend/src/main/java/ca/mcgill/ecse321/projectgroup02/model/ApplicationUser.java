package ca.mcgill.ecse321.projectgroup02.model;

import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
@Id
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
private ArtGallerySystem artGallerySystem;

@ManyToOne(optional=true)
@NotFound(action = NotFoundAction.IGNORE) 
public ArtGallerySystem getArtGallerySystem() {
   return this.artGallerySystem;
}

public void setArtGallerySystem(ArtGallerySystem artGallerySystem) {
   this.artGallerySystem = artGallerySystem;
}
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
