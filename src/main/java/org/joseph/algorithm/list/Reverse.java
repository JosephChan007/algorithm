package org.joseph.algorithm.list;

import org.joseph.algorithm.common.ListNode;


/**
 * 链表反转算法
 */
public class Reverse {

    /**
     * 递归方案
     */
    public ListNode solution(ListNode head) {
        if (null == head || null == head.next) return head;

        ListNode newHead = solution(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    /**
     * 迭代方案
     */
    public ListNode solution1(ListNode head) {
        if (null == head || null == head.next) return head;

        ListNode curNode = head.next;
        ListNode preNode = head;
        ListNode tmpNode = null;

        while (true) {
            if(null == curNode) break;

            tmpNode = curNode.next;
            curNode.next = preNode;
            preNode = curNode;
            curNode = tmpNode;
        }
        head.next = null;
        return preNode;
    }

}
