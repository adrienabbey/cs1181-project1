// Adrien Abbey, CS-1181L-07, Jan. 31, 2022
// Chromosome Class for Project 1: Genetic Algorithm

import java.util.ArrayList;
import java.util.Random;

public class Chromosome extends ArrayList<Item> implements Comparable<Chromosome> {

    // Variables:
    private static Random rng; // used for random number generation

    // Constructors:
    public Chromosome() {
        // This no-arg constructor can be empty.
        // Note to self: This class extends ArrayList.
    }

    public Chromosome(ArrayList<Item> items) {
        // Adds a copy of each of the item in the ArrayList passed in to this
        // Chromosome. Uses a random number to decide whether each item's included field
        // is set to true or false.

        // When passed an item list, create a new person, err chromosome with a random
        // inventory in their backpack, err chromosome.

        // Note: I first tried to just use this.add(items). Fortunately, VSC's quickfix
        // recommended I use .addAll instead.
        this.addAll(items);

        // For each item in this chromosome:
        for (int i = 0; i < this.size(); i++) {
            // Randomly set the item's inclusion:
            this.get(i).setIncluded(rng.nextBoolean());
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
        // instead, as mutation is a separate method in this class.

        // Each parent's "chromosome" is a list of items and their inclusion status. The
        // child's "chromosome" will be generated from their parents, with each item's
        // inclusion status selected randomly from one of their parents.

        // Procreation time:
        // TODO: verify this works!  I might already be setting items in the loop below.
        // A new chromosome needs an ArrayList of Items in order to properly procreate:
        Chromosome childChromosome = new Chromosome(this);

        // This form of procreation is genderless and creates a new inventory of items
        // that spontaneously came into existance, because reasons.

        // It's probably safe to assume all chromosomes are the same length, because I
        // wrote this code and I'm incapable of making such mistakes, hopefully. As
        // such, I'm using 'this' chromosome's length, as a means to test this theory,
        // as I'm not infallible.

        // For each item in the new chromosome:
        for (int i = 0; i < this.size(); i++) {
            // Randomly select a parent:
            if (rng.nextBoolean()) {
                // if true, use this chromosome:
                childChromosome.set(i, this.get(i));
            } else {
                // if false, use other chromosome:
                childChromosome.set(i, other.get(i));
            }
        }

        // Return to sender:
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
        for (int i = 0; i < this.size(); i++) {
            // Only affect a change 10% of the time (rng returns 0 to 9, and I feel like
            // only accepting 1):
            if (rng.nextInt(10) == 1) {
                // Mutate the given item:
                if (this.get(i).isIncluded()) {
                    // If included, then exclude it:
                    this.get(i).setIncluded(false);
                } else {
                    // If excluded, then include it:
                    this.get(i).setIncluded(true);
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
        for (Item item : this) {
            // Add the item's worth:
            value += item.getValue();
            // Add the item's weight:
            weight += item.getWeight();
        }

        // If the total weight is greater than 10:
        if (weight > 10) {
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
        for (int i = 0; i < this.size(); i++) {
            // If the given item is included:
            if (this.get(i).isIncluded()) {
                // Append the item's description (using the Item's overriden toString method):
                returnString += this.get(i).toString() + "\n";
            }
        }

        // Also append this chromosome's fitness:
        returnString += ("Fitness: " + this.getFitness() + "\n");

        // Return the finished string:
        return returnString;
    }
}