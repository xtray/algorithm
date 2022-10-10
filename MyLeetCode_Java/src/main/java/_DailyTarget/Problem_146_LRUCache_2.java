package _DailyTarget;

import java.util.HashMap;
import java.util.Map;

public class Problem_146_LRUCache_2 {

    // O(1)查是否在缓存, 使用map
    // 使用双向链表

    class LRUCache {

        private class DoubleLinkedList {
            private int key;
            private int val;
            private DoubleLinkedList pre;
            private DoubleLinkedList next;

            public DoubleLinkedList(int key, int value) {
                this.key = key;
                this.val = value;
            }
        }

        private DoubleLinkedList head;
        private DoubleLinkedList tail;
        Map<Integer, DoubleLinkedList> map;
        private int cap;

        public LRUCache(int capacity) {
            cap = capacity;
            map = new HashMap<>();
        }

        public int get(int key) {
            if (!map.containsKey(key)) return -1;
            DoubleLinkedList node = map.get(key);
            moveNodeToTail(node);
            return node.val;
        }

        public void put(int key, int value) {
            DoubleLinkedList node = null;
            if (map.containsKey(key)) {
                node = map.get(key);
                node.val = value;
                moveNodeToTail(node);
            } else {
                if (map.size() == cap) {
                    int removedKey = removeHead();
                    map.remove(removedKey);
                }
                node = new DoubleLinkedList(key, value);
                map.put(key, node);
                addNode(node);
            }

        }

        private int removeHead() {
            DoubleLinkedList node = head;
            if (head == tail) {
                head = null;
                tail = null;
                return node.key;
            }
            head = head.next;
            head.pre = null;
            node.next = null;
            return node.key;
        }

        private void moveNodeToTail(DoubleLinkedList node) {
            if (node == tail) return;
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
            tail.next = null;
        }

        private void addNode(DoubleLinkedList node) {
            if (head == null) {
                head = node;
                tail = node;
                return;
            }
            tail.next = node;
            node.pre = tail;
            tail = node;
            // tail.next = null;
        }
    }

}
