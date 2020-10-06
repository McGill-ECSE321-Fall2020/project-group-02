import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Set;
import javax.persistence.ManyToMany;
import ca.mcgill.ecse321.projectgroup02.model.ArtGallerySystem;

@Entity
public class Item{
private String name;
   
   public void setName(String value) {
this.name = value;
    }
public String getName() {
return this.name;
    }
private String itemId;

public void setItemId(String value) {
this.itemId = value;
    }
@Id
public String getItemId() {
return this.itemId;
    }
private double height;

public void setHeight(double value) {
this.height = value;
    }
public double getHeight() {
return this.height;
    }
private double width;

public void setWidth(double value) {
this.width = value;
    }
public double getWidth() {
return this.width;
    }
private double breadth;

public void setBreadth(double value) {
this.breadth = value;
    }
public double getBreadth() {
return this.breadth;
    }
private String creationDate;

public void setCreationDate(String value) {
this.creationDate = value;
    }
public String getCreationDate() {
return this.creationDate;
    }
private String description;

public void setDescription(String value) {
this.description = value;
    }
public String getDescription() {
return this.description;
    }
private double price;

public void setPrice(double value) {
this.price = value;
    }
public double getPrice() {
return this.price;
    }
private String pathToImage;

public void setPathToImage(String value) {
this.pathToImage = value;
    }
public String getPathToImage() {
return this.pathToImage;
    }
private boolean inStock;

public void setInStock(boolean value) {
this.inStock = value;
    }
public boolean isInStock() {
return this.inStock;
    }
private Order order;

@ManyToOne(optional=false)
public Order getOrder() {
   return this.order;
}

public void setOrder(Order order) {
   this.order = order;
}

private ShoppingCart shoppingCart;

@ManyToOne(optional=false)
public ShoppingCart getShoppingCart() {
   return this.shoppingCart;
}

public void setShoppingCart(ShoppingCart shoppingCart) {
   this.shoppingCart = shoppingCart;
}

private Set<Collection> collection;

@ManyToMany
public Set<Collection> getCollection() {
   return this.collection;
}

public void setCollection(Set<Collection> collections) {
   this.collection = collections;
}

private Artist creator;

@ManyToOne(optional=false)
public Artist getCreator() {
   return this.creator;
}

public void setCreator(Artist creator) {
   this.creator = creator;
}

private ArtGallerySystem artGallerySystem;

@ManyToOne(optional=false)
public ArtGallerySystem getArtGallerySystem() {
   return this.artGallerySystem;
}

public void setArtGallerySystem(ArtGallerySystem artGallerySystem) {
   this.artGallerySystem = artGallerySystem;
}

}
