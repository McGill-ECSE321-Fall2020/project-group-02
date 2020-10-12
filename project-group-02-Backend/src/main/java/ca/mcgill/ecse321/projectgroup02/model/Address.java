package ca.mcgill.ecse321.projectgroup02.model;

import javax.persistence.Column;
import javax.persistence.Entity;
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

@Column(name="addressId")
private int addressId;

public void setAddressId(int value) {
    this.addressId = value;
}
@Id
public int getAddressId() {
    return this.addressId;
}
}
