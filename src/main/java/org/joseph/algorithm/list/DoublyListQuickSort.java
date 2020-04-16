package org.joseph.algorithm.list;

public class DoublyListQuickSort {

    static class Node {
        int value;
        Node pre;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }

    /**
     * 参数为头节点和尾节点
     */
    public static void quickSort(Node head, Node tail) {
        if (head == null || tail == null || head == tail || head.next == tail) {
            return;
        }

        if (head != tail) {
            Node mid = getMid(head, tail);
            quickSort(head, mid);
            quickSort(mid.next, tail);
        }
    }

    public static Node getMid(Node start, Node end) {
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


}
