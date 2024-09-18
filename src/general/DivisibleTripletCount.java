package general;

import java.util.HashMap;
import java.util.Map;

public class DivisibleTripletCount {
    public static int divisibleTripletCount(int[] nums, int d) {
        Map<Integer, Integer> cnt = new HashMap<>();
        int ans = 0, n = nums.length;
        for (int j = 0; j < n; ++j) {
            for (int k = j + 1; k < n; ++k) {
                int x = (d - (nums[j] + nums[k]) % d) % d;
                ans += cnt.getOrDefault(x, 0);
            }
            cnt.merge(nums[j] % d, 1, Integer::sum);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 3, 4, 7, 8};
        int d = 5;
        System.out.println(divisibleTripletCount(nums, d));
    }
}
