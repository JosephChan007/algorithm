package org.joseph.algorithm.list;

import java.util.LinkedList;


/**
 * 链表快排序
 */
public class QuickSort {

    public void swap(LinkedList<Integer> list, int i, int j) {
        Integer temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    public int getIndex(LinkedList<Integer> list, int left, int right) {
        int index = left;
        int base = list.get(left);

        while (left < right) {
            while (left < right && list.get(right) >= base) {
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

    public void doSort(LinkedList<Integer> list, int start, int end) {
        if (start < end) {
            int index = getIndex(list, start, end);
            doSort(list, start, index - 1);
            doSort(list, index + 1, end);
        }
    }

    public void solution(LinkedList<Integer> list) {
        doSort(list, 0, list.size() - 1);
    }


}
