import java.util.ArrayList;
import java.util.Comparator;

/** A class administrating the Warehouse storage, with methods for finding,
 * editing and searching for items.
 *
 * @author 10119
 * @version 1.1.0
 */
public class ItemRegister {

  private final ArrayList<Item> allItems;

  public ItemRegister() {

    allItems = new ArrayList<>();
  }

  /** Method to test if the item number already exists, the method returns true if the
   * item number exists.
   *
   * @return returns if the item number is duplicate as boolean
   */
  public boolean testForDuplicateItemNumber(String itemNumber) {
    boolean itemExists = false;
    for (Item items : allItems) {
      if (items.getItemNumber().equalsIgnoreCase(itemNumber)) {
        itemExists = true;
        break;
      }
    }
    return itemExists;
  }

  /** Method to register a new item.
   *
   * @param itemNumber     item number as String
   * @param description    description of item as String
   * @param price          price of item as double
   * @param brandName      brand name of item as String
   * @param weight         weight of item as double
   * @param length         length of item as double
   * @param height         height of item as double
   * @param color          color of item as String
   * @param numberOfItems  number of items as int
   * @param categoryNumber categoryNumber of item as int
   */
  public void newItem(String itemNumber, String description, double price, String brandName,
                      double weight, double length, double height, String color, int numberOfItems,
                      int categoryNumber) {

    Item newItem = new Item(itemNumber, description, price, brandName, weight, length, height,
                color, numberOfItems, categoryNumber);

    if (!testForDuplicateItemNumber(itemNumber)) {
      allItems.add(newItem);
    } else {
      throw new IllegalArgumentException("This item number is already in use.");
    }
  }

  /** Method to print info about all the items registered.
   *
   * @return returns info about all the items
   */
  public String printAllItems() {
    StringBuilder allItemsList = new StringBuilder();

    if (getNumberOfItems() > 0) {
      for (Item items : allItems) {
        allItemsList.append(items).append("\n");
      }
      return allItemsList.toString();
    }
    throw new IllegalArgumentException("Couldn't find any items.");
  }

  /** Method to print all items in a list, but with less information.
   *
   */
  public void printItemsSmallString(ArrayList<Item> list) {
    if (list.size() > 0) {
      for (Item items : list) {
        System.out.println(items.toSmallString());
      }
    } else {
      throw new IllegalArgumentException("Couldn't find any items");
    }
  }

  /** Method to find an item with a specific itemNumber.
   *
   */
  public Item getItemWithItemNumber(String itemNumber) {
    for (Item items : allItems) {
      if (items.getItemNumber().equalsIgnoreCase(itemNumber)) {
        return new Item(items);
      }
    }
    throw new IllegalArgumentException("This item doesn't exist.");
  }

  /** Method to find an item with a specific description.
   *
   * @param description description of item from a user-input as String
   * @return returns a list of items with the same description
   */
  public ArrayList<Item> getItemWithDescription(String description) {

    ArrayList<Item> itemsWithDescription = new ArrayList<>();

    for (Item items : allItems) {
      if (items.getDescription().toLowerCase().contains(description)) {
        Item deepCopy = new Item(items);
        itemsWithDescription.add(deepCopy);
      }
    }
    return itemsWithDescription;
  }

  /** Method to find an item with a specific itemNumber and description.
   *
   * @param itemNumber item number as String
   * @param description description of item as String
   * @return returns a list of items with the same item number and description
   */
  public Item getItemWithItemNumberAndDescription(String itemNumber, String description) {
    for (Item items : allItems) {
      if (items.getItemNumber().equalsIgnoreCase(itemNumber)
          && items.getDescription().toLowerCase().contains(description)) {
        return new Item(items);
      }
    }
    throw new IllegalArgumentException("This item doesn't exist.");
  }

  /** Method to find an item by category.
   *
   */
  public ArrayList<Item> getItemsByCategory(int categoryInt) {

    ArrayList<Item> itemsWithCategory = new ArrayList<>();

    for (Item items : allItems) {
      if (items.getItemCategoryNumber() == categoryInt) {
        Item deepCopy = new Item(items);
        itemsWithCategory.add(deepCopy);
      }
    }
    return itemsWithCategory;
  }

  /** Method to increase the amounts of one item.
   *
   * @param itemNumber item number as String
   * @param addNumberOfItems the new number of items to add to the original number as int
   */
  public void increaseAmountOfOneItem(String itemNumber, int addNumberOfItems) {
    for (Item items : allItems) {
      if (items.getItemNumber().equalsIgnoreCase(itemNumber)) {
        int newNumberOfItems = items.getNumberOfItems() + addNumberOfItems;
        items.setNewNumberOfItems(newNumberOfItems);
      }
    }
  }

