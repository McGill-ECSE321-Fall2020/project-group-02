/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.projectgroup02.model;
import java.sql.Date;
import java.util.*;

// line 96 "../../../../../../model.ump"
// line 202 "../../../../../../model.ump"
public class Item
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Item Attributes
  private String name;
  private String id;
  private int height;
  private int width;
  private int breadth;
  private Date creationDate;
  private String desription;
  private double price;
  private List<Image> images;
  private double reducedPrice;
  private boolean inStock;

  //Item Associations
  private Artist creator;
  private ArtGallerySystem gallery;
  private Order order;
  private ShoppingCart shoppingCart;
  private Collection collection;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Item(String aName, String aId, int aHeight, int aWidth, int aBreadth, Date aCreationDate, String aDesription, double aPrice, double aReducedPrice, boolean aInStock, Artist aCreator, ArtGallerySystem aGallery, Order aOrder, ShoppingCart aShoppingCart, Collection aCollection)
  {
    name = aName;
    id = aId;
    height = aHeight;
    width = aWidth;
    breadth = aBreadth;
    creationDate = aCreationDate;
    desription = aDesription;
    price = aPrice;
    images = new ArrayList<Image>();
    reducedPrice = aReducedPrice;
    inStock = aInStock;
    boolean didAddCreator = setCreator(aCreator);
    if (!didAddCreator)
    {
      throw new RuntimeException("Unable to create creation due to creator. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddGallery = setGallery(aGallery);
    if (!didAddGallery)
    {
      throw new RuntimeException("Unable to create galleryItem due to gallery. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddOrder = setOrder(aOrder);
    if (!didAddOrder)
    {
      throw new RuntimeException("Unable to create orderedItem due to order. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddShoppingCart = setShoppingCart(aShoppingCart);
    if (!didAddShoppingCart)
    {
      throw new RuntimeException("Unable to create item due to shoppingCart. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddCollection = setCollection(aCollection);
    if (!didAddCollection)
    {
      throw new RuntimeException("Unable to create item due to collection. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setId(String aId)
  {
    boolean wasSet = false;
    id = aId;
    wasSet = true;
    return wasSet;
  }

  public boolean setHeight(int aHeight)
  {
    boolean wasSet = false;
    height = aHeight;
    wasSet = true;
    return wasSet;
  }

  public boolean setWidth(int aWidth)
  {
    boolean wasSet = false;
    width = aWidth;
    wasSet = true;
    return wasSet;
  }

  public boolean setBreadth(int aBreadth)
  {
    boolean wasSet = false;
    breadth = aBreadth;
    wasSet = true;
    return wasSet;
  }

  public boolean setCreationDate(Date aCreationDate)
  {
    boolean wasSet = false;
    creationDate = aCreationDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setDesription(String aDesription)
  {
    boolean wasSet = false;
    desription = aDesription;
    wasSet = true;
    return wasSet;
  }

  public boolean setPrice(double aPrice)
  {
    boolean wasSet = false;
    price = aPrice;
    wasSet = true;
    return wasSet;
  }
  /* Code from template attribute_SetMany */
  public boolean addImage(Image aImage)
  {
    boolean wasAdded = false;
    wasAdded = images.add(aImage);
    return wasAdded;
  }

  public boolean removeImage(Image aImage)
  {
    boolean wasRemoved = false;
    wasRemoved = images.remove(aImage);
    return wasRemoved;
  }

  public boolean setReducedPrice(double aReducedPrice)
  {
    boolean wasSet = false;
    reducedPrice = aReducedPrice;
    wasSet = true;
    return wasSet;
  }

  public boolean setInStock(boolean aInStock)
  {
    boolean wasSet = false;
    inStock = aInStock;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  /**
   * pk
   */
  public String getId()
  {
    return id;
  }

  public int getHeight()
  {
    return height;
  }

  public int getWidth()
  {
    return width;
  }

  public int getBreadth()
  {
    return breadth;
  }

  public Date getCreationDate()
  {
    return creationDate;
  }

  public String getDesription()
  {
    return desription;
  }

  public double getPrice()
  {
    return price;
  }
  /* Code from template attribute_GetMany */
  public Image getImage(int index)
  {
    Image aImage = images.get(index);
    return aImage;
  }

  public Image[] getImages()
  {
    Image[] newImages = images.toArray(new Image[images.size()]);
    return newImages;
  }

  public int numberOfImages()
  {
    int number = images.size();
    return number;
  }

  public boolean hasImages()
  {
    boolean has = images.size() > 0;
    return has;
  }

  public int indexOfImage(Image aImage)
  {
    int index = images.indexOf(aImage);
    return index;
  }

  public double getReducedPrice()
  {
    return reducedPrice;
  }

  public boolean getInStock()
  {
    return inStock;
  }
  /* Code from template association_GetOne */
  public Artist getCreator()
  {
    return creator;
  }
  /* Code from template association_GetOne */
  public ArtGallerySystem getGallery()
  {
    return gallery;
  }
  /* Code from template association_GetOne */
  public Order getOrder()
  {
    return order;
  }
  /* Code from template association_GetOne */
  public ShoppingCart getShoppingCart()
  {
    return shoppingCart;
  }
  /* Code from template association_GetOne */
  public Collection getCollection()
  {
    return collection;
  }
  /* Code from template association_SetOneToMany */
  public boolean setCreator(Artist aCreator)
  {
    boolean wasSet = false;
    if (aCreator == null)
    {
      return wasSet;
    }

    Artist existingCreator = creator;
    creator = aCreator;
    if (existingCreator != null && !existingCreator.equals(aCreator))
    {
      existingCreator.removeCreation(this);
    }
    creator.addCreation(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setGallery(ArtGallerySystem aGallery)
  {
    boolean wasSet = false;
    if (aGallery == null)
    {
      return wasSet;
    }

    ArtGallerySystem existingGallery = gallery;
    gallery = aGallery;
    if (existingGallery != null && !existingGallery.equals(aGallery))
    {
      existingGallery.removeGalleryItem(this);
    }
    gallery.addGalleryItem(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setOrder(Order aOrder)
  {
    boolean wasSet = false;
    if (aOrder == null)
    {
      return wasSet;
    }

    Order existingOrder = order;
    order = aOrder;
    if (existingOrder != null && !existingOrder.equals(aOrder))
    {
      existingOrder.removeOrderedItem(this);
    }
    order.addOrderedItem(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setShoppingCart(ShoppingCart aShoppingCart)
  {
    boolean wasSet = false;
    if (aShoppingCart == null)
    {
      return wasSet;
    }

    ShoppingCart existingShoppingCart = shoppingCart;
    shoppingCart = aShoppingCart;
    if (existingShoppingCart != null && !existingShoppingCart.equals(aShoppingCart))
    {
      existingShoppingCart.removeItem(this);
    }
    shoppingCart.addItem(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setCollection(Collection aCollection)
  {
    boolean wasSet = false;
    if (aCollection == null)
    {
      return wasSet;
    }

    Collection existingCollection = collection;
    collection = aCollection;
    if (existingCollection != null && !existingCollection.equals(aCollection))
    {
      existingCollection.removeItem(this);
    }
    collection.addItem(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Artist placeholderCreator = creator;
    this.creator = null;
    if(placeholderCreator != null)
    {
      placeholderCreator.removeCreation(this);
    }
    ArtGallerySystem placeholderGallery = gallery;
    this.gallery = null;
    if(placeholderGallery != null)
    {
      placeholderGallery.removeGalleryItem(this);
    }
    Order placeholderOrder = order;
    this.order = null;
    if(placeholderOrder != null)
    {
      placeholderOrder.removeOrderedItem(this);
    }
    ShoppingCart placeholderShoppingCart = shoppingCart;
    this.shoppingCart = null;
    if(placeholderShoppingCart != null)
    {
      placeholderShoppingCart.removeItem(this);
    }
    Collection placeholderCollection = collection;
    this.collection = null;
    if(placeholderCollection != null)
    {
      placeholderCollection.removeItem(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "id" + ":" + getId()+ "," +
            "height" + ":" + getHeight()+ "," +
            "width" + ":" + getWidth()+ "," +
            "breadth" + ":" + getBreadth()+ "," +
            "desription" + ":" + getDesription()+ "," +
            "price" + ":" + getPrice()+ "," +
            "reducedPrice" + ":" + getReducedPrice()+ "," +
            "inStock" + ":" + getInStock()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "creationDate" + "=" + (getCreationDate() != null ? !getCreationDate().equals(this)  ? getCreationDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "creator = "+(getCreator()!=null?Integer.toHexString(System.identityHashCode(getCreator())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "gallery = "+(getGallery()!=null?Integer.toHexString(System.identityHashCode(getGallery())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "order = "+(getOrder()!=null?Integer.toHexString(System.identityHashCode(getOrder())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "shoppingCart = "+(getShoppingCart()!=null?Integer.toHexString(System.identityHashCode(getShoppingCart())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "collection = "+(getCollection()!=null?Integer.toHexString(System.identityHashCode(getCollection())):"null");
  }
}