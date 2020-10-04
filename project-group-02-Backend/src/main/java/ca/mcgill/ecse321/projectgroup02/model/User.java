/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.projectgroup02.model;
import java.sql.Date;
import java.util.*;

// line 37 "../../../../../../model.ump"
// line 143 "../../../../../../model.ump"
public class User
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, User> usersByUsername = new HashMap<String, User>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //User Attributes
  private Date accountCreation;
  private String username;
  private String email;
  private String password;
  private String phoneNumber;
  private boolean loggedIn;

  //User Associations
  private List<Address> userAddress;
  private List<UserRole> userR;
  private ShoppingCart shoppingCart;
  private ArtGallerySystem artGallerySystem;
  private ChatMessage chatMessage;
  private List<OrderConfirmationEmail> orderConfirmationEmail;
  private Conversation conversation;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public User(Date aAccountCreation, String aUsername, String aEmail, String aPassword, String aPhoneNumber, boolean aLoggedIn, ShoppingCart aShoppingCart, ArtGallerySystem aArtGallerySystem, ChatMessage aChatMessage, Conversation aConversation, UserRole... allUserR)
  {
    accountCreation = aAccountCreation;
    email = aEmail;
    password = aPassword;
    phoneNumber = aPhoneNumber;
    loggedIn = aLoggedIn;
    if (!setUsername(aUsername))
    {
      throw new RuntimeException("Cannot create due to duplicate username. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    userAddress = new ArrayList<Address>();
    userR = new ArrayList<UserRole>();
    boolean didAddUserR = setUserR(allUserR);
    if (!didAddUserR)
    {
      throw new RuntimeException("Unable to create User, must have 2 userR. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    if (aShoppingCart == null || aShoppingCart.getUser() != null)
    {
      throw new RuntimeException("Unable to create User due to aShoppingCart. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    shoppingCart = aShoppingCart;
    boolean didAddArtGallerySystem = setArtGallerySystem(aArtGallerySystem);
    if (!didAddArtGallerySystem)
    {
      throw new RuntimeException("Unable to create user due to artGallerySystem. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    if (aChatMessage == null || aChatMessage.getUser() != null)
    {
      throw new RuntimeException("Unable to create User due to aChatMessage. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    chatMessage = aChatMessage;
    orderConfirmationEmail = new ArrayList<OrderConfirmationEmail>();
    boolean didAddConversation = setConversation(aConversation);
    if (!didAddConversation)
    {
      throw new RuntimeException("Unable to create user due to conversation. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  public User(Date aAccountCreation, String aUsername, String aEmail, String aPassword, String aPhoneNumber, boolean aLoggedIn, String aCartIDForShoppingCart, double aTotalPriceForShoppingCart, Customer aCustomerForShoppingCart, ArtGallerySystem aArtGallerySystem, String aChatMessageIDForChatMessage, Date aSentDateForChatMessage, String aMessageForChatMessage, Conversation aConversationForChatMessage, Conversation aConversation, UserRole... allUserR)
  {
    accountCreation = aAccountCreation;
    if (!setUsername(aUsername))
    {
      throw new RuntimeException("Cannot create due to duplicate username. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    email = aEmail;
    password = aPassword;
    phoneNumber = aPhoneNumber;
    loggedIn = aLoggedIn;
    userAddress = new ArrayList<Address>();
    userR = new ArrayList<UserRole>();
    boolean didAddUserR = setUserR(allUserR);
    if (!didAddUserR)
    {
      throw new RuntimeException("Unable to create User, must have 2 userR. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    shoppingCart = new ShoppingCart(aCartIDForShoppingCart, aTotalPriceForShoppingCart, this, aCustomerForShoppingCart);
    boolean didAddArtGallerySystem = setArtGallerySystem(aArtGallerySystem);
    if (!didAddArtGallerySystem)
    {
      throw new RuntimeException("Unable to create user due to artGallerySystem. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    chatMessage = new ChatMessage(aChatMessageIDForChatMessage, aSentDateForChatMessage, aMessageForChatMessage, aConversationForChatMessage, this);
    orderConfirmationEmail = new ArrayList<OrderConfirmationEmail>();
    boolean didAddConversation = setConversation(aConversation);
    if (!didAddConversation)
    {
      throw new RuntimeException("Unable to create user due to conversation. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setAccountCreation(Date aAccountCreation)
  {
    boolean wasSet = false;
    accountCreation = aAccountCreation;
    wasSet = true;
    return wasSet;
  }

  public boolean setUsername(String aUsername)
  {
    boolean wasSet = false;
    String anOldUsername = getUsername();
    if (anOldUsername != null && anOldUsername.equals(aUsername)) {
      return true;
    }
    if (hasWithUsername(aUsername)) {
      return wasSet;
    }
    username = aUsername;
    wasSet = true;
    if (anOldUsername != null) {
      usersByUsername.remove(anOldUsername);
    }
    usersByUsername.put(aUsername, this);
    return wasSet;
  }

  public boolean setEmail(String aEmail)
  {
    boolean wasSet = false;
    email = aEmail;
    wasSet = true;
    return wasSet;
  }

  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
  }

  public boolean setPhoneNumber(String aPhoneNumber)
  {
    boolean wasSet = false;
    phoneNumber = aPhoneNumber;
    wasSet = true;
    return wasSet;
  }

  public boolean setLoggedIn(boolean aLoggedIn)
  {
    boolean wasSet = false;
    loggedIn = aLoggedIn;
    wasSet = true;
    return wasSet;
  }

  public Date getAccountCreation()
  {
    return accountCreation;
  }

  /**
   * pk
   */
  public String getUsername()
  {
    return username;
  }
  /* Code from template attribute_GetUnique */
  public static User getWithUsername(String aUsername)
  {
    return usersByUsername.get(aUsername);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithUsername(String aUsername)
  {
    return getWithUsername(aUsername) != null;
  }

  public String getEmail()
  {
    return email;
  }

  public String getPassword()
  {
    return password;
  }

  public String getPhoneNumber()
  {
    return phoneNumber;
  }

  public boolean getLoggedIn()
  {
    return loggedIn;
  }
  /* Code from template association_GetMany */
  public Address getUserAddress(int index)
  {
    Address aUserAddress = userAddress.get(index);
    return aUserAddress;
  }

  public List<Address> getUserAddress()
  {
    List<Address> newUserAddress = Collections.unmodifiableList(userAddress);
    return newUserAddress;
  }

  public int numberOfUserAddress()
  {
    int number = userAddress.size();
    return number;
  }

  public boolean hasUserAddress()
  {
    boolean has = userAddress.size() > 0;
    return has;
  }

  public int indexOfUserAddress(Address aUserAddress)
  {
    int index = userAddress.indexOf(aUserAddress);
    return index;
  }
  /* Code from template association_GetMany */
  public UserRole getUserR(int index)
  {
    UserRole aUserR = userR.get(index);
    return aUserR;
  }

  public List<UserRole> getUserR()
  {
    List<UserRole> newUserR = Collections.unmodifiableList(userR);
    return newUserR;
  }

  public int numberOfUserR()
  {
    int number = userR.size();
    return number;
  }

  public boolean hasUserR()
  {
    boolean has = userR.size() > 0;
    return has;
  }

  public int indexOfUserR(UserRole aUserR)
  {
    int index = userR.indexOf(aUserR);
    return index;
  }
  /* Code from template association_GetOne */
  public ShoppingCart getShoppingCart()
  {
    return shoppingCart;
  }
  /* Code from template association_GetOne */
  public ArtGallerySystem getArtGallerySystem()
  {
    return artGallerySystem;
  }
  /* Code from template association_GetOne */
  public ChatMessage getChatMessage()
  {
    return chatMessage;
  }
  /* Code from template association_GetMany */
  public OrderConfirmationEmail getOrderConfirmationEmail(int index)
  {
    OrderConfirmationEmail aOrderConfirmationEmail = orderConfirmationEmail.get(index);
    return aOrderConfirmationEmail;
  }

  public List<OrderConfirmationEmail> getOrderConfirmationEmail()
  {
    List<OrderConfirmationEmail> newOrderConfirmationEmail = Collections.unmodifiableList(orderConfirmationEmail);
    return newOrderConfirmationEmail;
  }

  public int numberOfOrderConfirmationEmail()
  {
    int number = orderConfirmationEmail.size();
    return number;
  }

  public boolean hasOrderConfirmationEmail()
  {
    boolean has = orderConfirmationEmail.size() > 0;
    return has;
  }

  public int indexOfOrderConfirmationEmail(OrderConfirmationEmail aOrderConfirmationEmail)
  {
    int index = orderConfirmationEmail.indexOf(aOrderConfirmationEmail);
    return index;
  }
  /* Code from template association_GetOne */
  public Conversation getConversation()
  {
    return conversation;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfUserAddress()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addUserAddress(Address aUserAddress)
  {
    boolean wasAdded = false;
    if (userAddress.contains(aUserAddress)) { return false; }
    userAddress.add(aUserAddress);
    if (aUserAddress.indexOfUser(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aUserAddress.addUser(this);
      if (!wasAdded)
      {
        userAddress.remove(aUserAddress);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeUserAddress(Address aUserAddress)
  {
    boolean wasRemoved = false;
    if (!userAddress.contains(aUserAddress))
    {
      return wasRemoved;
    }

    int oldIndex = userAddress.indexOf(aUserAddress);
    userAddress.remove(oldIndex);
    if (aUserAddress.indexOfUser(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aUserAddress.removeUser(this);
      if (!wasRemoved)
      {
        userAddress.add(oldIndex,aUserAddress);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addUserAddressAt(Address aUserAddress, int index)
  {  
    boolean wasAdded = false;
    if(addUserAddress(aUserAddress))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUserAddress()) { index = numberOfUserAddress() - 1; }
      userAddress.remove(aUserAddress);
      userAddress.add(index, aUserAddress);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveUserAddressAt(Address aUserAddress, int index)
  {
    boolean wasAdded = false;
    if(userAddress.contains(aUserAddress))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUserAddress()) { index = numberOfUserAddress() - 1; }
      userAddress.remove(aUserAddress);
      userAddress.add(index, aUserAddress);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addUserAddressAt(aUserAddress, index);
    }
    return wasAdded;
  }
  /* Code from template association_RequiredNumberOfMethod */
  public static int requiredNumberOfUserR()
  {
    return 2;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfUserR()
  {
    return 2;
  }
  /* Code from template association_MaximumNumberOfMethod */
  public static int maximumNumberOfUserR()
  {
    return 2;
  }
  /* Code from template association_SetNToOptionalOne */
  public boolean setUserR(UserRole... newUserR)
  {
    boolean wasSet = false;
    ArrayList<UserRole> checkNewUserR = new ArrayList<UserRole>();
    for (UserRole aUserR : newUserR)
    {
      if (checkNewUserR.contains(aUserR))
      {
        return wasSet;
      }
      else if (aUserR.getUser() != null && !this.equals(aUserR.getUser()))
      {
        return wasSet;
      }
      checkNewUserR.add(aUserR);
    }

    if (checkNewUserR.size() != minimumNumberOfUserR())
    {
      return wasSet;
    }

    userR.removeAll(checkNewUserR);
    
    for (UserRole orphan : userR)
    {
      setUser(orphan, null);
    }
    userR.clear();
    for (UserRole aUserR : newUserR)
    {
      setUser(aUserR, this);
      userR.add(aUserR);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_GetPrivate */
  private void setUser(UserRole aUserR, User aUser)
  {
    try
    {
      java.lang.reflect.Field mentorField = aUserR.getClass().getDeclaredField("user");
      mentorField.setAccessible(true);
      mentorField.set(aUserR, aUser);
    }
    catch (Exception e)
    {
      throw new RuntimeException("Issue internally setting aUser to aUserR", e);
    }
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
      existingArtGallerySystem.removeUser(this);
    }
    artGallerySystem.addUser(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfOrderConfirmationEmail()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public OrderConfirmationEmail addOrderConfirmationEmail(String aOrderConfirmationID, String aMessage, Order aOrder)
  {
    return new OrderConfirmationEmail(aOrderConfirmationID, aMessage, this, aOrder);
  }

  public boolean addOrderConfirmationEmail(OrderConfirmationEmail aOrderConfirmationEmail)
  {
    boolean wasAdded = false;
    if (orderConfirmationEmail.contains(aOrderConfirmationEmail)) { return false; }
    User existingReceiver = aOrderConfirmationEmail.getReceiver();
    boolean isNewReceiver = existingReceiver != null && !this.equals(existingReceiver);
    if (isNewReceiver)
    {
      aOrderConfirmationEmail.setReceiver(this);
    }
    else
    {
      orderConfirmationEmail.add(aOrderConfirmationEmail);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeOrderConfirmationEmail(OrderConfirmationEmail aOrderConfirmationEmail)
  {
    boolean wasRemoved = false;
    //Unable to remove aOrderConfirmationEmail, as it must always have a receiver
    if (!this.equals(aOrderConfirmationEmail.getReceiver()))
    {
      orderConfirmationEmail.remove(aOrderConfirmationEmail);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addOrderConfirmationEmailAt(OrderConfirmationEmail aOrderConfirmationEmail, int index)
  {  
    boolean wasAdded = false;
    if(addOrderConfirmationEmail(aOrderConfirmationEmail))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOrderConfirmationEmail()) { index = numberOfOrderConfirmationEmail() - 1; }
      orderConfirmationEmail.remove(aOrderConfirmationEmail);
      orderConfirmationEmail.add(index, aOrderConfirmationEmail);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveOrderConfirmationEmailAt(OrderConfirmationEmail aOrderConfirmationEmail, int index)
  {
    boolean wasAdded = false;
    if(orderConfirmationEmail.contains(aOrderConfirmationEmail))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOrderConfirmationEmail()) { index = numberOfOrderConfirmationEmail() - 1; }
      orderConfirmationEmail.remove(aOrderConfirmationEmail);
      orderConfirmationEmail.add(index, aOrderConfirmationEmail);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addOrderConfirmationEmailAt(aOrderConfirmationEmail, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToAtMostN */
  public boolean setConversation(Conversation aConversation)
  {
    boolean wasSet = false;
    //Must provide conversation to user
    if (aConversation == null)
    {
      return wasSet;
    }

    //conversation already at maximum (2)
    if (aConversation.numberOfUsers() >= Conversation.maximumNumberOfUsers())
    {
      return wasSet;
    }
    
    Conversation existingConversation = conversation;
    conversation = aConversation;
    if (existingConversation != null && !existingConversation.equals(aConversation))
    {
      boolean didRemove = existingConversation.removeUser(this);
      if (!didRemove)
      {
        conversation = existingConversation;
        return wasSet;
      }
    }
    conversation.addUser(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    usersByUsername.remove(getUsername());
    ArrayList<Address> copyOfUserAddress = new ArrayList<Address>(userAddress);
    userAddress.clear();
    for(Address aUserAddress : copyOfUserAddress)
    {
      aUserAddress.removeUser(this);
    }
    for(UserRole aUserR : userR)
    {
      setUser(aUserR,null);
    }
    userR.clear();
    ShoppingCart existingShoppingCart = shoppingCart;
    shoppingCart = null;
    if (existingShoppingCart != null)
    {
      existingShoppingCart.delete();
    }
    ArtGallerySystem placeholderArtGallerySystem = artGallerySystem;
    this.artGallerySystem = null;
    if(placeholderArtGallerySystem != null)
    {
      placeholderArtGallerySystem.removeUser(this);
    }
    ChatMessage existingChatMessage = chatMessage;
    chatMessage = null;
    if (existingChatMessage != null)
    {
      existingChatMessage.delete();
    }
    for(int i=orderConfirmationEmail.size(); i > 0; i--)
    {
      OrderConfirmationEmail aOrderConfirmationEmail = orderConfirmationEmail.get(i - 1);
      aOrderConfirmationEmail.delete();
    }
    Conversation placeholderConversation = conversation;
    this.conversation = null;
    if(placeholderConversation != null)
    {
      placeholderConversation.removeUser(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "username" + ":" + getUsername()+ "," +
            "email" + ":" + getEmail()+ "," +
            "password" + ":" + getPassword()+ "," +
            "phoneNumber" + ":" + getPhoneNumber()+ "," +
            "loggedIn" + ":" + getLoggedIn()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "accountCreation" + "=" + (getAccountCreation() != null ? !getAccountCreation().equals(this)  ? getAccountCreation().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "shoppingCart = "+(getShoppingCart()!=null?Integer.toHexString(System.identityHashCode(getShoppingCart())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "artGallerySystem = "+(getArtGallerySystem()!=null?Integer.toHexString(System.identityHashCode(getArtGallerySystem())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "chatMessage = "+(getChatMessage()!=null?Integer.toHexString(System.identityHashCode(getChatMessage())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "conversation = "+(getConversation()!=null?Integer.toHexString(System.identityHashCode(getConversation())):"null");
  }
}