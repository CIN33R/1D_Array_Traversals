import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayTraversalsTest {

    // ============================================================
    // Reflection helpers
    // ============================================================

    private static Class<?> requireClass(String name) {
        try {
            return Class.forName(name);
        } catch (ClassNotFoundException e) {
            fail("Missing class: " + name + ". Make sure " + name + ".java exists in src/main/java.");
            return null;
        }
    }

    private static Method requireMethod(Class<?> clazz, String name, Class<?> returnType, Class<?>... params) {
        try {
            Method m = clazz.getDeclaredMethod(name, params);
            assertTrue(Modifier.isPublic(m.getModifiers()),
                    clazz.getSimpleName() + "." + name + "() must be public.");
            assertEquals(returnType, m.getReturnType(),
                    clazz.getSimpleName() + "." + name + "() has the wrong return type.");
            return m;
        } catch (NoSuchMethodException e) {
            fail(clazz.getSimpleName() + " is missing method: public "
                    + returnType.getSimpleName() + " " + name + "(" + paramList(params) + ")");
            return null;
        }
    }

    private static Object newInstance(Class<?> clazz) {
        try {
            Constructor<?> c = clazz.getDeclaredConstructor();
            return c.newInstance();
        } catch (Exception e) {
            fail("Could not instantiate " + clazz.getSimpleName() + ": " + e.getMessage());
            return null;
        }
    }

    private static Object invoke(Method m, Object target, Object... args) {
        try {
            return m.invoke(target, args);
        } catch (Exception e) {
            Throwable cause = (e.getCause() != null) ? e.getCause() : e;
            fail(m.getName() + "() threw an exception: "
                    + cause.getClass().getSimpleName() + " - " + cause.getMessage());
            return null;
        }
    }

    private static String paramList(Class<?>... params) {
        if (params == null || params.length == 0) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < params.length; i++) {
            if (i > 0) sb.append(", ");
            sb.append(params[i].getSimpleName());
        }
        return sb.toString();
    }

    // ============================================================
    // @AfterAll showcase — prints a summary grid when all tests finish
    // ============================================================

    @AfterAll
    static void displayShowcase() {
        System.out.println();
        System.out.println("==============================================");
        System.out.println("   Array Traversals — Algorithm Summary");
        System.out.println("==============================================");

        int[] demo = {5, 3, 8, 1, 6, 3};
        System.out.println("Demo array: " + Arrays.toString(demo));
        System.out.println();

        // Build display internally using correct answers
        System.out.println("  MinMax");
        System.out.println("    min              → " + buildMin(demo));
        System.out.println("    max              → " + buildMax(demo));
        System.out.println();

        System.out.println("  IndexFinder");
        System.out.println("    indexOfMin       → " + buildIndexOfMin(demo));
        System.out.println("    indexOfMax       → " + buildIndexOfMax(demo));
        System.out.println();

        System.out.println("  SumAverage");
        System.out.println("    sum              → " + buildSum(demo));
        System.out.println("    average (int)    → " + buildAverage(demo));
        System.out.println();

        System.out.println("  Counter  (threshold = 4)");
        System.out.println("    countAbove(4)    → " + buildCountAbove(demo, 4));
        System.out.println("    countBelow(4)    → " + buildCountBelow(demo, 4));
        System.out.println("    countEqual(3)    → " + buildCountEqual(demo, 3));
        System.out.println();

        System.out.println("  PropertyChecker");
        System.out.println("    hasPositive      → " + buildHasPositive(demo));
        System.out.println("    allPositive      → " + buildAllPositive(demo));
        System.out.println();

        System.out.println("  DuplicateDetector");
        System.out.println("    hasDuplicate     → " + buildHasDuplicate(demo));
        System.out.println();

        int[] shiftDemo = {10, 20, 30, 40, 50};
        System.out.println("  Shifter  (on {10,20,30,40,50})");
        System.out.println("    shiftLeft        → " + buildShiftLeft(shiftDemo.clone()));
        System.out.println("    shiftRight       → " + buildShiftRight(shiftDemo.clone()));
        System.out.println();

        System.out.println("  Rotator  (on {10,20,30,40,50})");
        System.out.println("    rotateLeft       → " + buildRotateLeft(shiftDemo.clone()));
        System.out.println("    rotateRight      → " + buildRotateRight(shiftDemo.clone()));
        System.out.println();

        int[] revDemo = {1, 2, 3, 4, 5};
        System.out.println("  Reverser  (on {1,2,3,4,5})");
        System.out.println("    reverse          → " + buildReverse(revDemo.clone()));
        System.out.println();

        System.out.println("==============================================");
    }

    // Internal reference implementations for the showcase display

    private static int buildMin(int[] arr) {
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) if (arr[i] < min) min = arr[i];
        return min;
    }

    private static int buildMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) if (arr[i] > max) max = arr[i];
        return max;
    }

    private static int buildIndexOfMin(int[] arr) {
        int idx = 0;
        for (int i = 1; i < arr.length; i++) if (arr[i] < arr[idx]) idx = i;
        return idx;
    }

    private static int buildIndexOfMax(int[] arr) {
        int idx = 0;
        for (int i = 1; i < arr.length; i++) if (arr[i] > arr[idx]) idx = i;
        return idx;
    }

    private static int buildSum(int[] arr) {
        int s = 0;
        for (int v : arr) s += v;
        return s;
    }

    private static int buildAverage(int[] arr) {
        return buildSum(arr) / arr.length;
    }

    private static int buildCountAbove(int[] arr, int t) {
        int c = 0;
        for (int v : arr) if (v > t) c++;
        return c;
    }

    private static int buildCountBelow(int[] arr, int t) {
        int c = 0;
        for (int v : arr) if (v < t) c++;
        return c;
    }

    private static int buildCountEqual(int[] arr, int t) {
        int c = 0;
        for (int v : arr) if (v == t) c++;
        return c;
    }

    private static boolean buildHasPositive(int[] arr) {
        boolean found = false;
        for (int v : arr) if (v > 0) found = true;
        return found;
    }

    private static boolean buildAllPositive(int[] arr) {
        boolean all = true;
        for (int v : arr) if (v <= 0) all = false;
        return all;
    }

    private static boolean buildHasDuplicate(int[] arr) {
        boolean dup = false;
        for (int i = 0; i < arr.length - 1; i++)
            for (int j = i + 1; j < arr.length; j++)
                if (arr[i] == arr[j]) dup = true;
        return dup;
    }

    private static String buildShiftLeft(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) arr[i] = arr[i + 1];
        arr[arr.length - 1] = 0;
        return Arrays.toString(arr);
    }

    private static String buildShiftRight(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) arr[i] = arr[i - 1];
        arr[0] = 0;
        return Arrays.toString(arr);
    }

    private static String buildRotateLeft(int[] arr) {
        int first = arr[0];
        for (int i = 0; i < arr.length - 1; i++) arr[i] = arr[i + 1];
        arr[arr.length - 1] = first;
        return Arrays.toString(arr);
    }

    private static String buildRotateRight(int[] arr) {
        int last = arr[arr.length - 1];
        for (int i = arr.length - 1; i > 0; i--) arr[i] = arr[i - 1];
        arr[0] = last;
        return Arrays.toString(arr);
    }

    private static String buildReverse(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int tmp = arr[left]; arr[left] = arr[right]; arr[right] = tmp;
            left++; right--;
        }
        return Arrays.toString(arr);
    }

    // ============================================================
    // MinMax tests
    // ============================================================

    @Test
    public void minMax_signatures_exist() {
        System.out.println("\n=== Signature Check: MinMax ===");
        Class<?> c = requireClass("MinMax");
        requireMethod(c, "min", int.class, int[].class);
        requireMethod(c, "max", int.class, int[].class);
    }

    @Test
    public void minMax_min_basicCases() {
        System.out.println("\n=== Behavior: MinMax.min ===");
        Class<?> c = requireClass("MinMax");
        Method m = requireMethod(c, "min", int.class, int[].class);
        Object obj = newInstance(c);

        assertEquals(1, (int) invoke(m, obj, new Object[]{new int[]{5, 3, 8, 1, 6}}),
                "min({5,3,8,1,6}) should return 1.");
        assertEquals(-5, (int) invoke(m, obj, new Object[]{new int[]{-2, -5, -1}}),
                "min({-2,-5,-1}) should return -5.");
        assertEquals(4, (int) invoke(m, obj, new Object[]{new int[]{4, 4, 4}}),
                "min({4,4,4}) should return 4 (all equal).");
        assertEquals(7, (int) invoke(m, obj, new Object[]{new int[]{7}}),
                "min({7}) single-element should return 7.");
    }

    @Test
    public void minMax_max_basicCases() {
        System.out.println("\n=== Behavior: MinMax.max ===");
        Class<?> c = requireClass("MinMax");
        Method m = requireMethod(c, "max", int.class, int[].class);
        Object obj = newInstance(c);

        assertEquals(8, (int) invoke(m, obj, new Object[]{new int[]{5, 3, 8, 1, 6}}),
                "max({5,3,8,1,6}) should return 8.");
        assertEquals(-1, (int) invoke(m, obj, new Object[]{new int[]{-2, -5, -1}}),
                "max({-2,-5,-1}) should return -1.");
        assertEquals(4, (int) invoke(m, obj, new Object[]{new int[]{4, 4, 4}}),
                "max({4,4,4}) should return 4 (all equal).");
        assertEquals(7, (int) invoke(m, obj, new Object[]{new int[]{7}}),
                "max({7}) single-element should return 7.");
    }

    // ============================================================
    // IndexFinder tests
    // ============================================================

    @Test
    public void indexFinder_signatures_exist() {
        System.out.println("\n=== Signature Check: IndexFinder ===");
        Class<?> c = requireClass("IndexFinder");
        requireMethod(c, "indexOfMin", int.class, int[].class);
        requireMethod(c, "indexOfMax", int.class, int[].class);
    }

    @Test
    public void indexFinder_indexOfMin_basicCases() {
        System.out.println("\n=== Behavior: IndexFinder.indexOfMin ===");
        Class<?> c = requireClass("IndexFinder");
        Method m = requireMethod(c, "indexOfMin", int.class, int[].class);
        Object obj = newInstance(c);

        assertEquals(3, (int) invoke(m, obj, new Object[]{new int[]{5, 3, 8, 1, 6}}),
                "indexOfMin({5,3,8,1,6}) should return 3 (value 1 at index 3).");
        assertEquals(0, (int) invoke(m, obj, new Object[]{new int[]{2, 9, 2, 5}}),
                "indexOfMin({2,9,2,5}) should return 0 (first occurrence of minimum 2).");
        assertEquals(0, (int) invoke(m, obj, new Object[]{new int[]{7}}),
                "indexOfMin({7}) single-element should return 0.");
        assertEquals(2, (int) invoke(m, obj, new Object[]{new int[]{10, 5, 1, 8}}),
                "indexOfMin({10,5,1,8}) should return 2.");
    }

    @Test
    public void indexFinder_indexOfMax_basicCases() {
        System.out.println("\n=== Behavior: IndexFinder.indexOfMax ===");
        Class<?> c = requireClass("IndexFinder");
        Method m = requireMethod(c, "indexOfMax", int.class, int[].class);
        Object obj = newInstance(c);

        assertEquals(2, (int) invoke(m, obj, new Object[]{new int[]{5, 3, 8, 1, 6}}),
                "indexOfMax({5,3,8,1,6}) should return 2 (value 8 at index 2).");
        assertEquals(0, (int) invoke(m, obj, new Object[]{new int[]{9, 2, 9, 5}}),
                "indexOfMax({9,2,9,5}) should return 0 (first occurrence of maximum 9).");
        assertEquals(0, (int) invoke(m, obj, new Object[]{new int[]{7}}),
                "indexOfMax({7}) single-element should return 0.");
        assertEquals(1, (int) invoke(m, obj, new Object[]{new int[]{3, 12, 7, 4}}),
                "indexOfMax({3,12,7,4}) should return 1.");
    }

    // ============================================================
    // SumAverage tests
    // ============================================================

    @Test
    public void sumAverage_signatures_exist() {
        System.out.println("\n=== Signature Check: SumAverage ===");
        Class<?> c = requireClass("SumAverage");
        requireMethod(c, "sum", int.class, int[].class);
        requireMethod(c, "average", int.class, int[].class);
    }

    @Test
    public void sumAverage_sum_basicCases() {
        System.out.println("\n=== Behavior: SumAverage.sum ===");
        Class<?> c = requireClass("SumAverage");
        Method m = requireMethod(c, "sum", int.class, int[].class);
        Object obj = newInstance(c);

        assertEquals(25, (int) invoke(m, obj, new Object[]{new int[]{3, 7, 2, 8, 5}}),
                "sum({3,7,2,8,5}) should return 25.");
        assertEquals(0, (int) invoke(m, obj, new Object[]{new int[]{-4, 10, -6}}),
                "sum({-4,10,-6}) should return 0.");
        assertEquals(9, (int) invoke(m, obj, new Object[]{new int[]{9}}),
                "sum({9}) single-element should return 9.");
        assertEquals(0, (int) invoke(m, obj, new Object[]{new int[]{0, 0, 0}}),
                "sum({0,0,0}) should return 0.");
    }

    @Test
    public void sumAverage_average_basicCases() {
        System.out.println("\n=== Behavior: SumAverage.average ===");
        Class<?> c = requireClass("SumAverage");
        Method m = requireMethod(c, "average", int.class, int[].class);
        Object obj = newInstance(c);

        assertEquals(5, (int) invoke(m, obj, new Object[]{new int[]{3, 7, 2, 8, 5}}),
                "average({3,7,2,8,5}) should return 5 (25/5 = 5).");
        assertEquals(2, (int) invoke(m, obj, new Object[]{new int[]{1, 2, 3, 4}}),
                "average({1,2,3,4}) should return 2 (10/4 = 2, integer division truncates).");
        assertEquals(0, (int) invoke(m, obj, new Object[]{new int[]{-4, 10, -6}}),
                "average({-4,10,-6}) should return 0 (0/3 = 0).");
        assertEquals(9, (int) invoke(m, obj, new Object[]{new int[]{9}}),
                "average({9}) single-element should return 9.");
    }

    // ============================================================
    // Counter tests
    // ============================================================

    @Test
    public void counter_signatures_exist() {
        System.out.println("\n=== Signature Check: Counter ===");
        Class<?> c = requireClass("Counter");
        requireMethod(c, "countAbove", int.class, int[].class, int.class);
        requireMethod(c, "countBelow", int.class, int[].class, int.class);
        requireMethod(c, "countEqual", int.class, int[].class, int.class);
    }

    @Test
    public void counter_countAbove_basicCases() {
        System.out.println("\n=== Behavior: Counter.countAbove ===");
        Class<?> c = requireClass("Counter");
        Method m = requireMethod(c, "countAbove", int.class, int[].class, int.class);
        Object obj = newInstance(c);

        assertEquals(3, (int) invoke(m, obj, new int[]{3, 7, 2, 8, 5}, 4),
                "countAbove({3,7,2,8,5}, 4) should return 3 (7, 8, 5 are > 4).");
        assertEquals(0, (int) invoke(m, obj, new int[]{1, 2, 3}, 5),
                "countAbove({1,2,3}, 5) should return 0 (none > 5).");
        assertEquals(3, (int) invoke(m, obj, new int[]{6, 7, 8}, 5),
                "countAbove({6,7,8}, 5) should return 3 (all > 5).");
        assertEquals(0, (int) invoke(m, obj, new int[]{5, 5, 5}, 5),
                "countAbove({5,5,5}, 5) should return 0 (equal is not strictly above).");
    }

    @Test
    public void counter_countBelow_basicCases() {
        System.out.println("\n=== Behavior: Counter.countBelow ===");
        Class<?> c = requireClass("Counter");
        Method m = requireMethod(c, "countBelow", int.class, int[].class, int.class);
        Object obj = newInstance(c);

        assertEquals(2, (int) invoke(m, obj, new int[]{3, 7, 2, 8, 5}, 4),
                "countBelow({3,7,2,8,5}, 4) should return 2 (3 and 2 are < 4).");
        assertEquals(3, (int) invoke(m, obj, new int[]{1, 2, 3}, 5),
                "countBelow({1,2,3}, 5) should return 3 (all < 5).");
        assertEquals(0, (int) invoke(m, obj, new int[]{6, 7, 8}, 5),
                "countBelow({6,7,8}, 5) should return 0 (none < 5).");
        assertEquals(0, (int) invoke(m, obj, new int[]{5, 5, 5}, 5),
                "countBelow({5,5,5}, 5) should return 0 (equal is not strictly below).");
    }

    @Test
    public void counter_countEqual_basicCases() {
        System.out.println("\n=== Behavior: Counter.countEqual ===");
        Class<?> c = requireClass("Counter");
        Method m = requireMethod(c, "countEqual", int.class, int[].class, int.class);
        Object obj = newInstance(c);

        assertEquals(3, (int) invoke(m, obj, new int[]{4, 2, 4, 7, 4}, 4),
                "countEqual({4,2,4,7,4}, 4) should return 3.");
        assertEquals(0, (int) invoke(m, obj, new int[]{1, 2, 3}, 9),
                "countEqual({1,2,3}, 9) should return 0 (9 not present).");
        assertEquals(1, (int) invoke(m, obj, new int[]{5}, 5),
                "countEqual({5}, 5) single-element match should return 1.");
        assertEquals(0, (int) invoke(m, obj, new int[]{5}, 6),
                "countEqual({5}, 6) single-element no match should return 0.");
    }

    // ============================================================
    // PropertyChecker tests
    // ============================================================

    @Test
    public void propertyChecker_signatures_exist() {
        System.out.println("\n=== Signature Check: PropertyChecker ===");
        Class<?> c = requireClass("PropertyChecker");
        requireMethod(c, "hasPositive", boolean.class, int[].class);
        requireMethod(c, "allPositive", boolean.class, int[].class);
    }

    @Test
    public void propertyChecker_hasPositive_basicCases() {
        System.out.println("\n=== Behavior: PropertyChecker.hasPositive ===");
        Class<?> c = requireClass("PropertyChecker");
        Method m = requireMethod(c, "hasPositive", boolean.class, int[].class);
        Object obj = newInstance(c);

        // assertTrue first — placeholder return false fails immediately
        assertTrue((boolean) invoke(m, obj, new Object[]{new int[]{-3, -1, 4, -2}}),
                "hasPositive({-3,-1,4,-2}) should return true (4 > 0).");
        assertTrue((boolean) invoke(m, obj, new Object[]{new int[]{7, 8, 9}}),
                "hasPositive({7,8,9}) should return true (all > 0).");
        assertTrue((boolean) invoke(m, obj, new Object[]{new int[]{1}}),
                "hasPositive({1}) single positive element should return true.");
        assertFalse((boolean) invoke(m, obj, new Object[]{new int[]{-5, 0, -1}}),
                "hasPositive({-5,0,-1}) should return false (none > 0).");
        assertFalse((boolean) invoke(m, obj, new Object[]{new int[]{0}}),
                "hasPositive({0}) should return false (0 is not > 0).");
    }

    @Test
    public void propertyChecker_allPositive_basicCases() {
        System.out.println("\n=== Behavior: PropertyChecker.allPositive ===");
        Class<?> c = requireClass("PropertyChecker");
        Method m = requireMethod(c, "allPositive", boolean.class, int[].class);
        Object obj = newInstance(c);

        // assertTrue first — placeholder return false fails immediately
        assertTrue((boolean) invoke(m, obj, new Object[]{new int[]{1, 5, 3, 8}}),
                "allPositive({1,5,3,8}) should return true (all > 0).");
        assertTrue((boolean) invoke(m, obj, new Object[]{new int[]{1}}),
                "allPositive({1}) single positive element should return true.");
        assertFalse((boolean) invoke(m, obj, new Object[]{new int[]{1, 5, -3, 8}}),
                "allPositive({1,5,-3,8}) should return false (-3 is not > 0).");
        assertFalse((boolean) invoke(m, obj, new Object[]{new int[]{0, 2, 4}}),
                "allPositive({0,2,4}) should return false (0 is not > 0).");
    }

    // ============================================================
    // DuplicateDetector tests
    // ============================================================

    @Test
    public void duplicateDetector_signature_exists() {
        System.out.println("\n=== Signature Check: DuplicateDetector ===");
        Class<?> c = requireClass("DuplicateDetector");
        requireMethod(c, "hasDuplicate", boolean.class, int[].class);
    }

    @Test
    public void duplicateDetector_hasDuplicate_basicCases() {
        System.out.println("\n=== Behavior: DuplicateDetector.hasDuplicate ===");
        Class<?> c = requireClass("DuplicateDetector");
        Method m = requireMethod(c, "hasDuplicate", boolean.class, int[].class);
        Object obj = newInstance(c);

        // assertTrue first — placeholder return false fails immediately
        assertTrue((boolean) invoke(m, obj, new Object[]{new int[]{3, 7, 2, 7, 5}}),
                "hasDuplicate({3,7,2,7,5}) should return true (7 appears twice).");
        assertTrue((boolean) invoke(m, obj, new Object[]{new int[]{4, 4}}),
                "hasDuplicate({4,4}) two identical elements should return true.");
        assertTrue((boolean) invoke(m, obj, new Object[]{new int[]{1, 2, 3, 1}}),
                "hasDuplicate({1,2,3,1}) should return true (1 at index 0 and 3).");
        assertFalse((boolean) invoke(m, obj, new Object[]{new int[]{1, 2, 3, 4, 5}}),
                "hasDuplicate({1,2,3,4,5}) should return false (all distinct).");
        assertFalse((boolean) invoke(m, obj, new Object[]{new int[]{9}}),
                "hasDuplicate({9}) single-element should return false.");
    }

    // ============================================================
    // Shifter tests
    // ============================================================

    @Test
    public void shifter_signatures_exist() {
        System.out.println("\n=== Signature Check: Shifter ===");
        Class<?> c = requireClass("Shifter");
        requireMethod(c, "shiftLeft", void.class, int[].class);
        requireMethod(c, "shiftRight", void.class, int[].class);
    }

    @Test
    public void shifter_shiftLeft_basicCases() {
        System.out.println("\n=== Behavior: Shifter.shiftLeft ===");
        Class<?> c = requireClass("Shifter");
        Method m = requireMethod(c, "shiftLeft", void.class, int[].class);
        Object obj = newInstance(c);

        int[] arr = {10, 20, 30, 40, 50};
        invoke(m, obj, new Object[]{arr});
        assertArrayEquals(new int[]{20, 30, 40, 50, 0}, arr,
                "shiftLeft({10,20,30,40,50}) should produce {20,30,40,50,0}.");

        int[] arr2 = {1, 2};
        invoke(m, obj, new Object[]{arr2});
        assertArrayEquals(new int[]{2, 0}, arr2,
                "shiftLeft({1,2}) should produce {2,0}.");

        int[] arr3 = {5, 9, 3};
        invoke(m, obj, new Object[]{arr3});
        assertArrayEquals(new int[]{9, 3, 0}, arr3,
                "shiftLeft({5,9,3}) should produce {9,3,0}.");
    }

    @Test
    public void shifter_shiftRight_basicCases() {
        System.out.println("\n=== Behavior: Shifter.shiftRight ===");
        Class<?> c = requireClass("Shifter");
        Method m = requireMethod(c, "shiftRight", void.class, int[].class);
        Object obj = newInstance(c);

        int[] arr = {10, 20, 30, 40, 50};
        invoke(m, obj, new Object[]{arr});
        assertArrayEquals(new int[]{0, 10, 20, 30, 40}, arr,
                "shiftRight({10,20,30,40,50}) should produce {0,10,20,30,40}.");

        int[] arr2 = {1, 2};
        invoke(m, obj, new Object[]{arr2});
        assertArrayEquals(new int[]{0, 1}, arr2,
                "shiftRight({1,2}) should produce {0,1}.");

        int[] arr3 = {5, 9, 3};
        invoke(m, obj, new Object[]{arr3});
        assertArrayEquals(new int[]{0, 5, 9}, arr3,
                "shiftRight({5,9,3}) should produce {0,5,9}.");
    }

    // ============================================================
    // Rotator tests
    // ============================================================

    @Test
    public void rotator_signatures_exist() {
        System.out.println("\n=== Signature Check: Rotator ===");
        Class<?> c = requireClass("Rotator");
        requireMethod(c, "rotateLeft", void.class, int[].class);
        requireMethod(c, "rotateRight", void.class, int[].class);
    }

    @Test
    public void rotator_rotateLeft_basicCases() {
        System.out.println("\n=== Behavior: Rotator.rotateLeft ===");
        Class<?> c = requireClass("Rotator");
        Method m = requireMethod(c, "rotateLeft", void.class, int[].class);
        Object obj = newInstance(c);

        int[] arr = {10, 20, 30, 40, 50};
        invoke(m, obj, new Object[]{arr});
        assertArrayEquals(new int[]{20, 30, 40, 50, 10}, arr,
                "rotateLeft({10,20,30,40,50}) should produce {20,30,40,50,10}.");

        int[] arr2 = {1, 2, 3};
        invoke(m, obj, new Object[]{arr2});
        assertArrayEquals(new int[]{2, 3, 1}, arr2,
                "rotateLeft({1,2,3}) should produce {2,3,1}.");

        int[] arr3 = {4, 7};
        invoke(m, obj, new Object[]{arr3});
        assertArrayEquals(new int[]{7, 4}, arr3,
                "rotateLeft({4,7}) two-element should produce {7,4}.");
    }

    @Test
    public void rotator_rotateRight_basicCases() {
        System.out.println("\n=== Behavior: Rotator.rotateRight ===");
        Class<?> c = requireClass("Rotator");
        Method m = requireMethod(c, "rotateRight", void.class, int[].class);
        Object obj = newInstance(c);

        int[] arr = {10, 20, 30, 40, 50};
        invoke(m, obj, new Object[]{arr});
        assertArrayEquals(new int[]{50, 10, 20, 30, 40}, arr,
                "rotateRight({10,20,30,40,50}) should produce {50,10,20,30,40}.");

        int[] arr2 = {1, 2, 3};
        invoke(m, obj, new Object[]{arr2});
        assertArrayEquals(new int[]{3, 1, 2}, arr2,
                "rotateRight({1,2,3}) should produce {3,1,2}.");

        int[] arr3 = {4, 7};
        invoke(m, obj, new Object[]{arr3});
        assertArrayEquals(new int[]{7, 4}, arr3,
                "rotateRight({4,7}) two-element should produce {7,4}.");
    }

    // ============================================================
    // Reverser tests
    // ============================================================

    @Test
    public void reverser_signature_exists() {
        System.out.println("\n=== Signature Check: Reverser ===");
        Class<?> c = requireClass("Reverser");
        requireMethod(c, "reverse", void.class, int[].class);
    }

    @Test
    public void reverser_reverse_basicCases() {
        System.out.println("\n=== Behavior: Reverser.reverse ===");
        Class<?> c = requireClass("Reverser");
        Method m = requireMethod(c, "reverse", void.class, int[].class);
        Object obj = newInstance(c);

        int[] arr = {1, 2, 3, 4, 5};
        invoke(m, obj, new Object[]{arr});
        assertArrayEquals(new int[]{5, 4, 3, 2, 1}, arr,
                "reverse({1,2,3,4,5}) should produce {5,4,3,2,1}.");

        int[] arr2 = {1, 2, 3, 4};
        invoke(m, obj, new Object[]{arr2});
        assertArrayEquals(new int[]{4, 3, 2, 1}, arr2,
                "reverse({1,2,3,4}) even-length should produce {4,3,2,1}.");

        int[] arr3 = {9, 1};
        invoke(m, obj, new Object[]{arr3});
        assertArrayEquals(new int[]{1, 9}, arr3,
                "reverse({9,1}) two-element should produce {1,9}.");

        int[] arr4 = {3, 6, 9};
        invoke(m, obj, new Object[]{arr4});
        assertArrayEquals(new int[]{9, 6, 3}, arr4,
                "reverse({3,6,9}) three-element should produce {9,6,3}.");
    }
}