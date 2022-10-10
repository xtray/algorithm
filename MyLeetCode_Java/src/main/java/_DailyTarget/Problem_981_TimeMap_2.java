package _DailyTarget;

import java.util.*;


public class Problem_981_TimeMap_2 {

    class TimeMap {

        class Node {
            public String key;
            public String val;
            public int time;

            public Node(String k, String v, int t) {
                key = k;
                val = v;
                time = t;
            }
        }

        private Map<String, List<Node>> map = new HashMap<>();

        public TimeMap() {

        }

        public void set(String key, String value, int timestamp) {
            Node node = new Node(key, value, timestamp);
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(node);
        }

        public String get(String key, int timestamp) {
            if (!map.containsKey(key)) return "";
            List<Node> list = map.get(key);
            int L = 0;
            int R = list.size() - 1;

            // <=timestamp最右的
            int pos = -1;
            while (L <= R) {
                int mid = L + ((R - L) >> 1);
                if (list.get(mid).time <= timestamp) {
                    pos = mid;
                    L = mid + 1;
                } else {
                    R = mid - 1;
                }
            }
            return pos == -1 ? "" : list.get(pos).val;
        }
    }
}
