package _DailyTarget;

import java.util.*;

public class Problem_692_TopKFreqWords {

    public List<String> topKFrequent(String[] words, int k) {
        List<String> ans = new ArrayList<>();
        if (words == null || words.length == 0 || k <= 0) {
            return ans;
        }
        Map<String, Integer> map = new HashMap<>();
        for (String w : words) {
            map.put(w, map.getOrDefault(w, 0) + 1);
        }
        ans.addAll(map.keySet());
        ans.sort((o1, o2) -> map.get(o2).equals(map.get(o1)) ? o1.compareTo(o2) : (map.get(o2) - map.get(o1)));
        if (ans.size() > k) {
            ans.subList(k, ans.size()).clear();
        }
        return ans;
    }


    // 优先队列, NlogK
    public List<String> topKFrequent1(String[] words, int k) {
        List<String> ans = new ArrayList<>();
        if (words == null || words.length == 0 || k <= 0) {
            return ans;
        }
        Map<String, Integer> map = new HashMap<>();
        for (String w : words) {
            map.put(w, map.getOrDefault(w, 0) + 1);
        }
        // 小顶堆
        PriorityQueue<String> pq =
                new PriorityQueue<>((o1, o2) -> map.get(o2).equals(map.get(o1)) ? o2.compareTo(o1) : (map.get(o1) - map.get(o2)));
        for (String w : map.keySet()) {
            if (pq.size() < k) {
                pq.add(w);
            } else { // >= k
                if (map.get(w) > map.get(pq.peek())) {
                    pq.poll();
                    pq.add(w);
                } else if (map.get(w).equals(map.get(pq.peek())) && w.compareTo(pq.peek()) < 0) {
                    pq.poll();
                    pq.add(w);
                }
            }
        }
        while (!pq.isEmpty()) {
            ans.add(pq.poll());
        }
        Collections.reverse(ans);
        return ans;
    }

    public static void main(String[] args) {
        String[] words = {"i","love","leetcode","i","love","coding"};
        int k= 1;
        var ans = new Problem_692_TopKFreqWords().topKFrequent1(words, k);
        System.out.println(ans);
    }


}
