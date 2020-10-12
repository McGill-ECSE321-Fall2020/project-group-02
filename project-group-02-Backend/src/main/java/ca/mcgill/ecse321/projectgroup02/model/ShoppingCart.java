package ca.mcgill.ecse321.projectgroup02.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name="shopping_cart")
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

@Column(nullable = false) //Tell JPA users must be non-null
@ElementCollection //Resolves "Failed to load ApplicationContext"
private Set<Item> item;

@OneToMany
@NotFound(action = NotFoundAction.IGNORE)
public Set<Item> getItem() {
   return this.item;
}

public void setItem(Set<Item> items) {
   this.item = items;
}
}
