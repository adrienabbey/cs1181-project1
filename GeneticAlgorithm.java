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

Notes:
 - Our class lectures recently went into more detail about try/catch exception 
   handling and its importance, but the PDF defines classes with throws with 
   explicit instructions to conform to that specification exactly.  As such, I 
   won't try to implement try/catch blocks in this project.
 - I really struggled for a bit with copying the ArrayList of items when 
   creating new chromosomes.  After realizing that I was not properly creating 
   a new ArrayList each time, I set about trying to fix that.  Eventually I 
   realized that I not only needed a new ArrayList for each chromosome's 
   inventory, but also needed to create proper deep copies of each item in 
   that list as well.
 - I added a few variables to make it easy to try different options.  
   Specifically, the filename, populationSize, and generations variables at 
   the top of the GeneticAlgorithm class, as well as a maxWeight variable at 
   the top of the Chromosome class.
 - Added some error handling and messages for FileNotFound exceptions.
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class GeneticAlgorithm {

    // Variables:
    // Note: These variables are provided here for easy adjustments. For example,
    // this makes it easy to change to the included more_items.txt list and adjust
    // variables to provide more useful results with that list. maxWeight is used by
    // the Chromosome.getFitness method.
    private static String filename = "items.txt"; // default: items.txt
    private static int populationSize = 10; // default: 10
    private static int generations = 20; // default: 20
    public static double maxWeight = 10.0; // default: 10.0

    public static ArrayList<Item> readData(String filename) throws FileNotFoundException {
        // Reads in a data file with the format shown below and creates and returns an
        // ArrayList of Item objects.

        // Note: The PDF explicitly states I should throw the FileNotFound exception
        // here.

        // File format:
        // item1_label, item1_weight, item1_value
        // item2_label, item2_weight, item2_value

        // Create an ArrayList of Items:
        ArrayList<Item> itemArray = new ArrayList<Item>();

        // Try-catch block to handle FileNotFoundException:
        try {
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
                // such as a blank line at the end of the file.

                // If the resulting String Array length is proper (to avoid errors):
                if (splitString.length == 3) {
                    // Add the new item to the ArrayList:
                    itemArray.add(new Item(splitString[0].trim(), Double.parseDouble(splitString[1].trim()),
                            Integer.parseInt(splitString[2].trim())));
                }
            }

            // Close the file Scanner:
            fileScanner.close();

        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }

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
            // Note: this method automatically randomizes each Item's inclusion.
            populationArray.add(new Chromosome(items));
        }

        // Return the ArrayList:
        return populationArray;
    }

    public static void main(String[] args) throws FileNotFoundException {
        // Reads the data about the items in a file called 'items.txt' and performs the
        // steps described in the "Running the Genetic Algorithm" section above.

        // Note: The PDF explicitly states I should throw FileNotFoundExceptions here.

        // Load the file's contents into an Item array:
        ArrayList<Item> itemArray = readData(filename);

        // If the itemArray is empty, then the file likely wasn't loaded:
        if (itemArray.size() == 0) {
            System.out.println("Please ensure the necessary item file is available before trying again.");
            return;
        }

        // Step 1:
        // Create the initial population:
        ArrayList<Chromosome> currentPopulation = initialPopulation(itemArray, populationSize);

        // Step 7: Repeat steps 2 through 6 twenty times. We're monsters like that.
        // NOTE: I'm using a "generations" variable here to make it easy to adjust:
        for (int i = 0; i < generations; i++) {
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

            // The instructions state that 10% of the population should mutate. I'm reading
            // this as to mean I should randomly select 10% of the population, rather than
            // randomly mutating individuals 10% of thet time.

            // Shuffle the next generation in preparation for mandated mutations:
            Collections.shuffle(nextGeneration);

            // Don't think of mutation as necessarily being a bad thing. Every rare often
            // some beneficial mutation happens.

            // For the first 10% of the shuffled population:
            for (int j = 0; j < nextGeneration.size() / 10; j++) {
                // Apply mutation directly to the chromosome:
                nextGeneration.get(j).mutate();
            }

            // Step 5: Sort the individuals in the next generation according to their
            // fitness.

            // Note: Since the Chromosome class already overrides the compareTo method to
            // sort by fitness, we can simply use collection sorting:
            Collections.sort(nextGeneration);

            // Step 6: Clear out the current population and add the top ten of the next
            // generation back into the population:

            // Genocide is depressingly easy:
            currentPopulation.clear();

            // Add the top ten of the next generation back into the current population:
            // NOTE: Instead of the top 10, I'm going to use the population size instead/
            for (int j = 0; j < populationSize; j++) {
                currentPopulation.add(nextGeneration.get(j));
            }
        }

        // Step 8: Sort the population and display the fittest individual to the
        // console:
        Collections.sort(currentPopulation);
        // Since the Chromosome class has a toString override method, this is easy.
        System.out.println(currentPopulation.get(0));
    }
}