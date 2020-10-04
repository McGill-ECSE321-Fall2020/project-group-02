/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.projectgroup02.model;
import java.util.*;
import java.sql.Date;

// line 7 "../../../../../../model.ump"
// line 228 "../../../../../../model.ump"
public class ArtGallerySystem
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ArtGallerySystem Attributes
  private double totalProfit;

  //ArtGallerySystem Associations
  private Address galleryAddress;
  private List<User> users;
  private List<Collection> collections;
  private List<Conversation> conversations;
  private List<Item> galleryItem;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ArtGallerySystem(double aTotalProfit, Address aGalleryAddress)
  {
    totalProfit = aTotalProfit;
    if (aGalleryAddress == null || aGalleryAddress.getGallery() != null)
    {
      throw new RuntimeException("Unable to create ArtGallerySystem due to aGalleryAddress. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    galleryAddress = aGalleryAddress;
    users = new ArrayList<User>();
    collections = new ArrayList<Collection>();
    conversations = new ArrayList<Conversation>();
    galleryItem = new ArrayList<Item>();
  }

  public ArtGallerySystem(double aTotalProfit, String aStreetForGalleryAddress, String aPostalCodeForGalleryAddress, String aProvinceForGalleryAddress, String aCountryForGalleryAddress, String aCityForGalleryAddress, PaymentCredentials aPaymentCredentialsForGalleryAddress)
  {
    totalProfit = aTotalProfit;
    galleryAddress = new Address(aStreetForGalleryAddress, aPostalCodeForGalleryAddress, aProvinceForGalleryAddress, aCountryForGalleryAddress, aCityForGalleryAddress, this, aPaymentCredentialsForGalleryAddress);
    users = new ArrayList<User>();
    collections = new ArrayList<Collection>();
    conversations = new ArrayList<Conversation>();
    galleryItem = new ArrayList<Item>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setTotalProfit(double aTotalProfit)
  {
    boolean wasSet = false;
    totalProfit = aTotalProfit;
    wasSet = true;
    return wasSet;
  }

  public double getTotalProfit()
  {
    return totalProfit;
  }
  /* Code from template association_GetOne */
  public Address getGalleryAddress()
  {
    return galleryAddress;
  }
  /* Code from template association_GetMany */
  public User getUser(int index)
  {
    User aUser = users.get(index);
    return aUser;
  }

  public List<User> getUsers()
  {
    List<User> newUsers = Collections.unmodifiableList(users);
    return newUsers;
  }

  public int numberOfUsers()
  {
    int number = users.size();
    return number;
  }

  public boolean hasUsers()
  {
    boolean has = users.size() > 0;
    return has;
  }

  public int indexOfUser(User aUser)
  {
    int index = users.indexOf(aUser);
    return index;
  }
  /* Code from template association_GetMany */
  public Collection getCollection(int index)
  {
    Collection aCollection = collections.get(index);
    return aCollection;
  }

  public List<Collection> getCollections()
  {
    List<Collection> newCollections = Collections.unmodifiableList(collections);
    return newCollections;
  }

  public int numberOfCollections()
  {
    int number = collections.size();
    return number;
  }

  public boolean hasCollections()
  {
    boolean has = collections.size() > 0;
    return has;
  }

  public int indexOfCollection(Collection aCollection)
  {
    int index = collections.indexOf(aCollection);
    return index;
  }
  /* Code from template association_GetMany */
  public Conversation getConversation(int index)
  {
    Conversation aConversation = conversations.get(index);
    return aConversation;
  }

  public List<Conversation> getConversations()
  {
    List<Conversation> newConversations = Collections.unmodifiableList(conversations);
    return newConversations;
  }

  public int numberOfConversations()
  {
    int number = conversations.size();
    return number;
  }

  public boolean hasConversations()
  {
    boolean has = conversations.size() > 0;
    return has;
  }

  public int indexOfConversation(Conversation aConversation)
  {
    int index = conversations.indexOf(aConversation);
    return index;
  }
  /* Code from template association_GetMany */
  public Item getGalleryItem(int index)
  {
    Item aGalleryItem = galleryItem.get(index);
    return aGalleryItem;
  }

  public List<Item> getGalleryItem()
  {
    List<Item> newGalleryItem = Collections.unmodifiableList(galleryItem);
    return newGalleryItem;
  }

  public int numberOfGalleryItem()
  {
    int number = galleryItem.size();
    return number;
  }

  public boolean hasGalleryItem()
  {
    boolean has = galleryItem.size() > 0;
    return has;
  }

  public int indexOfGalleryItem(Item aGalleryItem)
  {
    int index = galleryItem.indexOf(aGalleryItem);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfUsers()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public User addUser(Date aAccountCreation, String aUsername, String aEmail, String aPassword, String aPhoneNumber, boolean aLoggedIn, ShoppingCart aShoppingCart, ChatMessage aChatMessage, Conversation aConversation, UserRole... allUserR)
  {
    return new User(aAccountCreation, aUsername, aEmail, aPassword, aPhoneNumber, aLoggedIn, aShoppingCart, this, aChatMessage, aConversation, allUserR);
  }

  public boolean addUser(User aUser)
  {
    boolean wasAdded = false;
    if (users.contains(aUser)) { return false; }
    ArtGallerySystem existingArtGallerySystem = aUser.getArtGallerySystem();
    boolean isNewArtGallerySystem = existingArtGallerySystem != null && !this.equals(existingArtGallerySystem);
    if (isNewArtGallerySystem)
    {
      aUser.setArtGallerySystem(this);
    }
    else
    {
      users.add(aUser);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeUser(User aUser)
  {
    boolean wasRemoved = false;
    //Unable to remove aUser, as it must always have a artGallerySystem
    if (!this.equals(aUser.getArtGallerySystem()))
    {
      users.remove(aUser);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addUserAt(User aUser, int index)
  {  
    boolean wasAdded = false;
    if(addUser(aUser))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUsers()) { index = numberOfUsers() - 1; }
      users.remove(aUser);
      users.add(index, aUser);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveUserAt(User aUser, int index)
  {
    boolean wasAdded = false;
    if(users.contains(aUser))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUsers()) { index = numberOfUsers() - 1; }
      users.remove(aUser);
      users.add(index, aUser);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addUserAt(aUser, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfCollections()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Collection addCollection(String aDescription, String aName, int aNumberOfItems, Image aThumbnail)
  {
    return new Collection(aDescription, aName, aNumberOfItems, aThumbnail, this);
  }

  public boolean addCollection(Collection aCollection)
  {
    boolean wasAdded = false;
    if (collections.contains(aCollection)) { return false; }
    ArtGallerySystem existingArtGallerySystem = aCollection.getArtGallerySystem();
    boolean isNewArtGallerySystem = existingArtGallerySystem != null && !this.equals(existingArtGallerySystem);
    if (isNewArtGallerySystem)
    {
      aCollection.setArtGallerySystem(this);
    }
    else
    {
      collections.add(aCollection);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeCollection(Collection aCollection)
  {
    boolean wasRemoved = false;
    //Unable to remove aCollection, as it must always have a artGallerySystem
    if (!this.equals(aCollection.getArtGallerySystem()))
    {
      collections.remove(aCollection);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addCollectionAt(Collection aCollection, int index)
  {  
    boolean wasAdded = false;
    if(addCollection(aCollection))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCollections()) { index = numberOfCollections() - 1; }
      collections.remove(aCollection);
      collections.add(index, aCollection);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCollectionAt(Collection aCollection, int index)
  {
    boolean wasAdded = false;
    if(collections.contains(aCollection))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCollections()) { index = numberOfCollections() - 1; }
      collections.remove(aCollection);
      collections.add(index, aCollection);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addCollectionAt(aCollection, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfConversations()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Conversation addConversation(String aConversationID)
  {
    return new Conversation(aConversationID, this);
  }

  public boolean addConversation(Conversation aConversation)
  {
    boolean wasAdded = false;
    if (conversations.contains(aConversation)) { return false; }
    ArtGallerySystem existingArtGallerySystem = aConversation.getArtGallerySystem();
    boolean isNewArtGallerySystem = existingArtGallerySystem != null && !this.equals(existingArtGallerySystem);
    if (isNewArtGallerySystem)
    {
      aConversation.setArtGallerySystem(this);
    }
    else
    {
      conversations.add(aConversation);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeConversation(Conversation aConversation)
  {
    boolean wasRemoved = false;
    //Unable to remove aConversation, as it must always have a artGallerySystem
    if (!this.equals(aConversation.getArtGallerySystem()))
    {
      conversations.remove(aConversation);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addConversationAt(Conversation aConversation, int index)
  {  
    boolean wasAdded = false;
    if(addConversation(aConversation))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfConversations()) { index = numberOfConversations() - 1; }
      conversations.remove(aConversation);
      conversations.add(index, aConversation);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveConversationAt(Conversation aConversation, int index)
  {
    boolean wasAdded = false;
    if(conversations.contains(aConversation))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfConversations()) { index = numberOfConversations() - 1; }
      conversations.remove(aConversation);
      conversations.add(index, aConversation);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addConversationAt(aConversation, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfGalleryItem()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Item addGalleryItem(String aName, String aId, int aHeight, int aWidth, int aBreadth, Date aCreationDate, String aDesription, double aPrice, double aReducedPrice, boolean aInStock, Artist aCreator, Order aOrder, ShoppingCart aShoppingCart, Collection aCollection)
  {
    return new Item(aName, aId, aHeight, aWidth, aBreadth, aCreationDate, aDesription, aPrice, aReducedPrice, aInStock, aCreator, this, aOrder, aShoppingCart, aCollection);
  }

  public boolean addGalleryItem(Item aGalleryItem)
  {
    boolean wasAdded = false;
    if (galleryItem.contains(aGalleryItem)) { return false; }
    ArtGallerySystem existingGallery = aGalleryItem.getGallery();
    boolean isNewGallery = existingGallery != null && !this.equals(existingGallery);
    if (isNewGallery)
    {
      aGalleryItem.setGallery(this);
    }
    else
    {
      galleryItem.add(aGalleryItem);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeGalleryItem(Item aGalleryItem)
  {
    boolean wasRemoved = false;
    //Unable to remove aGalleryItem, as it must always have a gallery
    if (!this.equals(aGalleryItem.getGallery()))
    {
      galleryItem.remove(aGalleryItem);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addGalleryItemAt(Item aGalleryItem, int index)
  {  
    boolean wasAdded = false;
    if(addGalleryItem(aGalleryItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGalleryItem()) { index = numberOfGalleryItem() - 1; }
      galleryItem.remove(aGalleryItem);
      galleryItem.add(index, aGalleryItem);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveGalleryItemAt(Item aGalleryItem, int index)
  {
    boolean wasAdded = false;
    if(galleryItem.contains(aGalleryItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfGalleryItem()) { index = numberOfGalleryItem() - 1; }
      galleryItem.remove(aGalleryItem);
      galleryItem.add(index, aGalleryItem);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addGalleryItemAt(aGalleryItem, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    Address existingGalleryAddress = galleryAddress;
    galleryAddress = null;
    if (existingGalleryAddress != null)
    {
      existingGalleryAddress.delete();
    }
    while (users.size() > 0)
    {
      User aUser = users.get(users.size() - 1);
      aUser.delete();
      users.remove(aUser);
    }
    
    while (collections.size() > 0)
    {
      Collection aCollection = collections.get(collections.size() - 1);
      aCollection.delete();
      collections.remove(aCollection);
    }
    
    for(int i=conversations.size(); i > 0; i--)
    {
      Conversation aConversation = conversations.get(i - 1);
      aConversation.delete();
    }
    for(int i=galleryItem.size(); i > 0; i--)
    {
      Item aGalleryItem = galleryItem.get(i - 1);
      aGalleryItem.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "totalProfit" + ":" + getTotalProfit()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "galleryAddress = "+(getGalleryAddress()!=null?Integer.toHexString(System.identityHashCode(getGalleryAddress())):"null");
  }
}