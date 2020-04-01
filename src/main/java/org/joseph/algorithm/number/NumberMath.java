package org.joseph.algorithm.number;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class NumberMath {

    /**
     * 计算数组中两数和为target的组合
     */
    public int[] twoSum(int[] array, Integer target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            if (map.containsKey(array[i])) {
                return new int[]{map.get(array[i]), i};
            }
            map.put(target - array[i], i);
        }
        return new int[]{-1, -1};
    }


    /**
     * 计算数字数组组成的最大数字串
     */
    public String maxNumberStr(Integer[] nums) {
        Arrays.sort(nums, (a, b) -> {
            String s1 = String.valueOf(a) + String.valueOf(b);
            String s2 = String.valueOf(b) + String.valueOf(a);
            return s2.compareTo(s1);
        });

        System.out.println(Arrays.toString(nums));

        String sss = Arrays.stream(nums).map(String::valueOf).collect(Collectors.joining());
        return sss;
    }

    public static void main(String[] args) {
        Integer[] nums = {0,3,4,9,1,91};
        String s = new NumberMath().maxNumberStr(nums);
        System.out.println(s);
    }

}
