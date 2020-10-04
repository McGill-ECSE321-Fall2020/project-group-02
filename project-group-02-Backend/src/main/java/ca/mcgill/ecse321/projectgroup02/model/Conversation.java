/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.projectgroup02.model;
import java.util.*;
import java.sql.Date;

// line 127 "../../../../../../model.ump"
// line 240 "../../../../../../model.ump"
public class Conversation
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Conversation Attributes
  private String conversationID;

  //Conversation Associations
  private List<User> users;
  private ArtGallerySystem artGallerySystem;
  private List<ChatMessage> messages;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Conversation(String aConversationID, ArtGallerySystem aArtGallerySystem)
  {
    conversationID = aConversationID;
    users = new ArrayList<User>();
    boolean didAddArtGallerySystem = setArtGallerySystem(aArtGallerySystem);
    if (!didAddArtGallerySystem)
    {
      throw new RuntimeException("Unable to create conversation due to artGallerySystem. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    messages = new ArrayList<ChatMessage>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setConversationID(String aConversationID)
  {
    boolean wasSet = false;
    conversationID = aConversationID;
    wasSet = true;
    return wasSet;
  }

  /**
   * pk
   */
  public String getConversationID()
  {
    return conversationID;
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
  /* Code from template association_GetOne */
  public ArtGallerySystem getArtGallerySystem()
  {
    return artGallerySystem;
  }
  /* Code from template association_GetMany */
  public ChatMessage getMessage(int index)
  {
    ChatMessage aMessage = messages.get(index);
    return aMessage;
  }

  public List<ChatMessage> getMessages()
  {
    List<ChatMessage> newMessages = Collections.unmodifiableList(messages);
    return newMessages;
  }

  public int numberOfMessages()
  {
    int number = messages.size();
    return number;
  }

  public boolean hasMessages()
  {
    boolean has = messages.size() > 0;
    return has;
  }

  public int indexOfMessage(ChatMessage aMessage)
  {
    int index = messages.indexOf(aMessage);
    return index;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfUsersValid()
  {
    boolean isValid = numberOfUsers() >= minimumNumberOfUsers() && numberOfUsers() <= maximumNumberOfUsers();
    return isValid;
  }
  /* Code from template association_RequiredNumberOfMethod */
  public static int requiredNumberOfUsers()
  {
    return 2;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfUsers()
  {
    return 2;
  }
  /* Code from template association_MaximumNumberOfMethod */
  public static int maximumNumberOfUsers()
  {
    return 2;
  }
  /* Code from template association_AddMNToOnlyOne */
  public User addUser(Date aAccountCreation, String aUsername, String aEmail, String aPassword, String aPhoneNumber, boolean aLoggedIn, ShoppingCart aShoppingCart, ArtGallerySystem aArtGallerySystem, ChatMessage aChatMessage, UserRole... allUserR)
  {
    if (numberOfUsers() >= maximumNumberOfUsers())
    {
      return null;
    }
    else
    {
      return new User(aAccountCreation, aUsername, aEmail, aPassword, aPhoneNumber, aLoggedIn, aShoppingCart, aArtGallerySystem, aChatMessage, this, allUserR);
    }
  }

  public boolean addUser(User aUser)
  {
    boolean wasAdded = false;
    if (users.contains(aUser)) { return false; }
    if (numberOfUsers() >= maximumNumberOfUsers())
    {
      return wasAdded;
    }

    Conversation existingConversation = aUser.getConversation();
    boolean isNewConversation = existingConversation != null && !this.equals(existingConversation);

    if (isNewConversation && existingConversation.numberOfUsers() <= minimumNumberOfUsers())
    {
      return wasAdded;
    }

    if (isNewConversation)
    {
      aUser.setConversation(this);
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
    //Unable to remove aUser, as it must always have a conversation
    if (this.equals(aUser.getConversation()))
    {
      return wasRemoved;
    }

    //conversation already at minimum (2)
    if (numberOfUsers() <= minimumNumberOfUsers())
    {
      return wasRemoved;
    }
    users.remove(aUser);
    wasRemoved = true;
    return wasRemoved;
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
      existingArtGallerySystem.removeConversation(this);
    }
    artGallerySystem.addConversation(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfMessages()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public ChatMessage addMessage(String aChatMessageID, Date aSentDate, String aMessage, User aUser)
  {
    return new ChatMessage(aChatMessageID, aSentDate, aMessage, this, aUser);
  }

  public boolean addMessage(ChatMessage aMessage)
  {
    boolean wasAdded = false;
    if (messages.contains(aMessage)) { return false; }
    Conversation existingConversation = aMessage.getConversation();
    boolean isNewConversation = existingConversation != null && !this.equals(existingConversation);
    if (isNewConversation)
    {
      aMessage.setConversation(this);
    }
    else
    {
      messages.add(aMessage);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeMessage(ChatMessage aMessage)
  {
    boolean wasRemoved = false;
    //Unable to remove aMessage, as it must always have a conversation
    if (!this.equals(aMessage.getConversation()))
    {
      messages.remove(aMessage);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addMessageAt(ChatMessage aMessage, int index)
  {  
    boolean wasAdded = false;
    if(addMessage(aMessage))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMessages()) { index = numberOfMessages() - 1; }
      messages.remove(aMessage);
      messages.add(index, aMessage);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveMessageAt(ChatMessage aMessage, int index)
  {
    boolean wasAdded = false;
    if(messages.contains(aMessage))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMessages()) { index = numberOfMessages() - 1; }
      messages.remove(aMessage);
      messages.add(index, aMessage);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addMessageAt(aMessage, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=users.size(); i > 0; i--)
    {
      User aUser = users.get(i - 1);
      aUser.delete();
    }
    ArtGallerySystem placeholderArtGallerySystem = artGallerySystem;
    this.artGallerySystem = null;
    if(placeholderArtGallerySystem != null)
    {
      placeholderArtGallerySystem.removeConversation(this);
    }
    for(int i=messages.size(); i > 0; i--)
    {
      ChatMessage aMessage = messages.get(i - 1);
      aMessage.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "conversationID" + ":" + getConversationID()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "artGallerySystem = "+(getArtGallerySystem()!=null?Integer.toHexString(System.identityHashCode(getArtGallerySystem())):"null");
  }
}