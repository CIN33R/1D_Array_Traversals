public class Reverser {

    /**
     * Reverses the order of elements in the array in place.
     * The original array is modified; nothing is returned.
     *
     * Traversal strategy: use TWO index variables.
     *   - left  starts at 0 (the first index)
     *   - right starts at arr.length - 1 (the last index)
     *
     * While left < right:
     *   1. Save arr[left] into a temporary variable.
     *   2. Copy arr[right] into arr[left].
     *   3. Copy the temporary variable into arr[right].
     *   4. Increment left and decrement right.
     *
     * Use a while loop, not a for loop, for this algorithm.
     *
     * Assume the array is non-null and has at least one element.
     *
     * Example (before → after):
     *   {1, 2, 3, 4, 5}  →  {5, 4, 3, 2, 1}
     *   {1, 2, 3, 4}     →  {4, 3, 2, 1}
     *   {42}             →  {42}
     */
    public void reverse(int[] arr) {
        // TODO: declare int left = 0 and int right = arr.length - 1

        // TODO: write a while loop that continues as long as left < right
        //   Inside the loop:
        //     - save arr[left] in a temporary variable
        //     - copy arr[right] into arr[left]
        //     - copy the temporary variable into arr[right]
        //     - increment left
        //     - decrement right
    }
}