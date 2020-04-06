package org.joseph.algorithm.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 字符串算法
 */
public class StringMath {

    /**
     * 找出字符串中第一个不重复的字符
     */
    public int findUniqueCharIndex(String s) {
        int result = s.length();
        for (char ch ='a'; ch <='z'; ch++) {
            int start = s.indexOf(ch);
            int end = s.lastIndexOf(ch);
            if (start == end && start != -1) {
                result = Math.min(result, start);
            }
        }
        if (result == s.length()) {
            result = -1;
        }
        return result;
    }

    /**
     * 计算字符串中子串的长度为len的个数
     */
    public Integer toSubArrayCount(String str, Integer len) {
        String bin = Stream.of(str.split("")).map(i -> i = "1").collect(Collectors.joining());
        Long count = IntStream.range(1, Integer.valueOf(bin, 2)).map(Integer::bitCount).filter(i -> i == len).count();
        return count.intValue();
    }


    /**
     * 最长回文串-从中心向两端扩散
     */
    public String cycleString(String s, int l, int r) {
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--; r++;
        }
        return s.substring(l + 1, r);
    }

    /**
     * 最长回文串
     */
    public String doCycleString(String s) {
        String result = "";
        for (int i = 0; i < s.length(); i++) {
            String s1 = cycleString(s, i, i);
            String s2 = cycleString(s, i, i + 1);

            result = s1.length() > result.length() ? s1 : result;
            result = s2.length() > result.length() ? s2 : result;
        }
        return result;
    }

    public static void main(String[] args) {
        String s = "ddaaccaaddcc";
        System.out.println(new StringMath().doCycleString(s));
    }

}
