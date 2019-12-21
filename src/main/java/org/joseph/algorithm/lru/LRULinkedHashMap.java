package org.joseph.algorithm.lru;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRULinkedHashMap<K, V> extends LinkedHashMap<K, V> {

    private int capacity;

    public LRULinkedHashMap(int capacity) {
        /**
         * 参数1：初始化大小
         * 参数2：负载因子
         * 参数3：true-按访问先后排序；false-不按访问先后排序
         */
        super(16, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    public boolean removeEldestEntry(Map.Entry<K, V> entry) {
        return size() > capacity;
    }

}
