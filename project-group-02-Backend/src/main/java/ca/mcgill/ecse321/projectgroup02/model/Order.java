import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

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
private String orderNumber;

public void setOrderNumber(String value) {
this.orderNumber = value;
    }
@Id
public String getOrderNumber() {
return this.orderNumber;
    }
private String oderDate;

public void setOderDate(String value) {
this.oderDate = value;
    }
public String getOderDate() {
return this.oderDate;
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
private PaymentCredential paymentCredential;

@OneToOne(mappedBy="order", optional=false)
public PaymentCredential getPaymentCredential() {
   return this.paymentCredential;
}

public void setPaymentCredential(PaymentCredential paymentCredential) {
   this.paymentCredential = paymentCredential;
}

private Set<Item> orderedItem;

@OneToMany(mappedBy="order")
public Set<Item> getOrderedItem() {
   return this.orderedItem;
}

public void setOrderedItem(Set<Item> orderedItems) {
   this.orderedItem = orderedItems;
}

private Delivery delivery;

@OneToOne(mappedBy="order", cascade={CascadeType.ALL}, optional=false)
public Delivery getDelivery() {
   return this.delivery;
}

public void setDelivery(Delivery delivery) {
   this.delivery = delivery;
}

private Customer buyer;

@OneToOne(optional=false)
public Customer getBuyer() {
   return this.buyer;
}

public void setBuyer(Customer buyer) {
   this.buyer = buyer;
}

private OrderConfirmationEmail orderConfirmationEmail;

@OneToOne(mappedBy="order", cascade={CascadeType.ALL}, optional=false)
public OrderConfirmationEmail getOrderConfirmationEmail() {
   return this.orderConfirmationEmail;
}

public void setOrderConfirmationEmail(OrderConfirmationEmail orderConfirmationEmail) {
   this.orderConfirmationEmail = orderConfirmationEmail;
}

}
