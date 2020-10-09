package ca.mcgill.ecse321.projectgroup02.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Id;

@Entity
public class Customer extends UserRole{
private PaymentCredentials paymentCredentials;

@OneToOne(mappedBy="customer")
public PaymentCredentials getPaymentCredentials() {
   return this.paymentCredentials;
}

public void setPaymentCredentials(PaymentCredentials paymentCredentials) {
   this.paymentCredentials = paymentCredentials;
}

private ShoppingCart shoppingCart;

@OneToOne(mappedBy="customer")
public ShoppingCart getShoppingCart() {
   return this.shoppingCart;
}

public void setShoppingCart(ShoppingCart shoppingCart) {
   this.shoppingCart = shoppingCart;
}

private int customerId;

public void setCustomerId(int value) {
this.customerId = value;
    }
@Id
public int getCustomerId() {
return this.customerId;
       }
   }
