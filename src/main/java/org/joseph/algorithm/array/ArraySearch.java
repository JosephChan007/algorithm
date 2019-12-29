package org.joseph.algorithm.array;

public class ArraySearch {

    /**
     * 二分法查找，返回下标，否则返回-1
     */
    public int binaryFind(int[] array, int start, int end, int value) {
        if(value < array[start] || value > array[end]) return -1;
        if(value == array[start]) return start;
        if(value == array[end]) return end;
        int mid = Double.valueOf(Math.ceil(Double.valueOf(start) + Double.valueOf(end) / 2)).intValue();
        if(value == array[mid]) return mid;
        if(value < array[mid]) return binaryFind(array, start, mid - 1, value);
        if(value > array[mid]) return binaryFind(array, mid + 1, end, value);
        return -1;
    }



}
