public class Counter {

    /**
     * Returns the number of elements strictly greater than the given threshold.
     *
     * Traversal strategy: initialize a counter to 0, then loop through
     * the array and increment the counter each time an element satisfies
     * the condition. An enhanced for loop works well here.
     *
     * Assume the array is non-null and has at least one element.
     *
     * Example:
     *   arr = {3, 7, 2, 8, 5}, threshold = 4  →  returns 3  (7, 8, 5)
     *   arr = {1, 2, 3},       threshold = 5  →  returns 0
     */
    public int countAbove(int[] arr, int threshold) {
        // TODO: initialize a counter to 0

        // TODO: loop through arr
        //       if the element is strictly greater than threshold, increment the counter

        // TODO: return the counter
        return 0; // placeholder
    }

    /**
     * Returns the number of elements strictly less than the given threshold.
     *
     * Traversal strategy: same pattern as countAbove(), but the condition
     * checks for strictly less than.
     *
     * Assume the array is non-null and has at least one element.
     *
     * Example:
     *   arr = {3, 7, 2, 8, 5}, threshold = 4  →  returns 2  (3, 2)
     *   arr = {6, 7, 8},       threshold = 5  →  returns 0
     */
    public int countBelow(int[] arr, int threshold) {
        // TODO: initialize a counter to 0

        // TODO: loop through arr
        //       if the element is strictly less than threshold, increment the counter

        // TODO: return the counter
        return 0; // placeholder
    }

    /**
     * Returns the number of elements exactly equal to the given target.
     *
     * Traversal strategy: same pattern as countAbove(), but the condition
     * checks for equality using ==.
     *
     * Assume the array is non-null and has at least one element.
     *
     * Example:
     *   arr = {4, 2, 4, 7, 4}, target = 4  →  returns 3
     *   arr = {1, 2, 3},       target = 9  →  returns 0
     */
    public int countEqual(int[] arr, int target) {
        // TODO: initialize a counter to 0

        // TODO: loop through arr
        //       if the element equals target, increment the counter

        // TODO: return the counter
        return 0; // placeholder
    }
}