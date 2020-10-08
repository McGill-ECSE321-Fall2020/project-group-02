package ca.mcgill.ecse321.projectgroup02.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Delivery{
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
private ItemOrder itemOrder;

@OneToOne(optional=false)
public ItemOrder getItemOrder() {
   return this.itemOrder;
}

public void setItemOrder(ItemOrder itemOrder) {
   this.itemOrder = itemOrder;
}

}
