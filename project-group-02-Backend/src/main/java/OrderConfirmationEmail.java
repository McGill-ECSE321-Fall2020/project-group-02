import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class OrderConfirmationEmail{
   private int orderConfirmationId;

public void setOrderConfirmationId(int value) {
    this.orderConfirmationId = value;
}
@Id
public int getOrderConfirmationId() {
    return this.orderConfirmationId;
}
private String message;

public void setMessage(String value) {
    this.message = value;
}
public String getMessage() {
    return this.message;
}
   private User receiver;
   
   @ManyToOne(optional=false)
   public User getReceiver() {
      return this.receiver;
   }
   
   public void setReceiver(User receiver) {
      this.receiver = receiver;
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
