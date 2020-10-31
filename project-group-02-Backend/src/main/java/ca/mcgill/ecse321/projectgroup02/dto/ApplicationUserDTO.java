package ca.mcgill.ecse321.projectgroup02.dto;

import java.util.HashSet;
import java.util.Set;

import ca.mcgill.ecse321.projectgroup02.model.UserRole;

public class ApplicationUserDTO{

	private String username;
	private String accountCreationDate;
	private String email;
	private boolean isLoggedIn;
	private Set<UserRoleDTO> userRoles;
	private Set<AddressDTO> address;
	private String phoneNumber;
	private String password;
	//private ArtGallerySystemDTO artGallerySystem;
	

	public ApplicationUserDTO() {}

	public ApplicationUserDTO(String accountCreationDate, String username, String email, String password, String phoneNumber, Set<UserRoleDTO> ur, boolean isLoggedIn) {
		this.accountCreationDate = accountCreationDate;
		this.username = username;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		//this.artGallerySystem = artGallerySystem;
		this.address = new HashSet<AddressDTO>();
		this.userRoles= ur; 
		this.isLoggedIn = isLoggedIn;
		
	}
	
	public ApplicationUserDTO(String accountCreationDate, String username, String email, String password, String phoneNumber, Set<AddressDTO> adr, int i, boolean isLoggedIn) {
		this.accountCreationDate = accountCreationDate;
		this.username = username;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		//this.artGallerySystem = artGallerySystem;
		this.address = adr;
		this.userRoles= new HashSet<UserRoleDTO>(); 
		this.isLoggedIn = isLoggedIn;		
	}
	
//	public ApplicationUserDTO(String accountCreationDate, String username, String email, String password, String phoneNumber, Set<UserRoleDTO> ur) {
//		this.accountCreationDate = accountCreationDate;
//		this.username = username;
//		this.email = email;
//		this.password = password;
//		this.phoneNumber = phoneNumber;
//		//this.artGallerySystem = new ArtGallerySystemDTO();
//		this.address = new HashSet<AddressDTO>();
//		this.userRoles= ur; 
//	}
	
	public ApplicationUserDTO(String accountCreationDate, String username, String email, String password, String phoneNumber, boolean isLoggedIn) {
		this.accountCreationDate = accountCreationDate;
		this.username = username;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		//this.artGallerySystem = new ArtGallerySystemDTO();
		this.address = new HashSet<AddressDTO>();
		this.isLoggedIn = isLoggedIn;
	
	}

	public ApplicationUserDTO(String accountCreationDate, String username, String email, String password, String phoneNumber,Set<AddressDTO> address, Set<UserRoleDTO> ur, boolean isLoggedIn) {
		this.accountCreationDate = accountCreationDate;
		this.username = username;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		//this.artGallerySystem = artGallerySystem;
		this.address = address;
		this.userRoles= ur; 
		this.isLoggedIn = isLoggedIn;
}

	public String getAccountCreationDate() {
		return this.accountCreationDate;
	}

	public String getUsername() {
		return this.username;
	}

	public String getEmail() {
		return this.email;
	}

	public String getPassword() {
		return this.password;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public boolean isIsLoggedIn() {
		return this.isLoggedIn;
	}

	/*
	 * public ArtGallerySystemDTO getArtGallerySystem() { return
	 * this.artGallerySystem; }
	 * 
	 * public void setArtGallerySystem(ArtGallerySystemDTO artGallerySystem) {
	 * this.artGallerySystem = artGallerySystem; }
	 */

	public Set<UserRoleDTO> getUserRole() {
		return this.userRoles;
	}

	public Set<AddressDTO> getAddresses() {
		return this.address;
	}


	public void setAddresses(Set<AddressDTO> address) {
		this.address = address;
	}
}
