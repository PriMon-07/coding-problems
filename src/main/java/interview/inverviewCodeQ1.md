**First Non-Repeating Character**

```java
public static char firstNonRepeatingChar(String str) {
    Map<Character, Integer> charCount = new HashMap<>();
    for (char c : str.toCharArray()) {
        charCount.put(c, charCount.getOrDefault(c, 0) + 1);
    }

    for (char c : str.toCharArray()) {
        if (charCount.get(c) == 1) {
            return c;
        }
    }

    return '\0'; // No non-repeating character found
}
```

**ATOI**

```java
public static int myAtoi(String str) {
    int sign = 1;
    int result = 0;
    int index = 0;

    while (str.charAt(index) == ' ') {
        index++;
    }

    if (str.charAt(index) == '+' || str.charAt(index) == '-') {
        sign = str.charAt(index) == '+' ? 1 : -1;
        index++;
    }

    while (index < str.length() && Character.isDigit(str.charAt(index))) {
        int digit = str.charAt(index) - '0';
        if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && digit > 7)) {
            return Integer.MAX_VALUE;
        }
        if (result < Integer.MIN_VALUE / 10 || (result == Integer.MIN_VALUE / 10 && digit < -8)) {
            return Integer.MIN_VALUE;
        }
        result = result * 10 + digit;
        index++;
    }

    return sign * result;
}
```

**Group Anagrams**

```java
public List<List<String>> groupAnagrams(String[] strs) {
    Map<String, List<String>> groups = new HashMap<>();
    for (String str : strs) {
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        String sortedStr = new String(chars);
        groups.computeIfAbsent(sortedStr, k -> new ArrayList<>()).add(str);
    }
    return new ArrayList<>(groups.values());
}
```

**Apache Log**

Apache Log4j2 is a popular logging framework for Java applications. It allows you to configure logging levels, appenders, and layouts to control how logs are generated and formatted.

**Power of 10**

```java
public static boolean isPowerOfTen(int num) {
    if (num <= 0) {
        return false;
    }
    while (num % 10 == 0) {
        num /= 10;
    }
    return num == 1;
}
```

**Merge Two Sorted Arrays**

```java
public static int[] mergeSortedArrays(int[] nums1, int[] nums2) {
    int m = nums1.length;
    int n = nums2.length;
    int[] merged = new int[m + n];
    int i = 0, j = 0, k = 0;

    while (i < m && j < n) {
        if (nums1[i] <= nums2[j]) {
            merged[k++] = nums1[i++];
        } else {
            merged[k++] = nums2[j++];
        }
    }

    while (i < m) {
        merged[k++] = nums1[i++];
    }

    while (j < n) {
        merged[k++] = nums2[j++];
    }

    return merged;
}
```

**Dot Product**

```java
public static int dotProduct(int[] nums1, int[] nums2) {
    int product = 0;
    for (int i = 0; i < nums1.length; i++) {
        product += nums1[i] * nums2[i];
    }
    return product;
}
```

**Robot Code**

```java
public int[] robotSim(int[] commands, int[][] obstacles) {
    int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    int x = 0, y = 0, dirIndex = 0;
    Set<String> obstacleSet = new HashSet<>();
    for (int[] obstacle : obstacles) {
        obstacleSet.add(obstacle[0] + "," + obstacle[1]);
    }

    int maxDistance = 0;
    for (int command : commands) {
        if (command == -1) {
            dirIndex = (dirIndex + 1) % 4;
        } else {
            for (int i = 0; i < command; i++) {
                int newX = x + directions[dirIndex][0];
                int newY = y + directions[dirIndex][1];
                if (!obstacleSet.contains(newX + "," + newY)) {
                    x = newX;
                    y = newY;
                    maxDistance = Math.max(maxDistance, x * x + y * y);
                }
            }
        }
    }

    return new int[]{x, y};
}
```

**Relational Length Code**

