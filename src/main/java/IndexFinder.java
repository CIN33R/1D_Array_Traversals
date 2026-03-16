public class IndexFinder {

    /**
     * Returns the index of the smallest value in the array.
     * If the minimum value appears more than once, return the index
     * of its FIRST occurrence.
     *
     * Traversal strategy: track the index of the current minimum,
     * not the value itself. Initialize to 0, then loop from index 1
     * and update your tracked index whenever a strictly smaller
     * value is found.
     *
     * Assume the array is non-null and has at least one element.
     *
     * Example:
     *   arr = {5, 3, 8, 1, 6}  →  returns 3  (value 1 is at index 3)
     *   arr = {2, 9, 2, 5}     →  returns 0  (first occurrence of 2)
     */
    public int indexOfMin(int[] arr) {
        // TODO: initialize a variable to track the index of the minimum (start at 0)

        // TODO: loop from index 1 to arr.length - 1
        //       if arr[i] is strictly less than arr[your tracked index], update your tracked index

        // TODO: return the tracked index
        return 0; // placeholder
    }

    /**
     * Returns the index of the largest value in the array.
     * If the maximum value appears more than once, return the index
     * of its FIRST occurrence.
     *
     * Traversal strategy: same pattern as indexOfMin(), but update
     * when you find a strictly larger value.
     *
     * Assume the array is non-null and has at least one element.
     *
     * Example:
     *   arr = {5, 3, 8, 1, 6}  →  returns 2  (value 8 is at index 2)
     *   arr = {9, 2, 9, 5}     →  returns 0  (first occurrence of 9)
     */
    public int indexOfMax(int[] arr) {
        // TODO: initialize a variable to track the index of the maximum (start at 0)

        // TODO: loop from index 1 to arr.length - 1
        //       if arr[i] is strictly greater than arr[your tracked index], update your tracked index

        // TODO: return the tracked index
        return 0; // placeholder
    }
}