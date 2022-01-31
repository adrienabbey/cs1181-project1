public class Item {

    // Variables:
    private final String name; // label for the item, such as "Jewelry" or "Kindle"
    private final double weight; // weight of the item in pounds
    private final int value; // value of the item rounded to the nearest dollar
    private boolean included; // indicates whether the item should be taken or not

    // Constructor:
    public Item(String name, double weight, int value) {
        // Initializes the Item's fields to the values that are passed in.
        // The 'included' field is initialized to false.
    }

    // Copy Constructor:
    public Item(Item other) {
        // Initializes this item's fields to be the same as the other item's
    }

    // Getters:
    public double getWeight() {
        // Getter for the item's weight:
    }

    public int getValue() {
        // Getter for the item's value:
    }

    public boolean isIncluded() {
        // Getter for the item's inclusion status:
    }

    // Setter:
    public void setIncluded(boolean included) {
        // Setter for the item's included field:
    }

    // Override: toString method:
    public String toString() {
        // Displays the item in the form <name> (<weight> lbs, $<value>)
    }
}
