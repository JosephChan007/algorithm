package org.joseph.algorithm.string;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


/**
 * 字符全排列
 */
public class FullPermutation {

    public Set<String> result = new HashSet<>();

    public void swap(char[] array, int i, int j) {
        char temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public void doPermutation(char[] array, int start, int end) {
        if(start > end) return;

        if (start == end) {
            result.add(Arrays.toString(array));
        } else {
            for (int i = start; i <= end; i++) {
                swap(array, i, start);
                doPermutation(array, start + 1, end);
                swap(array, start, i);
            }
        }
    }

    public void solution(char[] array) {
        doPermutation(array, 0, array.length - 1);
    }


    public static void main(String[] args) {
        char[] array = {'a', 'b', 'a', 'c', 'd'};
        FullPermutation per = new FullPermutation();
        per.solution(array);
        per.result.stream().forEach(System.out::println);
    }

}
