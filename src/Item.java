
/**
 * @author Ina Martini
 * @version 1.2.0
 */
public class Item {

    private final String itemNumber, brandName, color;
    private String description;
    private final int category;
    private int numberOfItems;
    private final double weight, length, height;
    private double price;

    public Item(String itemNumber, String description, double price, String brandName, double weight,
                double length, double height, String color, int numberOfItems, int category) {

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

        this.itemNumber = itemNumber;
        this.description = description;
        this.price = price;
        this.brandName = brandName;
        this.weight = weight;
        this.length = length;
        this.height = height;
        this.color = color;
        this.numberOfItems = numberOfItems;
        this.category = category;
    }

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
        this.category = cloningItem.getCategory();
    }

    /**
     *
     * @return returns item number as String
     */
    public String getItemNumber() {

        return this.itemNumber;
    }

    /**
     *
     * @return returns description as String
     */
    public String getDescription() {

        return this.description;
    }

    /**
     *
     * @return returns price as double
     */
    public double getPrice() {

        return this.price;
    }

    /**
     *
     * @return returns brand name as String
     */
    public String getBrandName() {

        return this.brandName;
    }

    /**
     *
     * @return returns weight as double
     */
    public double getWeight() {

        return this.weight;
    }

    /**
     *
     * @return returns length as double
     */
    public double getLength() {

        return this.length;
    }

    /**
     *
     * @return returns height as double
     */
    public double getHeight() {

        return this.height;
    }

    /**
     *
     * @return returns an items color as String
     */
    public String getColor() {

        return this.color;
    }

    /**
     *
     * @return returns amount of an item as int
     */
    public int getNumberOfItems() {

        return this.numberOfItems;
    }

    /**
     *
     * @return returns category as int
     */
    public int getCategory() {

        return this.category;
    }

    public Enum <Category> getCategoryNumber() {

        return Category.getCategoryNumber(category);
    }

    /**
     *
     * @param newPrice set new price as double
     */
    public void setNewPrice(double newPrice) {

        this.price = newPrice;
    }

    /**
     *
     * @param newNumberOfItems set new amount of one item as int
     */
    public void setNewNumberOfItems(int newNumberOfItems) {

        this.numberOfItems = newNumberOfItems;
    }

    /**
     *
     * @param newDescription set new description as String
     */
    public void setNewDescription(String newDescription) {

        this.description = newDescription;
    }

    /**
     * @return returns info about the item as String
     */
    public String toString() {
        return "Item number: " + this.getItemNumber() + ", Brand name: " + this.getBrandName() + ", Category: " +
                this.getCategory() + ", Price: " + this.getPrice() + " kr, Description: " + this.getDescription()  +
                ", Color: " + this.getColor() + ", \nWeight: " + this.getWeight() + ", Length: " + this.getLength() +
                ", Height: " + this.getHeight() + ", Number of items: " + this.getNumberOfItems();
    }

    /**
     *
     * @return returns a shortened info about the item as String
     */
    public String toSmallString() {
        return "\nItem number: " + this.getItemNumber() + ", Brand name: " + this.getBrandName() + ", Category: " +
                this.getCategory() +  " (" + this.getPrice() + " kr), Color: " + this.getColor() +
                ", Number of items: " + this.getNumberOfItems();
    }
}
