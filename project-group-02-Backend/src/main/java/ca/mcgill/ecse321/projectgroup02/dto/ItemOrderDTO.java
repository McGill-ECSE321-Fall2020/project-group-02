package ca.mcgill.ecse321.projectgroup02.dto;

import java.util.Set;

public class ItemOrderDTO{

	private int itemOrderId;
	private String itemOrderDate;
	private CustomerDTO customer;
	private DeliveryMethodDTO delivery;
	private Set<ItemDTO> items;

	public ItemOrderDTO() {}

	public ItemOrderDTO(int itemOrderId,
		
			String itemOrderDate,
			CustomerDTO customer,
			DeliveryMethodDTO delivery,
			Set<ItemDTO> items) {
		
		this.itemOrderId = itemOrderId;
		this.itemOrderDate = itemOrderDate;
		this.customer = customer;
		this.delivery = delivery;
		this.items = items;
	}

	public int getItemOrderId() {
		return this.itemOrderId;
	}

	public String getItemOrderDate() {
		return this.itemOrderDate;
	}

	public CustomerDTO getCustomer() {
		return this.customer;
	}

	public void setCustomer(CustomerDTO customer) {
		this.customer = customer;
	}

	public void setDelivery(DeliveryMethodDTO value) {
		this.delivery = value;
	}
	public DeliveryMethodDTO getDelivery() {
		return this.delivery;
	}

	public Set<ItemDTO> getItems() {
		return this.items;
	}

	public void setItem(Set<ItemDTO> items) {
		this.items = items;
	}



}
