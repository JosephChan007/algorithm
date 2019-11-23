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

    public void doPermutation(char[] array, int left, int right) {
        if(left > right) return;

        if (left == right) {
            result.add(Arrays.toString(array));
        } else {
            for (int i = left; i <= right; i++) {
                swap(array, i, left);
                doPermutation(array, left + 1, right);
                swap(array, left, i);
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
