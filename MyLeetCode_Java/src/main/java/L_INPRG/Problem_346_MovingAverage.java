package L_INPRG;

import java.util.LinkedList;
import java.util.List;

public class Problem_346_MovingAverage {

    class MovingAverage {

        public int size;
        public LinkedList<Integer> queue;
        public int sum;

        public MovingAverage(int size) {
            this.size = size;
            queue = new LinkedList<>();
            sum = 0;
        }

        public double next(int val) {
            queue.add(val);
            int curSize = queue.size();
            sum += val;
            if (curSize > size) {
                sum -= queue.pollFirst();
                curSize = size;
            }
            return (double) sum / curSize;
        }
    }
}
