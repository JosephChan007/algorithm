package org.joseph.algorithm.sort;

import java.util.Arrays;
import java.util.Random;


/**
 * 划分基数用数组内的随机数，可以减少划片次数，从而递归次数也会减少
 */
public class QuickSortPlus {

    public void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public int getIndex(int[] nums, int left, int right) {

        /**
         * 划分基数是随机数，可以减少划片次数
         */
        Random random = new Random();
        int baseIdx = random.nextInt(right) % (right - left + 1) + left;
        int base = nums[baseIdx];

        System.out.println(baseIdx);

        while (left < right) {
            if (left < right && nums[right] >= base) {
                right--;
            }
            swap(nums, right, baseIdx);
            baseIdx = right;


            if (left < right && nums[left] <= base) {
                left++;
            }
            swap(nums, left, baseIdx);
            baseIdx = left;
        }

        return baseIdx;
    }

    public void sort(int[] nums, int left, int right) {
        if (left < right) {
            int index = getIndex(nums, left, right);
            sort(nums, left, index - 1);
            sort(nums, index + 1, right);
        }
    }

    public void solution(int[] nums) {
        sort(nums, 0, nums.length - 1);
    }

    public static void main(String[] args) {
        int[] nums = {4,3,8,1,2,0,9,11,34,67,36};
        new QuickSortPlus().solution(nums);
        System.out.println(Arrays.toString(nums));
    }



}
