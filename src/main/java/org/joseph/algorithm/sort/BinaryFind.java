package org.joseph.algorithm.sort;

public class BinaryFind {

    /**
     * 二分法查找，返回下标，否则返回-1
     */
    public int dichotomySearch(int[] source, int start, int end, int value) {
        if (value > source[end] || value < source[start]) return -1;
        if (value == source[start]) return start;
        if (value == source[end]) return end;
        Double dStart = Double.valueOf(start);
        Double dEnd = Double.valueOf(end);
        int mid = Double.valueOf(Math.ceil((dEnd + dStart) / 2)).intValue();
        if (source[mid] == value) return mid;
        if (source[mid] > value) return dichotomySearch(source, start, mid - 1, value);
        if (source[mid] < value) return dichotomySearch(source, mid + 1, end, value);
        return -1;
    }


}
