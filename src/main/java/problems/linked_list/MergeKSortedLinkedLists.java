package problems.linked_list;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;


/**
 * Problem Statement:
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
 * Merge all the linked-lists into one sorted linked-list and return it.
 *
 * Example:
 * Input: lists = [[1,4,5],[1,3,4],[2,6]]
 * Output: [1,1,2,3,4,4,5,6]
 * Explanation: The linked-lists are:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * merging them into one sorted list:
 * 1->1->2->3->4->4->5->6
 */
public class MergeKSortedLinkedLists {

    /**
     * Merges k sorted linked lists into one sorted linked list.
     * Uses heap approach.
     *
     * Time complexity: O(n log k), where n is the total number of nodes in all lists.
     * Space complexity: O(k), where k is the number of lists.
     */
    public ListNode mergeKListsHeap(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        PriorityQueue<ListNode> heap = new PriorityQueue<>((a, b) -> a.val-b.val);
        for (ListNode head : lists) {
            if (head != null) {
                heap.offer(head);
            }
        }
        ListNode res = new ListNode(0);
        ListNode curr = res;
        while (!heap.isEmpty()) {
            ListNode node = heap.poll();
            curr.next = node;
            curr = node;

            node = node.next;
            if (node != null) {
                heap.offer(node);
            }
        }

        return res.next;
    }

    /**
     * Merges k sorted linked lists into one sorted linked list.
     * Uses divide and conquer approach.
     *
     * Time complexity: O(n log k), where n is the total number of nodes in all lists.
     * Space complexity: O(log k), due to recursion stack.
     */
    public ListNode mergeKListsDivideConquer(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        while (lists.length > 1) {
            List<ListNode> mergedLists = new ArrayList<>();
            for (int i = 0; i < lists.length; i += 2) {
                ListNode l1 = lists[i];
                ListNode l2 = (i + 1) < lists.length ? lists[i + 1] : null;
                mergedLists.add(mergeList(l1, l2));
            }
            lists = mergedLists.toArray(new ListNode[0]);
        }
        return lists[0];
    }

    private ListNode mergeList(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode tail = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }

        if (l1 != null) {
            tail.next = l1;
        }
        if (l2 != null) {
            tail.next = l2;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        MergeKSortedLinkedLists mergeKSortedLinkedLists = new MergeKSortedLinkedLists();

        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(5);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);

        ListNode l3 = new ListNode(2);
        l3.next = new ListNode(6);

        ListNode[] lists = {l1, l2, l3};
//        System.out.println(mergeKSortedLinkedLists.mergeKListsHeap(lists));
        System.out.println(mergeKSortedLinkedLists.mergeKListsDivideConquer(lists));

        ListNode l4 = new ListNode(1);
        l4.next = new ListNode(4);
        l4.next.next = new ListNode(5);

        ListNode l5 = new ListNode(1);
        l5.next = new ListNode(3);
        l5.next.next = new ListNode(4);

        ListNode[] lists2 = {l4, l5};
//        System.out.println(mergeKSortedLinkedLists.mergeKListsHeap(lists2));
        System.out.println(mergeKSortedLinkedLists.mergeKListsDivideConquer(lists2));
    }

}
