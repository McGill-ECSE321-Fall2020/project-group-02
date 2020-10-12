package ca.mcgill.ecse321.projectgroup02.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
public class OrderConfirmationEmail{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
   private int orderConfirmationId;

public void setOrderConfirmationId(int value) {
    this.orderConfirmationId = value;
}

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

@OneToOne
@JoinColumn(name = "orderId")
private Order order;

@NotFound(action = NotFoundAction.IGNORE)
public Order getOrder() {
   return this.order;
}

public void setOrder(Order order) {
   this.order = order;
}

}

