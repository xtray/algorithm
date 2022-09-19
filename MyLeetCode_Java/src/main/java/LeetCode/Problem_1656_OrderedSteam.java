package LeetCode;

import java.util.*;

public class Problem_1656_OrderedSteam {

    // 只需要next指针即可, 不需要pre
    static class OrderedStream {
        public class Node {
            public String value;
            public Node next;

            public Node(String value) {
                this.value = value;
            }
        }

        private Map<Integer, Node> headMap;
        private Map<Integer, Node> tailMap;

        private int size;
        private int capacity;
        private int wait;

        public OrderedStream(int n) {
            capacity = n;
            wait = 1;
            headMap = new HashMap<>();
            tailMap = new HashMap<>();
        }

        public List<String> insert(int idKey, String value) {

            Node node = new Node(value);
            headMap.put(idKey, node);
            tailMap.put(idKey, node);

            // 找有没有以id-1结尾的
            if (tailMap.containsKey(idKey - 1)) {
                Node pre = tailMap.get(idKey - 1);
                pre.next = node;
                headMap.remove(idKey);
                tailMap.remove(idKey - 1);
            }

            // 找有没有以idKey+1开头的
            if (headMap.containsKey(idKey + 1)) {
                node.next = headMap.get(idKey + 1);
                headMap.remove(idKey + 1);
                tailMap.remove(idKey);
            }

            List<String> ans = new ArrayList<>();
            if (idKey == wait) {
                Node cur = headMap.get(idKey);
                while (cur != null) {
                    ans.add(cur.value);
                    cur = cur.next;
                    wait++;
                }
                headMap.remove(idKey);
                tailMap.remove(wait - 1);
            }
            return ans;
        }
    }


    static class OrderedStream2 {
        private String[] slot;
        private int wait;
        private int cap;

        public OrderedStream2(int n) {
            cap = n + 1;
            slot = new String[cap];
            wait = 1;
        }

        public List<String> insert(int key, String value) {
            slot[key] = value;
            List<String> ans = new ArrayList<>();
            // wait最后++后可能超过n
            while (wait < cap && slot[wait] != null) {
                ans.add(slot[wait++]);
            }
            return ans;
        }
    }


    public static void main(String[] args) {
        int n = 1000;
        // OrderedStream sl = new OrderedStream(n);
        OrderedStream2 sl = new OrderedStream2(n);
        List<String> list = sl.insert(9, "nghbm99");
        System.out.println(list);
        list = sl.insert(7, "hgeob77");
        System.out.println(list);
        list = sl.insert(6, "mwlrz66");
        System.out.println(list);
        list = sl.insert(4, "oalee44");
        System.out.println(list);
        list = sl.insert(2, "bouhq22");
        System.out.println(list);
        list = sl.insert(1, "mnknb11");
        System.out.println(list);
        list = sl.insert(5, "qkxbj55");
        System.out.println(list);
        list = sl.insert(8, "iydkk88");
        System.out.println(list);
        list = sl.insert(3, "oqdnf33");
        System.out.println(list);
    }

}
