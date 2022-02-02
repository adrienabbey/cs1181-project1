// Adrien Abbey, CS-1181L-07, Jan. 31, 2022
// Item Class for Project 1: Genetic Algorithm

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

        this.name = name;
        this.weight = weight;
        this.value = value;
    }

    // Copy Constructor:
    public Item(Item other) {
        // Initializes this item's fields to be the same as the other item's
        this.name = other.name;
        this.weight = other.weight;
        this.value = other.value;
    }

    // Getters:
    public double getWeight() {
        // Getter for the item's weight:
        return weight;
    }

    public int getValue() {
        // Getter for the item's value:
        return value;
    }

    public boolean isIncluded() {
        // Getter for the item's inclusion status:
        return included;
    }

    // Setter:
    public void setIncluded(boolean included) {
        // Setter for the item's included field:
        this.included = included;
    }

    // Override: toString method:
    public String toString() {
        // Displays the item in the form <name> (<weight> lbs, $<value>)
        return (name + "(" + weight + " lbs, $" + value +")");
    }
}
