package problems.linked_list;

import java.util.HashSet;
import java.util.Set;


/**
 * Problem Statement:
 * Given a linked list, determine if it has a cycle in it.
 *
 * A linked list is said to contain a cycle if any node in the list can be reached again by
 * continuously following the next pointer. Internally, pos is used to denote the index of the
 * node that tail's next pointer is connected to. Note that pos is not passed as a parameter.
 *
 * Return true if there is a cycle in the linked list. Otherwise, return false.
 *
 * Follow up:
 * Can you solve it without using extra space?
 */
public class LinkedListCycle {

    /**
     * Detects cycle using HashMap approach.
     * Time complexity: O(n), Space complexity: O(n)
     */
    public boolean hasCycleHashMap(ListNode head) {
        Set<ListNode> seen = new HashSet<>();
        ListNode curr = head;
        while (curr != null) {
            if (seen.contains(curr)) {
                return true;
            }
            seen.add(curr);
            curr = curr.next;
        }

        return false;
    }


    /**
     * Detects cycle using Floyd's Cycle Detection (Tortoise and Hare) approach.
     * Time complexity: O(n), Space complexity: O(1)
     */
    public boolean hasCycleTwoPointer(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        LinkedListCycle solution = new LinkedListCycle();

        // Test case: create a cycle
        ListNode node1 = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(0);
        ListNode node4 = new ListNode(-4);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2; // creates a cycle

        System.out.println("Has cycle: " + solution.hasCycleTwoPointer(node1));
        System.out.println("Has cycle (hash): " + solution.hasCycleHashMap(node1));
    }
}
