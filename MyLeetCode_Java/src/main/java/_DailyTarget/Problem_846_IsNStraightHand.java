package _DailyTarget;

import java.util.*;

public class Problem_846_IsNStraightHand {


    public boolean isNStraightHand0(int[] hand, int groupSize) {
        int N = hand.length;
        // [num, cnt
        TreeMap<Integer, Integer> cntMap = new TreeMap<>();
        for (int num : hand) {
            cntMap.put(num, cntMap.getOrDefault(num, 0) + 1);
        }
        while (!cntMap.isEmpty()) {
            int start = cntMap.firstKey();
            for (int i = 0; i < groupSize; i++) {
                if (cntMap.getOrDefault(start, 0) > 0) {
                    cntMap.put(start, cntMap.get(start) - 1);
                    if (cntMap.get(start) == 0) {
                        cntMap.remove(start);
                    }
                } else {
                    return false;
                }
                start++;
            }
        }
        return true;
    }


    // 模拟, 用有序表排序, 从group最小到最大逐个--
    public boolean isNStraightHand(int[] hand, int groupSize) {
        if (hand == null || hand.length < groupSize || hand.length % groupSize != 0) {
            return false;
        }
        TreeMap<Integer, Integer> cntMap = new TreeMap<>();
        for (int num : hand) {
            cntMap.put(num, cntMap.getOrDefault(num, 0) + 1);
        }
        while (!cntMap.isEmpty()) {
            int start = cntMap.firstKey();
            for (int i = start; i < start + groupSize; i++) {
                if (!cntMap.containsKey(i)) return false;
                cntMap.put(i, cntMap.get(i) - 1);
                if (cntMap.get(i) == 0) cntMap.remove(i);
            }
        }
        return true;
    }

    public boolean isNStraightHand1(int[] hand, int groupSize) {
        if (hand == null || hand.length < groupSize || hand.length % groupSize != 0) {
            return false;
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Map<Integer, Integer> cntMap = new HashMap<>();
        for (int num : hand) {
            cntMap.put(num, cntMap.getOrDefault(num, 0) + 1);
            pq.add(num);
        }
        while (!pq.isEmpty()) {
            int start = pq.poll(); // 获取每组的开始数字
            if (cntMap.get(start) == 0) continue; // 已经用过的, 跳过
            for (int i = start; i < start + groupSize; i++) {
                // 如果没有, 或者已经用掉了(因为map没有删除) 都是0, 都应该返回false
                if (cntMap.getOrDefault(i, 0) < 1) return false;
                cntMap.put(i, cntMap.get(i) - 1);
            }
        }
        return true;
    }

    public boolean isNStraightHand2(int[] hand, int groupSize) {
        if (hand == null || hand.length < groupSize || hand.length % groupSize != 0) {
            return false;
        }
        Arrays.sort(hand);
        int groupNums = hand.length / groupSize;
        int[] group = new int[groupNums]; // 记录每个组当前来到的最大值
        int[] size = new int[groupNums]; // 每个 group 的大小
        int count = 0; // 已经处理的数的个数
        for (int h : hand) {
            boolean used = false; // 看当前h 这个数有没有被使用过, 如果没有任何一组能使用就返回false
            for (int i = 0; i < group.length; i++) { //遍历每一个分组
                // 向每个没有满的组插入, 看当前数 h 能被插入到哪一个组
                // group[i] == h - 1 --> group[i] 相当于上一个插入的数 pre, h是当前, 二者差1
                if (size[i] == 0 || size[i] < groupSize && group[i] == h - 1) {
                    group[i] = h;
                    size[i]++;
                    used = true;
                    count++;
                    break;
                }
            }
            if (!used) { // 如果数没进任何组, 返回 false
                return false;
            }
        }
        // return count == hand.length;
        return true;
    }

    public static void main(String[] args) {
        // int[] hand = new int[]{8, 10, 12};
        int[] hand = new int[]{1, 2, 3, 6, 2, 3, 4, 7, 8};
        int size = 3;
        var ans = new Problem_846_IsNStraightHand().isNStraightHand0(hand, size);
        System.out.println(ans);
    }
}