  /** Method to decrease the amount of one item.
   *
   * @param itemNumber item number as String
   * @param subtractNumberOfItems the number of items to subtract from the original number as int
   */
  public void decreaseAmountOfOneItem(String itemNumber, int subtractNumberOfItems) {
    for (Item items : allItems) {
      if (items.getItemNumber().equalsIgnoreCase(itemNumber)) {
        int newNumberOfItems = items.getNumberOfItems() - subtractNumberOfItems;
        if (newNumberOfItems < 0) {
          throw new IllegalArgumentException("The number of items can't be negative.");
        } else {
          items.setNewNumberOfItems(newNumberOfItems);
        }
      }
    }
  }

  /** Method to change the price on an item.
   *
   * @param itemNumber item number as String
   * @param newPrice the new price of item as double
   */
  public void changePriceItem(String itemNumber, double newPrice) {
    for (Item items : allItems) {
      if (items.getItemNumber().equalsIgnoreCase(itemNumber)) {
        items.setNewPrice(newPrice);
      }
    }
  }

  /** Method to create a discount on an item.
   *
   * @param itemNumber item number as String
   * @param discountPercent the discount in percent as double
   */
  public void giveDiscountItem(String itemNumber, double discountPercent) {
    for (Item items : allItems) {
      if (items.getItemNumber().equalsIgnoreCase(itemNumber)) {
        double newPrice = items.getPrice() - ((items.getPrice() * discountPercent) / 100);
        items.setNewPrice(newPrice);
      }
    }
  }

  /** Method to change the description on an item.
   *
   * @param newDescription description of item as String
   */
  public void changeDescriptionItem(String itemNumber, String newDescription) {
    for (Item items : allItems) {
      if (items.getItemNumber().equalsIgnoreCase(itemNumber)) {
        items.setNewDescription(newDescription);
      }
    }
  }

  /** Method to sort items by price.
   *
   * @return returns list sorted by price
   */
  public ArrayList<Item> sortItemsByPrice() {
    allItems.sort(Comparator.comparing(Item::getPrice));
    return allItems;
  }

  /** Method to sort items by color.
   *
   * @return returns list sorted by color
   */
  public ArrayList<Item> sortItemsByColor() {
    allItems.sort(Comparator.comparing(Item::getColor));
    return allItems;
  }

  /** Method to sort items by brand name.
   *
   * @return returns list sorted by brand name
   */
  public ArrayList<Item> sortItemsByBrandName() {
    allItems.sort(Comparator.comparing(Item::getBrandName));
    return allItems;
  }

  /** Method to find all items within a price-range.
   *
   * @return returns list of all items between the two chosen prices
   */
  public ArrayList<Item> getAllItemsBetweenPrice(double startPrice, double endPrice) {
    ArrayList<Item> itemsBetweenPrices = new ArrayList<>();
    for (Item items : allItems) {
      if (items.getPrice() >= startPrice && items.getPrice() <= endPrice) {
        Item deepCopy = new Item(items);
        itemsBetweenPrices.add(deepCopy);
        return itemsBetweenPrices;
      }
    }
    throw new IllegalArgumentException("Couldn't find any items.");
  }

  /** Method to find number of items in storage.
   *
   * @return returns number of items in storage as int
   */
  public int getNumberOfItems() {
    return allItems.size();
  }

  /** Method to find number of individual items in storage.
   *
   * @return returns number of individual items in storage as int
   */
  public int getNumberOfIndividualItems() {
    int sum = 0;
    for (Item items : allItems) {
      sum += items.getNumberOfItems();
    }
    return sum;
  }

  /** Method to add default testdata to the itemRegister.
   * Used to test inputs and checks if they are added correctly.
   */
  public void addDefaultItems() {
    newItem("477B", "Big and heavy", 1000,
        "Andersen's Laminate Flooring", 1.2, 1.4, 1.2, "white",
        102, 1);
    newItem("5A7B", "Big and tall", 1300, "Ina Window-shop",
        1.5, 3.45, 1.05,  "black", 10, 2);
    newItem("8B00", "New and small", 590, "Andersen AS",
        0.2, 0.4, 2.05, "green", 2, 3);
    newItem("AA11", "Tall and heavy", 249, "Petersen Lumber",
        3.2, 1.0, 1.9, "white", 1022, 4);
    newItem("34VK", "Tall, big and heavy", 99, "Lumber AS",
        1.3, 0.9, 10, "black", 10, 4);
  }

  /** Method to delete an item from the item register.
   *
   * @param itemNumber item number as String
   */
  public void deleteItem(String itemNumber) {
    for (int i = 0; i < getNumberOfItems(); i++) {
      if (allItems.get(i).getItemNumber().equalsIgnoreCase(itemNumber)) {
        allItems.remove(allItems.get(i));
      }
    }
  }
}