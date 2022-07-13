package LeetCode.JZOffer;

import java.util.*;

public class Problem_JZII060_TopKFrequent {

    public int[] topKFrequent(int[] nums, int k) {
        int N = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        List<int[]> list = new ArrayList<>();
        for (int key : map.keySet()) {
            list.add(new int[]{key, map.get(key)});
        }
        list.sort((o1, o2) -> o2[1] - o1[1]);
        int[] ans = new int[k];
        int idx = 0;
        for (int[] cur : list) {
            ans[idx++] = cur[0];
            if (idx == k) break;
        }
        return ans;
    }

    public int[] topKFrequent2(int[] nums, int k) {
        int N = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        for (int key : map.keySet()) {
            if (pq.size() < k) {
                pq.add(new int[]{key, map.get(key)});
            } else {
                if (map.get(key) > pq.peek()[1]) {
                    pq.poll();
                    pq.add(new int[]{key, map.get(key)});
                }
            }
        }
        int[] ans = new int[k];
        int idx = 0;
        while (!pq.isEmpty()) {
            ans[idx++] = pq.poll()[0];
        }
        return ans;
    }


}
