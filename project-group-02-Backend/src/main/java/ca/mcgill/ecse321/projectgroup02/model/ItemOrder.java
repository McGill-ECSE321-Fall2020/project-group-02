package ca.mcgill.ecse321.projectgroup02.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
public class ItemOrder{

private int itemOrderId;

public void setItemOrderId(int value) {
    this.itemOrderId = value;
}
@Id
public int getItemOrderId() {
    return this.itemOrderId;
}
private String itemOrderDate;

public void setItemOrderDate(String value) {
    this.itemOrderDate = value;
}
public String getItemOrderDate() {
    return this.itemOrderDate;
}

//private double totalPrice;
//
//public void setTotalPrice(double total) {
//	this.totalPrice = total;
//}
//
//public double getTotalPrice() {
//	return this.totalPrice;
//}
   private Customer customer;
   
   @ManyToOne(optional=true)
   @NotFound(action = NotFoundAction.IGNORE) 
   public Customer getCustomer() {
      return this.customer;
   }
   
   public void setCustomer(Customer customer) {
      this.customer = customer;
   }
   
   @Enumerated(EnumType.STRING)
   private DeliveryMethod delivery;

   public void setDelivery(DeliveryMethod value) {
       this.delivery = value;
   }
   public DeliveryMethod getDelivery() {
       return this.delivery;
   }
   
   @ElementCollection //Resolves "Failed to load ApplicationContext"
   private Set<Item> item;
   
   @OneToMany
   @NotFound(action = NotFoundAction.IGNORE)
   public Set<Item> getItem() {
      return this.item;
   }
   
   public void setItem(Set<Item> items) {
      this.item = items;
   }
   
   
   
   }
