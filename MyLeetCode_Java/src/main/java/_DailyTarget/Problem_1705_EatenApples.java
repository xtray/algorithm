package _DailyTarget;

import java.util.PriorityQueue;

public class Problem_1705_EatenApples {

    public static class Node {
        int number;
        int endTime;

        public Node(int num, int end) {
            number = num;
            endTime = end;
        }
    }

    // 如果直接使用优先队列存储每个苹果的腐烂日期，则会导致优先队列中的元素个数过多，时间复杂度和空间复杂度过高，
    // 因此需要使用优化的表示法。可以在优先队列中存储二元组，每个二元组表示苹果的腐烂日期和在该日期腐烂的苹果个数，
    // 则优先队列中的元素个数最多为n个（即数组长度），可以显著降低时间复杂度和空间复杂度。
    public int eatenApples(int[] apples, int[] days) {
        // 先吃快要烂的苹果, 按腐烂最后日期排序
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.endTime - o2.endTime);
        int N = apples.length;
        int curtime = 0;
        int res = 0;
        while (curtime < N || !pq.isEmpty()) {
            if (curtime < N) { // 还在产生苹果的过程中
                pq.add(new Node(apples[curtime], curtime + days[curtime]));
            }
            curtime++; //真正的当前时刻
            // 先把烂苹果丢掉
            while (!pq.isEmpty() && pq.peek().endTime < curtime) {
                pq.poll();
            }
            // 没有可以吃的苹果
            if (pq.isEmpty()) {
                continue;
            }
            // 剩下都是好苹果, 腐烂日期大于当前时间, 可以吃
            // 吃掉一个苹果
            if (pq.peek().number > 0) {
                res++;
                pq.peek().number--; // 吃掉苹果后苹果数可能会为零
                if (pq.peek().number == 0) {
                    pq.poll();
                }
            }

        }
        return res;
    }

    public int eatenApples1(int[] apples, int[] days) {
        int N = apples.length;
        // 每天要烂的苹果数加入优先队列
        // [day, cnt]
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        int cnt = 0;
        int i = 0;
        while (i < N) {
            pq.add(new int[]{i + days[i] - 1, apples[i]});
            while (!pq.isEmpty() && pq.peek()[0] < i) {
                pq.poll();
            }
            if (!pq.isEmpty() && pq.peek()[0] >= i) {
                int[] cur = pq.poll();
                cur[1]--;
                cnt++;
                if (cur[1] > 0) {
                    pq.add(cur);
                }
            }
            i++;
        }
        while (!pq.isEmpty()) {
            while (!pq.isEmpty() && pq.peek()[0] < i) {
                pq.poll();
            }
            if (!pq.isEmpty() && pq.peek()[0] >= i) {
                int[] cur = pq.poll();
                cur[1]--;
                cnt++;
                if (cur[1] > 0) {
                    pq.add(cur);
                }
            }
            i++;
        }
        return cnt;
    }

    public int eatenApples2(int[] apples, int[] days) {
        int N = apples.length;
        // 要烂的苹果日期 跟数量加入优先队列
        // [day, cnt]
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        int cnt = 0;
        int i = 0;
        while (i < N || !pq.isEmpty()) {
            if (i < N) { // 每天源源不断的新苹果加入队列
                pq.add(new int[]{i + days[i] - 1, apples[i]});
            }
            while (!pq.isEmpty() && pq.peek()[0] < i) { // 弹出所有烂苹果
                pq.poll();
            }
            if (pq.isEmpty()) {
                i++;
                continue; // 没有可以吃的苹果, 到下一天去
            }
            // 吃掉一个好苹果
            if (pq.peek()[1] > 0) {
                pq.peek()[1]--;
                cnt++;
                if (pq.peek()[1] == 0) { // 吃光了, 弹出
                    pq.poll();
                }
            }
            i++; // 越界就判断队列了
        }
        return cnt;
    }

    public static void main(String[] args) {
        // int[] apples = {1, 2, 3, 5, 2}, days = {3, 2, 1, 4, 2}; // 7
        int[] apples = {3, 0, 0, 0, 0, 2}, days = {3, 0, 0, 0, 0, 2}; // 5
        var res = new Problem_1705_EatenApples().eatenApples(apples, days);
        System.out.println(res);

        var res1 = new Problem_1705_EatenApples().eatenApples1(apples, days);
        System.out.println(res1);


        var res2 = new Problem_1705_EatenApples().eatenApples2(apples, days);
        System.out.println(res2);
    }
}
