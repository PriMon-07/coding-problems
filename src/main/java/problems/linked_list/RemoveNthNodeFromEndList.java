package problems.linked_list;

/**
 * Problem Statement:
 * Given the head of a linked list, remove the nth node from the end of the list and return its head.
 *
 * Example 1:
 * Input: head = [1,2,3,4,5], n = 2
 * Output: [1,2,3,5]
 *
 * Example 2:
 * Input: head = [1], n = 1
 * Output: []
 *
 * Example 3:
 * Input: head = [1,2], n = 1
 * Output: [1]
 */
public class RemoveNthNodeFromEndList {

    /**
     * Removes the nth node from the end of the linked list.
     *
     * Given a linked list, remove the nth node from the end of the list and return its head.
     *
     * The function first counts the length of the list, then iterates through the list to find the node that needs to be removed.
     * Finally, it removes the node and returns the head of the modified list.
     *
     * Time complexity: O(n), where n is the number of nodes in the list.
     * Space complexity: O(1), no additional memory is used.
     *
     * @param head the head of the list to be modified
     * @param n the index of the node to be removed from the end of the list
     * @return the head of the modified list
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode left = dummy;
        ListNode right = head;

        while (n > 0) {
            right = right.next;
            n--;
        }

        while (right != null) {
            right = right.next;
            left = left.next;
        }

        left.next = left.next.next;

        return dummy.next;
    }

    public static void main(String[] args) {
        RemoveNthNodeFromEndList solution = new RemoveNthNodeFromEndList();

        // Test case
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        System.out.println("Before removal:");
        printList(node1);

        ListNode result = solution.removeNthFromEnd(node1, 2);

        System.out.println("After removing 2nd node from end:");
        printList(result);
    }

    private static void printList(ListNode head) {
        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.next;
        }
        System.out.println();
    }
}
