package problems.binary_search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Problem Statement:
 * Design a class to store string key/value pairs that can be inserted with a timestamp.
 * The class should also be able to return the value associated with a key at a given timestamp.
 *
 * Example:
 * Input:
 * ["TimeMap","set","get","get","set","get","get"]
 * [[],["foo","bar",1],["foo",1],["foo",3],["foo","bar2",4],["foo",4],["foo",5]]
 * Output:
 * [null,null,"bar","bar",null,"bar2","bar2"]
 * Explanation:
 * TimeMap kv;
 * kv.set("foo", "bar", 1); // store the key "foo" and value "bar" along with timestamp = 1
 * kv.get("foo", 1);  // output "bar"
 * kv.get("foo", 3); // output "bar" since there is no value corresponding to foo at timestamp 3 and timestamp 2, then lists all left values in ascending order is ["bar"]
 * kv.set("foo", "bar2", 4);
 * kv.get("foo", 4); // output "bar2"
 * kv.get("foo", 5); //output "bar2"
 */
class TimeBasedKeyValueStore {
    public static void main(String[] args) {
        TimeMap timeMap = new TimeMap();
        timeMap.set("foo", "bar", 1);
        System.out.println(timeMap.get("foo", 1));  // "bar"
        System.out.println(timeMap.get("foo", 3));  // "bar"
        timeMap.set("foo", "bar2", 4);
        System.out.println(timeMap.get("foo", 4));  // "bar2"
        System.out.println(timeMap.get("foo", 5));  // "bar2"
    }
}


/**
 * Solution:
 * We use a hash map to store the key value pairs. For each key, we maintain a list of TimeValue objects
 * that contains the timestamp and the value associated with the key. We use a list to preserve the order of
 * insertion.
 *
 * Time Complexity:
 * - set: O(1) on average, O(n) in worst case where n is the number of values associated with the key.
 * - get: O(1) on average, O(n) in worst case where n is the number of values associated with the key.
 *
 * Space Complexity:
 * O(n) where n is the number of all keys.
 */
class TimeMap {

    private final Map<String, List<TimeValue>> map;

    public TimeMap() {
        this.map = new HashMap<>();
    }


    /**
     * Inserts a key-value pair into the map with the given timestamp.
     * If the key already exists, the new value will be appended to the list of values associated with the key.
     * @param key the key to insert
     * @param value the value to associate with the key
     * @param timestamp the timestamp for the key-value pair
     */
    public void set(String key, String value, int timestamp) {
        if (!map.containsKey(key)) {
            map.put(key, new ArrayList<>());
        }
        List<TimeValue> timeValueList = map.get(key);
        TimeValue obj = new TimeValue();
        obj.timestamp = timestamp;
        obj.value = value;
        timeValueList.add(obj);
    }

    /**
     * Returns the value associated with the given key at the given timestamp.
     * If no such timestamp exists, it returns the value associated with the largest timestamp smaller than the given one.
     * If no such timestamp exists and the given timestamp is smaller than the smallest timestamp associated with the key, it returns an empty string.
     * @param key the key to retrieve
     * @param timestamp the timestamp for the key
     * @return the value associated with the key at the given timestamp
     */
    public String get(String key, int timestamp) {
        if (!map.containsKey(key)) {
            return "";
        }

        List<TimeValue> timeValueList = map.get(key);
        
        // If the requested timestamp is smaller than the first timestamp, return empty string
        if (timestamp < timeValueList.get(0).timestamp) {
            return "";
        }
        
        int left = 0;
        int right = timeValueList.size() - 1;
        int result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (timestamp >= timeValueList.get(mid).timestamp) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return result == -1 ? "" : timeValueList.get(result).value;
    }

    private static class TimeValue {
        int timestamp;
        String value;
    }
}
