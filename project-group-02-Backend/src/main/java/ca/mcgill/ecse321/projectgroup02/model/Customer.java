import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Customer extends UserRole{
private String customerId;
   
   public void setCustomerId(String value) {
this.customerId = value;
    }
@Id
public String getCustomerId() {
return this.customerId;
    }
private Set<PaymentCredential> paymentCredential;

@OneToMany(mappedBy="customer")
public Set<PaymentCredential> getPaymentCredential() {
   return this.paymentCredential;
}

public void setPaymentCredential(Set<PaymentCredential> paymentCredentials) {
   this.paymentCredential = paymentCredentials;
}

private ShoppingCart shoppingCart;

@OneToOne(mappedBy="customer", optional=false)
public ShoppingCart getShoppingCart() {
   return this.shoppingCart;
}

public void setShoppingCart(ShoppingCart shoppingCart) {
   this.shoppingCart = shoppingCart;
}

private Order order;

@OneToOne(mappedBy="buyer", optional=false)
public Order getOrder() {
   return this.order;
}

public void setOrder(Order order) {
   this.order = order;
}

}
