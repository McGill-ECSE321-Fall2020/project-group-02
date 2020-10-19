package ca.mcgill.ecse321.projectgroup02.model;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.OneToMany;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
public class Collection{
	private String description;

	public void setDescription(String value) {
		this.description = value;
	}
	public String getDescription() {
		return this.description;
	}
	private String name;

	public void setName(String value) {
		this.name = value;
	}
	public String getName() {
		return this.name;
	}
	private String pathToImage;

	public void setPathToImage(String value) {
		this.pathToImage = value;
	}
	public String getPathToImage() {
		return this.pathToImage;
	}
	private int collectionId;

	public void setCollectionId(int value) {
		this.collectionId = value;
	}
	@Id
	public int getCollectionId() {
		return this.collectionId;
	}

	@ElementCollection //Resolves "Failed to load ApplicationContext"
	private Set<Item> item;

	@OneToMany
	@NotFound(action = NotFoundAction.IGNORE)
	public Set<Item> getItem() {
		return this.item;
	}

	public void setItem(Set<Item> items) {
		this.item = items;
//		for (Item item: items) {
//			item.setCollection(this);
//		}
	}

}
