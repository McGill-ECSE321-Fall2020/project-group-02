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
   private int itemOrderConfirmationId;

public void setItemOrderConfirmationId(int value) {
    this.itemOrderConfirmationId = value;
}

public int getItemOrderConfirmationId() {
    return this.itemOrderConfirmationId;
}
private String message;

public void setMessage(String value) {
    this.message = value;
}
public String getMessage() {
    return this.message;
}

@OneToOne
@JoinColumn(name = "itemOrderId")
private ItemOrder itemOrder;

@NotFound(action = NotFoundAction.IGNORE)
public ItemOrder getItemOrder() {
   return this.itemOrder;
}

public void setItemOrder(ItemOrder itemOrder) {
   this.itemOrder = itemOrder;
}

}

