package ca.mcgill.ecse321.projectgroup02.dto;

import java.util.Collections;
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
public class ShoppingCartDTO{
	private double totalPrice;
	private double numberOfItems;
	private boolean isEmpty;
	private int shoppingCartId;
	private Set<ItemDTO> items;
	
	public ShoppingCartDTO() {}
	
	public ShoppingCartDTO(int shoppingCartId, double totalPrice, Set<ItemDTO> items) {
		this.shoppingCartId = shoppingCartId;
		this.totalPrice = totalPrice;
		this.numberOfItems = this.items.isEmpty() ? this.items.size() : 0;
		this.isEmpty = this.items.isEmpty() ? true : false;
		this.items = items;
	}
	
	@SuppressWarnings("unchecked")
	public ShoppingCartDTO(int shoppingCartId) {
		this.shoppingCartId = shoppingCartId;
		this.totalPrice = 0; // no items so price = $0
		this.numberOfItems = 0; //no items
		this.isEmpty = true; // no items present in the shopping cart
		this.items = Collections.EMPTY_SET;
	}
	
	public double getTotalPrice() {
		return this.totalPrice;
	}

	public double getNumberOfItems() {
		return this.numberOfItems;
	}

	public boolean isIsEmpty() {
		return this.isEmpty;
	}

	public int getShoppingCartId() {
		return this.shoppingCartId;
	}

	public Set<ItemDTO> getItems() {
		return this.items;
	}

	public void setItems(Set<ItemDTO> items) {
		this.items = items;
	}
}
