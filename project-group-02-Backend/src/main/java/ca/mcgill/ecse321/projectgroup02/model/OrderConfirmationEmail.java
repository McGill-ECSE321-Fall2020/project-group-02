/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.projectgroup02.model;
import java.sql.Date;

// line 120 "../../../../../../model.ump"
// line 221 "../../../../../../model.ump"
public class OrderConfirmationEmail
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //OrderConfirmationEmail Attributes
  private String orderConfirmationID;
  private String message;

  //OrderConfirmationEmail Associations
  private User receiver;
  private Order order;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public OrderConfirmationEmail(String aOrderConfirmationID, String aMessage, User aReceiver, Order aOrder)
  {
    orderConfirmationID = aOrderConfirmationID;
    message = aMessage;
    boolean didAddReceiver = setReceiver(aReceiver);
    if (!didAddReceiver)
    {
      throw new RuntimeException("Unable to create orderConfirmationEmail due to receiver. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    if (aOrder == null || aOrder.getOrderConfirmationEmail() != null)
    {
      throw new RuntimeException("Unable to create OrderConfirmationEmail due to aOrder. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    order = aOrder;
  }

  public OrderConfirmationEmail(String aOrderConfirmationID, String aMessage, User aReceiver, int aCommisionPercentageForOrder, double aCommissionTotalForOrder, String aOrderNumberForOrder, Date aOrderDateForOrder, double aItemsPriceForOrder, double aTaxesForOrder, double aTotalPriceForOrder, boolean aFreeShippingForOrder, PaymentCredentials aPaymentCredentialsForOrder, Delivery aDeliveryMethodForOrder, Customer aBuyerForOrder)
  {
    orderConfirmationID = aOrderConfirmationID;
    message = aMessage;
    boolean didAddReceiver = setReceiver(aReceiver);
    if (!didAddReceiver)
    {
      throw new RuntimeException("Unable to create orderConfirmationEmail due to receiver. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    order = new Order(aCommisionPercentageForOrder, aCommissionTotalForOrder, aOrderNumberForOrder, aOrderDateForOrder, aItemsPriceForOrder, aTaxesForOrder, aTotalPriceForOrder, aFreeShippingForOrder, aPaymentCredentialsForOrder, aDeliveryMethodForOrder, aBuyerForOrder, this);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setOrderConfirmationID(String aOrderConfirmationID)
  {
    boolean wasSet = false;
    orderConfirmationID = aOrderConfirmationID;
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
  public String getOrderConfirmationID()
  {
    return orderConfirmationID;
  }

  public String getMessage()
  {
    return message;
  }
  /* Code from template association_GetOne */
  public User getReceiver()
  {
    return receiver;
  }
  /* Code from template association_GetOne */
  public Order getOrder()
  {
    return order;
  }
  /* Code from template association_SetOneToMany */
  public boolean setReceiver(User aReceiver)
  {
    boolean wasSet = false;
    if (aReceiver == null)
    {
      return wasSet;
    }

    User existingReceiver = receiver;
    receiver = aReceiver;
    if (existingReceiver != null && !existingReceiver.equals(aReceiver))
    {
      existingReceiver.removeOrderConfirmationEmail(this);
    }
    receiver.addOrderConfirmationEmail(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    User placeholderReceiver = receiver;
    this.receiver = null;
    if(placeholderReceiver != null)
    {
      placeholderReceiver.removeOrderConfirmationEmail(this);
    }
    Order existingOrder = order;
    order = null;
    if (existingOrder != null)
    {
      existingOrder.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "orderConfirmationID" + ":" + getOrderConfirmationID()+ "," +
            "message" + ":" + getMessage()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "receiver = "+(getReceiver()!=null?Integer.toHexString(System.identityHashCode(getReceiver())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "order = "+(getOrder()!=null?Integer.toHexString(System.identityHashCode(getOrder())):"null");
  }
}