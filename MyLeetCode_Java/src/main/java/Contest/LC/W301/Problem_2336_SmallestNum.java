package Contest.LC.W301;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;

public class Problem_2336_SmallestNum {
    class SmallestInfiniteSet {

        private int curMin;
        private PriorityQueue<Integer> pq;
        private Set<Integer> set;

        public SmallestInfiniteSet() {
            curMin = 1;
            pq = new PriorityQueue<>();
            set = new HashSet<>();
        }

        public int popSmallest() {
            if (pq.isEmpty()) {
                return curMin++;
            }
            int num = pq.poll();
            set.remove(num);
            return num;
        }

        public void addBack(int num) {
            if (num >= curMin) return;
            if (set.contains(num)) return;
            set.add(num);
            pq.add(num);
        }
    }

    class SmallestInfiniteSet1 {

        private TreeSet<Integer> set;
        private int curNum; // 离散区最后一个

        public SmallestInfiniteSet1() {
            set = new TreeSet<>();
        }

        public int popSmallest() {
            return set.isEmpty() ? ++curNum : set.pollFirst();
        }

        public void addBack(int num) {
            if (num <= curNum) {
                set.add(num);
            }
        }
    }
}
