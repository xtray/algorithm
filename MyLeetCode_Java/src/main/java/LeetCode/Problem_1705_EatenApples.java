package LeetCode;

import java.util.PriorityQueue;

public class Problem_1705_EatenApples {

    public static class Node {
        int number;
        int endTime;

        public Node(int num,  int end) {
            number = num;
            endTime = end;
        }
    }

    // 如果直接使用优先队列存储每个苹果的腐烂日期，则会导致优先队列中的元素个数过多，时间复杂度和空间复杂度过高，
    // 因此需要使用优化的表示法。可以在优先队列中存储二元组，每个二元组表示苹果的腐烂日期和在该日期腐烂的苹果个数，
    // 则优先队列中的元素个数最多为 nn 个（即数组长度），可以显著降低时间复杂度和空间复杂度。
    public int eatenApples(int[] apples, int[] days) {
        // 先吃快要烂的苹果, 按腐烂最后日期排序
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.endTime - o2.endTime);
        int N = apples.length;
        int curtime = 0;
        int res = 0;
        while (curtime < N || !pq.isEmpty()) {
            if(curtime < N) { // 还在产生苹果的过程中
                pq.add(new Node(apples[curtime], curtime + days[curtime]));
            }
            curtime++; //真正的当前时刻
            // 先把烂苹果丢掉
            while (!pq.isEmpty()&&pq.peek().endTime < curtime) {
                pq.poll();
            }
            // 没有可以吃的苹果
            if (pq.isEmpty()) {
                continue;
            }
            // 剩下都是好苹果, 腐烂日期大于当前时间, 可以吃
            // 吃掉一个苹果
            if(pq.peek().number > 0) {
                res++;
                pq.peek().number--; // 吃掉苹果后苹果数可能会为零
                if (pq.peek().number == 0) {
                    pq.poll();
                }
            }

        }
        return res;
    }

    public static void main(String[] args) {
        int[] apples = {1, 2, 3, 5, 2}, days = {3, 2, 1, 4, 2};
        var res = new Problem_1705_EatenApples().eatenApples(apples, days);
        System.out.println(res);
    }
}
