import java.util.Objects;
import java.util.Scanner;

/** A class representing a text based user interface of the application.
 *
 * @author 10119
 * @version 1.2.0
 */

public class Client {
  private final Scanner sc = new Scanner(System.in);
  private static boolean finished = false;
  private final ItemRegister itemRegister = new ItemRegister();
  private static final int ADD_ITEM = 1;
  private static final int PRINT_ITEM = 2;
  private static final int FIND_ITEM = 3;
  private static final int FIND_CATEGORY = 4;
  private static final int EDIT_STOCK = 5;
  private static final int CHANGE_ITEM = 6;
  private static final int SORT_ITEMS = 7;
  private static final int ITEMS_BETWEEN_PRICES = 8;
  private static final int NUMBER_OF_ITEMS = 9;
  private static final int DELETE_ITEM = 10;
  private static final int ADD_DEFAULT_TESTDATA = 11;
  private static final int EXIT = 12;

  /** The main start of the application.
   *
   * @param args Commandline arguments as an array of String
   */
  public static void main(String[] args) {
    Client client = new Client();

    while (!finished) {
      try {
        client.showMenu();
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
      }
    }
  }

  /**
   * Displays the menu to the user, and waits for an input. The user is expected to type in
   * an integer between 1 and 10. If the input is invalid an error is returned
   */
  private void showMenu() {
    System.out.println();
    System.out.println("""
              ---------------MENU------------------
               Choose:
               1. Register a new item
               2. Print all items
               3. Find an item
               4. Find all items in a category
               5. Edit number of items
               6. Make changes to an item
               7. Sort items
               8. Find all items in a price-range
               9. Find number of items in storage
               10. Delete an item
               11. Add default test data
               12. Exit
              -------------------------------------""");
    int choice = Integer.parseInt(sc.nextLine());

    switch (choice) {
      case ADD_ITEM -> registerNewItem();
      case PRINT_ITEM -> printAllItems();
      case FIND_ITEM -> findAnItem();
      case FIND_CATEGORY -> findAllItemsInCategory();
      case EDIT_STOCK -> editStockItem();
      case CHANGE_ITEM -> makeChangesToAnItem();
      case SORT_ITEMS -> sortItems();
      case ITEMS_BETWEEN_PRICES -> findItemsBetweenPrices();
      case NUMBER_OF_ITEMS -> getTotalNumberOfItems();
      case DELETE_ITEM -> deleteAnItem();
      case ADD_DEFAULT_TESTDATA -> addDefaultTestData();
      case EXIT -> {
        System.out.println("Thank you for using the items app!");
        finished = true;
      }
      default -> System.out.println("Please choose a number between 1 and 12");
    }
  }

