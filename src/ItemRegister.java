import java.util.*;
/**
 * @author Ina Martini
 * @version 1.2.0
 */
public class ItemRegister {

    private final ArrayList <Item> allItems;

    public ItemRegister() {
        allItems = new ArrayList <>();
    }

    /**
     * Method to test if the item number already exists, the method returns true if the
     * item number exists
     *
     * @return returns if the item number is duplicate as boolean
     */
    public boolean testForDuplicateItemNumber(String itemNumber) {
        for (Item items : allItems) {
            if (items.getItemNumber().equalsIgnoreCase(itemNumber)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method to register a new item
     *
     * @param itemNumber    item number as String
     * @param description   description of item as String
     * @param price         price of item as double
     * @param brandName     brand name of item as String
     * @param weight        weight of item as double
     * @param length        length of item as double
     * @param height        height of item as double
     * @param color         color of item as String
     * @param numberOfItems number of items as int
     * @param category      category of item as int
     */
    public void newItem(String itemNumber, String description, double price, String brandName, double weight,
                        double length, double height, String color, int numberOfItems, int category) {

        Item newItem = new Item(itemNumber, description, price, brandName, weight, length, height,
                color, numberOfItems, category);

        if (!testForDuplicateItemNumber(itemNumber)) {
            allItems.add(newItem);
        } else {
            throw new IllegalArgumentException("This item number is already in use.");
        }
    }

    /**
     * Method to print info about all the items registered
     *
     * @return returns info about all the items
     */
    public String printAllItems() {
        String allItemsList = "";

        if (getNumberOfItems() > 0) {
            for (Item item : allItems) {
                allItemsList += item.toSmallString() + "\n";
            }
            return allItemsList;
        }
        throw new NullPointerException("Couldn't find any items.");
    }

    /**
     * Method to print one item
     */
    public void printItem(String itemNumber) {
        for (Item items : allItems) {
            if (items.getItemNumber().equalsIgnoreCase(itemNumber)) {
                System.out.println(items);
            }
        }
    }

    /**
     * Method to print all items in a list, but with less information
     */
    public void printItemsSmallString(ArrayList <Item> list) {
        if (list.size() > 0) {
            for (Item items : list) {
                System.out.println(items.toSmallString());
            }
        } else {
            throw new IllegalArgumentException("Couldn't find any items");
        }
    }


    /**
     * Method to find an item with a specific itemNumber
     */
    public Item findItemWithItemNumber (String itemNumber) {
        for (Item items : allItems){
            if(items.getItemNumber().equalsIgnoreCase(itemNumber)) {
                return new Item(items);
            }
        }
        throw new NullPointerException("This item doesn't exist.");
    }

    /**
     * Method to find an item with a specific description
     *
     * @param description description of item from a user-input as String
     * @return returns a list of items with the same description
     */
    public ArrayList <Item> findItemWithDescription(String description) {

        ArrayList <Item> itemsWithDescription = new ArrayList<>();

        for (Item items : allItems){
            if(items.getDescription().toLowerCase().contains(description)){
                Item deepCopy = new Item(items);
                itemsWithDescription.add(deepCopy);
                return itemsWithDescription;
            }
        }
        throw new NullPointerException("Couldn't find any items.");
    }

    /**
     * Method to find an item with a specific itemNumber and description
     *
     * @param itemNumber item number as String
     * @param description description of item as String
     * @return returns a list of items with the same item number and description
     */
    public Item findItemWithItemNumberAndDescription (String itemNumber, String description) {
        for (Item items : allItems){
            if(items.getItemNumber().equalsIgnoreCase(itemNumber)&&
                    items.getDescription().toLowerCase().contains(description)) {
                return new Item(items);
            }
        }
        throw new NullPointerException("This item doesn't exist.");
    }

    /**
     *  Method to find an item by category
     */
    public ArrayList<Item> findItemsByCategory(int category) {

        ArrayList <Item> itemsWithCategory = new ArrayList<>();

        for (Item items : allItems) {
            if(items.getCategory() == category) {
                Item deepCopy = new Item(items);
                itemsWithCategory.add(deepCopy);
            }
        }
        return itemsWithCategory;
    }

    /**
     * Method to increase the amounts of one item
     *
     * @param itemNumber item number as String
     * @param addNumberOfItems the new number of items to add to the original number as int
     */
    public void increaseAmountOfOneItem(String itemNumber, int addNumberOfItems) {
        for (Item items : allItems) {
            if(items.getItemNumber().equalsIgnoreCase(itemNumber)){
                int newNumberOfItems = items.getNumberOfItems() + addNumberOfItems;
                items.setNewNumberOfItems(newNumberOfItems);
            }
        }
    }

    /**
     * Method to decrease the amount of one item
     *
     * @param itemNumber item number as String
     * @param subtractNumberOfItems the number of items to subtract from the original number as int
     */
    public void decreaseAmountOfOneItem(String itemNumber, int subtractNumberOfItems) {
        for (Item items : allItems) {
            if(items.getItemNumber().equalsIgnoreCase(itemNumber)){
                int newNumberOfItems = items.getNumberOfItems() - subtractNumberOfItems;
                if (newNumberOfItems < 0) {
                    throw new IllegalArgumentException("The number of items can't be negative.");
                }
                else {
                    items.setNewNumberOfItems(newNumberOfItems);
                }
            }
        }
    }

    /**
     * Method to change the price on an item
     *
     * @param itemNumber item number as String
     * @param newPrice the new price of item as double
     */
    public void changePriceOnAnItem(String itemNumber, double newPrice) {
        for (Item item : allItems) {
            if(item.getItemNumber().equalsIgnoreCase(itemNumber)){
                item.setNewPrice(newPrice);
            }
        }
    }

    /**
     * Method to create a discount on an item
     *
     * @param itemNumber item number as String
     * @param discountPercent the discount in percent as double
     */
    public void giveDiscountOnAnItem(String itemNumber, double discountPercent) {
        for (Item item : allItems) {
            if(item.getItemNumber().equalsIgnoreCase(itemNumber)){
                double newPrice = item.getPrice() - ((item.getPrice() * discountPercent ) / 100);
                item.setNewPrice(newPrice);
            }
        }
    }

    /**
     * Method to change the description on an item
     *
     * @param newDescription description of item as String
     */
    public void changeDescriptionOnAnItem(String itemNumber, String newDescription) {
        for (Item items : allItems) {
            if (items.getItemNumber().equalsIgnoreCase(itemNumber)) {
                items.setNewDescription(newDescription);
            }
        }
    }

    /**
     * Method to sort items by price
     *
     * @return returns list sorted by price
     */
    public ArrayList <Item> sortItemsByPrice() {
        allItems.sort(Comparator.comparing(Item::getPrice));
        return allItems;
    }

    /**
     * Method to sort items by color
     *
     * @return returns list sorted by color
     */
    public ArrayList <Item> sortItemsByColor() {
        allItems.sort(Comparator.comparing(Item::getColor));
        return allItems;
    }

    /**
     * Method to sort items by brand name
     *
     * @return returns list sorted by brand name
     */
    public ArrayList <Item> sortItemsByBrandName() {
        allItems.sort(Comparator.comparing(Item::getBrandName));
        return allItems;
    }

    /**
     *  Method to find all items within a price-range
     *
     * @return returns list of all items between the two chosen prices
     */
    public ArrayList <Item> findAllItemsBetweenPrice(double startPrice, double endPrice) {
        ArrayList <Item> itemsBetweenPrices = new ArrayList<>();
        for (Item items : allItems) {
            if (items.getPrice() >= startPrice && items.getPrice() <= endPrice) {
                Item deepCopy = new Item(items);
                itemsBetweenPrices.add(deepCopy);
                return itemsBetweenPrices;
            }
        }
        throw new NullPointerException("Couldn't find any items.");
    }

    /**
     * Method to find number of items in storage
     *
     * @return returns number of items in storage as int
     */
    public int getNumberOfItems() {
        return allItems.size();
    }

    /**
     * Method to find number of individual items in storage
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

    /**
     * Testdata
     * Used to test inputs and checks if they are added correctly
     */
    public void addDefaultItems() {
        newItem("477B", "Big and heavy", 1000,
                "Andersen's Laminate Flooring", 1.2, 1.4, 12, "white",
                102, 1);
        newItem("5A7B", "Big and tall", 1300, "Ina Window-shop",
                1.5, 3.45, 1.05,  "black", 10, 2);
        newItem("8B00", "New and small", 500, "Andersen AS",
                0.2, 0.4, 10, "green", 2, 3);
        newItem("AA11", "Tall and heavy", 2000, "Petersen Lumber",
                3.2, 1.0, 11, "white", 1022, 4);
        newItem("2", "Tall, big and heavy", 99, "Lumber AS",
                1.3, 0.9, 10, "black", 10, 4);
    }

    /**
     * Method to delete an item from the item register
     *
     * @param itemNumber item number as String
     */
    public void deleteAnItem(String itemNumber) {
        for (Item items : allItems) {
            if(items.getItemNumber().equalsIgnoreCase(itemNumber)){
                Item deepCopy = new Item(items);
                allItems.remove(deepCopy);
            }
        }
        throw new IllegalArgumentException("This item doesn't exist.");
    }
}