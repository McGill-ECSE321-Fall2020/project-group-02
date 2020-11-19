package ca.mcgill.ecse321.projectgroup02.dto;

import java.util.Collections;
import java.util.Set;



public class ShoppingCartDTO{

	private int shoppingCartId;
	private Set<ItemDTO> items;
	
	public ShoppingCartDTO() {}
	
	public ShoppingCartDTO(int shoppingCartId, Set<ItemDTO> items) {
		this.shoppingCartId = shoppingCartId;
		this.items = items;
	}
	
	@SuppressWarnings("unchecked")
	public ShoppingCartDTO(int shoppingCartId) {
		this.shoppingCartId = shoppingCartId;

		this.items = Collections.EMPTY_SET;
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
