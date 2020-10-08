package ca.mcgill.ecse321.projectgroup02.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public class Customer extends UserRole{
private Set<PaymentCredentials> paymentCredential;

@OneToMany(mappedBy="customer")
public Set<PaymentCredentials> getPaymentCredential() {
   return this.paymentCredential;
}

public void setPaymentCredential(Set<PaymentCredentials> paymentCredentials) {
   this.paymentCredential = paymentCredentials;
}

}
