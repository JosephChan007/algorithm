package org.joseph.algorithm.sort;

import java.util.Arrays;

/**
 * 数字数组快排序
 */
public class QuickSort {

    public void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public int partition(int[] array, int left, int right) {
        int index = left;
        int base = array[left];

        while (left < right) {
            while (left < right && base <= array[right]) {
                right--;
            }
            swap(array, index, right);
            index = right;

            while (left < right && base >= array[left]) {
                left++;
            }
            swap(array, index, left);
            index = left;
        }

        return index;
    }

    public void doSort(int[] array, int start, int end) {
        if (start < end) {
            int index = partition(array, start, end);
            doSort(array, start, index - 1);
            doSort(array, index + 1, end);
        }
    }

    public void solution(int[] array) {
        doSort(array, 0, array.length - 1);
    }

    public static void main(String[] args) {
        int[] array = {4, 3, 1, 8, 5, 9, 7, 1, 3, 0};
        new QuickSort().solution(array);
        System.out.println(Arrays.toString(array));
    }

}
