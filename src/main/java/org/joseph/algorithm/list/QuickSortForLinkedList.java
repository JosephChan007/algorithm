package org.joseph.algorithm.list;

import java.util.LinkedList;


/**
 * 单链表快排序：链表是LinkedList结构
 */
public class QuickSortForLinkedList {

    public void swap(LinkedList<Integer> list, int i, int j) {
        Integer temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    /**
     * 分区节点索引
     */
    public int partition(LinkedList<Integer> list, int left, int right) {
        int index = left;
        int base = list.get(left);

        while (left < right) {
            while (left < right && list.get(right) <= base) {
                right--;
            }
            swap(list, index, right);
            index = right;

            while (left < right && list.get(left) >= base) {
                left++;
            }
            swap(list, index, left);
            index = left;
        }
        return index;
    }

    /**
     * 快排核心算法
     */
    public void sort(LinkedList<Integer> list, int start, int end) {
        if (start < end) {
            int index = partition(list, start, end);
            sort(list, start, index - 1);
            sort(list, index + 1, end);
        }
    }

    /**
     * 链表快排算法
     */
    public void solution(LinkedList<Integer> list) {
        sort(list, 0, list.size() - 1);
    }


}
