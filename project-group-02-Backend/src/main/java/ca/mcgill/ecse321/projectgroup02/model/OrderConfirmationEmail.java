package ca.mcgill.ecse321.projectgroup02.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="order_confirmation_email")
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
private NotificationHandler notificationHandler;

@ManyToOne(optional=false)
public NotificationHandler getNotificationHandler() {
   return this.notificationHandler;
}

public void setNotificationHandler(NotificationHandler notificationHandler) {
   this.notificationHandler = notificationHandler;
}

private ItemOrder itemOrder;

@OneToOne(optional=false)
public ItemOrder getItemOrder() {
   return this.itemOrder;
}

public void setItemOrder(ItemOrder itemOrder) {
   this.itemOrder = itemOrder;
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
