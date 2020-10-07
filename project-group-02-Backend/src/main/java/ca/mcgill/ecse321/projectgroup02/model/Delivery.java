package ca.mcgill.ecse321.projectgroup02.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Delivery{
private String/*No type specified!*/ var1;
   
   public void setVar1(String/*No type specified!*/ value) {
this.var1 = value;
    }
public String/*No type specified!*/ getVar1() {
return this.var1;
    }
private double deliveryPrice;

public void setDeliveryPrice(double value) {
this.deliveryPrice = value;
    }
public double getDeliveryPrice() {
return this.deliveryPrice;
    }
private String deliveryId;

public void setDeliveryId(String value) {
this.deliveryId = value;
    }
@Id
public String getDeliveryId() {
return this.deliveryId;
    }
private Order order;

@OneToOne(optional=false)
public Order getOrder() {
   return this.order;
}

public void setOrder(Order order) {
   this.order = order;
}

}
