import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.Id;

@Entity
public class ShoppingCart{
   private User user;
   
   @OneToOne(optional=false)
   public User getUser() {
      return this.user;
   }
   
   public void setUser(User user) {
      this.user = user;
   }
   
   private double totalPrice;

public void setTotalPrice(double value) {
    this.totalPrice = value;
}
public double getTotalPrice() {
    return this.totalPrice;
}
private double numberOfItems;

public void setNumberOfItems(double value) {
    this.numberOfItems = value;
}
public double getNumberOfItems() {
    return this.numberOfItems;
}
private boolean isEmpty;

public void setIsEmpty(boolean value) {
    this.isEmpty = value;
}
public boolean isIsEmpty() {
    return this.isEmpty;
}
private Set<Item> ;

@OneToMany
public Set<Item> get() {
   return this.;
}

public void set(Set<Item> s) {
   this. = s;
}

private int shoppingCartId;

public void setShoppingCartId(int value) {
    this.shoppingCartId = value;
}
@Id
public int getShoppingCartId() {
    return this.shoppingCartId;
}
}
