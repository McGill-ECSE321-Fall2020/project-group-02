package ca.mcgill.ecse321.projectgroup02.dto;

import java.util.Set;

public class ItemOrderDTO{
	private double commissionPercentage;
	private double commissionTotal;
	private int itemOrderId;
	private String itemOrderDate;
	private double totalPrice;
	private double taxes;
	private CustomerDTO customer;
	private DeliveryMethodDTO delivery;
	private Set<ItemDTO> items;

	public ItemOrderDTO() {}

	public ItemOrderDTO(int itemOrderId,
			double commissionPercentage,
			double commissionTotal,
			String itemOrderDate,
			double totalPrice,
			double taxes,
			CustomerDTO customer,
			DeliveryMethodDTO delivery,
			Set<ItemDTO> items) {
		
		this.itemOrderId = itemOrderId;
		this.commissionPercentage = commissionPercentage;
		this.commissionTotal = commissionTotal;
		this.itemOrderDate = itemOrderDate;
		this.totalPrice = totalPrice;
		this.taxes = taxes;
		this.customer = customer;
		this.delivery = delivery;
		this.items = items;
	}

	public double getCommissionPercentage() {
		return this.commissionPercentage;
	}

	public double getCommissionTotal() {
		return this.commissionTotal;
	}

	public int getItemOrderId() {
		return this.itemOrderId;
	}

	public String getItemOrderDate() {
		return this.itemOrderDate;
	}

	public double getTotalPrice() {
		return this.totalPrice;
	}

	public double getTaxes() {
		return this.taxes;
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
