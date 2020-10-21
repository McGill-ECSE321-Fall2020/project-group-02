package ca.mcgill.ecse321.projectgroup02.dto;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

public class CustomerDTO extends UserRoleDTO{

	private Set<PaymentCredentialsDTO> paymentCredentials;
	private Set<ShoppingCartDTO> shoppingCarts;
	private Set<ItemOrderDTO> itemOrders;

	@SuppressWarnings("unchecked")
	public CustomerDTO() {
		ShoppingCartDTO newShoppingCart = new ShoppingCartDTO();
		Set<ShoppingCartDTO> shopCarts = new HashSet<ShoppingCartDTO>();
		shopCarts.add(newShoppingCart);
		
		this.shoppingCarts = shopCarts;
		this.itemOrders = Collections.EMPTY_SET;
		this.paymentCredentials = Collections.EMPTY_SET;

	}
	
	public CustomerDTO(Set<PaymentCredentialsDTO> paymentCredentials, Set<ShoppingCartDTO> shoppingCarts, Set<ItemOrderDTO> itemOrders) {
		this.paymentCredentials = paymentCredentials;
		this.shoppingCarts = shoppingCarts;
		this.itemOrders = itemOrders;
	}
	
	public CustomerDTO(Set<PaymentCredentialsDTO> paymentCredentials, Set<ItemOrderDTO> itemOrders ) {
		ShoppingCartDTO newShoppingCart = new ShoppingCartDTO();
		Set<ShoppingCartDTO> shopCarts = new HashSet<ShoppingCartDTO>();
		shopCarts.add(newShoppingCart);
		
		this.paymentCredentials = paymentCredentials;
		this.shoppingCarts = shopCarts;
		this.itemOrders = itemOrders;
	}
	
	@SuppressWarnings("unchecked")
	public CustomerDTO(Set<PaymentCredentialsDTO> paymentCredentials) {
		ShoppingCartDTO newShoppingCart = new ShoppingCartDTO();
		Set<ShoppingCartDTO> shopCarts = new HashSet<ShoppingCartDTO>();
		shopCarts.add(newShoppingCart);
		
		this.paymentCredentials = paymentCredentials;
		this.shoppingCarts = shopCarts;
		this.itemOrders = Collections.EMPTY_SET;
	}
	
	
	public Set<PaymentCredentialsDTO> getPaymentCredentials() {
		return this.paymentCredentials;
	}

	public void setPaymentCredentials(Set<PaymentCredentialsDTO> paymentCredentialss) {
		this.paymentCredentials = paymentCredentialss;
	}

	public Set<ShoppingCartDTO> getShoppingCart() {
		return this.shoppingCarts;
	}

	public void setShoppingCart(Set<ShoppingCartDTO> shoppingCarts) {
		this.shoppingCarts = shoppingCarts;
	}

	public Set<ItemOrderDTO> getItemOrder() {
		return this.itemOrders;
	}

	public void setItemOrder(Set<ItemOrderDTO> itemOrders) {
		this.itemOrders = itemOrders;
	}

}
