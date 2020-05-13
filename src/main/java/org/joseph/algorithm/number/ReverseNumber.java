package org.joseph.algorithm.number;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ReverseNumber {


    public int reverse(int x) {
        int res = 0;
        while (x > 0) {
            res = res * 10 + x % 10;
            x /= 10;
        }
        return res;
    }

    public int reverse1(int x) {
        String[] o = String.valueOf(x).split("");
        String[] v = new String[o.length];

        int index = 0;
        while (x > 0) {
            int a = x % 10;
            x /= 10;

            v[index] = String.valueOf(a);
            index++;
        }
        return Integer.valueOf(Arrays.stream(v).collect(Collectors.joining()));
    }


    public static void main(String[] args) {
        System.out.println(new ReverseNumber().reverse(203212));
    }


}
