public class DuplicateDetector {

    /**
     * Returns true if any two elements in the array are equal to each other,
     * false if all elements are distinct.
     *
     * Traversal strategy: use a NESTED loop. The outer loop picks a candidate
     * at index i. The inner loop compares it against every element at index
     * j, where j starts at i + 1. This avoids comparing an element to itself
     * and avoids checking the same pair twice.
     * Use a boolean flag initialized to false; set it to true when a match
     * is found. Do NOT use return or break inside the loops.
     *
     * Assume the array is non-null and has at least one element.
     *
     * Example:
     *   arr = {3, 7, 2, 7, 5}  →  returns true   (7 appears at index 1 and 3)
     *   arr = {1, 2, 3, 4, 5}  →  returns false  (all distinct)
     *   arr = {9}              →  returns false  (only one element)
     *
     * Outer loop:  for (int i = 0; i < arr.length - 1; i++)
     * Inner loop:  for (int j = i + 1; j < arr.length; j++)
     */
    public boolean hasDuplicate(int[] arr) {
        // TODO: initialize a boolean flag to false

        // TODO: write the outer loop (i from 0 to arr.length - 2)

        // TODO: write the inner loop (j from i + 1 to arr.length - 1)

        // TODO: if arr[i] == arr[j], set the flag to true

        // TODO: return the flag
        return false; // placeholder
    }
}