package ca.mcgill.ecse321.projectgroup02.model;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;

import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
public class Customer extends UserRole{
   
	@Column(nullable = false) //Tell JPA users must be non-null
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
   
   @Column(nullable = false) //Tell JPA users must be non-null
   @ElementCollection //Resolves "Failed to load ApplicationContext"
   private Set<ShoppingCart> shoppingCart;
   
   @OneToMany
   @NotFound(action = NotFoundAction.IGNORE)
   public Set<ShoppingCart> getShoppingCart() {
      return this.shoppingCart;
   }
   
   public void setShoppingCart(Set<ShoppingCart> shoppingCarts) {
      this.shoppingCart = shoppingCarts;
   }
   
   @Column(nullable = false) //Tell JPA users must be non-null
   @ElementCollection //Resolves "Failed to load ApplicationContext"
   private Set<OrderConfirmationEmail> orderConfirmationEmail;
   
   @OneToMany
   @NotFound(action = NotFoundAction.IGNORE)
   public Set<OrderConfirmationEmail> getOrderConfirmationEmail() {
      return this.orderConfirmationEmail;
   }
   
   public void setOrderConfirmationEmail(Set<OrderConfirmationEmail> orderConfirmationEmails) {
      this.orderConfirmationEmail = orderConfirmationEmails;
   }
   
   }
