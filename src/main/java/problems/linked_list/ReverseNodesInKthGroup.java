package problems.linked_list;


/**
 * Problem Statement:
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
 *
 * Example:
 * Input: head = [1,2,3,4,5], k = 2
 * Output: [2,1,4,3,5]
 *
 * Input: head = [1,2,3,4,5], k = 3
 * Output: [3,2,1,4,5]
 */
public class ReverseNodesInKthGroup {

    /**
     * Time complexity: O(n), where n is the total number of nodes in the list.
     * Space complexity: O(1), no additional memory is used.
     *
     * The implementation uses a dummy node to keep track of the head of the modified list.
     * It then iterates through the list, reversing the nodes in groups of size k.
     * To reverse the nodes in a group, it uses a two-pointer approach, where the first pointer
     * iterates through the original group, and the second pointer is used to maintain the reversed group.
     * The second pointer is initially set to the last node of the group, and its next pointer is used
     * to maintain the reversed group.
     * Finally, the head of the modified list is returned.
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0, head);
        ListNode groupPrev = dummy;

        while (true) {
            ListNode kth = getKthNode(groupPrev, k);
            if (kth == null) {
                break;
            }

            ListNode groupNext = kth.next;

            ListNode curr = groupPrev.next;
            ListNode prev = groupNext;
            while (curr != groupNext) {
                ListNode temp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = temp;
            }

            ListNode node = groupPrev.next;
            groupPrev.next = kth;
            groupPrev = node;
        }
        return dummy.next;
    }

    private ListNode getKthNode(ListNode node, int k) {
        ListNode curr = node;
        while (curr != null && k > 0) {
            curr = curr.next;
            k--;
        }
        return curr;
    }

    public static void main(String[] args) {
        ReverseNodesInKthGroup reverseNodesInKthGroup = new ReverseNodesInKthGroup();

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        System.out.println(reverseNodesInKthGroup.reverseKGroup(head, 2));
    }
}
