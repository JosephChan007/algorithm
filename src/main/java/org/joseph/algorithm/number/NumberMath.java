package org.joseph.algorithm.number;

import java.util.HashMap;
import java.util.Map;

public class NumberMath {

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

    


}
