package _LintCode;

import java.util.*;

public class Problem_471_TopKFreqWords {

    public String[] topKFrequentWords(String[] words, int k) {
        if (words == null || words.length == 0 || k <= 0) {
            return new String[0];
        }
        String[] ans = new String[k];
        Map<String, Integer> map = new HashMap<>();
        for (String w : words) map.put(w, map.getOrDefault(w, 0) + 1);
        // 小顶堆
        PriorityQueue<String> pq = new PriorityQueue<>(
                (o1, o2) -> map.get(o1).equals(map.get(o2)) ? o2.compareTo(o1) : map.get(o1) - map.get(o2));
        for (String w : map.keySet()) {
            if (pq.size() < k) {
                pq.add(w);
                continue;
            }
            if (map.get(w) > map.get(pq.peek()) || map.get(w).equals(map.get(pq.peek())) && w.compareTo(pq.peek()) < 0) {
                pq.poll();
                pq.add(w);
            }
        }
        int idx = k - 1;
        while (!pq.isEmpty()) {
            ans[idx--] = pq.poll();
        }
        return ans;
    }
}
