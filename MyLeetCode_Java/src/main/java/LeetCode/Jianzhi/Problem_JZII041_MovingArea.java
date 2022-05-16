package LeetCode.Jianzhi;

import java.util.LinkedList;

public class Problem_JZII041_MovingArea {

    static class MovingAverage {

        public int limit;
        public LinkedList<Integer> queue;
        public int sum;

        public MovingAverage(int size) {
            this.limit = size;
            queue = new LinkedList<>();
            sum = 0;
        }

        public double next(int val) {
            queue.add(val);
            sum += val;
            if (queue.size() > limit) {
                sum -= queue.poll();
            }
            return (double) sum / queue.size();
        }
    }

}
