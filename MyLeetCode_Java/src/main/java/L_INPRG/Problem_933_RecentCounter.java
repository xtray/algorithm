package L_INPRG;

import java.util.ArrayDeque;

public class Problem_933_RecentCounter {

    // 队列
    static class RecentCounter {

        private ArrayDeque<Integer> queue;


        public RecentCounter() {
            queue = new ArrayDeque<>();
        }

        public int ping(int t) {
            queue.add(t);
            while (!queue.isEmpty() && queue.peekFirst() < Math.max(1, t - 3000)) {
                queue.pollFirst();
            }
            return queue.size();
        }
    }
}
