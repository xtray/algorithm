package _DailyTarget;

import java.util.*;

public class Problem_981_TimeMap {

    class TimeMap {

        class Node {
            String key;
            String val;
            int time;

            public Node(String k, String v, int t) {
                k = key;
                val = v;
                time = t;
            }
        }

        Map<String, List<Node>> map = new HashMap<>();

        public TimeMap() {

        }

        public void set(String key, String value, int timestamp) {
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(new Node(key, value, timestamp));
        }

        public String get(String key, int timestamp) {
            if (!map.containsKey(key)) return "";
            List<Node> list = map.get(key);
            int L = 0;
            int R = list.size() - 1;
            int res = -1;
            while (L <= R) {
                int mid = L + ((R - L) >> 1);
                if (list.get(mid).time <= timestamp) {
                    res = mid;
                    L = mid + 1;
                } else {
                    R = mid - 1;
                }
            }
            return res == -1 ? "" : list.get(res).val;
        }
    }
}
