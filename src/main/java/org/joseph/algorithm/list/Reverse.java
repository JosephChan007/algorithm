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
        if (null == head || null == head.getNext()) {
            return head;
        }

        ListNode newHead = solution(head.getNext());
        head.getNext().setNext(head);
        head.setNext(null);
        return newHead;
    }

    /**
     * 迭代方案
     */
    public ListNode solution1(ListNode head) {
        if (null == head || null == head.getNext()) {
            return head;
        }

        ListNode curNode = head.getNext();
        ListNode preNode = head;
        ListNode tmpNode = null;

        while (true) {
            if(null == curNode) break;

            tmpNode = curNode.getNext();
            curNode.setNext(preNode);
            preNode = curNode;
            curNode = tmpNode;
        }
        head.setNext(null);
        return preNode;
    }

}
