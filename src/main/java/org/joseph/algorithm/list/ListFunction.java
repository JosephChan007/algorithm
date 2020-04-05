package org.joseph.algorithm.list;

import org.joseph.algorithm.common.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListFunction {

    public static ListNode createList(int[] nums) {
        if(nums.length == 0) return null;

        ListNode head = null, cursor = null;
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                head = new ListNode(nums[i]);
                cursor = head;
            } else {
                cursor.next = new ListNode(nums[i]);
                cursor = cursor.next;
            }
        }
        return head;
    }

    public static void printList(ListNode head) {
        List<Integer> result = new ArrayList<>();
        while (null != head) {
            result.add(head.val);
            head = head.next;
        }
        System.out.println(Arrays.toString(result.toArray()));
    }

    public static ListNode reverse(ListNode head) {
        if (null == head || null == head.next) return head;

        ListNode newHead = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    public static int length(ListNode head) {
        if(null == head) return 0;
        int length = 0;
        while (null == head) {
            length++;
            head = head.next;
        }
        return length;
    }

}
