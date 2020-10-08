package ca.mcgill.ecse321.projectgroup02.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class ShoppingCart{
private double totalPrice;
   
   public void setTotalPrice(double value) {
this.totalPrice = value;
    }
public double getTotalPrice() {
return this.totalPrice;
    }
private double numberOfItems;

public void setNumberOfItems(double value) {
this.numberOfItems = value;
    }
public double getNumberOfItems() {
return this.numberOfItems;
    }
private boolean isEmpty;

public void setIsEmpty(boolean value) {
this.isEmpty = value;
    }
public boolean isIsEmpty() {
return this.isEmpty;
    }
private int shoppingCartId;

public void setShoppingCartId(int value) {
this.shoppingCartId = value;
    }
@Id
public int getShoppingCartId() {
return this.shoppingCartId;
    }
private Set<Item> item;

@OneToMany(mappedBy="shoppingCart")
public Set<Item> getItem() {
   return this.item;
}

public void setItem(Set<Item> items) {
   this.item = items;
}

private ApplicationUser applicationUser;

@OneToOne(optional=false)
public ApplicationUser getApplicationUser() {
   return this.applicationUser;
}

public void setApplicationUser(ApplicationUser applicationUser) {
   this.applicationUser = applicationUser;
}

private Customer customer;

@OneToOne(optional=false)
public Customer getCustomer() {
   return this.customer;
}

public void setCustomer(Customer customer) {
   this.customer = customer;
}

}
