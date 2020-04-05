package org.joseph.algorithm.list;

import org.joseph.algorithm.common.ListNode;

public class NumListAdd {

    /**
     * 将两个链表里的数字按照次序相加
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null, currNode = null;
        int increment = 0;

        ListNode n1 = l1, n2 = l2;
        while (null != n1 || null != n2) {
            int v1 = null != n1 ? n1.val : 0;
            int v2 = null != n2 ? n2.val : 0;

            int res = v1 + v2 + increment;
            increment = res / 10;

            if (n1 == l1 && n2 == l2) {     // 链表头
                head = new ListNode(res % 10);
                currNode = head;
            } else {
                currNode.next = new ListNode(res % 10);
                currNode = currNode.next;
            }
            if(null != n1) n1 = n1.next;
            if(null != n2) n2 = n2.next;
        }

        if (increment == 1){
            currNode.next = new ListNode(1);
        }

        return head;  // 第一个结点是冗余结点
    }

    public static void main(String[] args) {
        int[] nums1 = {1,5,5,7};
        int[] nums2 = {2,6,0,4,2};
        ListNode head1 = ListFunction.createList(nums1);
        ListNode head2 = ListFunction.createList(nums2);
        ListNode listNode = NumListAdd.addTwoNumbers(head1, head2);
        ListFunction.printList(listNode);
    }

}
