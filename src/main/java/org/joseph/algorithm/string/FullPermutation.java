package org.joseph.algorithm.string;

import java.util.*;


/**
 * 字符全排列
 */
public class FullPermutation {

    public Set<String> result = new HashSet<>();

    public void swap(String[] array, int i, int j) {
        String temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public void doPermutation(String[] array, int start, int end) {
        if(start > end) return;

        if (start == end) {
            result.add(String.join("", array));
        } else {
            for (int i = start; i <= end; i++) {
                swap(array, i, start);
                doPermutation(array, start + 1, end);
                swap(array, start, i);
            }
        }
    }

    public void solution(String[] array) {
        doPermutation(array, 0, array.length - 1);
    }


    public static void main(String[] args) {
        String[] array = "abacd".split("");
        FullPermutation per = new FullPermutation();
        per.solution(array);
        per.result.stream().forEach(System.out::println);
    }

}
