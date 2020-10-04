/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.projectgroup02.model;
import java.util.*;
import java.sql.Date;

// line 54 "../../../../../../model.ump"
// line 157 "../../../../../../model.ump"
public class Artist extends UserRole
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Artist Attributes
  private String originStory;

  //Artist Associations
  private List<Item> creations;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Artist(String aOriginStory)
  {
    super();
    originStory = aOriginStory;
    creations = new ArrayList<Item>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setOriginStory(String aOriginStory)
  {
    boolean wasSet = false;
    originStory = aOriginStory;
    wasSet = true;
    return wasSet;
  }

  public String getOriginStory()
  {
    return originStory;
  }
  /* Code from template association_GetMany */
  public Item getCreation(int index)
  {
    Item aCreation = creations.get(index);
    return aCreation;
  }

  public List<Item> getCreations()
  {
    List<Item> newCreations = Collections.unmodifiableList(creations);
    return newCreations;
  }

  public int numberOfCreations()
  {
    int number = creations.size();
    return number;
  }

  public boolean hasCreations()
  {
    boolean has = creations.size() > 0;
    return has;
  }

  public int indexOfCreation(Item aCreation)
  {
    int index = creations.indexOf(aCreation);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfCreations()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Item addCreation(String aName, String aId, int aHeight, int aWidth, int aBreadth, Date aCreationDate, String aDesription, double aPrice, double aReducedPrice, boolean aInStock, ArtGallerySystem aGallery, Order aOrder, ShoppingCart aShoppingCart, Collection aCollection)
  {
    return new Item(aName, aId, aHeight, aWidth, aBreadth, aCreationDate, aDesription, aPrice, aReducedPrice, aInStock, this, aGallery, aOrder, aShoppingCart, aCollection);
  }

  public boolean addCreation(Item aCreation)
  {
    boolean wasAdded = false;
    if (creations.contains(aCreation)) { return false; }
    Artist existingCreator = aCreation.getCreator();
    boolean isNewCreator = existingCreator != null && !this.equals(existingCreator);
    if (isNewCreator)
    {
      aCreation.setCreator(this);
    }
    else
    {
      creations.add(aCreation);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeCreation(Item aCreation)
  {
    boolean wasRemoved = false;
    //Unable to remove aCreation, as it must always have a creator
    if (!this.equals(aCreation.getCreator()))
    {
      creations.remove(aCreation);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addCreationAt(Item aCreation, int index)
  {  
    boolean wasAdded = false;
    if(addCreation(aCreation))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCreations()) { index = numberOfCreations() - 1; }
      creations.remove(aCreation);
      creations.add(index, aCreation);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCreationAt(Item aCreation, int index)
  {
    boolean wasAdded = false;
    if(creations.contains(aCreation))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCreations()) { index = numberOfCreations() - 1; }
      creations.remove(aCreation);
      creations.add(index, aCreation);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addCreationAt(aCreation, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=creations.size(); i > 0; i--)
    {
      Item aCreation = creations.get(i - 1);
      aCreation.delete();
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "originStory" + ":" + getOriginStory()+ "]";
  }
}