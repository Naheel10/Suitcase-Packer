//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Packing
// Course: CS 300 Spring 2024
//
// Author: Muhammad Naheel
// Email: naheel@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Abheesh Kumar
// Partner Email: akumar265@wisc.edu
// Partner Lecturer's Name: Hobbes LeGault
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// X Write-up states that pair programming is allowed for this assignment.
// X We have both read and understand the course Pair Programming Policy.
// X We have registered our team prior to the team registration deadline.
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons: The rushedPackingRecursiveTest kept failing for me and I couldn't figure out the issue
// so I asked my friend "Nischal Bista" to take a look and he identified a silly mistake which was
// that I had incorrectly calculated the area for the test so I fixed it and it worked.
// Online Sources: none
//
///////////////////////////////////////////////////////////////////////////////
import java.util.ArrayList;


/**
 * Class used for testing the methods in the Packing class.
 */
public class PackingTester {
  /**
   * Tester method for the Packing.rushedPacking() method base cases. It should test at least the
   * following scenarios: - There are no items left to pack in the suitcase - There are items left
   * to pack, but none of them fit
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean rushedPackingBaseTest() {
    // Case 1: No items left to pack
    Suitcase suitcase1 = new Suitcase(5, 5, new ArrayList<>());
    Suitcase result1 = Packing.rushedPacking(suitcase1);
    if (result1.numItemsPacked() != 0 || result1.areaPacked() != 0) {
      return false;
    }

    // Case 2: Items left to pack, but none of them fit
    ArrayList<Item> items2 = new ArrayList<>();
    items2.add(new Item("Hoodie", 6, 6)); // Too large to fit
    Suitcase suitcase2 = new Suitcase(5, 5, items2);
    Suitcase result2 = Packing.rushedPacking(suitcase2);
    if (result2.numItemsPacked() != 0 || result2.areaPacked() != 0) {
      return false;
    }

    return true;
  }

  /**
   * Tester method for the Packing.rushedPacking() method recursive cases. It should test at least
   * the following scenarios: - All the items remaining can fit in the suitcase - At least one item
   * remaining cannot fit in the suitcase
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean rushedPackingRecursiveTest() {
    // Case 1: All items fit in the suitcase
    ArrayList<Item> items1 = new ArrayList<>();
    items1.add(new Item("Coat", 3, 3));
    items1.add(new Item("Shoes", 2, 2));
    Suitcase suitcase1 = new Suitcase(5, 5, items1);
    Suitcase result1 = Packing.rushedPacking(suitcase1);
    if (result1.numItemsPacked() != 2 || result1.areaPacked() != 13) {
      return false;
    }


    // Case 2: At least one item cannot fit in the suitcase
    ArrayList<Item> items2 = new ArrayList<>();
    items2.add(new Item("Hoodie", 6, 6)); // Does not fit
    items2.add(new Item("Coat", 3, 3)); // Fits
    items2.add(new Item("Shoes", 4, 4)); // Fits
    Suitcase suitcase2 = new Suitcase(5, 5, items2);

    Suitcase result2 = Packing.rushedPacking(suitcase2);
    if (result2.numItemsPacked() != 1 || result2.areaPacked() != 9) {
      return false;
    }



    return true;
  }



  /**
   * Tester method for the Packing.greedyPacking() method base cases. It should test at least the
   * following scenarios: - There are no items left to pack in the suitcase - There are items left
   * to pack, but none of them fit
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean greedyPackingBaseTest() {
    // Case 1: No items left to pack
    Suitcase suitcase1 = new Suitcase(5, 5, new ArrayList<>());
    Suitcase result1 = Packing.greedyPacking(suitcase1);
    if (result1.numItemsPacked() != 0 || result1.areaPacked() != 0) {
      return false;
    }

    // Case 2: Items left to pack, but none of them fit
    ArrayList<Item> items2 = new ArrayList<>();
    items2.add(new Item("Hoodie", 6, 6)); // Too large to fit
    Suitcase suitcase2 = new Suitcase(5, 5, items2);
    Suitcase result2 = Packing.greedyPacking(suitcase2);
    if (result2.numItemsPacked() != 0 || result2.areaPacked() != 0) {
      return false;
    }

    return true;
  }


  /**
   * Tester method for the Packing.greedyPacking() method recursive cases. It should test at least
   * the following scenarios: - At least one item is packed out of order (an item with a higher
   * index is packed before an item with a lower index) - A scenario where the greedy packing method
   * packs more of the suitcase than the rushed packing (you can use the example given in the
   * writeup)
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean greedyPackingRecursiveTest() {
    // Case 1: All items fit in the suitcase
    ArrayList<Item> items1 = new ArrayList<>();
    items1.add(new Item("Jacket", 3, 3));
    items1.add(new Item("Shoes", 2, 2));
    Suitcase suitcase1 = new Suitcase(5, 5, items1);
    Suitcase result1 = Packing.greedyPacking(suitcase1);
    if (result1.numItemsPacked() != 2 || result1.areaPacked() != 13) {
      return false;
    }

    // Case 2: No items fit in the suitcase
    ArrayList<Item> items2 = new ArrayList<>();
    items2.add(new Item("Hoodie", 6, 6)); // Does not fit
    items2.add(new Item("Jeans", 7, 7)); // Does not fit
    Suitcase suitcase2 = new Suitcase(5, 5, items2);
    Suitcase result2 = Packing.greedyPacking(suitcase2);
    if (result2.numItemsPacked() != 0 || result2.areaPacked() != 0) {
      return false;
    }

    return true;
  }



  /**
   * Tester method for the Packing.optimalPacking() method. This tester should test the
   * optimalPacking() method by randomly generating at least TEN (10) different scenarios, and
   * randomly generating at least ONE-HUNDRED (100) different packing solutions for EACH of the
   * scenarios. Each scenario should have at least FIVE (5) random items, and the suitcases should
   * be of size at least 5x5. If any random solution is better than the optimal packing then it is
   * not actually optimal, so the method does not pass the test. You should use the Utilities method
   * to generate random lists of items, and to randomly pack the suitcases.
   * 
   * @return true if all tests pass, false otherwise
   */
  public static boolean optimalPackingRandomTest() {
    final int NUM_SCENARIOS = 10;
    final int NUM_PACKING_TESTS = 100;
    final int MIN_ITEMS = 5;
    final int SUITCASE_SIZE = 5;

    for (int s = 0; s < NUM_SCENARIOS; s++) {
      // Generating random items for the cases
      ArrayList<Item> items = Utilities.randomItems(MIN_ITEMS);

      // Generating a random suitcase
      Suitcase suitcase = new Suitcase(SUITCASE_SIZE, SUITCASE_SIZE, new ArrayList<>(items));

      // Doing optimal packing
      Suitcase optimalPackingResult = Packing.optimalPacking(suitcase);

      // Generating many random packing solutions so they can be compared
      for (int i = 0; i < NUM_PACKING_TESTS; i++) {
        Suitcase randomPackingResult = Utilities.randomlyPack(suitcase);

        // Checking if any random solution is better than the optimal packing
        if (randomPackingResult.areaPacked() > optimalPackingResult.areaPacked()) {
          return false;
        }
      }
    }
    return true;
  }

  public static void main(String[] args) {
    boolean allPass = true;
    String printFormat = "%-29s %s\n";

    boolean rushedBase = rushedPackingBaseTest();
    allPass &= rushedBase;
    System.out.printf(printFormat, "rushedPackingBaseTest():", rushedBase);

    boolean rushedRecur = rushedPackingRecursiveTest();
    allPass &= rushedRecur;
    System.out.printf(printFormat, "rushedPackingRecursiveTest():", rushedRecur);

    boolean greedyBase = greedyPackingBaseTest();
    allPass &= greedyBase;
    System.out.printf(printFormat, "greedyPackingBaseTest():", greedyBase);

    boolean greedyRecur = greedyPackingRecursiveTest();
    allPass &= greedyRecur;
    System.out.printf(printFormat, "greedyPackingRecursiveTest():", greedyRecur);

    boolean optimalRandom = optimalPackingRandomTest();
    allPass &= optimalRandom;
    System.out.printf(printFormat, "optimalPackingRandomTest():", optimalRandom);

    System.out.printf(printFormat, "All tests:", allPass);
  }
}
