package _DailyTarget;

import java.util.HashMap;
import java.util.Map;

public class Problem_146_LRUCache {

    // O(1)查是否在缓存, 使用map
    // 使用双向链表

    class LRUCache {

        class Node {
            private Node pre;
            private Node next;
            private int key;
            private int val;

            public Node(int k, int v) {
                key = k;
                val = v;
            }

        }

        private Node head;
        private Node tail;
        private int cap;

        private Map<Integer, Node> map = new HashMap<>();

        public LRUCache(int capacity) {
            cap = capacity;
        }

        public int get(int key) {
            if (!map.containsKey(key)) return -1;
            Node node = map.get(key);
            moveNodeToTail(node);
            return node.val;
        }

        private void moveNodeToTail(Node node) {
            if (node == tail) {
                return;
            }
            if (node == head) {
                head = head.next;
                head.pre = null;
            } else {
                node.pre.next = node.next;
                if (node.next != null) {
                    node.next.pre = node.pre;
                }
            }
            tail.next = node;
            node.pre = tail;
            tail = node;
        }

        public void put(int key, int value) {
            Node node = null;
            if (map.containsKey(key)) {
                node = map.get(key);
                node.val = value;
                moveNodeToTail(node);
                return;
            }
            node = new Node(key, value);
            if (map.size() == cap) {
                int removed = removeHead();
                map.remove(removed);
            }
            addNode(node);
            map.put(key, node);

        }

        private void addNode(Node node) {
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
            int ret = head.key;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                Node next = head.next;
                head.next = null;
                head = next;
                next.pre = head;
            }
            return ret;
        }
    }

}
