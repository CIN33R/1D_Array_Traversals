public class Shifter {

    /**
     * Shifts all elements one position to the LEFT.
     * The element at index 0 is discarded.
     * The last position (index arr.length - 1) is filled with 0.
     * The original array is modified in place; nothing is returned.
     *
     * Traversal strategy: loop from index 0 to arr.length - 2.
     * At each index i, copy arr[i + 1] into arr[i].
     * After the loop, set the last element to 0.
     *
     * Assume the array is non-null and has at least one element.
     *
     * Example (before → after):
     *   {10, 20, 30, 40, 50}  →  {20, 30, 40, 50, 0}
     *   {7}                   →  {0}
     */
    public void shiftLeft(int[] arr) {
        // TODO: loop from i = 0 to arr.length - 2
        //       assign arr[i + 1] to arr[i]

        // TODO: set arr[arr.length - 1] to 0
    }

    /**
     * Shifts all elements one position to the RIGHT.
     * The element at index arr.length - 1 is discarded.
     * The first position (index 0) is filled with 0.
     * The original array is modified in place; nothing is returned.
     *
     * Traversal strategy: loop from index arr.length - 1 DOWN to index 1.
     * At each index i, copy arr[i - 1] into arr[i].
     * After the loop, set the first element to 0.
     *
     * IMPORTANT: the loop must run in reverse to avoid overwriting
     * values before they have been moved.
     *
     * Assume the array is non-null and has at least one element.
     *
     * Example (before → after):
     *   {10, 20, 30, 40, 50}  →  {0, 10, 20, 30, 40}
     *   {7}                   →  {0}
     */
    public void shiftRight(int[] arr) {
        // TODO: loop from i = arr.length - 1 DOWN to i = 1
        //       assign arr[i - 1] to arr[i]

        // TODO: set arr[0] to 0
    }
}