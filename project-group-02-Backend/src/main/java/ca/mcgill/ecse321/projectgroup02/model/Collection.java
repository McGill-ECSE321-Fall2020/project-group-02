/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.projectgroup02.model;
import java.util.*;
import java.sql.Date;

// line 88 "../../../../../../model.ump"
// line 196 "../../../../../../model.ump"
public class Collection
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Collection Attributes
  private String description;
  private String name;
  private int numberOfItems;
  private Image thumbnail;

  //Collection Associations
  private List<Item> items;
  private ArtGallerySystem artGallerySystem;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Collection(String aDescription, String aName, int aNumberOfItems, Image aThumbnail, ArtGallerySystem aArtGallerySystem)
  {
    description = aDescription;
    name = aName;
    numberOfItems = aNumberOfItems;
    thumbnail = aThumbnail;
    items = new ArrayList<Item>();
    boolean didAddArtGallerySystem = setArtGallerySystem(aArtGallerySystem);
    if (!didAddArtGallerySystem)
    {
      throw new RuntimeException("Unable to create collection due to artGallerySystem. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDescription(String aDescription)
  {
    boolean wasSet = false;
    description = aDescription;
    wasSet = true;
    return wasSet;
  }

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setNumberOfItems(int aNumberOfItems)
  {
    boolean wasSet = false;
    numberOfItems = aNumberOfItems;
    wasSet = true;
    return wasSet;
  }

  public boolean setThumbnail(Image aThumbnail)
  {
    boolean wasSet = false;
    thumbnail = aThumbnail;
    wasSet = true;
    return wasSet;
  }

  public String getDescription()
  {
    return description;
  }

  /**
   * pk
   */
  public String getName()
  {
    return name;
  }

  public int getNumberOfItems()
  {
    return numberOfItems;
  }

  public Image getThumbnail()
  {
    return thumbnail;
  }
  /* Code from template association_GetMany */
  public Item getItem(int index)
  {
    Item aItem = items.get(index);
    return aItem;
  }

  public List<Item> getItems()
  {
    List<Item> newItems = Collections.unmodifiableList(items);
    return newItems;
  }

  public int numberOfItems()
  {
    int number = items.size();
    return number;
  }

  public boolean hasItems()
  {
    boolean has = items.size() > 0;
    return has;
  }

  public int indexOfItem(Item aItem)
  {
    int index = items.indexOf(aItem);
    return index;
  }
  /* Code from template association_GetOne */
  public ArtGallerySystem getArtGallerySystem()
  {
    return artGallerySystem;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfItems()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Item addItem(String aName, String aId, int aHeight, int aWidth, int aBreadth, Date aCreationDate, String aDesription, double aPrice, double aReducedPrice, boolean aInStock, Artist aCreator, ArtGallerySystem aGallery, Order aOrder, ShoppingCart aShoppingCart)
  {
    return new Item(aName, aId, aHeight, aWidth, aBreadth, aCreationDate, aDesription, aPrice, aReducedPrice, aInStock, aCreator, aGallery, aOrder, aShoppingCart, this);
  }

  public boolean addItem(Item aItem)
  {
    boolean wasAdded = false;
    if (items.contains(aItem)) { return false; }
    Collection existingCollection = aItem.getCollection();
    boolean isNewCollection = existingCollection != null && !this.equals(existingCollection);
    if (isNewCollection)
    {
      aItem.setCollection(this);
    }
    else
    {
      items.add(aItem);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeItem(Item aItem)
  {
    boolean wasRemoved = false;
    //Unable to remove aItem, as it must always have a collection
    if (!this.equals(aItem.getCollection()))
    {
      items.remove(aItem);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addItemAt(Item aItem, int index)
  {  
    boolean wasAdded = false;
    if(addItem(aItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfItems()) { index = numberOfItems() - 1; }
      items.remove(aItem);
      items.add(index, aItem);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveItemAt(Item aItem, int index)
  {
    boolean wasAdded = false;
    if(items.contains(aItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfItems()) { index = numberOfItems() - 1; }
      items.remove(aItem);
      items.add(index, aItem);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addItemAt(aItem, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setArtGallerySystem(ArtGallerySystem aArtGallerySystem)
  {
    boolean wasSet = false;
    if (aArtGallerySystem == null)
    {
      return wasSet;
    }

    ArtGallerySystem existingArtGallerySystem = artGallerySystem;
    artGallerySystem = aArtGallerySystem;
    if (existingArtGallerySystem != null && !existingArtGallerySystem.equals(aArtGallerySystem))
    {
      existingArtGallerySystem.removeCollection(this);
    }
    artGallerySystem.addCollection(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=items.size(); i > 0; i--)
    {
      Item aItem = items.get(i - 1);
      aItem.delete();
    }
    ArtGallerySystem placeholderArtGallerySystem = artGallerySystem;
    this.artGallerySystem = null;
    if(placeholderArtGallerySystem != null)
    {
      placeholderArtGallerySystem.removeCollection(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "description" + ":" + getDescription()+ "," +
            "name" + ":" + getName()+ "," +
            "numberOfItems" + ":" + getNumberOfItems()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "thumbnail" + "=" + (getThumbnail() != null ? !getThumbnail().equals(this)  ? getThumbnail().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "artGallerySystem = "+(getArtGallerySystem()!=null?Integer.toHexString(System.identityHashCode(getArtGallerySystem())):"null");
  }
}