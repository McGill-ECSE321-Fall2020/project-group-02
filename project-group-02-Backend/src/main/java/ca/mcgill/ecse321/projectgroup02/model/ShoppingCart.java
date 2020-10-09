package ca.mcgill.ecse321.projectgroup02.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;

import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="shopping_cart")
public class ShoppingCart{

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private int shoppingCartId;

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

public void setShoppingCartId(int value) {
this.shoppingCartId = value;
    }


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


@OneToOne
@JoinColumn(name = "applicationUserId")
private ApplicationUser applicationUser;


public ApplicationUser getApplicationUser() {
   return this.applicationUser;
}

public void setApplicationUser(ApplicationUser applicationUser) {
   this.applicationUser = applicationUser;
}

@OneToOne
@JoinColumn(name = "customerId")
private Customer customer;

@OneToOne(optional=false)
public Customer getCustomer() {
   return this.customer;
}

public void setCustomer(Customer customer) {
   this.customer = customer;
}

}