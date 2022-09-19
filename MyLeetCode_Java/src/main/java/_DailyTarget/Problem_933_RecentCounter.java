package _DailyTarget;

import java.util.ArrayDeque;

public class Problem_933_RecentCounter {

    class RecentCounter {

        private ArrayDeque<Integer> queue;

        public RecentCounter() {
            queue = new ArrayDeque<>();
        }

        public int ping(int t) {
            while (!queue.isEmpty() && queue.peekFirst() < t - 3000) {
                queue.pollFirst();
            }
            queue.add(t);
            return queue.size();
        }
    }

}
