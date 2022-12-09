/** Enum class for category.
 *
 * @author 10119
 * @version 1.1.0
 */
public enum Category {
    LAMINATE_FLOORING(1),
    WINDOWS(2),
    DOORS(3),
    LUMBER(4);

  final int categoryNumber;

  Category(int categoryNumber) {
    this.categoryNumber = categoryNumber;
  }

  public int getCategoryNumber() {

    return categoryNumber;
  }

  /** Method to take a user input as int and return category name.
   *
   * @param categoryNumber categoryNumber from user-input as int
   * @return returns categoryName
   */
  public static Category getCategory(int categoryNumber) {
    for (Category c : Category.values()
    ) {
      if (c.getCategoryNumber() == categoryNumber) {
        return c;
      }
    }
    throw new IllegalArgumentException("This category doesn't exist. You must type a "
        + "number between 1 and 4.");
  }
}