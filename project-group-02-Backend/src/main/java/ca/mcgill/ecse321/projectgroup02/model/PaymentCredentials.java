package ca.mcgill.ecse321.projectgroup02.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Id;

@Entity
public class PaymentCredentials{
private String cardHolderName;
   
   public void setCardHolderName(String value) {
this.cardHolderName = value;
    }
public String getCardHolderName() {
return this.cardHolderName;
    }
private String ccNumber;

public void setCcNumber(String value) {
this.ccNumber = value;
    }
public String getCcNumber() {
return this.ccNumber;
    }
private String expirationDate;

public void setExpirationDate(String value) {
this.expirationDate = value;
    }
public String getExpirationDate() {
return this.expirationDate;
    }
private String cvc;

public void setCvc(String value) {
this.cvc = value;
    }
public String getCvc() {
return this.cvc;
    }
private Customer customer;

@ManyToOne(optional=false)
public Customer getCustomer() {
   return this.customer;
}

public void setCustomer(Customer customer) {
   this.customer = customer;
}

private Address address;

@OneToOne(mappedBy="paymentCredential", optional=false)
public Address getAddress() {
   return this.address;
}

public void setAddress(Address address) {
   this.address = address;
}

private int paymentCredentialsId;

public void setPaymentCredentialsId(int value) {
this.paymentCredentialsId = value;
    }
@Id
public int getPaymentCredentialsId() {
return this.paymentCredentialsId;
       }
   }
