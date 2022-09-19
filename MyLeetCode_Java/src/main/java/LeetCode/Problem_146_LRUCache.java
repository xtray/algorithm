package LeetCode;

import java.util.HashMap;
import java.util.Map;

public class Problem_146_LRUCache {

    static class LRUCache {

        private static class Node {
            public int key;
            public int val;
            public Node pre;
            public Node next;

            public Node(int k, int v) {
                key = k;
                val = v;
            }
        }

        private static class DoubleLinkedList {
            private Node head, tail;

            public DoubleLinkedList() {
                head = null;
                tail = null;
            }

            private void addNode(Node node) {
                if (node == null) return;
                if (head == null) {
                    head = node;
                    tail = node;
                } else {
                    tail.next = node;
                    node.pre = tail;
                    tail = node;
                }
            }

            private int removeHead() {
                if (head == null) return -1; // 题目中有效value>=0
                Node res = head;
                if (head == tail) { // 链表中只有一个节点的时候
                    head = null;
                    tail = null;
                } else {
                    head = head.next;
                    head.pre = null;
                    res.next = null;
                }
                return res.key; // NOTE: 这里返回的是key, 不是value
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

        private Map<Integer, Node> keyNodeMap;
        private int capacity;
        private DoubleLinkedList nodeList;

        public LRUCache(int capacity) {
            if (capacity < 1) {
                throw new RuntimeException("should be more than 0.");
            }
            this.capacity = capacity;
            keyNodeMap = new HashMap<>();
            nodeList = new DoubleLinkedList();
        }

        public int get(int key) {
            int res = -1;
            if (keyNodeMap.containsKey(key)) {
                Node node = keyNodeMap.get(key);
                res = node.val;
                nodeList.moveNodeToTail(node);
            }
            return res;
        }

        public void put(int key, int value) {
            Node node = null;
            if (keyNodeMap.containsKey(key)) {
                node = keyNodeMap.get(key);
                node.val = value;
                nodeList.moveNodeToTail(node);
            } else {
                if (keyNodeMap.size() == capacity) {
                    int removed = nodeList.removeHead();
                    keyNodeMap.remove(removed);
                }
                node = new Node(key, value);
                keyNodeMap.put(key, node);
                nodeList.addNode(node);
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
