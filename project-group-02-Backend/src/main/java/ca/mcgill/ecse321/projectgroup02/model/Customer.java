package ca.mcgill.ecse321.projectgroup02.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public class Customer extends UserRole{
private int customerId;
   
   public void setCustomerId(int value) {
this.customerId = value;
    }
@Id
public int getCustomerId() {
return this.customerId;
    }
private String/*No type specified!*/ shoppingCart;

public void setShoppingCart(String/*No type specified!*/ value) {
this.shoppingCart = value;
    }
public String/*No type specified!*/ getShoppingCart() {
return this.shoppingCart;
    }
private Set<PaymentCredentials> paymentCredential;

@OneToMany(mappedBy="customer")
public Set<PaymentCredentials> getPaymentCredential() {
   return this.paymentCredential;
}

public void setPaymentCredential(Set<PaymentCredentials> paymentCredentials) {
   this.paymentCredential = paymentCredentials;
}

}
