package ca.mcgill.ecse321.projectgroup02.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.Set;
import javax.persistence.ManyToMany;
import javax.persistence.Id;

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
private String country;

public void setCountry(String value) {
this.country = value;
    }
public String getCountry() {
return this.country;
    }
private String city;

public void setCity(String value) {
this.city = value;
    }
public String getCity() {
return this.city;
    }
private PaymentCredentials paymentCredential;

@OneToOne(optional=false)
public PaymentCredentials getPaymentCredential() {
   return this.paymentCredential;
}

public void setPaymentCredential(PaymentCredentials paymentCredential) {
   this.paymentCredential = paymentCredential;
}

private Set<User> user;

@ManyToMany(mappedBy="address")
public Set<User> getUser() {
   return this.user;
}

public void setUser(Set<User> users) {
   this.user = users;
}

private int addressId;

public void setAddressId(int value) {
this.addressId = value;
    }
@Id
public int getAddressId() {
return this.addressId;
    }
private ArtGallerySystem artGallerySystem;

@OneToOne(optional=false)
public ArtGallerySystem getArtGallerySystem() {
   return this.artGallerySystem;
}

public void setArtGallerySystem(ArtGallerySystem artGallerySystem) {
   this.artGallerySystem = artGallerySystem;
}

}
