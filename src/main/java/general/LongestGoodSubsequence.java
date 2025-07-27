package general;

import java.util.Scanner;
import java.util.TreeMap;

public class LongestGoodSubsequence {
    // Segment tree implementation to efficiently manage range queries and updates.
    static class SegmentTree {
        int[] tree;
        int size;

        public SegmentTree(int n) {
            size = n;
            tree = new int[2 * n];
        }

        // Function to update an index in the tree.
        public void update(int index, int value) {
            index += size;
            tree[index] = Math.max(tree[index], value);
            while (index > 1) {
                index /= 2;
                tree[index] = Math.max(tree[2 * index], tree[2 * index + 1]);
            }
        }

        // Function to query the maximum value in the range [l, r).
        public int query(int l, int r) {
            int res = 0;
            l += size;
            r += size;
            while (l < r) {
                if ((l & 1) == 1) res = Math.max(res, tree[l++]);
                if ((r & 1) == 1) res = Math.max(res, tree[--r]);
                l /= 2;
                r /= 2;
            }
            return res;
        }
    }

    public static int findLongestGoodSubsequence(int[] A, int N, int K) {
        // To store the dp values
        SegmentTree segmentTree = new SegmentTree(100001); // Array A values are in range [1, 10^5]
        int maxLength = 0;

        for (int i = 0; i < N; i++) {
            // Find the maximum length of subsequence ending at some index j where j + K >= i
            int maxLengthUntilNow = segmentTree.query(0, A[i]) + 1;

            // Update the current value in the segment tree
            segmentTree.update(A[i], maxLengthUntilNow);

            // Track the maximum length of the subsequence found so far
            maxLength = Math.max(maxLength, maxLengthUntilNow);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Reading input
        int N = scanner.nextInt();
        int K = scanner.nextInt();
        int[] A = new int[N];

        for (int i = 0; i < N; i++) {
            A[i] = scanner.nextInt();
        }

        // Calculate the result
        int result = findLongestGoodSubsequence(A, N, K);
        System.out.println(result);

        scanner.close();
    }
}
