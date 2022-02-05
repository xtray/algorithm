package LeetCode;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Problem_2034_StockPrice {
    class StockPrice {

        Map<Integer, Integer> map; // key: 时间戳, value: 价格
        PriorityQueue<int[]> maxQ; // int[timestamp, value]
        PriorityQueue<int[]> minQ;
        int curPrice;
        int curTime;

        public StockPrice() {
            map = new HashMap<>();
            maxQ = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);
            minQ = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
            curTime = 0;
            curPrice = 0;
        }

        public void update(int timestamp, int price) {
            map.put(timestamp, price);
            curTime = Math.max(curTime, timestamp);
            curPrice = curTime == timestamp ? price : curPrice;
            maxQ.add(new int[]{timestamp, price});
            minQ.add(new int[]{timestamp, price});
        }

        public int current() {
            return map.get(curTime);
        }

        public int maximum() {
            // maxQ top
            // map 里存的是准确的
            // 如果 maxQ 里的 top 对应的时间戳取到的值跟 map 不一致,说明是过时的
            int[] max = maxQ.peek();
            while (max != null && max[1] != map.get(max[0])) {
                maxQ.poll();
                max = maxQ.peek();
            }
            return max[1];
        }

        public int minimum() {
            // minQ top
            int[] min = minQ.peek();
            while (min != null && min[1] != map.get(min[0])) {
                minQ.poll();
                min = minQ.peek();
            }
            return min[1];
        }

        public void test() {
            StockPrice sp = new StockPrice();
            sp.update(1, 10);
            sp.update(2, 5);
            System.out.println(sp.current());
            System.out.println(sp.maximum());
            sp.update(1, 3); // -->5
            System.out.println(sp.maximum());
            sp.update(4, 2);
            System.out.println(sp.minimum());
        }
    }

    public static void main(String[] args) {
        var sl = new Problem_2034_StockPrice().new StockPrice();
        sl.test();
    }
}