import javax.persistence.Entity;
import javax.persistence.Id;
import ca.mcgill.ecse321.projectgroup02.model.ArtGallerySystem;
import javax.persistence.OneToOne;
import java.util.Set;
import javax.persistence.ManyToMany;

@Entity
public class Address{
private String street;
   
   public void setStreet(String value) {
this.street = value;
    }
public String getStreet() {
return this.street;
    }
private String postalCode;

public void setPostalCode(String value) {
this.postalCode = value;
    }
@Id
public String getPostalCode() {
return this.postalCode;
    }
private String province;

public void setProvince(String value) {
this.province = value;
    }
public String getProvince() {
return this.province;
    }
private String coutry;

public void setCoutry(String value) {
this.coutry = value;
    }
public String getCoutry() {
return this.coutry;
    }
private String city;

public void setCity(String value) {
this.city = value;
    }
public String getCity() {
return this.city;
    }
private ArtGallerySystem artGallerySystem;

@OneToOne(mappedBy="address", optional=false)
public ArtGallerySystem getArtGallerySystem() {
   return this.artGallerySystem;
}

public void setArtGallerySystem(ArtGallerySystem artGallerySystem) {
   this.artGallerySystem = artGallerySystem;
}

private Set<User> user;

@ManyToMany
public Set<User> getUser() {
   return this.user;
}

public void setUser(Set<User> users) {
   this.user = users;
}

private PaymentCredential paymentCredential;

@OneToOne(optional=false)
public PaymentCredential getPaymentCredential() {
   return this.paymentCredential;
}

public void setPaymentCredential(PaymentCredential paymentCredential) {
   this.paymentCredential = paymentCredential;
}

}
