package LeetCode;

import java.util.ArrayDeque;

public class Problem_346_AverageDataInStream {

    class MovingAverage {

        private ArrayDeque<Integer> queue;
        private int size;
        private double sum;

        public MovingAverage(int size) {
            queue = new ArrayDeque<>();
            this.size = size;
        }

        public double next(int val) {
            if (queue.size() == size) {
                sum -= queue.pollFirst();
            }
            queue.add(val);
            sum += val;
            return sum / queue.size();
        }
    }
}
