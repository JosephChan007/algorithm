package org.joseph.algorithm.list;

import org.joseph.algorithm.common.ListNode;

public class NumListAdd {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);  // 该结点冗余，第二个结点才是链表的头结点
        int increment = 0;
        ListNode currNode = dummyHead;

        for (ListNode n1 = l1, n2 = l2; l1 != null || l2 != null;){
            int x = l1 != null ? l1.val : 0;
            int y = l2 != null ? l2.val : 0;

            int result = x + y + increment;
            currNode.next = new ListNode(result % 10);
            increment = result / 10;
            currNode = currNode.next;

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        if (increment == 1){
            currNode.next = new ListNode(1);
        }

        return dummyHead.next;  // 第一个结点是冗余结点
    }

}
