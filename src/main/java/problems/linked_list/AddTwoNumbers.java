package problems.linked_list;


/**
 * Problem Statement:
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order, and each of their nodes contains a single digit.
 * Add the two numbers and return the sum as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Example:
 * Input: l1 = [2,4,3], l2 = [5,6,4]
 * Output: [7,0,8]
 * Explanation: 342 + 465 = 807.
 */
public class AddTwoNumbers {

    /**
     * Adds two linked lists together as numbers.
     *
     * Time complexity: O(max(m, n)), where m and n are lengths of the two lists.
     * Space complexity: O(1), no additional memory is used.
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode curr = dummy;
        int carry = 0;
        while (l1 != null || l2 != null || carry > 0) {
            int val1 = l1 != null ? l1.val : 0;
            int val2 = l2 != null ? l2.val : 0;
            int add = val1 + val2 + carry;
            carry = add / 10;
            int res = add % 10;

            curr.next = new ListNode(res);
            curr = curr.next;

            l1 = l1 !=null ? l1.next : null;
            l2 = l2 !=null ? l2.next : null;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        // Test case: 342 + 465 = 807
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        AddTwoNumbers solution = new AddTwoNumbers();
        ListNode result = solution.addTwoNumbers(l1, l2);
        System.out.println("Result: " + result);
    }
}
