package ca.mcgill.ecse321.projectgroup02.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="delivery")
public class Delivery{
private double deliveryPrice;
   
   public void setDeliveryPrice(double value) {
this.deliveryPrice = value;
    }
public double getDeliveryPrice() {
return this.deliveryPrice;
    }
private int deliveryId;

public void setDeliveryId(int value) {
this.deliveryId = value;
    }
@Id
public int getDeliveryId() {
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
