package problems.linked_list;

import java.util.HashMap;
import java.util.Map;


/**
 * Problem Statement:
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.
 *
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
 *
 * Implementation:
 * To implement an LRU cache, we can use a doubly linked list to keep track of the order of the items in the cache. The head of the list represents the most recently used item, and the tail represents the least recently used item.
 *
 * Time complexity:
 * - get(key) - O(1)
 * - put(key, value) - O(1)
 *
 * Space complexity:
 * - O(capacity), where capacity is the maximum number of items the cache can hold.
 */
public class LRUCache {
    Map<Integer, Node> cache = new HashMap<>();
    Node left;
    Node right;
    int capacity;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.left = new Node(0,0);
        this.right = new Node(0,0);
        this.left.next = this.right;
        this.right.prev = this.left;
    }

    /**
     * Removes a node from the doubly linked list.
     * @param node the node to be removed
     */
    private void remove(Node node) {
        Node prev = node.prev;
        Node next = node.next;
        prev.next = next;
        next.prev = prev;
    }

    /**
     * Inserts a node into the doubly linked list.
     * The node is inserted just before the rightmost node in the list.
     * @param node the node to be inserted
     */
    private void insert(Node node) {
        Node prev = this.right.prev;
        prev.next = node;
        node.next = this.right;
        node.prev = prev;
        this.right.prev = node;
    }

    /**
     * Gets the value of the key from the cache.
     * If the key exists in the cache, it removes the key from the cache and inserts it back into the cache,
     * then returns the value of the key. If the key does not exist in the cache, it returns -1.
     *
     * Time complexity: O(1)
     *
     * @param key the key to get the value for
     * @return the value of the key if it exists in the cache, otherwise -1
     */
    public int get(int key) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            remove(node);
            insert(node);
            return node.value;
        }
        return -1;
    }

    /**
     * Sets or inserts the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
     *
     * Time complexity: O(1)
     *
     * @param key the key to set or insert
     * @param value the value to associate with the key
     */
    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            remove(cache.get(key));
        }

        Node node = new Node(key, value);
        cache.put(key, node);
        insert(node);

        if (cache.size() > this.capacity) {
            Node lru = this.left.next;
            remove(lru);
            cache.remove(lru.key);
        }
    }

    private static class Node {
        int key;
        int value;
        Node prev;
        Node next;

        Node (int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        System.out.println(lruCache.get(1));
        lruCache.put(3, 3);
        System.out.println(lruCache.get(2));
        lruCache.put(4, 4);
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(3));
        System.out.println(lruCache.get(4));
    }
}
