# Array Traversals Practice Lab

## Objective

In this lab you will implement the nine standard array traversal algorithms
covered in AP Computer Science A. Each algorithm lives in its own class so
you can focus on one pattern at a time.

You will practice:

- traversing arrays with indexed `for` loops and `while` loops
- using accumulator variables, tracking variables, and boolean flags
- modifying arrays in place
- choosing the correct loop style for each task

No ArrayLists. No randomness. No user input. No `StringBuilder`.

---

## Files You Will Complete

All files are in `src/main/java/` (default package).

| Class | Methods | Algorithm Group |
|---|---|---|
| `MinMax.java` | `min`, `max` | Minimum / Maximum value |
| `IndexFinder.java` | `indexOfMin`, `indexOfMax` | Index of minimum / maximum |
| `SumAverage.java` | `sum`, `average` | Sum and integer average |
| `Counter.java` | `countAbove`, `countBelow`, `countEqual` | Counting elements |
| `PropertyChecker.java` | `hasPositive`, `allPositive` | Boolean property checks |
| `DuplicateDetector.java` | `hasDuplicate` | Duplicate detection |
| `Shifter.java` | `shiftLeft`, `shiftRight` | Shift (destructive) |
| `Rotator.java` | `rotateLeft`, `rotateRight` | Rotation (wrapping) |
| `Reverser.java` | `reverse` | Reversal |

The test file is `src/test/java/ArrayTraversalsTest.java`. Do not modify it.

---

## General Rules

- Every method receives an `int[]` array.
- You may assume arrays are non-null and have at least one element.
- Methods that return `void` modify the array in place — do not create a new array.
- Do **not** use `return` or `break` inside loops unless a method's Javadoc explicitly allows it. Use boolean flags instead.
- All return types are `int`, `boolean`, or `void`. No `double`. No `String`.

---

## Class Specifications

---

### Class 1: `MinMax`

Finds the minimum and maximum values in an array.

**Traversal strategy for both methods:** initialize a tracking variable to
`arr[0]`, then loop from index `1` to the end, updating whenever a smaller
(or larger) value is found.

```java
public int min(int[] arr)
```
Returns the smallest value in `arr`.

```java
public int max(int[] arr)
```
Returns the largest value in `arr`.

**Examples:**

| Call | Returns |
|---|---|
| `min(new int[]{5, 3, 8, 1, 6})` | `1` |
| `min(new int[]{-2, -5, -1})` | `-5` |
| `max(new int[]{5, 3, 8, 1, 6})` | `8` |
| `max(new int[]{-2, -5, -1})` | `-1` |

---

### Class 2: `IndexFinder`

Returns the **index** (not the value) of the minimum or maximum element.
When there are ties, return the index of the **first** occurrence.

**Traversal strategy for both methods:** track the index of the current
best element (not the value). Update the tracked index whenever a
**strictly** smaller or larger value is found.

```java
public int indexOfMin(int[] arr)
```
Returns the index of the smallest value. Ties go to the first occurrence.

```java
public int indexOfMax(int[] arr)
```
Returns the index of the largest value. Ties go to the first occurrence.

**Examples:**

| Call | Returns | Reason |
|---|---|---|
| `indexOfMin(new int[]{5, 3, 8, 1, 6})` | `3` | value `1` is at index 3 |
| `indexOfMin(new int[]{2, 9, 2, 5})` | `0` | first `2` is at index 0 |
| `indexOfMax(new int[]{5, 3, 8, 1, 6})` | `2` | value `8` is at index 2 |
| `indexOfMax(new int[]{9, 2, 9, 5})` | `0` | first `9` is at index 0 |

---

### Class 3: `SumAverage`

Computes the sum and integer average of all elements.

```java
public int sum(int[] arr)
```
Returns the sum of all elements. An enhanced `for` loop works well here.

```java
public int average(int[] arr)
```
Returns the integer average using **integer division** — no casting required.
You may call `sum(arr)` or re-traverse; either approach is fine.

**Examples:**

| Call | Returns | Reason |
|---|---|---|
| `sum(new int[]{3, 7, 2, 8, 5})` | `25` | 3+7+2+8+5 |
| `sum(new int[]{-4, 10, -6})` | `0` | cancels out |
| `average(new int[]{3, 7, 2, 8, 5})` | `5` | 25 / 5 |
| `average(new int[]{1, 2, 3, 4})` | `2` | 10 / 4 = 2 (truncated) |

---

### Class 4: `Counter`

Counts elements that satisfy a condition relative to a given value.

```java
public int countAbove(int[] arr, int threshold)
```
Returns the number of elements **strictly greater than** `threshold`.

```java
public int countBelow(int[] arr, int threshold)
```
Returns the number of elements **strictly less than** `threshold`.

```java
public int countEqual(int[] arr, int target)
```
Returns the number of elements **exactly equal to** `target`.

**Examples (arr = `{3, 7, 2, 8, 5}`):**

| Call | Returns | Matching elements |
|---|---|---|
| `countAbove(arr, 4)` | `3` | 7, 8, 5 |
| `countBelow(arr, 4)` | `2` | 3, 2 |
| `countEqual(arr, 7)` | `1` | 7 |

---

### Class 5: `PropertyChecker`

Tests whether a property holds for some or all elements.

```java
public boolean hasPositive(int[] arr)
```
Returns `true` if **at least one** element is greater than `0`.
Initialize the flag to `false`; set it to `true` when a qualifying element is found.

