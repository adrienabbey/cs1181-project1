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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class GeneticAlgorithm {

    // Variables:
    // Placing the variables here makes it easier to find and change them as
    // desired.
    private static String filename = "items.txt";
    private static int populationSize = 10;

    public static ArrayList<Item> readData(String filename) throws FileNotFoundException {
        // Reads in a data file with the format shown below and creates and returns an
        // ArrayList of Item objects.

        // File format:
        // item1_label, item1_weight, item1_value
        // item2_label, item2_weight, item2_value

        // Create an ArrayList of Items:
        ArrayList<Item> itemArray = new ArrayList<Item>();

        // Create a File object that points to the filename:
        File file = new File(filename);

        // Create a Scanner object to read from the file:
        Scanner fileScanner = new Scanner(file);

        // While there are lines to be read from the file:
        while (fileScanner.hasNextLine()) {
            // Grab the next line from the file:
            String nextLine = fileScanner.nextLine();

            // Note: I'm assuming the file is properly formatted.

            // Split the string into a String Array, using commas as the seperator:
            String[] splitString = nextLine.split(",");

            // There's a possibility that the split String is not providing a valid input,
            // such as the blank line at the end of the file. Don't try to add a blank line
            // as an item.

            // If the split String Array length is 3 like it should be:
            if (splitString.length == 3) {
                // Add the new item to the ArrayList:
                itemArray.add(new Item(splitString[0].trim(), Double.parseDouble(splitString[1].trim()),
                        Integer.parseInt(splitString[2].trim())));
            }
        }

        // Close the file Scanner:
        fileScanner.close();

        // Return the new ArrayList:
        return itemArray;
    }

    public static ArrayList<Chromosome> initialPopulation(ArrayList<Item> items, int populationSize) {
        // Creates and returns an ArrayList of 'populationSize Chromosome' objects that
        // each contain the 'items', with their 'included' field randomly set to true or
        // false.

        // Create an ArrayList of Chromosomes:
        ArrayList<Chromosome> populationArray = new ArrayList<Chromosome>();

        // For each population member:
        for (int i = 0; i < populationSize; i++) {
            // Create a new person, err backpack, err chromosome:
            // Note: this method automatically randomize each Item's inclusion.
            populationArray.add(new Chromosome(items));
        }

        // Return the ArrayList:
        return populationArray;
    }

    public static void main(String[] args) throws FileNotFoundException {
        // Reads the data about the items in a file called 'items.txt' and performs the
        // steps described in the "Running the Genetic Algorithm" section above.

        // Load the file's contents into an Item array:
        ArrayList<Item> itemArray = readData(filename);

        // TEST: Print out the itemArray:
        // for (Item item : itemArray) {
        // System.out.println(item);
        // }

        // Step 1:
        // Create the initial population:
        ArrayList<Chromosome> currentPopulation = initialPopulation(itemArray, populationSize);

        // TEST: Print out the new generation:
        for (int i = 0; i < currentPopulation.size(); i++) {
            System.out.println(currentPopulation.get(i).toString());
        }

        // Apply the Genetic Algorithm to these victims 20 times:
        for (int i = 0; i < 20; i++) {
            // Step 2:
            // Create a NEW ArrayList containing the current population:
            ArrayList<Chromosome> nextGeneration = new ArrayList<>(currentPopulation);

            // Step 3:
            // Randomly pair off individuals and perform a crossover to create a child and
            // add it to the next generation as well.

            // Each child has two different genderless parents. I must be careful, as
            // carelessness could lead to individuals procreating with themselves, which we
            // probably do not want.

            // Let's assume that each individual in the currentPopulation procreates no
            // more than once each generation, because otherwise this will get real messy.

            // Shuffle the population list, because random partners are desirable somehow:
            Collections.shuffle(currentPopulation);

            // Begin random, mandated procreation:
            for (int j = 0; j < currentPopulation.size() / 2; j++) {
                // Since the population has been shuffled, we'll just go down the list in
                // pairs. Add new children to the nextGeneration:
                nextGeneration.add(currentPopulation.get(j * 2).crossover(currentPopulation.get(j * 2 + 1)));
            }

            // Step 4: Randomly choose ten percent of the individuals in the next generation
            // and expose them to mutation.

            // For every individual in the nextGeneration:
            for (Chromosome individual : nextGeneration) {
                // Note: the mutate method already applies mutations only 10% of the time, so we
                // can pass the whole list:
                individual.mutate();
            }

            // Step 5: Sort the individuals in the next generation according to their
            // fitness.

            // Note: Since the Chromosome class already overrides the compareTo method to
            // sort by fitness, we can simply use collection sorting:
            Collections.sort(nextGeneration);

            // TEST: Print out the sorted list:
            // for (Chromosome c : nextGeneration) {
            // System.out.println(c);
            // }

            // Step 6: Clear out the current population and add the top ten of the next
            // generation back into the population:

            // Genocide is easy:
            currentPopulation.clear();

            // Add the top ten of the next generation back into the current population:
            for (int j = 0; j < 10; j++) {
                currentPopulation.add(nextGeneration.get(j));
            }

            // Step 7: Repeat steps 2 through 6 twenty times. We're monsters like that.
        }

        // Step 8: Sort the population and display the fittest individual to the
        // console:
        Collections.sort(currentPopulation);
        // Since the Chromosome class has a toString override method, this is easy.
        System.out.println(currentPopulation.get(0));
    }
}