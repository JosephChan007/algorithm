package org.joseph.algorithm.list;

import org.joseph.algorithm.common.ListNode;

public class LinkMath {

    /**
     * 将节点newNode查入到已拍好序的列表head中
     */
    public ListNode insert(ListNode head, ListNode newNode) {
        if(head == null) return head;
        if(head.getVal() >= newNode.getVal()) {
            newNode.setNext(head);
            return newNode;
        } else {
            ListNode cursor = head;
            ListNode preCursor = head;
            while(true) {
                if(cursor == null) break;
                if(cursor.getVal() < newNode.getVal()) {
                    preCursor = cursor;
                    cursor = cursor.getNext();
                } else {
                    break;
                }
            }
            newNode.setNext(cursor);
            preCursor.setNext(newNode);
            return head;
        }
    }

    /**
     * 排序链表：插入排序
     */
    public ListNode sortList(ListNode head) {
        if(head == null) return null;
        if(head.getNext() == null) return head;

        ListNode newHead = new ListNode(head.getVal());
        ListNode cursor = head.getNext();
        while(cursor != null) {
            newHead = insert(newHead, new ListNode(cursor.getVal()));
            cursor = cursor.getNext();
        }
        return newHead;
    }

    /**
     * 合并两个链表
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null && l2 == null) return null;
        if(l1 == null) return l2;
        if(l2 == null) return l1;

        ListNode newHead = l1;
        ListNode cursor = l2;
        while(cursor != null) {
            newHead = insert(newHead, new ListNode(cursor.getVal()));
            cursor = cursor.getNext();
        }
        return newHead;
    }

    /**
     * 合并K个链表
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0) return null;
        if(lists.length == 1) return lists[0];

        ListNode mergeNode = null;
        int index = 0;
        for(int i=0; i<lists.length; i++) {
            ListNode node = lists[i];
            if(node != null) {
                mergeNode = node;
                index = i;
                break;
            }
        }
        if(index == lists.length -1) return mergeNode;

        ListNode newHead = mergeNode;
        for(int i=index + 1; i<lists.length; i++) {
            ListNode node = lists[i];
            if(node != null) {
                newHead = mergeTwoLists(newHead, node);
            }
        }
        return newHead;
    }

    /**
     * 反转链表（递归法）
     */
    private ListNode reverse(ListNode head) {
        if(head == null || head.getNext() == null) return head;
        ListNode newHead = reverse(head.getNext());
        head.getNext().setNext(head);
        head.setNext(null);
        return newHead;
    }

    /**
     * 反转链表（非递归法）
     */
    public ListNode reverseList(ListNode head) {
        // 迭代算法
        if(head == null) return head;

        ListNode preNode = head;
        ListNode currNode = head.getNext();
        ListNode tmpNode;

        while(true) {
            if(currNode == null) break;
            tmpNode = currNode.getNext();
            currNode.setNext(preNode);

            preNode = currNode;
            currNode = tmpNode;
        }

        head.setNext(null);
        return preNode;
    }

    /**
     * 旋转转链表：
     * 输入: 1->2->3->4->5->NULL, k = 2
     * 输出: 4->5->1->2->3->NULL
     */
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.getNext() == null || k == 0) return head;

        int len = 1;
        ListNode point = head;
        while(point != null && point.getNext() != null) {
            point = point.getNext();
            len++;
        }


        int mov = len - (k % len) - 1;
        point.setNext(head);
        point = head;
        for(int i=0; i<mov; i++) {
            point = point.getNext();
        }
        ListNode newHead = point.getNext();
        point.setNext(null);
        return newHead;
    }

    /**
     * 探测链表是否有环，有环时，返回环的起始点，否则返回null
     */
    public ListNode detectCycle(ListNode head) {
        if(head == null || head.getNext() == null) return null;
        ListNode spoint = head;
        ListNode dpoint = head;
        while(true) {
            if(dpoint == null || dpoint.getNext() == null || dpoint.getNext().getNext() == null) return null;
            spoint = spoint.getNext();
            dpoint = dpoint.getNext().getNext();
            if(spoint == dpoint) {
                ListNode tmp = head;
                if(head == dpoint) return dpoint;
                while(true) {
                    dpoint = dpoint.getNext();
                    tmp = tmp.getNext();
                    if(dpoint == tmp) {
                        return dpoint;
                    }
                }
            }
        }
    }

    /**
     * 探测两个链表是否相交，相交返回交点，否则返回null
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) return null;

        ListNode tmpA = headA;
        ListNode tmpB = headB;

        int lenA = 0;
        while(tmpA != null) {
            tmpA = tmpA.getNext();
            ++lenA;
        }

        int lenB = 0;
        while(tmpB != null) {
            tmpB = tmpB.getNext();
            ++lenB;
        }

        tmpA = headA;
        tmpB = headB;
        if(lenA > lenB) {
            for(int i=0; i< lenA - lenB; i++) {
                tmpA = tmpA.getNext();
            }
        } else {
            for(int i=0; i< lenB - lenA; i++) {
                tmpB = tmpB.getNext();
            }
        }

        while(tmpA != null && tmpB != null) {
            if(tmpA == tmpB) {
                return tmpA;
            } else {
                tmpA = tmpA.getNext();
                tmpB = tmpB.getNext();
            }
        }
        return null;
    }

}
