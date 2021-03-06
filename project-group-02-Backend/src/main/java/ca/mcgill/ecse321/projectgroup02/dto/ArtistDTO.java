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

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

public class ArtistDTO {
	private Set<ItemDTO> items;

	

	@SuppressWarnings("unchecked")
	public ArtistDTO() {
		this(Collections.EMPTY_SET);	   
	}
	
	public ArtistDTO(Set<ItemDTO> items) {
		this.items = items;	   
	}


	public Set<ItemDTO> getItems() {
		return this.items;
	}

	public void setItems(Set<ItemDTO> items) {
		this.items = items;

	}

}
