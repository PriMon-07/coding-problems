package problems.linked_list;


/**
 * Problem Statement:
 * Given a singly linked list L: L0 → L1 → … → Ln-1 → Ln,
 * reorder it to: L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → …
 * You must do this in-place without altering the nodes' values.
 *
 * For example, given {1, 2, 3, 4}, reorder it to {1, 4, 2, 3}.
 */
public class ReorderList {

    /**
     * Reorders a linked list into a list with alternating nodes.
     * For example, given a list [1,2,3,4,5], the reordered list is [1,3,5,2,4].
     * The implementation uses the slow and fast pointer approach to find the middle of the list,
     * then reverses the second half of the list, and finally reorders the two halves.
     * Time complexity: O(n), where n is the number of nodes in the list.
     * Space complexity: O(1), no additional memory is used.
     * @param head the head of the list to be reordered
     */
    public void reorderList(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;

        while (fast != null && fast.next !=null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode second = slow.next;
        slow.next = null;
        ListNode prev = null;
        while (second !=null) {
            ListNode temp = second.next;
            second.next = prev;
            prev = second;
            second = temp;

        }

        ListNode first = head;
        second = prev;
        while (second != null) {
            ListNode temp1 = first.next;
            ListNode temp2 = second.next;
            first.next = second;
            second.next = temp1;
            first = temp1;
            second = temp2;

        }
    }

    public static void main(String[] args) {
        ReorderList solution = new ReorderList();

        // Test case
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        System.out.println("Before reordering:");
        printList(node1);

        solution.reorderList(node1);

        System.out.println("After reordering:");
        printList(node1);
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
