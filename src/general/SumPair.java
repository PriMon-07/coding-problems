package general;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class SumPair {

    public static void sumPair(int[] A, int[] B) {
        // Convert array A to a HashSet for quick look-up
        Set<Integer> setA = new HashSet<>();
        for (int num : A) {
            setA.add(num);
        }

        int count = 0;
        int lengthB = B.length;

        // Find all unique pairs in array B
        for (int i = 0; i < lengthB; i++) {
            for (int j = i + 1; j < lengthB; j++) {
                int sum = B[i] + B[j];
                if (setA.contains(sum)) {
                    count++;
                }
            }
        }

        // Print the result
        System.out.println(count);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read sizes of arrays A and B
        int N = scanner.nextInt();
        int M = scanner.nextInt();

        // Read array A
        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = scanner.nextInt();
        }

        // Read array B
        int[] B = new int[M];
        for (int i = 0; i < M; i++) {
            B[i] = scanner.nextInt();
        }

        // Close the scanner
        scanner.close();

        // Call the sumPair method with the input arrays
        sumPair(A,B);}
}