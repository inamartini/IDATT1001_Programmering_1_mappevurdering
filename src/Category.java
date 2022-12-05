public enum Category {
    LAMINATE_FLOORING(1),
    WINDOWS(2),
    DOORS(3),
    LUMBER(4);

    final int categoryNumber;
    Category (int categoryNumber) {
        if (categoryNumber < 1 || categoryNumber > 4) {
            throw new IllegalArgumentException("You must type a number between 1 and 4.");
        }
        this.categoryNumber = categoryNumber;
    }

    public  int getCategoryNumber() {
        return categoryNumber;
    }

    public static Category getCategoryNumber (int categoryNumber) {
        for (Category n : Category.values()) {
            if (n.getCategoryNumber() == categoryNumber) {
                return n;
            }
        }
        return null; // evt kaste exception
    }
}