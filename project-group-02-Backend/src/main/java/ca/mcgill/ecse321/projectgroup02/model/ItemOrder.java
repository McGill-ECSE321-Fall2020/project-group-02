package ca.mcgill.ecse321.projectgroup02.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;

@Entity
public class ItemOrder{
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
private int itemOrderId;

public void setItemOrderId(int value) {
this.itemOrderId = value;
    }
@Id
public int getItemOrderId() {
return this.itemOrderId;
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
private Set<Item> item;

@OneToMany(mappedBy="order")
public Set<Item> getItem() {
   return this.item;
}

public void setItem(Set<Item> items) {
   this.item = items;
}

private OrderConfirmationEmail orderConfirmationEmail;

@OneToOne(mappedBy="itemOrder", cascade={CascadeType.ALL}, optional=false)
public OrderConfirmationEmail getOrderConfirmationEmail() {
   return this.orderConfirmationEmail;
}

public void setOrderConfirmationEmail(OrderConfirmationEmail orderConfirmationEmail) {
   this.orderConfirmationEmail = orderConfirmationEmail;
}

private Delivery delivery;

@OneToOne(mappedBy="itemOrder", cascade={CascadeType.ALL}, optional=false)
public Delivery getDelivery() {
   return this.delivery;
}

public void setDelivery(Delivery delivery) {
   this.delivery = delivery;
}

private ApplicationUser applicationUser;

@ManyToOne(optional=false)
public ApplicationUser getApplicationUser() {
   return this.applicationUser;
}

public void setApplicationUser(ApplicationUser applicationUser) {
   this.applicationUser = applicationUser;
}

}
