package ca.mcgill.ecse321.projectgroup02.dto;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

public class CustomerDTO extends UserRoleDTO{

	private Set<PaymentCredentialsDTO> paymentCredentials;
	private ShoppingCartDTO shoppingCart;
	private Set<ItemOrderDTO> itemOrders;

	@SuppressWarnings("unchecked")
	public CustomerDTO() {
		
		
		this.shoppingCart = new ShoppingCartDTO();
		this.itemOrders = Collections.EMPTY_SET;
		this.paymentCredentials = Collections.EMPTY_SET;

	}
	
	public CustomerDTO(Set<PaymentCredentialsDTO> paymentCredentials, ShoppingCartDTO shoppingCart, Set<ItemOrderDTO> itemOrders) {
		this.paymentCredentials = paymentCredentials;
		this.shoppingCart = shoppingCart;
		this.itemOrders = itemOrders;
	}
	
	@SuppressWarnings("unchecked")
	public CustomerDTO(Set<PaymentCredentialsDTO> paymentCredentials, ShoppingCartDTO shoppingCart) {
	
		this.paymentCredentials = paymentCredentials;
		this.shoppingCart = shoppingCart;
		this.itemOrders = Collections.EMPTY_SET;
	}
	
	@SuppressWarnings("unchecked")
	public CustomerDTO(ShoppingCartDTO shoppingCart, int n) {
		
		this.paymentCredentials = Collections.EMPTY_SET;
		this.shoppingCart = shoppingCart;
		this.itemOrders = Collections.EMPTY_SET;
	}
	
	@SuppressWarnings("unchecked")
	public CustomerDTO(Set<PaymentCredentialsDTO> paymentCredentials) {
		
		
		this.paymentCredentials = paymentCredentials;
		this.shoppingCart = new ShoppingCartDTO();
		this.itemOrders = Collections.EMPTY_SET;
	}
	
	public Set<PaymentCredentialsDTO> getPaymentCredentials() {
		return this.paymentCredentials;
	}

	public void setPaymentCredentials(Set<PaymentCredentialsDTO> paymentCredentialss) {
		this.paymentCredentials = paymentCredentialss;
	}

	public ShoppingCartDTO getShoppingCart() {
		return this.shoppingCart;
	}

	public void setShoppingCart(ShoppingCartDTO shoppingCarts) {
		this.shoppingCart = shoppingCarts;
	}

	public Set<ItemOrderDTO> getItemOrder() {
		return this.itemOrders;
	}

	public void setItemOrder(Set<ItemOrderDTO> itemOrders) {
		this.itemOrders = itemOrders;
	}

}
