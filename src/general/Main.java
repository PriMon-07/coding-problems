package general;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5 ,6};

        Arrays.sort(arr);

        System.out.println(arr[arr.length - 2]);

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(1);
        list.add(6);
        System.out.println(list.stream().sorted(Comparator.reverseOrder()).skip(1).findFirst());
        Set<Integer> set = list.stream().collect(Collectors.toSet());
        System.out.println(set);

    }
}