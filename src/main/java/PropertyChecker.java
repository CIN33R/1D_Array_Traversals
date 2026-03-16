public class PropertyChecker {

    /**
     * Returns true if at least one element in the array is positive (> 0),
     * false otherwise.
     *
     * Traversal strategy: initialize a boolean flag to false before the loop.
     * Set it to true the moment a qualifying element is found.
     * Continue looping through all remaining elements — do NOT use return
     * or break inside the loop.
     *
     * Assume the array is non-null and has at least one element.
     *
     * Example:
     *   arr = {-3, -1, 4, -2}  →  returns true   (4 is positive)
     *   arr = {-5, 0, -1}      →  returns false   (none are > 0)
     *   arr = {7, 8, 9}        →  returns true
     */
    public boolean hasPositive(int[] arr) {
        // TODO: initialize a boolean flag to false

        // TODO: loop through arr
        //       if the element is greater than 0, set the flag to true

        // TODO: return the flag
        return false; // placeholder
    }

    /**
     * Returns true if every element in the array is positive (> 0),
     * false otherwise.
     *
     * Traversal strategy: initialize a boolean flag to true before the loop.
     * Set it to false the moment a non-qualifying element is found.
     * Continue looping through all remaining elements — do NOT use return
     * or break inside the loop.
     *
     * Assume the array is non-null and has at least one element.
     *
     * Example:
     *   arr = {1, 5, 3, 8}   →  returns true    (all are > 0)
     *   arr = {1, 5, -3, 8}  →  returns false   (-3 is not > 0)
     *   arr = {0, 2, 4}      →  returns false   (0 is not > 0)
     */
    public boolean allPositive(int[] arr) {
        // TODO: initialize a boolean flag to true

        // TODO: loop through arr
        //       if the element is NOT greater than 0, set the flag to false

        // TODO: return the flag
        return false; // placeholder
    }
}