package Contest.LC.W301;

import java.util.Arrays;
import java.util.PriorityQueue;

// https://leetcode.cn/problems/minimum-amount-of-time-to-fill-cups/

public class Problem_2335_FillCups {

    public int fillCups(int[] amount) {
        Arrays.sort(amount);
        if (amount[2] > amount[0] + amount[1]) return amount[2];
        return (Arrays.stream(amount).sum() + 1) >> 1;
    }

    public int fillCups2(int[] amount) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int i = 0; i < amount.length; i++) {
            if (amount[i] > 0) {
                pq.add(amount[i]);
            }
        }
        int ans = 0;
        while (pq.size() >= 2) {
            Integer num1 = pq.poll();
            Integer num2 = pq.poll();
            if (--num1 > 0) {
                pq.add(num1);
            }
            if (--num2 > 0) {
                pq.add(num2);
            }
            ans++;
        }
        if (!pq.isEmpty()) {
            ans += pq.poll();
        }
        return ans;
    }

    public static void main(String[] args) {
        // int[] amount = {5, 4, 4};
        int[] amount = {5, 0, 0};
        var ans = new Problem_2335_FillCups().fillCups2(amount);
        System.out.println(ans);
    }
}
