import javax.persistence.Entity;
import javax.persistence.Id;
import ca.mcgill.ecse321.projectgroup02.model.ArtGallerySystem;
import javax.persistence.ManyToOne;
import java.util.Set;
import javax.persistence.ManyToMany;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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

@ManyToOne(optional=false)
public ArtGallerySystem getArtGallerySystem() {
   return this.artGallerySystem;
}

public void setArtGallerySystem(ArtGallerySystem artGallerySystem) {
   this.artGallerySystem = artGallerySystem;
}

private Set<Address> address;

@ManyToMany(mappedBy="user")
public Set<Address> getAddress() {
   return this.address;
}

public void setAddress(Set<Address> addresss) {
   this.address = addresss;
}

private Set<UserRole> userRole;

@OneToMany(mappedBy="user", cascade={CascadeType.ALL})
public Set<UserRole> getUserRole() {
   return this.userRole;
}

public void setUserRole(Set<UserRole> userRoles) {
   this.userRole = userRoles;
}

private ShoppingCart shoppingCart;

@OneToOne(mappedBy="user", optional=false)
public ShoppingCart getShoppingCart() {
   return this.shoppingCart;
}

public void setShoppingCart(ShoppingCart shoppingCart) {
   this.shoppingCart = shoppingCart;
}

private ChatMessage chatMessage;

@OneToOne(optional=false)
public ChatMessage getChatMessage() {
   return this.chatMessage;
}

public void setChatMessage(ChatMessage chatMessage) {
   this.chatMessage = chatMessage;
}

private Set<OrderConfirmationEmail> orderConfirmationEmail;

@OneToMany(mappedBy="receiver")
public Set<OrderConfirmationEmail> getOrderConfirmationEmail() {
   return this.orderConfirmationEmail;
}

public void setOrderConfirmationEmail(Set<OrderConfirmationEmail> orderConfirmationEmails) {
   this.orderConfirmationEmail = orderConfirmationEmails;
}

private Conversation conversation;

@ManyToOne(optional=false)
public Conversation getConversation() {
   return this.conversation;
}

public void setConversation(Conversation conversation) {
   this.conversation = conversation;
}

}
