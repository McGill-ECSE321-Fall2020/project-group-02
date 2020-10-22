package ca.mcgill.ecse321.projectgroup02.dto;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ArtGallerySystemDTO{
	private Set<ApplicationUserDTO> applicationUsers;
	private double totalProfit;
	private int artGalleryId;
	private AddressDTO address;
	private Set<ItemDTO> items;
	
	public ArtGallerySystemDTO(Set<ApplicationUserDTO> users, double totalProfit, int artGalleryId, AddressDTO address, Set<ItemDTO> items) {
		this.applicationUsers = users;
		this.totalProfit = totalProfit;
		this.artGalleryId = artGalleryId;
		this.address = address;
		this.items = items;
	}
	
	public ArtGallerySystemDTO(Set<ApplicationUserDTO> users, double totalProfit, int artGalleryId, AddressDTO address) {
		this.applicationUsers = users;
		this.totalProfit = totalProfit;
		this.artGalleryId = artGalleryId;
		this.address = address;
		this.items = new HashSet<ItemDTO>();
	}
	
	public ArtGallerySystemDTO() {}
	
	@SuppressWarnings("unchecked")
	public ArtGallerySystemDTO(int artGalleryId, AddressDTO address, Set<ItemDTO> items) {
		this(Collections.EMPTY_SET, 0, artGalleryId, address, items);	   
	}
	
	@SuppressWarnings("unchecked")
	public ArtGallerySystemDTO(int artGalleryId, AddressDTO address) {
		this(Collections.EMPTY_SET, 0, artGalleryId, address, Collections.EMPTY_SET);	   
	}
	
	@SuppressWarnings("unchecked")
	public ArtGallerySystemDTO(int artGalleryId) {
		this(Collections.EMPTY_SET, 0, artGalleryId, new AddressDTO() , Collections.EMPTY_SET);	   
	}
	
	public Set<ApplicationUserDTO> getApplicationUsers() {
		return this.applicationUsers;
	}

	public void setApplicationUsers(Set<ApplicationUserDTO> applicationUserss) {
		this.applicationUsers = applicationUserss;
	}

	public double getTotalProfit() {
		return this.totalProfit;
	}

	public int getArtGalleryId() {
		return this.artGalleryId;
	}

	public AddressDTO getAddress() {
		return this.address;
	}

	public void setAddress(AddressDTO address) {
		this.address = address;
	}

	public Set<ItemDTO> getItems() {
		return this.items;
	}

	public void setItem(Set<ItemDTO> items) {
		this.items = items;
	}

}