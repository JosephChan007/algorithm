package org.joseph.algorithm.list;

/**
 * 双向链表的快速排序
 */
public class QuickSortForDoublyList {

    static class Node {
        int value;
        Node pre;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }

    /**
     * 得到链表的尾节点
     */
    public static Node getTail(Node head) {
        Node current = head;
        while (null != current.next) {
            current = current.next;
        }
        return current;
    }

    /**
     * 取分区节点
     */
    public static Node partition(Node start, Node end) {
        int base = start.value;
        while (start != end) {
            while(start != end && base >= end.value) {
                end = end.pre;
            }
            start.value = end.value;
            while(start != end && base <= start.value) {
                start = start.next;
            }
            end.value = start.value;
        }
        start.value = base;
        return start;
    }

    /**
     * 参数为头节点和尾节点
     */
    public static void sort(Node head, Node tail) {
        if (head == null || tail == null || head == tail || head.next == tail) {
            return;
        }

        if (head != tail) {
            Node mid = partition(head, tail);
            sort(head, mid);
            sort(mid.next, tail);
        }
    }

    /**
     * 快排序一个双向链表
     */
    public static void solution(Node head) {
        Node tail = getTail(head);
        sort(head, tail);
    }

}
