package general;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MedianSum {

    public static long getMedianSum(List<Integer> rewards) {
        int n = rewards.size();
        long sum = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int len = j - i + 1;
                int mid = len / 2;

                if (len % 2 == 1) {
                    // Odd length → middle element
                    sum += rewards.get(i + mid);
                } else {
                    // Even length → floor((a[mid-1] + a[mid]) / 2)


                    int m1 = rewards.get(i + mid - 1);
                    int m2 = rewards.get(i + mid);
                    sum += (m1 + m2) / 2;
                }
            }
        }

        return sum;
    }

    public static long getMedianSumOptimized(List<Integer> rewards) {
        int n = rewards.size();
        long sum = 0;

        // Odd-length subarrays contribution
        for (int i = 0; i < n; i++) {
            long oddCount = Math.min(i, n - i - 1) + 1;
            sum += rewards.get(i) * oddCount;
        }

        // Even-length subarrays contribution
        for (int i = 0; i < n - 1; i++) {
            long evenCount = Math.min(i + 1, n - (i + 1));
            int medianEven = (rewards.get(i) + rewards.get(i + 1)) / 2;
            sum += medianEven * evenCount;
        }

        return sum;

    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 3 1 1 2 -> 7
        // 3 1 4 5 -> 20
        // 2 3 5 -> 12
        int n = sc.nextInt();
        List<Integer> rewards = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            rewards.add(sc.nextInt());
        }

        long result = getMedianSum(rewards);
        long resultOptimized = getMedianSumOptimized(rewards);
        System.out.println(result);
        System.out.println(resultOptimized);

        sc.close();
    }
}