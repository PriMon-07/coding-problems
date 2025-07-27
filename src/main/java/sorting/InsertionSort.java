package sorting;

import java.util.Arrays;

public class InsertionSort {
    public static void sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i-1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
            Arrays.stream(arr).forEach(a -> System.out.print(a + " "));
            System.out.println();
        }
    }

    public static void main(String[] args) {
        //  Best Case O(n), average and worst O(n^2)
        int[] num = {10, 6, 8, 5, 7, 3, 4};
        InsertionSort.sort(num);
    }
}
