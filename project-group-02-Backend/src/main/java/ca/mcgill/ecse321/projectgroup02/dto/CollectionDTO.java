package ca.mcgill.ecse321.projectgroup02.dto;

import java.util.Collections;
import java.util.Set;

public class CollectionDTO{
	private String description;
	private String collectionName;
	private String pathToImage;
	private Set<ItemDTO> items;

	public CollectionDTO() {}
	
	public CollectionDTO(String name, String description, String pathToImage, Set<ItemDTO> items) {
		this.collectionName = name;
		this.description = description;
		this.pathToImage = pathToImage;
		this.items = items;
	}
	
	@SuppressWarnings("unchecked")
	public CollectionDTO(String name, String description, String pathToImage) {
		this(name, description, pathToImage, Collections.EMPTY_SET);
	}
	
	@SuppressWarnings("unchecked")
	public CollectionDTO(String name, String description) {
		this(name, description, null, Collections.EMPTY_SET);
	}
	
	@SuppressWarnings("unchecked")
	public CollectionDTO(String name) {
		this(name, null, null, Collections.EMPTY_SET);
	}

	public String getDescription() {
		return this.description;
	}

	public String getCollectionName() {
		return this.collectionName;
	}

	public String getPathToImage() {
		return this.pathToImage;
	}

	public Set<ItemDTO> getItems() {
		return this.items;
	}

	public void setItems(Set<ItemDTO> items) {
		this.items = items;
	}

}
