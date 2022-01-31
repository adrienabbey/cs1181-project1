/*
Adrien Abbey, CS-1181L-07, Jan. 31, 2022
Project 1: Genetic Algorithm
Visual Studio Code, Windows 10, Eclipse Temurin JDK/JRE 11

Running the Genetic Algorithm:
 1. Create a set of ten random individuals to serve as the initial population.
 2. Add each of the individuals in the current population to the next 
    generation.
 3. Randomly pair off the individuals and perform crossover to create a child 
    and add it to the next generation as well.
 4. Randomly choose ten percent of the individuals in the next generation and 
    expose them to mutation.
 5. Sort the individuals in the next generation according to their fitness.
 6. Clear out the current population and add the top ten of the next generation 
    back into the population.
 7. Repeat steps 2 through 6 twenty times.
 8. Sort the population and display the fittest individual to the console.
*/

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class GeneticAlgorithm {

    public static ArrayList<Item> readData(String filename) throws FileNotFoundException {
        // Reads in a data file with the format shown below and creates and returns an
        // ArrayList of Item objects.
        // item1_label, item1_weight, item1_value
        // item2_label, item2_weight, item2_value
    }

    public static ArrayList<Chromosome> initialPopulation(ArrayList<Item> items, int populationSize) {
        // Creates and returns an ArrayList of 'populationSize Chromosome' objects that
        // each contain the 'items', with their 'included' field randomly set to true or
        // false.
    }

    public static void main(String[] args) throws FileNotFoundException {
        // Reads the data about the items in a file called 'items.txt' and performs the
        // steps described in the "Running the Genetic Algorithm" section above.
    }
}