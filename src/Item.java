/** A class representing each item in the warehouse storage.
 *
 * @author 10119
 * @version 1.1.0
 */
public class Item {
  private final String itemNumber;
  private final String brandName;
  private final String color;
  private String description;
  private final Category category;
  private int numberOfItems;
  private final double weight;
  private final double length;
  private final double height;
  private double price;


  /** Constructor for the class, with information about the item.
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
   * @param categoryIndex  category of item as int
   */
  public Item(String itemNumber, String description, double price, String brandName, double weight,
                double length, double height, String color, int numberOfItems, int categoryIndex) {

    if (itemNumber.isBlank()) {
      throw new IllegalArgumentException("The item number can't be left blank.");
    }
    if (brandName.isBlank()) {
      throw new IllegalArgumentException("The brand name can't be left blank.");
    }
    if (price < 0) {
      throw new IllegalArgumentException("The price can't be a negative number.");
    }
    if (weight <= 0) {
      throw new IllegalArgumentException("The weight can't be 0 or a negative number.");
    }
    if (length <= 0) {
      throw new IllegalArgumentException("The length can't be 0 or a negative number.");
    }
    if (height <= 0) {
      throw new IllegalArgumentException("The height can't be 0 or a negative number.");
    }
    if (numberOfItems < 0) {
      throw new IllegalArgumentException("The number of items can't be a negative number.");
    }
    if (categoryIndex < 1 || categoryIndex > 4) {
      throw new IllegalArgumentException("This category doesn't exist. You must type a "
          + "number between 1 and 4.");
    }

    this.itemNumber = itemNumber;
    this.description = description;
    this.price = price;
    this.brandName = brandName;
    this.weight = weight;
    this.length = length;
    this.height = height;
    this.color = color;
    this.numberOfItems = numberOfItems;
    this.category = Category.getCategory(categoryIndex);
  }

  /** Constructor for cloning the Item-register.
   *
   * @param cloningItem a deepCopy as Item
   */
  public Item(Item cloningItem) {
    this.itemNumber = cloningItem.getItemNumber();
    this.description = cloningItem.getDescription();
    this.price = cloningItem.getPrice();
    this.brandName = cloningItem.getBrandName();
    this.weight = cloningItem.getWeight();
    this.length = cloningItem.getLength();
    this.height = cloningItem.getHeight();
    this.color = cloningItem.getColor();
    this.numberOfItems = cloningItem.getNumberOfItems();
    this.category = cloningItem.getItemCategory();
  }

  public String getItemNumber() {

    return this.itemNumber;
  }

  public String getDescription() {

    return this.description;
  }

  public double getPrice() {

    return this.price;
  }

  public String getBrandName() {

    return this.brandName;
  }

  public double getWeight() {

    return this.weight;
  }

  public double getLength() {

    return this.length;
  }

  public double getHeight() {

    return this.height;
  }

  public String getColor() {

    return this.color;
  }

  public int getNumberOfItems() {

    return this.numberOfItems;
  }

  public int getItemCategoryNumber() {

    return category.getCategoryNumber();
  }

  public String getItemCategoryString() {

    return String.valueOf(category);
  }

  private Category getItemCategory() {

    return category;
  }

  public void setNewPrice(double newPrice) {

    this.price = newPrice;
  }

  public void setNewNumberOfItems(int newNumberOfItems) {

    this.numberOfItems = newNumberOfItems;
  }

  public void setNewDescription(String newDescription) {

    this.description = newDescription;
  }

  /** toString method to print all info about an item.
   *
   * @return returns info about the item as String
   */
  public String toString() {
    return "\nItem number: " + this.getItemNumber() + ", Brand name: " + this.getBrandName()
        + ", Category: " + this.getItemCategoryString() + ", Price: " + this.getPrice()
        + " kr, Description: " + this.getDescription() + ", Color: " + this.getColor()
        + ", \nWeight: " + this.getWeight() + ", Length: " + this.getLength()
        + ", Height: " + this.getHeight() + ", Number of items: " + this.getNumberOfItems();
  }

  /** toSmallString method to print info about an item.
   *
   * @return returns a shortened info about the item as String
   */
  public String toSmallString() {
    return "\nItem number: " + this.getItemNumber() + ", Brand name: " + this.getBrandName()
        + ", Category: " + this.getItemCategoryString() +  " (" + this.getPrice()
        + " kr), Description: " + this.getDescription() + ", Number of items: "
        + this.getNumberOfItems();
  }
}
