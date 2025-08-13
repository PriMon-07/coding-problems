package general;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class OptimalMaxProcessingBias {

    public static int getOptimalMaxProcessingBias(List<Integer> time, int minRequests) {
        Collections.sort(time);
        int low = 0;
        int high = time.get(time.size() - 1) - time.get(0);
        int answer = high;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (canPartition(time, minRequests, mid)) {
                answer = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return answer;
    }

    private static boolean canPartition(List<Integer> time, int minRequests, int maxBias) {
        int i = 0;
        int n = time.size();

        while (i < n) {
            int j = i;
            // Expand group while bias is within limit
            while (j < n && time.get(j) - time.get(i) <= maxBias) {
                j++;
            }
            if (j - i < minRequests) return false;
            i = j; // Move to next group
        }

        return true;
    }


    public static void main(String[] args) {
        // Hardcoded input
        List<Integer> time = Arrays.asList(1, 3, 2, 9, 5, 2);
        int minRequests = 2;

//        List<Integer> time = Arrays.asList(60,50,10,20,10);
//        int minRequests = 2;

        int result = getOptimalMaxProcessingBias(time, minRequests);
        System.out.println(result);
    }

}