[Leetcode Interval](https://leetcode.com/problem-list/52dlem1s/)

**Here are the solutions to the given problems in Java:**

**Median of Two Sorted Arrays**

```java
public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    int m = nums1.length;
    int n = nums2.length;
    int total = m + n;
    int half = total / 2;

    if (m > n) {
        return findMedianSortedArrays(nums2, nums1);
    }

    int left = 0, right = m;
    while (true) {
        int partitionX = left + (right - left) / 2;
        int partitionY = half - partitionX - 1;

        int Aleft = partitionX >= 0 ? nums1[partitionX] : Integer.MIN_VALUE;
        int Aright = partitionX + 1 < m ? nums1[partitionX + 1] : Integer.MAX_VALUE;
        int Bleft = partitionY >= 0 ? nums2[partitionY] : Integer.MIN_VALUE;
        int Bright = partitionY + 1 < n ? nums2[partitionY + 1] : Integer.MAX_VALUE;

        // partition is correct
        if (Aleft <= Bright && Bleft <= Aright) {
            // odd
            if (total % 2 == 1) {
                return Math.min(Aright, Bright);
            }
            // even
            return (Math.max(Aleft, Bleft) + Math.min(Aright, Bright)) / 2.0;
        } else if (Aleft > Bright) {
            right = partitionX - 1;
        } else {
            left = partitionX + 1;
        }
    }
}
```

**Optimal Value in a 2D Array**

```java
public int maxSumSubmatrix(int[][] matrix) {
    int m = matrix.length, n = matrix[0].length;
    int maxSum = Integer.MIN_VALUE;

    for (int left = 0; left < n; left++) {
        int[] rowSum = new int[m];
        for (int right = left; right < n; right++) {
            for (int i = 0; i < m; i++) {
                rowSum[i] += matrix[i][right];
            }

            int currSum = 0, maxRowSum = Integer.MIN_VALUE;
            for (int i = 0; i < m; i++) {
                currSum = Math.max(currSum + rowSum[i], rowSum[i]);
                maxRowSum = Math.max(maxRowSum, currSum);
            }
            maxSum = Math.max(maxSum, maxRowSum);
        }
    }

    return maxSum;
}
```

**Co-ordinates (Up, Down, Right, Left)**

```java
public int[] robotSim(int[] commands, int[][] obstacles) {
    // ... (implementation similar to the previous response)
}
```

**4. Minimal Subset of an Integer Array**

This problem can be solved using a backtracking approach or a dynamic programming approach. The dynamic programming approach is more efficient.

**Minimum Subarray Exceeding a Given Sum**

```java
public int minSubArrayLen(int target, int[] nums) {
    int n = nums.length;
    int left = 0, right = 0, sum = 0, minLen = Integer.MAX_VALUE;

    while (right < n) {
        sum += nums[right];

        while (sum >= target) {
            minLen = Math.min(minLen, right - left + 1);
            sum -= nums[left++];
        }

        right++;
    }

    return minLen == Integer.MAX_VALUE ? 0 : minLen;
}
```

**Run Length Encoding**

```java
public String encode(String s) {
    StringBuilder sb = new StringBuilder();
    int count = 1;
    for (int i = 1; i < s.length(); i++) {
        if (s.charAt(i) == s.charAt(i - 1)) {
            count++;
        } else {
            sb.append(s.charAt(i - 1));
            sb.append(count);
            count = 1;
        }
    }
    sb.append(s.charAt(s.length() - 1));
    sb.append(count);
    return sb.toString();
}
```

**Unique Tuples**

```java
public List<List<Integer>> uniqueTuples(int[] nums, int k) {
    Arrays.sort(nums);
    List<List<Integer>> result = new ArrayList<>();

    for (int i = 0; i < nums.length - k + 1; i++) {
        List<Integer> tuple = new ArrayList<>();
        tuple.add(nums[i]);
        dfs(nums, i + 1, k - 1, tuple, result);
    }

    return result;
}

private void dfs(int[] nums, int start, int k, List<Integer> tuple, List<List<Integer>> result) {
    if (k == 0) {
        result.add(new ArrayList<>(tuple));
        return;
    }

    for (int i = start; i < nums.length; i++) {
        if (i > start && nums[i] == nums[i - 1]) {
            continue;
        }
        tuple.add(nums[i]);
        dfs(nums, i + 1, k - 1, tuple, result);
        tuple.remove(tuple.size() - 1);
    }
}
```

**Magic Potion Snippet**

Without more specific details about the problem, it's difficult to provide a tailored solution. However, you can often use dynamic programming or greedy algorithms to solve problems involving potions and their effects.

**Second Smallest Element**

```java
public int secondSmallest(int[] arr) {
    int smallest = Integer.MAX_VALUE;
    int secondSmallest = Integer.MAX_VALUE;
    for (int num : arr) {
        if (num < smallest) {
            secondSmallest = smallest;
            smallest = num;
        } else if (num < secondSmallest && num != smallest) {
            secondSmallest = num;
        }
    }
    return secondSmallest;
}
```
**Run Length Encoding**

```java
public String encode(String s) {
    StringBuilder sb = new StringBuilder();
    int count = 1;
    for (int i = 1; i < s.length(); i++) {
        if (s.charAt(i) == s.charAt(i - 1)) {
            count++;
        } else {
            sb.append(s.charAt(i - 1));
            sb.append(count);
            count = 1;
        }
    }
    sb.append(s.charAt(s.length() - 1));
    sb.append(count);
    return sb.toString();
}
```

**Count Length of Cycle in a Linked List**

```java
public int cycleLength(ListNode head) {
    ListNode slow = head, fast = head;
    while (fast != null && fast.next != null) {
        slow = slow.next;
        fast = fast.next.next;
        if (slow == fast) {
            int count = 0;
            do {
                slow = slow.next;
                count++;
            } while (slow != fast);
            return count;
        }
    }
    return 0; // No cycle
}
```

**Longest Uniform Substring**

```java
public int longestUniformSubstring(String s) {
    int maxLen = 0, currLen = 1;
    for (int i = 1; i < s.length(); i++) {
        if (s.charAt(i) == s.charAt(i - 1)) {
            currLen++;
        } else {
            maxLen = Math.max(maxLen, currLen);
            currLen = 1;
        }
    }
    return Math.max(maxLen, currLen);
}
```

**Pangrams**

```java
public String findMissingLetters(String sentence) {
    boolean[] alphabet = new boolean[26];
    for (char c : sentence.toLowerCase().toCharArray()) {
        if (Character.isAlphabetic(c)) {
            alphabet[c - 'a'] = true;
        }
    }

    StringBuilder missingLetters = new StringBuilder();
    for (int i = 0; i < 26; i++) {
        if (!alphabet[i]) {
            missingLetters.append((char) ('a' + i));
        }
    }

    return missingLetters.toString();
}
```

**Prime Factorization**

```java
public List<Integer> primeFactors(int n) {
    List<Integer> factors = new ArrayList<>();
    while (n % 2 == 0) {
        factors.add(2);
        n /= 2;
    }

    for (int i = 3; i * i <= n; i += 2) {
        while (n % i == 0) {
            factors.add(i);
            n /= i;
        }
    }

    if (n > 1) {
        factors.add(n);
    }

    return factors;
}
```

**Pascal's Triangle**

```java
public List<List<Integer>> generatePascalTriangle(int numRows) {
    List<List<Integer>> triangle = new ArrayList<>();
    if (numRows == 0) {
        return triangle;
    }

    triangle.add(new ArrayList<>());
    triangle.get(0).add(1);

    for (int i = 1; i < numRows; i++) {
        List<Integer> row = new ArrayList<>();
        row.add(1);
        for (int j = 1; j < i; j++) {
            row.add(triangle.get(i - 1).get(j - 1) + triangle.get(i - 1).get(j));
        }
        row.add(1);
        triangle.add(row);
    }

    return triangle;
}
```

**Circular List**

A circular linked list is a linked list where the last node points back to the first node, forming a circular structure. You can detect a cycle in a linked list using the Floyd's Cycle-Finding Algorithm (slow and fast pointer technique).

**Fractions Addition in Two Arrays**

You can add fractions by finding a common denominator and then adding the numerators.

**Subarray Exceeding Sum**

```java
public int minSubArrayLen(int target, int[] nums) {
    int n = nums.length;
    int left = 0, right = 0, sum = 0, minLen = Integer.MAX_VALUE;

    while (right < n) {
        sum += nums[right];

        while (sum >= target) {
            minLen = Math.min(minLen, right - left + 1);
            sum -= nums[left++];
        }

        right++;
    }

    return minLen == Integer.MAX_VALUE ? 0 : minLen;
}
```

**Election Snippet**

Without more specific details about the election scenario, it's difficult to provide a tailored solution. However, you can use data structures like heaps or priority queues to efficiently process election results and identify the winner.

Please provide more specific requirements or constraints for a more accurate solution.

**Second Smallest Number**

```java
public int secondSmallest(int[] arr) {
    int smallest = Integer.MAX_VALUE;
    int secondSmallest = Integer.MAX_VALUE;
    
    for (int num : arr) {
        if (num < smallest) {
            secondSmallest = smallest;
            smallest = num;
        } else if (num < secondSmallest && num != smallest) {
            secondSmallest = num;
        }
    }
    
    return secondSmallest;
}
```

**Mirror a Binary Tree**

```java
public TreeNode invertTree(TreeNode root) {
    if (root == null) {
        return null;
    }

    TreeNode temp = root.left;
    root.left = invertTree(root.right);
    root.right = invertTree(temp);

    return root;
}
```

**BFS in Matrix**

```java
public List<List<Integer>> levelOrder(int[][] matrix) {
    List<List<Integer>> result = new ArrayList<>();
    Queue<int[]> queue = new LinkedList<>();
    queue.offer(new int[]{0, 0});
    int m = matrix.length, n = matrix[0].length;
    boolean[][] visited = new boolean[m][n];
    visited[0][0] = true;

    while (!queue.isEmpty()) {
        int size = queue.size();
        List<Integer> level = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            int[] curr = queue.poll();
            int x = curr[0], y = curr[1];
            level.add(matrix[x][y]);

            // Check neighbors
            if (x + 1 < m && !visited[x + 1][y]) {
                queue.offer(new int[]{x + 1, y});
                visited[x + 1][y] = true;
            }
            if (x - 1 >= 0 && !visited[x - 1][y]) {
                queue.offer(new int[]{x - 1, y});
                visited[x - 1][y] = true;
            }
            if (y + 1 < n && !visited[x][y + 1]) {
                queue.offer(new int[]{x, y + 1});
                visited[x][y + 1] = true;
            }
            if (y - 1 >= 0 && !visited[x][y - 1]) {
                queue.offer(new int[]{x, y - 1});
                visited[x][y - 1] = true;
            }
        }
        result.add(level);
    }

    return result;
}
```

**Unique Tuples**

```java
public List<List<Integer>> uniqueTuples(int[] nums, int k) {
    Arrays.sort(nums);
    List<List<Integer>> result = new ArrayList<>();

    for (int i = 0; i < nums.length - k + 1; i++) {
        List<Integer> tuple = new ArrayList<>();
        tuple.add(nums[i]);
        dfs(nums, i + 1, k - 1, tuple, result);
    }

    return result;
}

private void dfs(int[] nums, int start, int k, List<Integer> tuple, List<List<Integer>> result) {
    if (k == 0) {
        result.add(new ArrayList<>(tuple));
        return;
    }

    for (int i = start; i < nums.length; i++) {
        if (i > start && nums[i] == nums[i - 1]) {
            continue;
        }
        tuple.add(nums[i]);
        dfs(nums, i + 1, k - 1, tuple, result);
        tuple.remove(tuple.size() - 1);
    }
}
```
