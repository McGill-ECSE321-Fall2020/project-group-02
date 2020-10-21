package ca.mcgill.ecse321.projectgroup02.dto;

import java.util.HashSet;
import java.util.Set;

public class ApplicationUserDTO{

	private String username;
	private String accountCreationDate;
	private String email;
	private boolean isLoggedIn;
	private Set<UserRoleDTO> userRole;
	private Set<AddressDTO> address;
	private String phoneNumber;
	private String password;
	private ArtGallerySystemDTO artGallerySystem;

	public ApplicationUserDTO() {}

	public ApplicationUserDTO(String accountCreationDate, String username, String email, String password, String phoneNumber, ArtGallerySystemDTO artGallerySystem) {
		this.accountCreationDate = accountCreationDate;
		this.username = username;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.artGallerySystem = artGallerySystem;
		this.address = new HashSet<AddressDTO>();
	}

	public ApplicationUserDTO(String accountCreationDate, String username, String email, String password, String phoneNumber, ArtGallerySystemDTO artGallerySystem, Set<AddressDTO> address) {
		this.accountCreationDate = accountCreationDate;
		this.username = username;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.artGallerySystem = artGallerySystem;
		this.address = address;
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

	public ArtGallerySystemDTO getArtGallerySystem() {
		return this.artGallerySystem;
	}

	public void setArtGallerySystem(ArtGallerySystemDTO artGallerySystem) {
		this.artGallerySystem = artGallerySystem;
	}

	public Set<UserRoleDTO> getUserRole() {
		return this.userRole;
	}

	public Set<AddressDTO> getAddresses() {
		return this.address;
	}

	public Set<AddressDTO> setAddresses() {
		return this.address;
	}
}
