package org.joseph.algorithm.list;


/**
 * 给定给一个数组nums和滑动窗口大小k，找出所有滑动窗口里的最大值
 * 举例：
 *      输入：nums = [1, 3, 2, 3, 5, 3, 6, 7] 和 k=3, 输出：[3, 5, 5, 6, 7]
 */
public class SlideWinMaxVal {

    static class Node {
        int val;
        Node next;
        Node prev;

        public Node(int val) {
            this.val = val;
        }
    }

    static class Window {
        Node head;
        Node tail;
        int count;
        int size;

        public Window(int size) {
            this.size = size;
        }

        public Node maxNode() {
            int temp = 0;
            Node consur = this.head;
            Node res = null;
            while (consur != null) {
                if (consur.val > temp) {
                    temp = consur.val;
                    res = consur;
                }
                consur = consur.next;
            }
            return res;
        }

        public void remove(Node node) {
            node.prev = node.next;
            this.count--;
        }

        public void addToTail(Node node) {
            this.tail.next = node;
            node.prev = this.tail;
            this.tail = node;
            this.count++;
        }

        public int putAndGetMax(Node node) {
            if (this.count >= this.size) {
                Node max = this.maxNode();
                if (node.val > max.val) {
                    this.addToTail(node);
                    this.remove(max);
                    return node.val;
                } else {
                    return max.val;
                }
            } else if (this.count == 0) {
                this.head = node;
                this.tail = node;
                this.count++;
            } else if (this.count < this.size) {
                this.addToTail(node);
            }
            return -1;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 2, 3, 5, 3, 6, 7};
        Window win = new Window(3);
        for (int i = 0; i < nums.length; i++) {
            Node n = new Node(nums[i]);
            int r = win.putAndGetMax(n);
            if (r == -1) {
                continue;
            }
            System.out.println(r);
        }
    }

}
