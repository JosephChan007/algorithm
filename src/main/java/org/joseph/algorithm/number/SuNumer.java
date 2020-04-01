package org.joseph.algorithm.number;

import java.util.Arrays;

public class SuNumer {

    public boolean isSuOk(int num) {
        for (int i = 2; i < num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    public boolean isSuOk1(int num) {
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    public void solution(int[] numbers) {
        for (int i : numbers) {
            if (isSuOk(i)) System.out.println(i);
        }
    }

    public void solution1(int num) {
        Boolean[] flag = new Boolean[num];
        Arrays.fill(flag, true);

        for (int i = 2; i <= num; i++) {
            for (int j = 2 * i; j <= num; j+=i) {
                if(flag[j-1]) flag[j-1] = false;
            }
        }

        for (int i = 1; i <= num; i++) {
            if(flag[i-1]) System.out.println(i);
        }
    }



    public static void main(String[] args) {
        int[] nums = {1,2,4,6,9,15,17,34,32,31,37};
        new SuNumer().solution(nums);
        System.out.println("=====================");
        new SuNumer().solution1(43);
    }




}
