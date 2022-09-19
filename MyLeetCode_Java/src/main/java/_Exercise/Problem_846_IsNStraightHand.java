package _Exercise;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Problem_846_IsNStraightHand {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        if (hand == null || hand.length < groupSize || hand.length % groupSize != 0) {
            return false;
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int h : hand) {
            countMap.put(h, countMap.getOrDefault(h, 0) + 1);
            pq.add(h);
        }
        while (!pq.isEmpty()) {
            int start = pq.poll();
            if (countMap.get(start) == 0) continue; // 已经用过的, 跳过
            // start~start+groupSize -1, 应该是连续的
            for (int i = 0; i < groupSize; i++) { // 一次消去以 start 开头的一组数
                if (countMap.getOrDefault(start + i, 0) < 1) return false;
                countMap.put(start + i, countMap.get(start + i) - 1);
            }
        }
        return true;
    }

    public boolean isNStraightHand1(int[] hand, int groupSize) {
        if (hand.length % groupSize != 0) return false;
        Arrays.sort(hand);
        int groupNums = hand.length / groupSize;
        int[] group = new int[groupNums]; // 记录每个组当前来到的最大值
        int[] size = new int[groupNums]; // 每个 group 的大小
        int count = 0;
        for (int h : hand) {
            int flag = 0;
            for (int i = 0; i < group.length; i++) { //遍历每一个分组
                // 向每个没有满的组插入, 看当前数 h 能被插入到哪一个组
                if (size[i] == 0 || size[i] < groupSize && group[i] == h - 1) {
                    group[i] = h;
                    size[i]++;
                    flag++;
                    count++;
                    break;
                }
            }
            if (flag == 0) { // 如果数没进任何组, 返回 false
                return false;
            }
        }
        return count == hand.length;
    }

    public static void main(String[] args) {
        int[] hand = new int[]{8, 10, 12};
        int size = 3;
        var ans = new Problem_846_IsNStraightHand().isNStraightHand(hand, size);
        System.out.println(ans);
    }
}
