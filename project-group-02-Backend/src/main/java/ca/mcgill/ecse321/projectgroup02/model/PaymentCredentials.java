package ca.mcgill.ecse321.projectgroup02.model;

import javax.persistence.Entity;
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

}
