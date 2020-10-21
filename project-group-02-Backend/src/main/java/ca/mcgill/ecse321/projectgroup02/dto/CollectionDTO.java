package ca.mcgill.ecse321.projectgroup02.dto;

import java.util.Collections;
import java.util.Set;

public class CollectionDTO{
	private String description;
	private String name;
	private String pathToImage;
	private int collectionId;
	private Set<ItemDTO> items;

	public CollectionDTO() {}
	
	public CollectionDTO(int collectionId, String name, String description, String pathToImage, Set<ItemDTO> items) {
		this.collectionId = collectionId;
		this.name = name;
		this.description = description;
		this.pathToImage = pathToImage;
		this.items = items;
	}
	
	@SuppressWarnings("unchecked")
	public CollectionDTO(int collectionId, String name, String description, String pathToImage) {
		this(collectionId, name, description, pathToImage, Collections.EMPTY_SET);
	}
	
	@SuppressWarnings("unchecked")
	public CollectionDTO(int collectionId, String name, String description) {
		this(collectionId, name, description, null, Collections.EMPTY_SET);
	}
	
	@SuppressWarnings("unchecked")
	public CollectionDTO(int collectionId) {
		this(collectionId, null, null, null, Collections.EMPTY_SET);
	}

	public String getDescription() {
		return this.description;
	}

	public String getName() {
		return this.name;
	}

	public String getPathToImage() {
		return this.pathToImage;
	}

	public int getCollectionId() {
		return this.collectionId;
	}

	public Set<ItemDTO> getItems() {
		return this.items;
	}

	public void setItems(Set<ItemDTO> items) {
		this.items = items;
	}

}
