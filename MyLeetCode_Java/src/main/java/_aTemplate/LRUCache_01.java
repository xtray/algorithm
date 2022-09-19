package _aTemplate;

import java.util.HashMap;
import java.util.Map;

public class LRUCache_01 {


    public static class LRUCache {

        private static class Node {
            public int key; // NOTE: 从map删除头结点的时候使用
            public int val;
            public Node pre;
            public Node next;

            public Node(int k, int v) {
                key = k;
                val = v;
            }
        }

        // 自定义双向链表
        private Node head;
        private Node tail;
        private Map<Integer, Node> map; // 用map的size表示当前容量, 所以不用的要删除
        private int cap;

        // 把当前节点移动到双向链表尾部
        private void moveNodeToTail(Node node) {
            // if (head == tail || node == tail) {
            if (node == tail) {
                // 只有一个节点
                return;
            }
            if (node == head) {
                head = head.next;
                head.pre = null;
            } else { // 中间节点
                node.pre.next = node.next;
                node.next.pre = node.pre;
            }
            tail.next = node;
            node.pre = tail;
            tail = node;
        }

        private int removeHead() {
            if (head == null) return -1;
            int res = head.key; // NOTE: 返回头结点key, map删除用
            if (head == tail) {
                head = null;
                tail = null;
                return res;
            }
            head = head.next;
            head.pre = null;
            return res;
        }

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

        public LRUCache(int capacity) {
            cap = capacity;
            map = new HashMap<>();
        }

        public int get(int key) {
            int res = -1;
            if (map.containsKey(key)) {
                Node node = map.get(key);
                res = node.val;
                moveNodeToTail(node);
            }
            return res;
        }

        public void set(int key, int value) {
            if (map.containsKey(key)) { // 更新
                Node node = map.get(key);
                node.val = value;
                moveNodeToTail(node);
                return;
            }
            // 新增
            if (map.size() == cap) { // 空间满了, 删除头部最老的
                int removeKey = removeHead();
                map.remove(removeKey);
            }
            // 添加到双向队列尾部
            Node node = new Node(key, value);
            map.put(key, node);
            addNode(node);
        }
    }

    public static void main(String[] args) {

        LRUCache lruCache = new LRUCache(2);
        lruCache.set(1, 0);
        lruCache.set(2, 2);
        int res = lruCache.get(1); // 0
        System.out.println(res);
        lruCache.set(3, 3);
        res = lruCache.get(2);
        System.out.println(res); // -1
        lruCache.set(4, 4);
        res = lruCache.get(1); // -1
        System.out.println(res);
    }
}
