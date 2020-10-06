import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Delivery{
private DeliveryMethod deliveringMethod;
   
   public void setDeliveringMethod(DeliveryMethod value) {
this.deliveringMethod = value;
    }
public DeliveryMethod getDeliveringMethod() {
return this.deliveringMethod;
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
