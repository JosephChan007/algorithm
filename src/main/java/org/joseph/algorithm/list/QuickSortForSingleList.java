package org.joseph.algorithm.list;

public class QuickSortForSingleList {

    static class Node {
        int val;
        Node next;

        public Node(int value) {
            this.val = value;
        }
    }

    private static void swap(Node node1, Node node2) {
        int temp = node1.val;
        node1.val = node2.val;
        node2.val = temp;
    }

    private static Node partition(Node start, Node end) {
        int baseVal = start.val;
        Node base = start;
        Node cur = start.next;

        // 快速排序之单向划分方式
        while (cur != end) {
            if (cur.val < baseVal) {
                base = base.next;
                swap(base, cur);
            }
            cur = cur.next;
        }

        swap(base, start);
        return base;
    }

    private static void sort(Node start, Node end) {
        if (start != end && start.next != end) {
            Node p = partition(start, end);
            sort(start, p);
            sort(p.next, end);
        }
    }

    public static void solution(Node head) {
        if (head == null || head.next == null) {
            return;
        }
        sort(head, null);
    }


}
