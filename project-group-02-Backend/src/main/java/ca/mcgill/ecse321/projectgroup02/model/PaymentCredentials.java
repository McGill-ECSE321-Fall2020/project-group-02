/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.projectgroup02.model;
import java.sql.Date;

// line 74 "../../../../../../model.ump"
// line 174 "../../../../../../model.ump"
public class PaymentCredentials
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //PaymentCredentials Attributes
  private String cardholderName;
  private String ccNumber;
  private String expirationDate;
  private int cvc;

  //PaymentCredentials Associations
  private Address billingAddress;
  private Order order;
  private Customer customer;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public PaymentCredentials(String aCardholderName, String aCcNumber, String aExpirationDate, int aCvc, Address aBillingAddress, Order aOrder, Customer aCustomer)
  {
    cardholderName = aCardholderName;
    ccNumber = aCcNumber;
    expirationDate = aExpirationDate;
    cvc = aCvc;
    if (aBillingAddress == null || aBillingAddress.getPaymentCredentials() != null)
    {
      throw new RuntimeException("Unable to create PaymentCredentials due to aBillingAddress. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    billingAddress = aBillingAddress;
    if (aOrder == null || aOrder.getPaymentCredentials() != null)
    {
      throw new RuntimeException("Unable to create PaymentCredentials due to aOrder. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    order = aOrder;
    boolean didAddCustomer = setCustomer(aCustomer);
    if (!didAddCustomer)
    {
      throw new RuntimeException("Unable to create paymentCredential due to customer. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  public PaymentCredentials(String aCardholderName, String aCcNumber, String aExpirationDate, int aCvc, String aStreetForBillingAddress, String aPostalCodeForBillingAddress, String aProvinceForBillingAddress, String aCountryForBillingAddress, String aCityForBillingAddress, ArtGallerySystem aGalleryForBillingAddress, int aCommisionPercentageForOrder, double aCommissionTotalForOrder, String aOrderNumberForOrder, Date aOrderDateForOrder, double aItemsPriceForOrder, double aTaxesForOrder, double aTotalPriceForOrder, boolean aFreeShippingForOrder, Delivery aDeliveryMethodForOrder, Customer aBuyerForOrder, OrderConfirmationEmail aOrderConfirmationEmailForOrder, Customer aCustomer)
  {
    cardholderName = aCardholderName;
    ccNumber = aCcNumber;
    expirationDate = aExpirationDate;
    cvc = aCvc;
    billingAddress = new Address(aStreetForBillingAddress, aPostalCodeForBillingAddress, aProvinceForBillingAddress, aCountryForBillingAddress, aCityForBillingAddress, aGalleryForBillingAddress, this);
    order = new Order(aCommisionPercentageForOrder, aCommissionTotalForOrder, aOrderNumberForOrder, aOrderDateForOrder, aItemsPriceForOrder, aTaxesForOrder, aTotalPriceForOrder, aFreeShippingForOrder, this, aDeliveryMethodForOrder, aBuyerForOrder, aOrderConfirmationEmailForOrder);
    boolean didAddCustomer = setCustomer(aCustomer);
    if (!didAddCustomer)
    {
      throw new RuntimeException("Unable to create paymentCredential due to customer. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCardholderName(String aCardholderName)
  {
    boolean wasSet = false;
    cardholderName = aCardholderName;
    wasSet = true;
    return wasSet;
  }

  public boolean setCcNumber(String aCcNumber)
  {
    boolean wasSet = false;
    ccNumber = aCcNumber;
    wasSet = true;
    return wasSet;
  }

  public boolean setExpirationDate(String aExpirationDate)
  {
    boolean wasSet = false;
    expirationDate = aExpirationDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setCvc(int aCvc)
  {
    boolean wasSet = false;
    cvc = aCvc;
    wasSet = true;
    return wasSet;
  }

  public String getCardholderName()
  {
    return cardholderName;
  }

  /**
   * pk
   */
  public String getCcNumber()
  {
    return ccNumber;
  }

  public String getExpirationDate()
  {
    return expirationDate;
  }

  public int getCvc()
  {
    return cvc;
  }
  /* Code from template association_GetOne */
  public Address getBillingAddress()
  {
    return billingAddress;
  }
  /* Code from template association_GetOne */
  public Order getOrder()
  {
    return order;
  }
  /* Code from template association_GetOne */
  public Customer getCustomer()
  {
    return customer;
  }
  /* Code from template association_SetOneToMany */
  public boolean setCustomer(Customer aCustomer)
  {
    boolean wasSet = false;
    if (aCustomer == null)
    {
      return wasSet;
    }

    Customer existingCustomer = customer;
    customer = aCustomer;
    if (existingCustomer != null && !existingCustomer.equals(aCustomer))
    {
      existingCustomer.removePaymentCredential(this);
    }
    customer.addPaymentCredential(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Address existingBillingAddress = billingAddress;
    billingAddress = null;
    if (existingBillingAddress != null)
    {
      existingBillingAddress.delete();
    }
    Order existingOrder = order;
    order = null;
    if (existingOrder != null)
    {
      existingOrder.delete();
    }
    Customer placeholderCustomer = customer;
    this.customer = null;
    if(placeholderCustomer != null)
    {
      placeholderCustomer.removePaymentCredential(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "cardholderName" + ":" + getCardholderName()+ "," +
            "ccNumber" + ":" + getCcNumber()+ "," +
            "expirationDate" + ":" + getExpirationDate()+ "," +
            "cvc" + ":" + getCvc()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "billingAddress = "+(getBillingAddress()!=null?Integer.toHexString(System.identityHashCode(getBillingAddress())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "order = "+(getOrder()!=null?Integer.toHexString(System.identityHashCode(getOrder())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "customer = "+(getCustomer()!=null?Integer.toHexString(System.identityHashCode(getCustomer())):"null");
  }
}