  /**
   *  Method to register a new item based on user-inputs, this method also checks for
   *  duplicate item number.
   */
  private void registerNewItem() {
    System.out.println("Register a new item, please make sure every field is filled in: ");
    System.out.println("Item number:");
    String itemNumber = sc.nextLine().trim();
    System.out.println("Brand name:");
    String brandName = sc.nextLine();
    System.out.println("""
        Choose Category:
        1. Laminate flooring
        2. Windows
        3. Doors
        4. Lumber""");
    int categoryNumber = Integer.parseInt(sc.nextLine());
    System.out.println("Price (kr):");
    double price = Double.parseDouble(sc.nextLine());
    System.out.println("Description:");
    String description = sc.nextLine();
    System.out.println("Color:");
    String color = sc.nextLine();
    System.out.println("Weight (kg):");
    double weight = Double.parseDouble(sc.nextLine());
    System.out.println("Length (m):");
    double length = Double.parseDouble(sc.nextLine());
    System.out.println("Height (m):");
    double height = Double.parseDouble(sc.nextLine());
    System.out.println("Number of items:");
    int numberOfItems = Integer.parseInt(sc.nextLine());

    itemRegister.newItem(itemNumber, description, price, brandName, weight, length, height,
                color, numberOfItems, categoryNumber);
    try {
      System.out.println("Your item was successfully added: ");
      System.out.println(itemRegister.getItemWithItemNumber(itemNumber));
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * Method to print all items.
   */
  private void printAllItems() {
    System.out.println("All items: ");
    try {
      System.out.println(itemRegister.printAllItems());
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * Method to find an item by item number, description or both.
   */
  private void findAnItem() {
    System.out.println("Type in item number and/or description to find an item, "
        + "press enter to leave one blank.");
    System.out.println("Item number: ");
    String findItemNumber = sc.nextLine().trim();
    System.out.println("Description: ");
    String findItemDescription = sc.nextLine().toLowerCase().trim();

    if (findItemDescription.isBlank() && findItemNumber.isBlank()) {
      System.out.println("You can't leave both blank.");
    } else if (findItemDescription.isBlank()) {
      try {
        System.out.println("Item with item number: " + findItemNumber);
        System.out.println(itemRegister.getItemWithItemNumber(findItemNumber));
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
      }
    } else if (findItemNumber.isBlank()) {
      try {
        System.out.println("Items with '" + findItemDescription + "' in the description:");
        itemRegister.printItemsSmallString(
            itemRegister.getItemWithDescription(findItemDescription));
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
      }
    } else {
      try {
        System.out.println("Item with item number: " + findItemNumber
            + ", and '" + findItemDescription + "' in the description:");
        System.out.println(itemRegister.getItemWithItemNumberAndDescription(
            findItemNumber, findItemDescription));
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
      }
    }
  }

  /**Method to find all items in a category.
   *
   */
  public void findAllItemsInCategory() {
    System.out.println("""
                 Choose category:
                 1. Laminate flooring
                 2. Windows
                 3. Doors
                 4. Lumber""");
    int choice = Integer.parseInt(sc.nextLine());

    System.out.println("All items in the category " + Category.getCategory(choice) + ": ");
    itemRegister.printItemsSmallString(itemRegister.getItemsByCategory(choice));
  }

  /**
   * Method to edit the amount of one item, the user is expected to choose an
   * item number to edit the amount of items for that item.
   * The user can choose to either increase or decrease the amount of items.
   * If the user don't press 1 or 2, or if th user inputs a negative number/value,
   * an error will appear.
   */
  private void editStockItem() {
    System.out.println("Choose an item number, to edit number of items in storage: ");
    String itemNumber = sc.nextLine().trim();
    if (itemRegister.testForDuplicateItemNumber(itemNumber)) {
      System.out.println("Type 1 to increase number of items or type 2 to "
          + "decrease number of items: ");
      int choice = Integer.parseInt(sc.nextLine());

      if (choice == 1) {
        System.out.println("Choose a number of items to increase by: ");
        int addNumberOfItems = Integer.parseInt(sc.nextLine());

        if (addNumberOfItems > 0) {
          itemRegister.increaseAmountOfOneItem(itemNumber, addNumberOfItems);
          System.out.println(itemRegister.getItemWithItemNumber(itemNumber));
        } else {
          System.out.println("Your input-number can't be a negative number.");
        }
      } else if (choice == 2) {
        System.out.println("Choose a number of items to decrease by: ");
        int subNumberOfItems = Integer.parseInt(sc.nextLine());

        if (subNumberOfItems > 0) {
          itemRegister.decreaseAmountOfOneItem(itemNumber, subNumberOfItems);
          System.out.println(itemRegister.getItemWithItemNumber(itemNumber));
        } else {
          System.out.println("Your input-number can't be a negative number.");
        }
      } else {
        System.out.println("Please type 1 or 2 ");
      }
    } else {
      System.out.println("This item number doesn't exist.");
    }
  }

  /**
   * Method to make changes to an item, the price and description can be edited, the
   *  user can also add a discount to the price.
   */
  private void makeChangesToAnItem() {
    boolean finished = false;

    System.out.println("Type an item number to make changes: ");
    String itemNumber = sc.nextLine().trim();

    if (itemRegister.testForDuplicateItemNumber(itemNumber)) {
      System.out.println("You are making changes to: ");
      System.out.println(itemRegister.getItemWithItemNumber(itemNumber));

      while (!finished) {
        System.out.println("""
               
                 Choose:
                 1. Change price
                 2. Add a discount
                 3. Change an item-description
                 4. Print changes and exit""");
        int choice = Integer.parseInt(sc.nextLine());

        if (choice == 1) {
          System.out.println("Type in the new price: ");
          double newPrice = Double.parseDouble(sc.nextLine());
          if (newPrice >= 0) {
            itemRegister.changePriceItem(itemNumber, newPrice);
          } else {
            System.out.println("The new price can't be a negative number. ");
          }
        } else if (choice == 2) {
          System.out.println("Type in a discount between 0% and 100%: ");
          double discount = Double.parseDouble(sc.nextLine());
          if (discount >= 0 && discount <= 100) {
            itemRegister.giveDiscountItem(itemNumber, discount);
          } else {
            System.out.println("The discount must be between 0% and 100%.");
          }
        } else if (choice == 3) {
          System.out.println("Type in the new description: ");
          String newDescription = sc.nextLine();
          itemRegister.changeDescriptionItem(itemNumber, newDescription);
        } else if (choice == 4) {
          System.out.println("Your edited item: ");
          System.out.println(itemRegister.getItemWithItemNumber(itemNumber));
          finished = true;
        } else {
          System.out.println("You have to choose a number between 1 and 4. Please try again. ");
        }
      }
    } else {
      System.out.println("This item number doesn't exists. ");
    }
  }

  /**
   *  Method to sort items based on different criteria, they can be sorted by price,
   *  purchase price, color or brand name.
   */
  public void sortItems() {
    System.out.println("""
              Type:
              1. Sort items by price
              2. Sort items by color
              3. Sort items by brand name""");
    int choice = Integer.parseInt(sc.nextLine());
    System.out.println();

    if (choice == 1) {
      System.out.println("Items sorted by price: ");
      itemRegister.printItemsSmallString(itemRegister.sortItemsByPrice());
    } else if (choice == 2) {
      System.out.println("Items sorted by color: ");
      itemRegister.printItemsSmallString(itemRegister.sortItemsByColor());
    } else if (choice == 3) {
      System.out.println("Items sorted by brand name: ");
      itemRegister.printItemsSmallString(itemRegister.sortItemsByBrandName());
    } else {
      System.out.println("You have to type a number between 1 and 3. ");
    }
  }

  /**
   * Method to find all items in a price-range based on user-input.
   */
  public void findItemsBetweenPrices() {
    System.out.println("Choose a price-range to find all items within that range: ");
    System.out.println("Start price: ");
    double startPrice = Integer.parseInt(sc.nextLine());
    System.out.println("End price: ");
    double endPrice = Integer.parseInt(sc.nextLine());

    if (startPrice > endPrice) {
      System.out.println("The start price must be bigger than the end price.");
    } else {
      System.out.println("Items between " + startPrice + " kr and " + endPrice + " kr: ");
      try {
        itemRegister.printItemsSmallString(
            itemRegister.getAllItemsBetweenPrice(startPrice, endPrice));
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
      }
    }
  }

  /**
   * Method to find the number of individual items in storage.
   */
  private void getTotalNumberOfItems() {
    System.out.println("There are " + itemRegister.getNumberOfIndividualItems()
        + " individual items in storage, " + "divided by "
        + itemRegister.getNumberOfItems() + " items.");
  }

  /**
   * Method to add default testdata, this makes it easier to test if the methods are running.
   */
  private void addDefaultTestData() {
    itemRegister.addDefaultItems();
    System.out.println("Default testdata successfully added.");
  }

  /**
   * Method to delete an item.
   */
  private void deleteAnItem() {
    System.out.println("Type in the item number of the item you want to delete: ");
    String itemNumber = sc.nextLine().trim();

    if (itemRegister.testForDuplicateItemNumber(itemNumber)) {
      System.out.println("Are you sure you want to delete: ");
      System.out.println(itemRegister.getItemWithItemNumber(itemNumber));
      System.out.println();
      System.out.println("Type 1 to delete, or press any key to exit: ");
      String choice = sc.nextLine();

      if (Objects.equals(choice, "1")) {
        itemRegister.deleteItem(itemNumber);
        System.out.println("The item is deleted.");
      }
    } else {
      System.out.println("This item doesn't exist.");
    }
  }
}