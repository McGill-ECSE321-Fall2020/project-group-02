package ca.mcgill.ecse321.projectgroup02.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;

@Entity
public class Order{
private double commissionPercentage;
   
   public void setCommissionPercentage(double value) {
this.commissionPercentage = value;
    }
public double getCommissionPercentage() {
return this.commissionPercentage;
    }
private double commissionTotal;

public void setCommissionTotal(double value) {
this.commissionTotal = value;
    }
public double getCommissionTotal() {
return this.commissionTotal;
    }
private int orderId;

public void setOrderId(int value) {
this.orderId = value;
    }
@Id
public int getOrderId() {
return this.orderId;
    }
private String orderDate;

public void setOrderDate(String value) {
this.orderDate = value;
    }
public String getOrderDate() {
return this.orderDate;
    }
private double totalPrice;

public void setTotalPrice(double value) {
this.totalPrice = value;
    }
public double getTotalPrice() {
return this.totalPrice;
    }
private double taxes;

public void setTaxes(double value) {
this.taxes = value;
    }
public double getTaxes() {
return this.taxes;
    }
private Set<Collection> collection;

@OneToMany(mappedBy="order", cascade={CascadeType.ALL})
public Set<Collection> getCollection() {
   return this.collection;
}

public void setCollection(Set<Collection> collections) {
   this.collection = collections;
}

private Delivery delivery;

@OneToOne(mappedBy="order", cascade={CascadeType.ALL}, optional=false)
public Delivery getDelivery() {
   return this.delivery;
}

public void setDelivery(Delivery delivery) {
   this.delivery = delivery;
}

private OrderConfirmationEmail orderConfirmationEmail;

@OneToOne(mappedBy="order", cascade={CascadeType.ALL}, optional=false)
public OrderConfirmationEmail getOrderConfirmationEmail() {
   return this.orderConfirmationEmail;
}

public void setOrderConfirmationEmail(OrderConfirmationEmail orderConfirmationEmail) {
   this.orderConfirmationEmail = orderConfirmationEmail;
}

private Set<Item> orderedItems;

@OneToMany
public Set<Item> getOrderedItems() {
   return this.orderedItems;
}

public void setOrderedItems(Set<Item> orderedItemss) {
   this.orderedItems = orderedItemss;
}

private User user;

@ManyToOne(optional=false)
public User getUser() {
   return this.user;
}

public void setUser(User user) {
   this.user = user;
}

}
