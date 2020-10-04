/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse321.projectgroup02.model;
import java.sql.Date;
import java.util.*;

// line 23 "../../../../../../model.ump"
// line 187 "../../../../../../model.ump"
public class Order
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Order Attributes
  private int commisionPercentage;
  private double commissionTotal;
  private String orderNumber;
  private Date orderDate;
  private double itemsPrice;
  private double taxes;
  private double totalPrice;
  private boolean freeShipping;

  //Order Associations
  private PaymentCredentials paymentCredentials;
  private List<Item> orderedItems;
  private Delivery deliveryMethod;
  private Customer buyer;
  private OrderConfirmationEmail orderConfirmationEmail;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Order(int aCommisionPercentage, double aCommissionTotal, String aOrderNumber, Date aOrderDate, double aItemsPrice, double aTaxes, double aTotalPrice, boolean aFreeShipping, PaymentCredentials aPaymentCredentials, Delivery aDeliveryMethod, Customer aBuyer, OrderConfirmationEmail aOrderConfirmationEmail)
  {
    commisionPercentage = aCommisionPercentage;
    commissionTotal = aCommissionTotal;
    orderNumber = aOrderNumber;
    orderDate = aOrderDate;
    itemsPrice = aItemsPrice;
    taxes = aTaxes;
    totalPrice = aTotalPrice;
    freeShipping = aFreeShipping;
    if (aPaymentCredentials == null || aPaymentCredentials.getOrder() != null)
    {
      throw new RuntimeException("Unable to create Order due to aPaymentCredentials. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    paymentCredentials = aPaymentCredentials;
    orderedItems = new ArrayList<Item>();
    if (aDeliveryMethod == null || aDeliveryMethod.getOrder() != null)
    {
      throw new RuntimeException("Unable to create Order due to aDeliveryMethod. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    deliveryMethod = aDeliveryMethod;
    if (aBuyer == null || aBuyer.getOrder() != null)
    {
      throw new RuntimeException("Unable to create Order due to aBuyer. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    buyer = aBuyer;
    if (aOrderConfirmationEmail == null || aOrderConfirmationEmail.getOrder() != null)
    {
      throw new RuntimeException("Unable to create Order due to aOrderConfirmationEmail. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    orderConfirmationEmail = aOrderConfirmationEmail;
  }

  public Order(int aCommisionPercentage, double aCommissionTotal, String aOrderNumber, Date aOrderDate, double aItemsPrice, double aTaxes, double aTotalPrice, boolean aFreeShipping, String aCardholderNameForPaymentCredentials, String aCcNumberForPaymentCredentials, String aExpirationDateForPaymentCredentials, int aCvcForPaymentCredentials, Address aBillingAddressForPaymentCredentials, Customer aCustomerForPaymentCredentials, DeliveryMethod aDeliveryMethodForDeliveryMethod, double aDeliveryPriceForDeliveryMethod, String aDeliveryIDForDeliveryMethod, ShoppingCart aShoppingCartForBuyer, String aOrderConfirmationIDForOrderConfirmationEmail, String aMessageForOrderConfirmationEmail, User aReceiverForOrderConfirmationEmail)
  {
    commisionPercentage = aCommisionPercentage;
    commissionTotal = aCommissionTotal;
    orderNumber = aOrderNumber;
    orderDate = aOrderDate;
    itemsPrice = aItemsPrice;
    taxes = aTaxes;
    totalPrice = aTotalPrice;
    freeShipping = aFreeShipping;
    paymentCredentials = new PaymentCredentials(aCardholderNameForPaymentCredentials, aCcNumberForPaymentCredentials, aExpirationDateForPaymentCredentials, aCvcForPaymentCredentials, aBillingAddressForPaymentCredentials, this, aCustomerForPaymentCredentials);
    orderedItems = new ArrayList<Item>();
    deliveryMethod = new Delivery(aDeliveryMethodForDeliveryMethod, aDeliveryPriceForDeliveryMethod, aDeliveryIDForDeliveryMethod, this);
    buyer = new Customer(aShoppingCartForBuyer, this);
    orderConfirmationEmail = new OrderConfirmationEmail(aOrderConfirmationIDForOrderConfirmationEmail, aMessageForOrderConfirmationEmail, aReceiverForOrderConfirmationEmail, this);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setCommisionPercentage(int aCommisionPercentage)
  {
    boolean wasSet = false;
    commisionPercentage = aCommisionPercentage;
    wasSet = true;
    return wasSet;
  }

  public boolean setCommissionTotal(double aCommissionTotal)
  {
    boolean wasSet = false;
    commissionTotal = aCommissionTotal;
    wasSet = true;
    return wasSet;
  }

  public boolean setOrderNumber(String aOrderNumber)
  {
    boolean wasSet = false;
    orderNumber = aOrderNumber;
    wasSet = true;
    return wasSet;
  }

  public boolean setOrderDate(Date aOrderDate)
  {
    boolean wasSet = false;
    orderDate = aOrderDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setItemsPrice(double aItemsPrice)
  {
    boolean wasSet = false;
    itemsPrice = aItemsPrice;
    wasSet = true;
    return wasSet;
  }

  public boolean setTaxes(double aTaxes)
  {
    boolean wasSet = false;
    taxes = aTaxes;
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

  public boolean setFreeShipping(boolean aFreeShipping)
  {
    boolean wasSet = false;
    freeShipping = aFreeShipping;
    wasSet = true;
    return wasSet;
  }

  public int getCommisionPercentage()
  {
    return commisionPercentage;
  }

  public double getCommissionTotal()
  {
    return commissionTotal;
  }

  /**
   * pk
   */
  public String getOrderNumber()
  {
    return orderNumber;
  }

  public Date getOrderDate()
  {
    return orderDate;
  }

  public double getItemsPrice()
  {
    return itemsPrice;
  }

  public double getTaxes()
  {
    return taxes;
  }

  public double getTotalPrice()
  {
    return totalPrice;
  }

  public boolean getFreeShipping()
  {
    return freeShipping;
  }
  /* Code from template association_GetOne */
  public PaymentCredentials getPaymentCredentials()
  {
    return paymentCredentials;
  }
  /* Code from template association_GetMany */
  public Item getOrderedItem(int index)
  {
    Item aOrderedItem = orderedItems.get(index);
    return aOrderedItem;
  }

  public List<Item> getOrderedItems()
  {
    List<Item> newOrderedItems = Collections.unmodifiableList(orderedItems);
    return newOrderedItems;
  }

  public int numberOfOrderedItems()
  {
    int number = orderedItems.size();
    return number;
  }

  public boolean hasOrderedItems()
  {
    boolean has = orderedItems.size() > 0;
    return has;
  }

  public int indexOfOrderedItem(Item aOrderedItem)
  {
    int index = orderedItems.indexOf(aOrderedItem);
    return index;
  }
  /* Code from template association_GetOne */
  public Delivery getDeliveryMethod()
  {
    return deliveryMethod;
  }
  /* Code from template association_GetOne */
  public Customer getBuyer()
  {
    return buyer;
  }
  /* Code from template association_GetOne */
  public OrderConfirmationEmail getOrderConfirmationEmail()
  {
    return orderConfirmationEmail;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfOrderedItems()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Item addOrderedItem(String aName, String aId, int aHeight, int aWidth, int aBreadth, Date aCreationDate, String aDesription, double aPrice, double aReducedPrice, boolean aInStock, Artist aCreator, ArtGallerySystem aGallery, ShoppingCart aShoppingCart, Collection aCollection)
  {
    return new Item(aName, aId, aHeight, aWidth, aBreadth, aCreationDate, aDesription, aPrice, aReducedPrice, aInStock, aCreator, aGallery, this, aShoppingCart, aCollection);
  }

  public boolean addOrderedItem(Item aOrderedItem)
  {
    boolean wasAdded = false;
    if (orderedItems.contains(aOrderedItem)) { return false; }
    Order existingOrder = aOrderedItem.getOrder();
    boolean isNewOrder = existingOrder != null && !this.equals(existingOrder);
    if (isNewOrder)
    {
      aOrderedItem.setOrder(this);
    }
    else
    {
      orderedItems.add(aOrderedItem);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeOrderedItem(Item aOrderedItem)
  {
    boolean wasRemoved = false;
    //Unable to remove aOrderedItem, as it must always have a order
    if (!this.equals(aOrderedItem.getOrder()))
    {
      orderedItems.remove(aOrderedItem);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addOrderedItemAt(Item aOrderedItem, int index)
  {  
    boolean wasAdded = false;
    if(addOrderedItem(aOrderedItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOrderedItems()) { index = numberOfOrderedItems() - 1; }
      orderedItems.remove(aOrderedItem);
      orderedItems.add(index, aOrderedItem);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveOrderedItemAt(Item aOrderedItem, int index)
  {
    boolean wasAdded = false;
    if(orderedItems.contains(aOrderedItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOrderedItems()) { index = numberOfOrderedItems() - 1; }
      orderedItems.remove(aOrderedItem);
      orderedItems.add(index, aOrderedItem);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addOrderedItemAt(aOrderedItem, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    PaymentCredentials existingPaymentCredentials = paymentCredentials;
    paymentCredentials = null;
    if (existingPaymentCredentials != null)
    {
      existingPaymentCredentials.delete();
    }
    for(int i=orderedItems.size(); i > 0; i--)
    {
      Item aOrderedItem = orderedItems.get(i - 1);
      aOrderedItem.delete();
    }
    Delivery existingDeliveryMethod = deliveryMethod;
    deliveryMethod = null;
    if (existingDeliveryMethod != null)
    {
      existingDeliveryMethod.delete();
    }
    Customer existingBuyer = buyer;
    buyer = null;
    if (existingBuyer != null)
    {
      existingBuyer.delete();
    }
    OrderConfirmationEmail existingOrderConfirmationEmail = orderConfirmationEmail;
    orderConfirmationEmail = null;
    if (existingOrderConfirmationEmail != null)
    {
      existingOrderConfirmationEmail.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "commisionPercentage" + ":" + getCommisionPercentage()+ "," +
            "commissionTotal" + ":" + getCommissionTotal()+ "," +
            "orderNumber" + ":" + getOrderNumber()+ "," +
            "itemsPrice" + ":" + getItemsPrice()+ "," +
            "taxes" + ":" + getTaxes()+ "," +
            "totalPrice" + ":" + getTotalPrice()+ "," +
            "freeShipping" + ":" + getFreeShipping()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "orderDate" + "=" + (getOrderDate() != null ? !getOrderDate().equals(this)  ? getOrderDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "paymentCredentials = "+(getPaymentCredentials()!=null?Integer.toHexString(System.identityHashCode(getPaymentCredentials())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "deliveryMethod = "+(getDeliveryMethod()!=null?Integer.toHexString(System.identityHashCode(getDeliveryMethod())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "buyer = "+(getBuyer()!=null?Integer.toHexString(System.identityHashCode(getBuyer())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "orderConfirmationEmail = "+(getOrderConfirmationEmail()!=null?Integer.toHexString(System.identityHashCode(getOrderConfirmationEmail())):"null");
  }
}