package ca.mcgill.ecse321.projectgroup02.model;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
public class Customer extends UserRole{
   
	@ElementCollection //Resolves "Failed to load ApplicationContext"
	private Set<PaymentCredentials> paymentCredentials;
   
	@OneToMany
	@NotFound(action = NotFoundAction.IGNORE) 
    public Set<PaymentCredentials> getPaymentCredentials() {
      return this.paymentCredentials;
   }
   
   public void setPaymentCredentials(Set<PaymentCredentials> paymentCredentialss) {
      this.paymentCredentials = paymentCredentialss;
   }
   
   private ShoppingCart shoppingCart;
   
   @OneToOne
   @NotFound(action = NotFoundAction.IGNORE)
   public ShoppingCart getShoppingCart() {
      return this.shoppingCart;
   }
   
   public void setShoppingCart(ShoppingCart shoppingCart) {
      this.shoppingCart = shoppingCart;
   }
   
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
 
   }
