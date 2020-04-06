package org.joseph.algorithm.list;

import org.joseph.algorithm.common.ListNode;


/**
 * 链表反转算法
 */
public class Reverse {

    /**
     * 链表反转-递归方案
     */
    public ListNode solution(ListNode head) {
        if (null == head || null == head.next) return head;

        ListNode newHead = solution(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    /**
     * 链表反转-迭代方案
     */
    public ListNode solution1(ListNode head) {
        if (null == head || null == head.next) return head;

        ListNode curNode = head.next;
        ListNode preNode = head;
        ListNode nxtNode = null;

        while (true) {
            if(null == curNode) break;

            nxtNode = curNode.next;
            curNode.next = preNode;
            preNode = curNode;
            curNode = nxtNode;
        }
        head.next = null;
        return preNode;
    }

    /**
     * 链表反转-反转start开始end结束部分的链表
     */
    public ListNode reverseRange(ListNode start, ListNode end) {
        if(null == start || null == end || start == end) return start;

        ListNode curNode = start.next;
        ListNode preNode = start;
        ListNode nxtNode = null;

        while (curNode != end) {
            nxtNode = curNode.next;
            curNode.next = preNode;
            preNode = curNode;
            curNode = nxtNode;
        }
        return preNode;
    }

    /**
     * 链表反转-每次k个节点一组，旋转整个链表
     */
    public ListNode reverseGroup(ListNode head, int k) {
        if(head == null) return null;

        ListNode a = head, b = head;

        for (int i = 0; i < k; i++) {
            if(null == b) return head;
            b = b.next;
        }

        ListNode newHead = reverseRange(a, b);
        a.next = reverseGroup(b, k);
        return newHead;
    }

}
