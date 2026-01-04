package problems.linked_list;

public class ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        ListNode curr = head;
        ListNode prev = null;

        while (curr != null) {
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        return prev;
    }

    /**
     * This function reverses a linked list in O(n) time and O(1) space.
     *
     * The implementation uses a two-pointer approach. The 'curr' pointer
     * iterates through the original list, and the 'prev' pointer is used
     * to maintain the reverse linked list.
     *
     * Time complexity: O(n), where n is the number of nodes in the list.
     * Space complexity: O(1), no additional memory is used.
     */
    public static void main(String[] args) {
        ReverseLinkedList solution = new ReverseLinkedList();

        // Test case: 1->2->3->4->5
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        System.out.println("Original: " + head);
        ListNode reversed = solution.reverseList(head);
        System.out.println("Reversed: " + reversed);
    }
}
