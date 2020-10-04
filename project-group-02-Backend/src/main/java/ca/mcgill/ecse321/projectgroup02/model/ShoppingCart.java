/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.projectgroup02.model;
import java.util.*;
import java.sql.Date;

// line 82 "../../../../../../model.ump"
// line 179 "../../../../../../model.ump"
public class ShoppingCart
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ShoppingCart Attributes
  private String cartID;
  private double totalPrice;

  //ShoppingCart Associations
  private List<Item> items;
  private User user;
  private Customer customer;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ShoppingCart(String aCartID, double aTotalPrice, User aUser, Customer aCustomer)
  {
    cartID = aCartID;
    totalPrice = aTotalPrice;
    items = new ArrayList<Item>();
    if (aUser == null || aUser.getShoppingCart() != null)
    {
      throw new RuntimeException("Unable to create ShoppingCart due to aUser. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    user = aUser;
    if (aCustomer == null || aCustomer.getShoppingCart() != null)
    {
      throw new RuntimeException("Unable to create ShoppingCart due to aCustomer. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    customer = aCustomer;
  }

  public ShoppingCart(String aCartID, double aTotalPrice, Date aAccountCreationForUser, String aUsernameForUser, String aEmailForUser, String aPasswordForUser, String aPhoneNumberForUser, boolean aLoggedInForUser, ArtGallerySystem aArtGallerySystemForUser, ChatMessage aChatMessageForUser, Conversation aConversationForUser, UserRole... allUserRForUser, Order aOrderForCustomer)
  {
    cartID = aCartID;
    totalPrice = aTotalPrice;
    items = new ArrayList<Item>();
    user = new User(aAccountCreationForUser, aUsernameForUser, aEmailForUser, aPasswordForUser, aPhoneNumberForUser, aLoggedInForUser, this, aArtGallerySystemForUser, aChatMessageForUser, aConversationForUser, allUserRForUser);
    customer = new Customer(this, aOrderForCustomer);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCartID(String aCartID)
  {
    boolean wasSet = false;
    cartID = aCartID;
    wasSet = true;
    return wasSet;
  }

  public boolean setTotalPrice(double aTotalPrice)
  {
    boolean wasSet = false;
    totalPrice = aTotalPrice;
    wasSet = true;
    return wasSet;
  }

  /**
   * pk
   */
  public String getCartID()
  {
    return cartID;
  }

  public double getTotalPrice()
  {
    return totalPrice;
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
  public User getUser()
  {
    return user;
  }
  /* Code from template association_GetOne */
  public Customer getCustomer()
  {
    return customer;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfItems()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Item addItem(String aName, String aId, int aHeight, int aWidth, int aBreadth, Date aCreationDate, String aDesription, double aPrice, double aReducedPrice, boolean aInStock, Artist aCreator, ArtGallerySystem aGallery, Order aOrder, Collection aCollection)
  {
    return new Item(aName, aId, aHeight, aWidth, aBreadth, aCreationDate, aDesription, aPrice, aReducedPrice, aInStock, aCreator, aGallery, aOrder, this, aCollection);
  }

  public boolean addItem(Item aItem)
  {
    boolean wasAdded = false;
    if (items.contains(aItem)) { return false; }
    ShoppingCart existingShoppingCart = aItem.getShoppingCart();
    boolean isNewShoppingCart = existingShoppingCart != null && !this.equals(existingShoppingCart);
    if (isNewShoppingCart)
    {
      aItem.setShoppingCart(this);
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
    //Unable to remove aItem, as it must always have a shoppingCart
    if (!this.equals(aItem.getShoppingCart()))
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

  public void delete()
  {
    for(int i=items.size(); i > 0; i--)
    {
      Item aItem = items.get(i - 1);
      aItem.delete();
    }
    User existingUser = user;
    user = null;
    if (existingUser != null)
    {
      existingUser.delete();
    }
    Customer existingCustomer = customer;
    customer = null;
    if (existingCustomer != null)
    {
      existingCustomer.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "cartID" + ":" + getCartID()+ "," +
            "totalPrice" + ":" + getTotalPrice()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "user = "+(getUser()!=null?Integer.toHexString(System.identityHashCode(getUser())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "customer = "+(getCustomer()!=null?Integer.toHexString(System.identityHashCode(getCustomer())):"null");
  }
}