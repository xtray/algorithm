package _Contest.LC.DW88;

import java.util.*;

public class Problem_2424_MaxLUPrefix {

    class LUPrefix {

      public class Node {
            public Integer value;
            public Node next;

            public Node(Integer value) {
                this.value = value;
            }
        }

        private Map<Integer, Node> headMap;
        private Map<Integer, Node> tailMap;
        private int wait;

        public LUPrefix(int n) {
            wait = 1;
            headMap = new HashMap<>();
            tailMap = new HashMap<>();

        }

        public void upload(int video) {
            Node node = new Node(video);
            headMap.put(video, node);
            tailMap.put(video, node);

            // 找有没有以id-1结尾的
            if (tailMap.containsKey(video - 1)) {
                Node pre = tailMap.get(video - 1);
                pre.next = node;
                headMap.remove(video);
                tailMap.remove(video - 1);
            }

            // 找有没有以idKey+1开头的
            if (headMap.containsKey(video + 1)) {
                node.next = headMap.get(video + 1);
                headMap.remove(video + 1);
                tailMap.remove(video);
            }

            if (video == wait) {
                Node cur = headMap.get(video);
                while (cur != null) {
                    cur = cur.next;
                    wait++;
                }
                headMap.remove(video);
                tailMap.remove(wait - 1);
            }
        }

        public int longest() {
            return wait - 1;
        }
    }
}
