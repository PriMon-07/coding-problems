package problems.linked_list;


/**
 * Problem Statement:
 * You are given the heads of two sorted linked lists list1 and list2.
 * Merge the two lists into one sorted list. The list should be made
 * by splicing together the nodes of the first two lists.
 * Return the head of the merged linked list.
 *
 * Example:
 * Input: list1 = [1,2,4], list2 = [1,3,4]
 * Output: [1,1,2,3,4,4]
 * Explanation: The resulting linked list should look like:
 * 1 -> 1 -> 2 -> 3 -> 4 -> 4 -> NULL
 *
 * Constraints:
 * The number of nodes in both lists is in the range [0, 50].
 * -100 <= Node.val <= 100
 * Both list1 and list2 are sorted in non-decreasing order.
 */
public class MergeTwoSortedListNode {


    /**
     * Merges two sorted linked lists into one sorted linked list.
     * Uses iterative approach with two pointers.
     * Time complexity: O(m + n), where m and n are lengths of the two lists.
     * Space complexity: O(1).
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode sortedListNode;

        if (list1 == null && list2 == null) {
            return null;
        }else if (list1 != null && list2 == null) {
            sortedListNode = list1;
            list1 = list1.next;
        } else if (list1 == null && list2 != null){
            sortedListNode = list2;
            list2 = list2.next;
        } else if (list1.val < list2.val) {
            sortedListNode = list1;
            list1 = list1.next;
        } else {
            sortedListNode = list2;
            list2 = list2.next;
        }

        ListNode curr = sortedListNode;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                curr.next = list1;
                list1 = list1.next;
            } else {
                curr.next = list2;
                list2 = list2.next;
            }
            curr = curr.next;
        }

        if (list1 != null) {
            curr.next = list1;
        } else {
            curr.next = list2;
        }

        return sortedListNode;
    }

    public static void main(String[] args) {
        MergeTwoSortedListNode solution = new MergeTwoSortedListNode();

        // Test case 1: [1,2,4], [1,3,4]
        ListNode list1 = new ListNode(1);
        list1.next = new ListNode(2);
        list1.next.next = new ListNode(4);

        ListNode list2 = new ListNode(1);
        list2.next = new ListNode(3);
        list2.next.next = new ListNode(4);

        ListNode result = solution.mergeTwoLists(list1, list2);
        System.out.println("Merged: " + result);
    }
}
