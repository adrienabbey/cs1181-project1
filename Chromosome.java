import java.util.ArrayList;

public class Chromosome extends ArrayList<Item> implements Comparable<Chromosome> {

    // Variables:
    private static Random rng; // used for random number generation

    // Constructors:
    public Chromosome() {
        // This no-arg constructor can be empty
    }

    // Methods:
    public Chromosome(ArrayList<Item> items) {
        // Adds a copy of each of the items passed in to this Chromosome. Uses a random
        // number to decide whether each item's included field is set to true or false.
    }

    public Chromosome crossover(Chromosome other) {
        // Creates and returns a new 'child' chromosome by performing the crossover
        // operation on 'this' chromosome and the 'other' one that is passed in (i.e.
        // for each item, use a random number to decide which parent's item should be
        // copied and added to the child).
    }

    public void mutate() {
        // Performs the mutation operation on this chromosome (i.e. for each item in
        // this chromosome, use a random number to decide whether or not to flip it's
        // included field from true to false or vice verse).
    }

    public int getFitness() {
        // Returns the fitness of this chromosome. If the sum of all the included items'
        // weights are greater than 10, the fitness is zero. Otherwise, the fitness is
        // equal to the sum of all the included items' values.
    }

    // Overrides:
    public int compareTo(Chromosome other) {
        // Returns -1 if 'this' chromosome's fitness is greater than the 'other's
        // fitness, +1 if 'this' chromosome's fitness is less than the 'other' one's,
        // and 0 if their fitness is the same.
    }

    public String toString() {
        // Displays the name, weight, and value of all items in this chromosome whose
        // included value is true, followed by the fitness of this chromosome.
    }
}
