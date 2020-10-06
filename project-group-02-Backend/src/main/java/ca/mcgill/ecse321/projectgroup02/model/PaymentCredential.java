import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;

@Entity
public class PaymentCredential{
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
@Id
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
private Order order;

@OneToOne(optional=false)
public Order getOrder() {
   return this.order;
}

public void setOrder(Order order) {
   this.order = order;
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

}
