package org.joseph.algorithm.lru;

import java.util.HashMap;
import java.util.Map;

/**
 * LRU缓存算法
 *      map：保存数据
 *      Node：保存访问先后次序
 */
public class LRUCache {

    // 双向链表节点定义
    class Node {
        int key;
        int val;
        Node prev;
        Node next;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    private int capacity;

    // 保存链表的头节点和尾节点
    private Node first;
    private Node last;

    private Map<Integer, Node> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>(capacity);
    }

    public int get(int key) {
        Node node = map.get(key);
        //为空返回-1
        if (node == null) {
            return -1;
        }
        moveToHead(node);
        return node.val;
    }

    public void put(int key, int value) {
        Node node = map.get(key);

        if (node == null) {
            node = new Node(key, value);

            if(map.size() == capacity) {
                removeLast();
            }

            addToHead(node);
            map.put(key, node);
        } else {
            node.val = value;
            moveToHead(node);
        }
    }

    private void moveToHead(Node node) {
        if (node == first) {
            return;
        } else if (node == last) {
            last.prev.next = null;
            last = last.prev;
        } else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        node.prev = first.prev;
        node.next = first;
        first.prev = node;
        first = node;
    }

    private void addToHead(Node node) {
        if (map.isEmpty()) {
            first = node;
            last = node;
        } else {
            node.next = first;
            first.prev = node;
            first = node;
        }
    }

    private void removeLast() {
        map.remove(last.key);
        Node prevNode = last.prev;
        if (prevNode != null) {
            prevNode.next = null;
            last = prevNode;
        }
    }


}
