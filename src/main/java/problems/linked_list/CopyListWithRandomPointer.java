package problems.linked_list;

import java.util.HashMap;
import java.util.Map;


/**
 * Problem Statement:
 * A linked list of length n is given such that each node contains an additional random pointer, which could point to any node in the list, or null.
 * Construct a deep copy of the list. The deep copy should consist of entirely new nodes, where each new node has its value set to the value of its corresponding original node.
 * Both the next and random pointer of the new nodes should point to new nodes in the copied list such that the pointers in the original list and copied list represent the same list state.
 * None of the pointers in the new list should point to nodes in the original list.
 * For example, if there are two nodes X and Y in the original list, where X.random --> Y, then for the corresponding two nodes x and y in the copied list, x.random --> y.
 * Return the head of the copied linked list.
 * The linked list is represented in the input/output as a list of n nodes. Each node is represented as a pair of [val, random_index] where:
 * val: an integer representing Node.val
 * random_index: the index of the node (range from 0 to n-1) that the random pointer points to, or null if it does not point to any node.
 * Your code will only be given the head of the original linked list.
 * It is guaranteed that the given linked list is not exceptionally large.
 */
public class CopyListWithRandomPointer {

    /**
     * Hash Map approach to copy a linked list with random pointers.
     * Time complexity: O(n), Space complexity: O(n)
     */
    public Node copyRandomListHashMap(Node head) {
        Map<Node, Node> oldToCopy = new HashMap<>();

        Node curr = head;
        while (curr != null) {
            Node copy = new Node(curr.val);
            oldToCopy.put(curr, copy);
            curr = curr.next;
        }

        curr = head;
        while (curr != null) {
            Node newNode = oldToCopy.get(curr);
            newNode.next = oldToCopy.get(curr.next);
            newNode.random = oldToCopy.get(curr.random);
            curr = curr.next;
        }

        return oldToCopy.get(head);
    }

    /**
     * The algorithm first creates a copy of each node in the original list and links the copies after the original
     * nodes. Then, it iterates through the list again and sets the random pointers and next pointers of the copies.
     * The time complexity is O(n), where n is the number of nodes in the list, since we iterate through the list twice.
     * The space complexity is O(n), where n is the number of nodes in the list, since we use a hash map to store
     * the mapping from old nodes to new nodes.
     */
    public Node copyRandomListSpaceOptimized(Node head) {

        if (head == null) {
            return null;
        }

        Node l1 = head;
        while (l1 != null) {
            Node l2 = new Node(l1.val);
            l2.next = l1.next;
            l1.next = l2;
            l1 = l2.next;
        }
        Node newHead = head.next;

        l1 = head;
        while (l1 != null) {
            Node l2 = l1.next;
            if (l1.random != null) {
                l2.random = l1.random.next;
            }
            l1 = l1.next.next;
        }

        l1 = head;
        while (l1 != null) {
            Node l2 = l1.next;
            l1.next = l2.next;
            if (l2.next != null) {
                l2.next = l2.next.next;
            }
            l1 = l1.next;
        }

        return newHead;
    }

    public static void main(String[] args) {
        // Test case: create a simple list with random pointers
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);

        n1.next = n2;
        n2.next = n3;

        n1.random = n3;
        n2.random = n1;

        CopyListWithRandomPointer solution = new CopyListWithRandomPointer();
        Node copied = solution.copyRandomListHashMap(n1);
        System.out.println("Original: " + n1);
        System.out.println("Copied: " + copied);

        Node copiedOpt = solution.copyRandomListSpaceOptimized(n1);
        System.out.println("Copied (Optimized): " + copiedOpt);
    }
}
