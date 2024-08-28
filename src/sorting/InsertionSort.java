package sorting;

import java.util.Arrays;

public class InsertionSort {
    public static void sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int index = i;
            for (int j = i -1; j >= 0; j--) {
                if (arr[index] < arr[j]) {
                    int temp = arr[index];
                    arr[index] = arr[j];
                    arr[j] = temp;
                    index = j;
                }
                Arrays.stream(arr).forEach(a -> System.out.print(a + " "));
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        int[] num = {10,6,8,5,7,3,4};
        InsertionSort.sort(num);
    }
}
