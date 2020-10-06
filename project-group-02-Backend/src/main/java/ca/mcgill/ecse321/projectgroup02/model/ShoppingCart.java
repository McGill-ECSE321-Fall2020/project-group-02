import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public class ShoppingCart{
private String cartId;
   
   public void setCartId(String value) {
this.cartId = value;
    }
@Id
public String getCartId() {
return this.cartId;
    }
private double totalPrice;

public void setTotalPrice(double value) {
this.totalPrice = value;
    }
public double getTotalPrice() {
return this.totalPrice;
    }
private User user;

@OneToOne(optional=false)
public User getUser() {
   return this.user;
}

public void setUser(User user) {
   this.user = user;
}

private Customer customer;

@OneToOne(optional=false)
public Customer getCustomer() {
   return this.customer;
}

public void setCustomer(Customer customer) {
   this.customer = customer;
}

private Set<Item> creations;

@OneToMany(mappedBy="shoppingCart")
public Set<Item> getCreations() {
   return this.creations;
}

public void setCreations(Set<Item> creationss) {
   this.creations = creationss;
}

}