```java
public boolean allPositive(int[] arr)
```
Returns `true` if **every** element is greater than `0`.
Initialize the flag to `true`; set it to `false` when a non-qualifying element is found.

**Note:** `0` is not positive. Both methods must check `> 0`, not `>= 0`.

**Examples:**

| Call | Returns |
|---|---|
| `hasPositive(new int[]{-3, -1, 4, -2})` | `true` |
| `hasPositive(new int[]{-5, 0, -1})` | `false` |
| `allPositive(new int[]{1, 5, 3, 8})` | `true` |
| `allPositive(new int[]{0, 2, 4})` | `false` |

---

### Class 6: `DuplicateDetector`

Checks whether any two elements in the array are equal.

```java
public boolean hasDuplicate(int[] arr)
```
Returns `true` if any two elements are equal; `false` if all are distinct.

**Traversal strategy:** use a **nested loop**. The outer loop picks index `i`
from `0` to `arr.length - 2`. The inner loop picks index `j` from `i + 1`
to `arr.length - 1`. This compares every unique pair exactly once.

```
Outer loop:  for (int i = 0; i < arr.length - 1; i++)
Inner loop:  for (int j = i + 1; j < arr.length; j++)
```

**Examples:**

| Call | Returns |
|---|---|
| `hasDuplicate(new int[]{3, 7, 2, 7, 5})` | `true` |
| `hasDuplicate(new int[]{1, 2, 3, 4, 5})` | `false` |
| `hasDuplicate(new int[]{9})` | `false` |

---

### Class 7: `Shifter`

Shifts all elements one position left or right. The displaced element is
**discarded** and the vacated position is filled with `0`.

```java
public void shiftLeft(int[] arr)
```
Moves every element one position toward index `0`.
Index `0` is discarded. Index `arr.length - 1` becomes `0`.

Loop direction: **left to right** (`i` from `0` to `arr.length - 2`).
Inside the loop: `arr[i] = arr[i + 1]`.

```java
public void shiftRight(int[] arr)
```
Moves every element one position away from index `0`.
Index `arr.length - 1` is discarded. Index `0` becomes `0`.

Loop direction: **right to left** (`i` from `arr.length - 1` down to `1`).
Inside the loop: `arr[i] = arr[i - 1]`.

**Examples (before → after):**

| Method | Before | After |
|---|---|---|
| `shiftLeft` | `{10, 20, 30, 40, 50}` | `{20, 30, 40, 50, 0}` |
| `shiftRight` | `{10, 20, 30, 40, 50}` | `{0, 10, 20, 30, 40}` |

---

### Class 8: `Rotator`

Rotates all elements one position left or right. Unlike a shift, the
displaced element **wraps around** to the opposite end.

```java
public void rotateLeft(int[] arr)
```
Moves every element one position toward index `0`.
The original `arr[0]` wraps to `arr[arr.length - 1]`.

Steps:
1. Save `arr[0]` before the loop.
2. Loop left to right: `arr[i] = arr[i + 1]`.
3. Place the saved value at `arr[arr.length - 1]`.

```java
public void rotateRight(int[] arr)
```
Moves every element one position away from index `0`.
The original `arr[arr.length - 1]` wraps to `arr[0]`.

Steps:
1. Save `arr[arr.length - 1]` before the loop.
2. Loop right to left: `arr[i] = arr[i - 1]`.
3. Place the saved value at `arr[0]`.

**Examples (before → after):**

| Method | Before | After |
|---|---|---|
| `rotateLeft` | `{10, 20, 30, 40, 50}` | `{20, 30, 40, 50, 10}` |
| `rotateRight` | `{10, 20, 30, 40, 50}` | `{50, 10, 20, 30, 40}` |

---

### Class 9: `Reverser`

Reverses the order of all elements in place.

```java
public void reverse(int[] arr)
```
The first element swaps with the last, the second with the second-to-last,
and so on, until the two index variables meet in the middle.

**Use a `while` loop (not a `for` loop) for this algorithm.**

```
int left  = 0;
int right = arr.length - 1;

while (left < right) {
    // swap arr[left] and arr[right] using a temp variable
    // move left and right toward each other
}
```

**Examples (before → after):**

| Before | After |
|---|---|
| `{1, 2, 3, 4, 5}` | `{5, 4, 3, 2, 1}` |
| `{1, 2, 3, 4}` | `{4, 3, 2, 1}` |
| `{42}` | `{42}` |

---

## Tips

**Initializing tracking variables correctly is the most common source of bugs.**
Set accumulators, flags, and index trackers to their starting values *before*
the loop begins — not inside it.

**Shift vs. Rotate:**
- Shift: the displaced element is gone; a `0` fills the vacated spot.
- Rotate: the displaced element wraps to the opposite end.

**`shiftRight` and `rotateRight` must loop in reverse.** Looping left to right
would overwrite a value before it has been moved.

**`hasDuplicate` requires a nested loop.** A single traversal is not enough
to compare every pair of elements.

**Boolean flag initialization:**
- `hasPositive` → start `false`, flip to `true` when the condition is met.
- `allPositive` → start `true`, flip to `false` when the condition fails.

---

## Running the Tests

Compile all source files and run `ArrayTraversalsTest` with JUnit 5.
Each method has its own test. A passing test prints a short label;
a failing test prints a message describing what went wrong.

After all tests finish, a summary grid is printed showing the
expected result of every algorithm applied to a demo array.