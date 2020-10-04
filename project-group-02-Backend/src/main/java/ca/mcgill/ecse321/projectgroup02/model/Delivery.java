/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.projectgroup02.model;
import java.sql.Date;

// line 61 "../../../../../../model.ump"
// line 162 "../../../../../../model.ump"
public class Delivery
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum DeliveryMethod { InStorePickup, HomeDelivery }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Delivery Attributes
  private DeliveryMethod deliveryMethod;
  private double deliveryPrice;
  private String deliveryID;

  //Delivery Associations
  private Order order;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Delivery(DeliveryMethod aDeliveryMethod, double aDeliveryPrice, String aDeliveryID, Order aOrder)
  {
    deliveryMethod = aDeliveryMethod;
    deliveryPrice = aDeliveryPrice;
    deliveryID = aDeliveryID;
    if (aOrder == null || aOrder.getDeliveryMethod() != null)
    {
      throw new RuntimeException("Unable to create Delivery due to aOrder. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    order = aOrder;
  }

  public Delivery(DeliveryMethod aDeliveryMethod, double aDeliveryPrice, String aDeliveryID, int aCommisionPercentageForOrder, double aCommissionTotalForOrder, String aOrderNumberForOrder, Date aOrderDateForOrder, double aItemsPriceForOrder, double aTaxesForOrder, double aTotalPriceForOrder, boolean aFreeShippingForOrder, PaymentCredentials aPaymentCredentialsForOrder, Customer aBuyerForOrder, OrderConfirmationEmail aOrderConfirmationEmailForOrder)
  {
    deliveryMethod = aDeliveryMethod;
    deliveryPrice = aDeliveryPrice;
    deliveryID = aDeliveryID;
    order = new Order(aCommisionPercentageForOrder, aCommissionTotalForOrder, aOrderNumberForOrder, aOrderDateForOrder, aItemsPriceForOrder, aTaxesForOrder, aTotalPriceForOrder, aFreeShippingForOrder, aPaymentCredentialsForOrder, this, aBuyerForOrder, aOrderConfirmationEmailForOrder);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setDeliveryMethod(DeliveryMethod aDeliveryMethod)
  {
    boolean wasSet = false;
    deliveryMethod = aDeliveryMethod;
    wasSet = true;
    return wasSet;
  }

  public boolean setDeliveryPrice(double aDeliveryPrice)
  {
    boolean wasSet = false;
    deliveryPrice = aDeliveryPrice;
    wasSet = true;
    return wasSet;
  }

  public boolean setDeliveryID(String aDeliveryID)
  {
    boolean wasSet = false;
    deliveryID = aDeliveryID;
    wasSet = true;
    return wasSet;
  }

  public DeliveryMethod getDeliveryMethod()
  {
    return deliveryMethod;
  }

  public double getDeliveryPrice()
  {
    return deliveryPrice;
  }

  /**
   * pk
   */
  public String getDeliveryID()
  {
    return deliveryID;
  }
  /* Code from template association_GetOne */
  public Order getOrder()
  {
    return order;
  }

  public void delete()
  {
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
            "deliveryPrice" + ":" + getDeliveryPrice()+ "," +
            "deliveryID" + ":" + getDeliveryID()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "deliveryMethod" + "=" + (getDeliveryMethod() != null ? !getDeliveryMethod().equals(this)  ? getDeliveryMethod().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "order = "+(getOrder()!=null?Integer.toHexString(System.identityHashCode(getOrder())):"null");
  }
}