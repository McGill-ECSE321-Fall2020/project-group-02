/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.projectgroup02.model;
import java.sql.Date;
import java.util.*;

// line 112 "../../../../../../model.ump"
// line 210 "../../../../../../model.ump"
public class ChatMessage
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ChatMessage Attributes
  private String chatMessageID;
  private Date sentDate;
  private String message;

  //ChatMessage Associations
  private Conversation conversation;
  private User user;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ChatMessage(String aChatMessageID, Date aSentDate, String aMessage, Conversation aConversation, User aUser)
  {
    chatMessageID = aChatMessageID;
    sentDate = aSentDate;
    message = aMessage;
    boolean didAddConversation = setConversation(aConversation);
    if (!didAddConversation)
    {
      throw new RuntimeException("Unable to create message due to conversation. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    if (aUser == null || aUser.getChatMessage() != null)
    {
      throw new RuntimeException("Unable to create ChatMessage due to aUser. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    user = aUser;
  }

  public ChatMessage(String aChatMessageID, Date aSentDate, String aMessage, Conversation aConversation, Date aAccountCreationForUser, String aUsernameForUser, String aEmailForUser, String aPasswordForUser, String aPhoneNumberForUser, boolean aLoggedInForUser, ShoppingCart aShoppingCartForUser, ArtGallerySystem aArtGallerySystemForUser, Conversation aConversationForUser, UserRole... allUserRForUser)
  {
    chatMessageID = aChatMessageID;
    sentDate = aSentDate;
    message = aMessage;
    boolean didAddConversation = setConversation(aConversation);
    if (!didAddConversation)
    {
      throw new RuntimeException("Unable to create message due to conversation. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    user = new User(aAccountCreationForUser, aUsernameForUser, aEmailForUser, aPasswordForUser, aPhoneNumberForUser, aLoggedInForUser, aShoppingCartForUser, aArtGallerySystemForUser, this, aConversationForUser, allUserRForUser);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setChatMessageID(String aChatMessageID)
  {
    boolean wasSet = false;
    chatMessageID = aChatMessageID;
    wasSet = true;
    return wasSet;
  }

  public boolean setSentDate(Date aSentDate)
  {
    boolean wasSet = false;
    sentDate = aSentDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setMessage(String aMessage)
  {
    boolean wasSet = false;
    message = aMessage;
    wasSet = true;
    return wasSet;
  }

  /**
   * pk
   */
  public String getChatMessageID()
  {
    return chatMessageID;
  }

  public Date getSentDate()
  {
    return sentDate;
  }

  public String getMessage()
  {
    return message;
  }
  /* Code from template association_GetOne */
  public Conversation getConversation()
  {
    return conversation;
  }
  /* Code from template association_GetOne */
  public User getUser()
  {
    return user;
  }
  /* Code from template association_SetOneToMany */
  public boolean setConversation(Conversation aConversation)
  {
    boolean wasSet = false;
    if (aConversation == null)
    {
      return wasSet;
    }

    Conversation existingConversation = conversation;
    conversation = aConversation;
    if (existingConversation != null && !existingConversation.equals(aConversation))
    {
      existingConversation.removeMessage(this);
    }
    conversation.addMessage(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Conversation placeholderConversation = conversation;
    this.conversation = null;
    if(placeholderConversation != null)
    {
      placeholderConversation.removeMessage(this);
    }
    User existingUser = user;
    user = null;
    if (existingUser != null)
    {
      existingUser.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "chatMessageID" + ":" + getChatMessageID()+ "," +
            "message" + ":" + getMessage()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "sentDate" + "=" + (getSentDate() != null ? !getSentDate().equals(this)  ? getSentDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "conversation = "+(getConversation()!=null?Integer.toHexString(System.identityHashCode(getConversation())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "user = "+(getUser()!=null?Integer.toHexString(System.identityHashCode(getUser())):"null");
  }
}