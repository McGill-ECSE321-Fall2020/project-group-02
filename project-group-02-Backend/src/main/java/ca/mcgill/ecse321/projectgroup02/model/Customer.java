/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.projectgroup02.model;
import java.util.*;
import java.sql.Date;

// line 67 "../../../../../../model.ump"
// line 167 "../../../../../../model.ump"
public class Customer extends UserRole
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Customer Associations
  private List<PaymentCredentials> paymentCredentials;
  private ShoppingCart shoppingCart;
  private Order order;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Customer(ShoppingCart aShoppingCart, Order aOrder)
  {
    super();
    paymentCredentials = new ArrayList<PaymentCredentials>();
    if (aShoppingCart == null || aShoppingCart.getCustomer() != null)
    {
      throw new RuntimeException("Unable to create Customer due to aShoppingCart. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    shoppingCart = aShoppingCart;
    if (aOrder == null || aOrder.getBuyer() != null)
    {
      throw new RuntimeException("Unable to create Customer due to aOrder. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    order = aOrder;
  }

  public Customer(String aCartIDForShoppingCart, double aTotalPriceForShoppingCart, User aUserForShoppingCart, int aCommisionPercentageForOrder, double aCommissionTotalForOrder, String aOrderNumberForOrder, Date aOrderDateForOrder, double aItemsPriceForOrder, double aTaxesForOrder, double aTotalPriceForOrder, boolean aFreeShippingForOrder, PaymentCredentials aPaymentCredentialsForOrder, Delivery aDeliveryMethodForOrder, OrderConfirmationEmail aOrderConfirmationEmailForOrder)
  {
    super();
    paymentCredentials = new ArrayList<PaymentCredentials>();
    shoppingCart = new ShoppingCart(aCartIDForShoppingCart, aTotalPriceForShoppingCart, aUserForShoppingCart, this);
    order = new Order(aCommisionPercentageForOrder, aCommissionTotalForOrder, aOrderNumberForOrder, aOrderDateForOrder, aItemsPriceForOrder, aTaxesForOrder, aTotalPriceForOrder, aFreeShippingForOrder, aPaymentCredentialsForOrder, aDeliveryMethodForOrder, this, aOrderConfirmationEmailForOrder);
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public PaymentCredentials getPaymentCredential(int index)
  {
    PaymentCredentials aPaymentCredential = paymentCredentials.get(index);
    return aPaymentCredential;
  }

  public List<PaymentCredentials> getPaymentCredentials()
  {
    List<PaymentCredentials> newPaymentCredentials = Collections.unmodifiableList(paymentCredentials);
    return newPaymentCredentials;
  }

  public int numberOfPaymentCredentials()
  {
    int number = paymentCredentials.size();
    return number;
  }

  public boolean hasPaymentCredentials()
  {
    boolean has = paymentCredentials.size() > 0;
    return has;
  }

  public int indexOfPaymentCredential(PaymentCredentials aPaymentCredential)
  {
    int index = paymentCredentials.indexOf(aPaymentCredential);
    return index;
  }
  /* Code from template association_GetOne */
  public ShoppingCart getShoppingCart()
  {
    return shoppingCart;
  }
  /* Code from template association_GetOne */
  public Order getOrder()
  {
    return order;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPaymentCredentials()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public PaymentCredentials addPaymentCredential(String aCardholderName, String aCcNumber, String aExpirationDate, int aCvc, Address aBillingAddress, Order aOrder)
  {
    return new PaymentCredentials(aCardholderName, aCcNumber, aExpirationDate, aCvc, aBillingAddress, aOrder, this);
  }

  public boolean addPaymentCredential(PaymentCredentials aPaymentCredential)
  {
    boolean wasAdded = false;
    if (paymentCredentials.contains(aPaymentCredential)) { return false; }
    Customer existingCustomer = aPaymentCredential.getCustomer();
    boolean isNewCustomer = existingCustomer != null && !this.equals(existingCustomer);
    if (isNewCustomer)
    {
      aPaymentCredential.setCustomer(this);
    }
    else
    {
      paymentCredentials.add(aPaymentCredential);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePaymentCredential(PaymentCredentials aPaymentCredential)
  {
    boolean wasRemoved = false;
    //Unable to remove aPaymentCredential, as it must always have a customer
    if (!this.equals(aPaymentCredential.getCustomer()))
    {
      paymentCredentials.remove(aPaymentCredential);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addPaymentCredentialAt(PaymentCredentials aPaymentCredential, int index)
  {  
    boolean wasAdded = false;
    if(addPaymentCredential(aPaymentCredential))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPaymentCredentials()) { index = numberOfPaymentCredentials() - 1; }
      paymentCredentials.remove(aPaymentCredential);
      paymentCredentials.add(index, aPaymentCredential);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePaymentCredentialAt(PaymentCredentials aPaymentCredential, int index)
  {
    boolean wasAdded = false;
    if(paymentCredentials.contains(aPaymentCredential))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPaymentCredentials()) { index = numberOfPaymentCredentials() - 1; }
      paymentCredentials.remove(aPaymentCredential);
      paymentCredentials.add(index, aPaymentCredential);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPaymentCredentialAt(aPaymentCredential, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=paymentCredentials.size(); i > 0; i--)
    {
      PaymentCredentials aPaymentCredential = paymentCredentials.get(i - 1);
      aPaymentCredential.delete();
    }
    ShoppingCart existingShoppingCart = shoppingCart;
    shoppingCart = null;
    if (existingShoppingCart != null)
    {
      existingShoppingCart.delete();
    }
    Order existingOrder = order;
    order = null;
    if (existingOrder != null)
    {
      existingOrder.delete();
    }
    super.delete();
  }

}