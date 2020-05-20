package org.joseph.algorithm.string;


/**
 * 求最长公共子串
 * 题目：给定一个query和一个text，均由小写字母组成。要求在text中找出以同样的顺序连续出现在query中的最长连续字母序列的长度。
 * 例如，query为"acbac"，text为"acaccbabb"，那么text中的"cba"为最长的联系出现在query中的字母序列，因此，返回结果应该为其长度3。请注意程序效率。
 *
 * 思路：
 *    对长度小串用窗口欢动方法得到子串，然后看该子串是为长串的子串。
 */

public class CommonSubString {

    public static String solution(String str1, String str2) {
        int left = 0, right = 1;
        String result = "";
        String str = (str1.length() < str2.length()) ? str1 : str2;
        while (right <= str.length()) {
            String s = str.substring(left, right);
            System.out.println("%%%: " + s + " : " + left + " : " + right);
            if (str2.indexOf(s) != -1) {
                if (s.length() > result.length()) {
                    result = s;
                }
                right++;
            } else {
                left++;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String str1 = "acbax";
        String str2 = "defghhkli";
        String s = CommonSubString.solution(str1, str2);
        System.out.println(s);
    }


}
