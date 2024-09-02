package general;

public class MaxCount {

    public static int maxCount(int N, int[] A, int[] B) {
        int maxCount = 0;
        int[] perm = B.clone();

        // Generate all permutations of B
        do {
            int[] C = new int[N];
            int[] D = new int[N];
            int[] E = new int[N];

            // Compute C, D, and E arrays
            for (int i = 0; i < N; i++) {
                C[i] = A[i] ^ perm[i];
                D[i] = 2 * (A[i] & perm[i]);
                E[i] = C[i] * D[i];
            }

            // Count how many times E[i] == 2 * A[i]
            int count = 0;
            for (int i = 0; i < N; i++) {
                if (E[i] == 2 * A[i]) {
                    count++;
                }
            }

            // Update the maximum count
            maxCount = Math.max(maxCount, count);

        } while (nextPermutation(perm));

        return maxCount;
    }

    // Helper function to generate the next permutation
    public static boolean nextPermutation(int[] array) {
        int n = array.length;
        int i = n - 2;

        // Find the first index from the end where array[i] < array[i + 1]
        while (i >= 0 && array[i] >= array[i + 1]) {
            i--;
        }

        if (i >= 0) {
            int j = n - 1;
            while (array[j] <= array[i]) {
                j--;
            }
            swap(array, i, j);
        } else {
            return false; // no more permutations
        }

        reverse(array, i + 1, n - 1);
        return true;
    }

    // Helper function to swap two elements in an array
    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    // Helper function to reverse a portion of the array
    private static void reverse(int[] array, int start, int end) {
        while (start < end) {
            swap(array, start++, end--);
        }
    }

    public static void main(String[] args) {
        int N = 8;
        int[] A = {6,5,2,3,1,6,4,2};
        int[] B = {10,10,7,6,8,4,2,11

        };
        System.out.println(maxCount(N, A, B));  // Output should be 0
    }
}
