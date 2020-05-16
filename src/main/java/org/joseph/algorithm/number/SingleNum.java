package org.joseph.algorithm.number;


/**
 * 一个数组中，只有一个数字仅出现一次，其他数字均出现两次，找出这个数字
 */
public class SingleNum {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 4, 5, 3, 3, 4, 1, 2};
        System.out.println(singleNumber(nums));
    }

    /**
     * 思路：a^0 = a, a^a = 0
     */
    public static int singleNumber(int[] nums) {
        //不借助额外空间，得到只出现一次的数据
        int num = 0;
        for (int i = 0; i < nums.length; i++) {
            int tmp = num;
            num ^= nums[i];
            System.out.println(String.format("%s ^ %s = %s", tmp, nums[i], num));
        }
        return num;
    }

}
