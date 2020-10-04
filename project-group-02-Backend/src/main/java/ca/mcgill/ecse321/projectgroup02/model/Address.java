/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.projectgroup02.model;
import java.util.*;

// line 15 "../../../../../../model.ump"
// line 138 "../../../../../../model.ump"
public class Address
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Address Attributes
  private String street;
  private String postalCode;
  private String province;
  private String country;
  private String city;

  //Address Associations
  private ArtGallerySystem gallery;
  private List<User> user;
  private PaymentCredentials paymentCredentials;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Address(String aStreet, String aPostalCode, String aProvince, String aCountry, String aCity, ArtGallerySystem aGallery, PaymentCredentials aPaymentCredentials)
  {
    street = aStreet;
    postalCode = aPostalCode;
    province = aProvince;
    country = aCountry;
    city = aCity;
    if (aGallery == null || aGallery.getGalleryAddress() != null)
    {
      throw new RuntimeException("Unable to create Address due to aGallery. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    gallery = aGallery;
    user = new ArrayList<User>();
    if (aPaymentCredentials == null || aPaymentCredentials.getBillingAddress() != null)
    {
      throw new RuntimeException("Unable to create Address due to aPaymentCredentials. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    paymentCredentials = aPaymentCredentials;
  }

  public Address(String aStreet, String aPostalCode, String aProvince, String aCountry, String aCity, double aTotalProfitForGallery, String aCardholderNameForPaymentCredentials, String aCcNumberForPaymentCredentials, String aExpirationDateForPaymentCredentials, int aCvcForPaymentCredentials, Order aOrderForPaymentCredentials, Customer aCustomerForPaymentCredentials)
  {
    street = aStreet;
    postalCode = aPostalCode;
    province = aProvince;
    country = aCountry;
    city = aCity;
    gallery = new ArtGallerySystem(aTotalProfitForGallery, this);
    user = new ArrayList<User>();
    paymentCredentials = new PaymentCredentials(aCardholderNameForPaymentCredentials, aCcNumberForPaymentCredentials, aExpirationDateForPaymentCredentials, aCvcForPaymentCredentials, this, aOrderForPaymentCredentials, aCustomerForPaymentCredentials);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setStreet(String aStreet)
  {
    boolean wasSet = false;
    street = aStreet;
    wasSet = true;
    return wasSet;
  }

  public boolean setPostalCode(String aPostalCode)
  {
    boolean wasSet = false;
    postalCode = aPostalCode;
    wasSet = true;
    return wasSet;
  }

  public boolean setProvince(String aProvince)
  {
    boolean wasSet = false;
    province = aProvince;
    wasSet = true;
    return wasSet;
  }

  public boolean setCountry(String aCountry)
  {
    boolean wasSet = false;
    country = aCountry;
    wasSet = true;
    return wasSet;
  }

  public boolean setCity(String aCity)
  {
    boolean wasSet = false;
    city = aCity;
    wasSet = true;
    return wasSet;
  }

  public String getStreet()
  {
    return street;
  }

  /**
   * pk
   */
  public String getPostalCode()
  {
    return postalCode;
  }

  public String getProvince()
  {
    return province;
  }

  public String getCountry()
  {
    return country;
  }

  public String getCity()
  {
    return city;
  }
  /* Code from template association_GetOne */
  public ArtGallerySystem getGallery()
  {
    return gallery;
  }
  /* Code from template association_GetMany */
  public User getUser(int index)
  {
    User aUser = user.get(index);
    return aUser;
  }

  public List<User> getUser()
  {
    List<User> newUser = Collections.unmodifiableList(user);
    return newUser;
  }

  public int numberOfUser()
  {
    int number = user.size();
    return number;
  }

  public boolean hasUser()
  {
    boolean has = user.size() > 0;
    return has;
  }

  public int indexOfUser(User aUser)
  {
    int index = user.indexOf(aUser);
    return index;
  }
  /* Code from template association_GetOne */
  public PaymentCredentials getPaymentCredentials()
  {
    return paymentCredentials;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfUser()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addUser(User aUser)
  {
    boolean wasAdded = false;
    if (user.contains(aUser)) { return false; }
    user.add(aUser);
    if (aUser.indexOfUserAddress(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aUser.addUserAddress(this);
      if (!wasAdded)
      {
        user.remove(aUser);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeUser(User aUser)
  {
    boolean wasRemoved = false;
    if (!user.contains(aUser))
    {
      return wasRemoved;
    }

    int oldIndex = user.indexOf(aUser);
    user.remove(oldIndex);
    if (aUser.indexOfUserAddress(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aUser.removeUserAddress(this);
      if (!wasRemoved)
      {
        user.add(oldIndex,aUser);
      }
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
      if(index > numberOfUser()) { index = numberOfUser() - 1; }
      user.remove(aUser);
      user.add(index, aUser);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveUserAt(User aUser, int index)
  {
    boolean wasAdded = false;
    if(user.contains(aUser))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUser()) { index = numberOfUser() - 1; }
      user.remove(aUser);
      user.add(index, aUser);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addUserAt(aUser, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    ArtGallerySystem existingGallery = gallery;
    gallery = null;
    if (existingGallery != null)
    {
      existingGallery.delete();
    }
    ArrayList<User> copyOfUser = new ArrayList<User>(user);
    user.clear();
    for(User aUser : copyOfUser)
    {
      aUser.removeUserAddress(this);
    }
    PaymentCredentials existingPaymentCredentials = paymentCredentials;
    paymentCredentials = null;
    if (existingPaymentCredentials != null)
    {
      existingPaymentCredentials.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "street" + ":" + getStreet()+ "," +
            "postalCode" + ":" + getPostalCode()+ "," +
            "province" + ":" + getProvince()+ "," +
            "country" + ":" + getCountry()+ "," +
            "city" + ":" + getCity()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "gallery = "+(getGallery()!=null?Integer.toHexString(System.identityHashCode(getGallery())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "paymentCredentials = "+(getPaymentCredentials()!=null?Integer.toHexString(System.identityHashCode(getPaymentCredentials())):"null");
  }
}