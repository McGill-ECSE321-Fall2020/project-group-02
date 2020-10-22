package ca.mcgill.ecse321.projectgroup02.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class ShoppingCart{

private int shoppingCartId;

public void setShoppingCartId(int value) {
    this.shoppingCartId = value;
}
@Id
public int getShoppingCartId() {
    return this.shoppingCartId;
}

private double totalPrice;

public void setTotalPrice(double total) {
	this.totalPrice = total;
}

public double getTotalPrice() {
	return this.totalPrice;
}

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
