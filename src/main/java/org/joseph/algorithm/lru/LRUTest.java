package org.joseph.algorithm.lru;

import java.util.HashMap;
import java.util.Map;

public class LRUTest<K, V> {

    class Node<K, V> {
        private K key;
        private V val;
        private Node prev;
        private Node next;

        Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    private int capacity;
    private Map<K, Node> data;
    private Node first;
    private Node tail;

    LRUTest(int capacity) {
        this.capacity = capacity;
        this.data = new HashMap<>(capacity);
    }

    public void put(K key, V val) {
        Node node = data.get(key);
        if (null != node) {
            node.val = val;
            moveToHead(node);
        } else {
            node = new Node(key, val);
            if (data.size() == capacity) {
                removeLast();
            }
            addToHead(node);
            data.put(key, node);
        }

    }

    public V get(K key) {
        Node node = data.get(key);
        if (null == node) {
            return null;
        }
        moveToHead(node);
        return (V) node.val;
    }


    public void moveToHead(Node node) {
        if (first == node) {
            return;
        } else if (tail == node) {
            tail.prev.next = null;
            tail = tail.prev;

            node.next = first;
            node.prev = null;
            first.prev = node;
            first = node;

        } else {
            node.prev.next = node.next;
            node.next.prev = node.prev;

            node.next = first;
            node.prev = null;
            first.prev = node;
            first = node;
        }
    }

    public void addToHead(Node node) {
        if (data.isEmpty()) {
            first = node;
            tail = node;
            return;
        }
        node.next = first;
        first.prev = node;
        node.prev = null;
        first = node;
    }

    public void removeLast() {
        data.remove(tail.key);
        if (first == tail) {
            first = null;
            tail = null;
            return;
        }
        tail.prev.next = null;
        tail = tail.prev;
    }
}
