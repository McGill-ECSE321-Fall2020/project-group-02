import javax.persistence.Entity;
import EnumType;

@Entity
public enum DeliveryMethod{
private EnumType inStorePickup;
   
   public void setInStorePickup(EnumType value) {
this.inStorePickup = value;
    }
public EnumType getInStorePickup() {
return this.inStorePickup;
    }
private EnumType homeDelivery;

public void setHomeDelivery(EnumType value) {
this.homeDelivery = value;
    }
public EnumType getHomeDelivery() {
return this.homeDelivery;
       }
   }
