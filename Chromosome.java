// Adrien Abbey, CS-1181L-07, Jan. 31, 2022
// Chromosome Class for Project 1: Genetic Algorithm

import java.util.ArrayList;
import java.util.Random;

public class Chromosome extends ArrayList<Item> implements Comparable<Chromosome> {

    // Variables:
    private ArrayList<Item> itemList; // each chromosome needs a unique ArrayList of items
    private static Random rng = new Random(); // used for random number generation

    // Constructors:
    public Chromosome() {
        // This no-arg constructor can be empty.
    }

    public Chromosome(ArrayList<Item> items) {
        // Adds a copy of each of the items in the ArrayList passed in to this
        // Chromosome. Uses a random number to decide whether each item's included field
        // is set to true or false.

        // Create a new ArrayList to contain the items:
        itemList = new ArrayList<Item>();

        // Add each item to the new item list as a new, unique object (deep copy):
        for (Item item : items) {
            itemList.add(new Item(item));
        }

        // Randomly set each item's inclusion:
        for (int i = 0; i < itemList.size(); i++) {
            itemList.get(i).setIncluded(rng.nextBoolean());
        }
    }

    // Methods:
    public Chromosome crossover(Chromosome other) {
        // Creates and returns a new 'child' chromosome by performing the crossover
        // operation on 'this' chromosome and the 'other' one that is passed in (i.e.
        // for each item, use a random number to decide which parent's item should be
        // copied and added to the child).

        // Note: While the description of a crossover in the PDF uses a random number
        // between 1 and 10 and bases mutations off that, I'll use a random boolean here
        // instead, as mutation is handled by a separate method in this class.

        // Each parent's "chromosome" is a list of items and their inclusion status. The
        // child's "chromosome" will be generated from their parents, with each item's
        // inclusion status selected randomly from one of their parents.

        // Procreation time:
        // A new chromosome needs an ArrayList of Items in order to properly procreate:
        Chromosome childChromosome = new Chromosome(itemList);

        // This form of procreation is genderless and creates a new inventory of items
        // that spontaneously came into existance, because reasons.

        // For each item in the new chromosome:
        for (int i = 0; i < itemList.size(); i++) {
            // Randomly select a parent:
            if (rng.nextBoolean()) {
                // if true, use this chromosome:
                childChromosome.itemList.get(i).setIncluded(this.itemList.get(i).isIncluded());
            } else {
                // if false, use other chromosome:
                childChromosome.itemList.get(i).setIncluded(other.itemList.get(i).isIncluded());
            }
        }

        // Return to sender. This miracle child pops into existance with a backpack of
        // items, no child-rearing required:
        return childChromosome;
    }

    public void mutate() {
        // Performs the mutation operation on this chromosome (i.e. for each item in
        // this chromosome, use a random number to decide whether or not to flip it's
        // included field from true to false or vice verse).

        // The description of mutations in the PDF happens 10% of the time for each item
        // in the chromosome, so I'll use that.

        // If 10 percent of the population randomly has 10% of their chromosome mutate
        // every generation, then zombies might not be the only problem these people are
        // fleeing from.

        // For each item in the chromosome:
        for (int i = 0; i < itemList.size(); i++) {
            // Only affect a change 10% of the time (rng returns 0 to 9, and I feel like
            // only accepting 1):
            if (rng.nextInt(10) == 1) {
                // Mutate the given item:
                if (itemList.get(i).isIncluded()) {
                    // If included, then exclude it:
                    itemList.get(i).setIncluded(false);
                } else {
                    // If excluded, then include it:
                    itemList.get(i).setIncluded(true);
                }
            }
        }
    }

    public int getFitness() {
        // Returns the fitness of this chromosome. If the sum of all the included items'
        // weights are greater than 10, the fitness is zero. Otherwise, the fitness is
        // equal to the sum of all the included items' values.

        // Variables:
        int value = 0;
        double weight = 0;

        // For each item in this person's backpack, err, chromosome?:
        for (int i = 0; i < itemList.size(); i++) {
            // Add the item's weight and value, but only if it's included:
            if (itemList.get(i).isIncluded()) {
                value += itemList.get(i).getValue();
                weight += itemList.get(i).getWeight();
            }
        }

        // If the total weight is greater than 10:
        // Note: this variable is defined at the top of the GeneticAlogrithm class to
        // make it easy to adjust values as desired.
        if (weight > GeneticAlgorithm.maxWeight) {
            // ...then this chromosome's fitness is zero:
            return 0;
        } else {
            // Otherwise, return the total value of this chromosome:
            return value;
        }
    }

    // Overrides:
    public int compareTo(Chromosome other) {
        // Returns -1 if 'this' chromosome's fitness is greater than the 'other's
        // fitness, +1 if 'this' chromosome's fitness is less than the 'other' one's,
        // and 0 if their fitness is the same.

        // If this chromosome's fitness is greater than the other's fitness...
        if (this.getFitness() > other.getFitness()) {
            // return -1:
            return -1;
        } else if (this.getFitness() < other.getFitness()) {
            // If this chromosome's fitness is less than the other's fitness, return +1:
            return 1;
        } else {
            // Otherwise, they must be equal, so return 0:
            return 0;
        }
    }

    public String toString() {
        // Displays the name, weight, and value of all items in this chromosome whose
        // included value is true, followed by the fitness of this chromosome.

        String returnString = "";

        // For each item in this chromosome:
        for (int i = 0; i < itemList.size(); i++) {
            // If the given item is included:
            if (itemList.get(i).isIncluded()) {
                // Append the item's description (using the Item's overriden toString method):
                returnString += itemList.get(i).toString() + "\n";
            }
        }

        // Also append this chromosome's fitness:
        returnString += ("Fitness: " + getFitness() + "\n");

        // Return the finished string:
        return returnString;
    }
}