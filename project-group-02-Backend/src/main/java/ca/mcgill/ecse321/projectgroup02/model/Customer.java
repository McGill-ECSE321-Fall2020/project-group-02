package ca.mcgill.ecse321.projectgroup02.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity
@Table(name="customer")
public class Customer{
private PaymentCredentials paymentCredentials;

@OneToOne(mappedBy="customer", optional=false)
public PaymentCredentials getPaymentCredentials() {
   return this.paymentCredentials;
}

public void setPaymentCredentials(PaymentCredentials paymentCredentials) {
   this.paymentCredentials = paymentCredentials;
}

@OneToOne(mappedBy = "customer")
private ShoppingCart shoppingCart;

public ShoppingCart getShoppingCart() {
   return this.shoppingCart;
}

public void setShoppingCart(ShoppingCart shoppingCart) {
   this.shoppingCart = shoppingCart;
}

@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private int customerId;

public void setCustomerId(int value) {
this.customerId = value;
    }

public int getCustomerId() {
return this.customerId;
       }
   }