package ca.mcgill.ecse321.projectgroup02.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
//@Table(name="notification_handler")
public class NotificationHandler{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
   private int notificationHandlerId;

public void setNotificationHandlerId(int value) {
    this.notificationHandlerId = value;
}

public int getNotificationHandlerId() {
    return this.notificationHandlerId;
}
	
	@OneToOne(mappedBy= "notificationHandler")
	@JoinColumn(name = "artGallerySystemId")
   private ArtGallerySystem artGallerySystem;
   
	@NotFound(action = NotFoundAction.IGNORE)
   public ArtGallerySystem getArtGallerySystem() {
      return this.artGallerySystem;
   }
   
   public void setArtGallerySystem(ArtGallerySystem artGallerySystem) {
      this.artGallerySystem = artGallerySystem;
   }
   
   @OneToOne
   @JoinColumn(name = "orderConfirmationEmailId")
   private OrderConfirmationEmail orderConfirmationEmail;
   
   @NotFound(action = NotFoundAction.IGNORE)
   public OrderConfirmationEmail getOrderConfirmationEmail() {
      return this.orderConfirmationEmail;
   }
   
   public void setOrderConfirmationEmail(OrderConfirmationEmail orderConfirmationEmail) {
      this.orderConfirmationEmail = orderConfirmationEmail;
   }
   
   }
