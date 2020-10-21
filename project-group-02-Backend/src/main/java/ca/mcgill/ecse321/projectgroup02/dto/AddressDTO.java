package ca.mcgill.ecse321.projectgroup02.dto;

public class AddressDTO{
	private String street;
	private String postalCode;
	private String province;
	private String city;
	private String country;

	public AddressDTO() {}

	public AddressDTO(String street, String postalCode, String province, String city, String country) {
		this.street = street;
		this.postalCode = postalCode;
		this.province = province;
		this.city = city;
		this.country = country;
	}
	
	
	public String getStreet() {
		return this.street;
	}

	public String getPostalCode() {
		return this.postalCode;
	}

	public String getProvince() {
		return this.province;
	}

	public String getCountry() {
		return this.country;
	}

	public String getCity() {
		return this.city;
	}

}
