package org.joseph.algorithm.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Combination {

    /**
     * 计算numbers的长度为len的子数组个数
     */
    public static Integer toSubArrayCount(Integer[] numbers, Integer len) {
        String binaryTxt = Stream.of(numbers).map(i -> i = 1).map(String::valueOf).collect(Collectors.joining());
        Long count = IntStream.range(1, Integer.valueOf(binaryTxt, 2)).boxed()
                .map(Integer::bitCount).filter(i -> i.equals(len)).count();
        return count.intValue();
    }

    /**
     * 计算子数组的和
     */
    public static Integer calcSubArraySum(Integer[] numbers) {
        String binaryTxt = Stream.of(numbers).map(i -> i = 1).map(String::valueOf).collect(Collectors.joining());
        return IntStream.range(1, Integer.valueOf(binaryTxt, 2)).boxed()
                .map(Integer::toBinaryString).map(s -> s.split(""))
                .map(s -> Arrays.stream(s).map(Integer::valueOf).collect(Collectors.toList()))
                .map(i -> IntStream.range(0, i.size()).map(index -> i.get(index) * numbers[index]).reduce(0, Integer::sum))
                .reduce(0, Integer::sum);
    }

    /**
     * 计算子数组中和为k的那些子数组
     */
    public static List<String> calcSubArraySum(Integer[] numbers, Integer k) {
        String binaryTxt = Stream.of(numbers).map(i -> i = 1).map(String::valueOf).collect(Collectors.joining());
        List<List<Integer>> ins1 = IntStream.range(1, Integer.valueOf(binaryTxt, 2)).boxed()
                .map(Integer::toBinaryString).map(s -> {
                    StringBuffer buffer = new StringBuffer();
                    IntStream.rangeClosed(0, numbers.length - s.length() - 1).forEach(index -> buffer.append("0"));
                    buffer.append(s);
                    return buffer.toString();
                }).map(s -> s.split("")).map(s -> Arrays.stream(s).map(Integer::valueOf).collect(Collectors.toList()))
                .collect(Collectors.toList());
        System.out.println(ins1);

        List<List<Integer>> ins3 = ins1.stream()
                .filter(arr -> IntStream.rangeClosed(0, arr.size() - 1).map(index -> arr.get(index) * numbers[index]).reduce(0, Integer::sum) == k)
                .collect(Collectors.toList());
        System.out.println(ins3);

        List<String> result = new ArrayList<>();
        ins3.stream().forEach(arr -> {
            List<Integer> list = IntStream.rangeClosed(0, arr.size() - 1).filter(index -> arr.get(index) != 0).map(index -> numbers[index]).boxed().collect(Collectors.toList());
            result.add(list.stream().map(String::valueOf).collect(Collectors.joining()));
        });

        return result;
    }


}
