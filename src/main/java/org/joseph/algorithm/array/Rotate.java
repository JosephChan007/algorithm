package org.joseph.algorithm.array;

import java.util.Arrays;

public class Rotate {


    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * 反转数组
     */
    public static void rotate(int[] nums, int k) {
        int lastIndex = nums.length - 1;
        for (int i = 1; i <= k; i++) {
            int temp = nums[lastIndex];
            for (int j = lastIndex - 1; j >= i - 1; j--) {
                nums[j + 1] = nums[j];
            }
            nums[i - 1] = temp;
        }
    }

    /**
     * 反转数组1
     */
    public static void rotate1(int[] nums) {
        int mid = nums.length / 2 - 1;
        for (int i = 0; i <= mid; i++) {
            int end = nums.length - 1 - i;
            swap(nums, i, end);
        }
    }

    /**
     * 数字反转
     */
    public static int rotate2(int num) {
        int res = 0;
        while (num > 0) {
            int i = num % 10;
            num /= 10;
            res = res * 10 + i;
        }
        return res;
    }


    public static void main(String[] args) {
        /*
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        Rotate.rotate1(nums);
        System.out.println(Arrays.toString(nums));
        */

        System.out.println(Rotate.rotate2(1023));
    }
}
