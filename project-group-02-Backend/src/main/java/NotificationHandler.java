import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public class NotificationHandler{
   private ArtGallerySystem artGallerySystem;
   
   @OneToOne(optional=false)
   public ArtGallerySystem getArtGallerySystem() {
      return this.artGallerySystem;
   }
   
   public void setArtGallerySystem(ArtGallerySystem artGallerySystem) {
      this.artGallerySystem = artGallerySystem;
   }
   
   private int notificationHandlerId;

public void setNotificationHandlerId(int value) {
    this.notificationHandlerId = value;
}
@Id
public int getNotificationHandlerId() {
    return this.notificationHandlerId;
}
   private Set<OrderConfirmationEmail> ;
   
   @OneToMany
   public Set<OrderConfirmationEmail> get() {
      return this.;
   }
   
   public void set(Set<OrderConfirmationEmail> s) {
      this. = s;
   }
   
   }
