package sorting;

import java.util.Arrays;

public class MergeSort {
    private static void merge(int[] arr, int[] l, int[] r, int left, int right) {

        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (l[i] <= r[j]) {
                arr[k++] = l[i++];
            } else {
                arr[k++] = r[j++];
            }
        }
        while (i < left) {
            arr[k++] = l[i++];
        }
        while (j < right) {
            arr[k++] = r[j++];
        }
        Arrays.stream(arr).forEach(a -> System.out.print(a + " "));
        System.out.println();
    }

    public static void sort(int arr[], int n) {
        if (n < 2) {
            return;
        }

        int mid = n / 2;

        int[] l = new int[mid];
        int[] r = new int[n - mid];
        for (int i = 0; i < mid; i++) {
            l[i] = arr[i];
        }
        for (int i = mid; i < n; i++) {
            r[i-mid] = arr[i];
        }

        Arrays.stream(l).forEach(a -> System.out.print(a + " "));
        System.out.print("-- ");
        Arrays.stream(r).forEach(a -> System.out.print(a + " "));
        System.out.println();

        sort(l, mid);
        sort(r, n - mid);

        merge(arr, l, r, mid, n - mid);
    }

    public static void main(String[] args) {
        int[] num = {10,6,8,5,7,3,4};
        MergeSort.sort(num, num.length);
    }
}
