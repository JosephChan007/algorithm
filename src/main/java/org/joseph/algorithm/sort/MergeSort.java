package org.joseph.algorithm.sort;

public class MergeSort {

    /**
     * 数组合并并排序
     */
    public static int[] merge(int[] nums1, int m, int[] nums2, int n) {
        int len = m + n;
        int[] result = new int[len];
        int i = 0, j = 0, index = 0;
        while(index < len) {
            if(nums1[i] < nums2[j]) {
                result[index++] = nums1[i++];
            } else if(nums1[i] >= nums2[j]) {
                result[index++] = nums2[j++];
            }
        }
        return result;
    }

}
