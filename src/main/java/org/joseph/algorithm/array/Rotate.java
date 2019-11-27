package org.joseph.algorithm.array;

public class Rotate {

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

}
