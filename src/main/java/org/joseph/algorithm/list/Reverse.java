package org.joseph.algorithm.list;

import org.joseph.algorithm.common.Node;


/**
 * 链表反转算法
 */
public class Reverse {

    /**
     * 递归方案
     */
    public Node solution(Node head) {
        if (null == head || null == head.next) {
            return head;
        }

        Node newHead = solution(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    /**
     * 迭代方案
     */
    public Node solution1(Node head) {
        if (null == head || null == head.next) {
            return head;
        }

        Node curNode = head.next;
        Node preNode = head;
        Node tmpNode;

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
