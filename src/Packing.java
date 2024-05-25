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
// Persons: I was struggling with one of the recursive methods as it was resulting in Stack Overflow
// error so I asked my friend "Nischal Bista" who clarified the problem to me and I
// found and fixed the error myself.
// Online Sources: none
//
///////////////////////////////////////////////////////////////////////////////
import java.util.ArrayList;

/**
 * Class used for packing a 2D suitcase with items using various strategies.
 */
public class Packing {
  /**
   * Tries to pack each item in the items list in-order. If an item can fit, it must be packed.
   * Otherwise, it should be skipped. Must be a recursive method.
   *
   * @param suitcase current Suitcase object
   * @return a Suitcase representing the outcome of a strategy in which the items were attempted to
   *         be packed in-order.
   */
  public static Suitcase rushedPacking(Suitcase suitcase) {
    return rushedPackingHelper(suitcase, suitcase.getUnpackedItems());
  }


  private static Suitcase rushedPackingHelper(Suitcase suitcase, ArrayList<Item> items) {
    // Base case: No more items to pack or none of the remaining items can fit
    if (items.isEmpty() || findLargestFitItem(suitcase, items) == null) {
      return suitcase;
    }

    // Attempt to pack the first item in the list
    Item currentItem = items.get(0);
    if (suitcase.canPackItem(currentItem)) {
      // If the item can be packed, pack it and recursively call with updated suitcase and items
      Suitcase packedSuitcase = suitcase.packItem(currentItem);
      ArrayList<Item> remainingItems = new ArrayList<>(items);
      // Make a copy of items to avoid modifying original list.
      remainingItems.remove(currentItem); // Remove the current item that has been packed
      return rushedPackingHelper(packedSuitcase, remainingItems); // Use updated suitcase and items
    } else {
      // If the item cannot be packed, skip it and recursively call with remaining items
      return rushedPackingHelper(suitcase, new ArrayList<>(items.subList(1, items.size())));
    }
  }



  /**
   * Packs items by greedily packing the largest item which can currently be packed. Must be a
   * recursive method.
   *
   * @param suitcase current Suitcase object
   * @return a Suitcase representing the outcome of a greedy strategy in which at each point the
   *         largest item that can fit is packed.
   */
  public static Suitcase greedyPacking(Suitcase suitcase) {
    // Start packing from the largest item that fits into the suitcase
    return greedyPackingHelper(suitcase, suitcase.getUnpackedItems());
  }

  private static Suitcase greedyPackingHelper(Suitcase suitcase, ArrayList<Item> items) {
    // Check if there are any items that can fit into the suitcase
    if (findLargestFitItem(suitcase, items) == null) {
      return suitcase; // If no items can fit, return the current suitcase
    }

    // Find the largest item that can fit into the suitcase
    Item largestFitItem = findLargestFitItem(suitcase, items);
    // If a fit is found, pack it and recursively call with updated suitcase and remaining items
    Suitcase packedSuitcase = suitcase.packItem(largestFitItem);
    ArrayList<Item> remainingItems = new ArrayList<>(items); 
    // Create a copy of items to avoid modifying the original list.
    remainingItems.remove(largestFitItem); // Remove the packed item from the list
    return greedyPackingHelper(packedSuitcase, remainingItems); 
    // Return updated suitcase and remaining items.
  }



  /**
   * Finds the largest item from the list of items that can fit into the suitcase.
   *
   * @param suitcase current Suitcase object
   * @param items    list of items to be considered
   * @return the largest item that can fit into the suitcase, null if none can fit
   */
  private static Item findLargestFitItem(Suitcase suitcase, ArrayList<Item> items) {
    Item largestFitItem = null;
    int largestArea = 0;

    // Loop through the items and find the largest item that can fit
    for (Item item : items) {
      if (suitcase.canPackItem(item)) {
        int area = item.width * item.height;
        if (area > largestArea) {
          largestArea = area;
          largestFitItem = item;
        }
      }
    }

    return largestFitItem;
  }



  /**
   * Finds the optimal packing of items by trying all packing orders. Must be a recursive method.
   *
   * @param suitcase current Suitcase
   * @return a Suitcase representing the optimal outcome.
   */
  public static Suitcase optimalPacking(Suitcase suitcase) {
    ArrayList<Item> remainingItems = suitcase.getUnpackedItems();
    ArrayList<Item> packedItems = new ArrayList<>();
    return optimalPackingHelper(suitcase, remainingItems, packedItems);
  }

  /**
   * Helper method for optimalPacking. Recursively tries all permutations of packing orders to find
   * the optimal packing solution.
   *
   * @param suitcase    current Suitcase object
   * @param items       list of items to be packed
   * @param packedItems list of items already packed
   * @return a Suitcase representing the optimal packing solution
   */
  private static Suitcase optimalPackingHelper(Suitcase suitcase, ArrayList<Item> items,
      ArrayList<Item> packedItems) {
    // Base case: No more items to pack
    if (items.isEmpty()) {
      return suitcase;
    }

    Suitcase bestSuitcase = suitcase; // Initialize with current state
    int maxAreaPacked = suitcase.areaPacked(); // Initialize with current area packed

    // Try all possible combinations of packing orders
    for (Item item : items) {
      // Check if the item can be packed into the current state of the suitcase
      if (suitcase.canPackItem(item)) {
        // Try packing the current item
        Suitcase updatedSuitcase = suitcase.packItem(item);
        ArrayList<Item> remainingItems = new ArrayList<>(items);
        remainingItems.remove(item); // Exclude the chosen item from remaining items

        // Recursively call optimalPackingHelper with updated suitcase and remaining items
        Suitcase result = optimalPackingHelper(updatedSuitcase, remainingItems, packedItems);

        // Update bestSuitcase if the result has more area packed
        if (result.areaPacked() > maxAreaPacked) {
          bestSuitcase = result;
          maxAreaPacked = result.areaPacked();
        }
      }
    }

    return bestSuitcase;
  }



}
