package _Simulation;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Problem_146_LRUCache {

    static class LRUCache {

        private static class Node {
            private int key; // NOTE: 从map删除头结点的时候使用
            private int val;
            private Node pre;
            private Node next;

            public Node(int k, int v) {
                key = k;
                val = v;
            }
        }

        private static class DoubleLinkedList {
            private Node head, tail;

            private void addNode(Node node) {
                if (head == null) {
                    head = node;
                    tail = node;
                    return;
                }
                tail.next = node;
                node.pre = tail;
                tail = node;
            }

            private int removeHead() {
                if (head == null) return -1;
                int res = head.key;
                if (head == tail) {
                    head = null;
                    tail = null;
                    return res;
                }
                head = head.next;
                head.pre = null;
                return res;
            }

            private void moveNodeToTail(Node node) {
                if (node == tail) return;
                if (node == head) {
                    head = head.next;
                    head.pre = null;
                } else {
                    Node pre = node.pre;
                    Node next = node.next;
                    pre.next = next;
                    next.pre = pre;
                }
                tail.next = node;
                node.pre = tail;
                tail = node;
            }
        }

        private Map<Integer, Node> map;
        private int cap;
        private DoubleLinkedList myCache;

        public LRUCache(int capacity) {
            cap = capacity;
            map = new HashMap<>();
            myCache = new DoubleLinkedList();
        }

        public int get(int key) {
            int res = -1;
            if (map.containsKey(key)) {
                Node node = map.get(key);
                res = node.val;
                myCache.moveNodeToTail(node);
            }
            return res;
        }

        public void put(int key, int value) {
            Node node = null;
            if (map.containsKey(key)) {
                node = map.get(key);
                node.val = value;
                myCache.moveNodeToTail(node);
            } else {
                if (map.size() == cap) {
                    int val = myCache.removeHead();
                    map.remove(val);
                }
                node = new Node(key, value);
                map.put(key, node);
                myCache.addNode(node);
            }
        }
    }

    public static void main(String[] args) {

        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 0);
        lruCache.put(2, 2);
        int res = lruCache.get(1);
        System.out.println(res);
        lruCache.put(3, 3);
        res = lruCache.get(2);
        System.out.println(res);
        lruCache.put(4, 4);
        res = lruCache.get(1);
        System.out.println(res);
    }
}
