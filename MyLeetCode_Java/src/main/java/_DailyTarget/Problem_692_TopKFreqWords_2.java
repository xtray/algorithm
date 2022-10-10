package _DailyTarget;

import java.util.*;

public class Problem_692_TopKFreqWords_2 {

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
        // 小顶堆, 最薄弱点放上面
        // 如果词频相同, 字典序大的在上面
        PriorityQueue<String> pq =
                new PriorityQueue<>((o1, o2) -> map.get(o2).equals(map.get(o1)) ? o2.compareTo(o1) : (map.get(o1) - map.get(o2)));
        for (String word : map.keySet()) { // NOTE: 字符串要去重!!
            if (pq.size() < k) {
                pq.add(word);
            } else {
                if (map.get(word) > map.get(pq.peek()) ||
                        map.get(word).equals(map.get(pq.peek())) && word.compareTo(pq.peek()) < 0) {
                    pq.poll();
                    pq.add(word);
                }
            }
        }
        // pq里顶部词频最小, 字典序最大
        while (!pq.isEmpty()) {
            ans.add(pq.poll());
        }
        // 整体逆序
        Collections.reverse(ans);
        return ans;
    }

    public static void main(String[] args) {
        String[] words = {"i", "love", "leetcode", "i", "love", "coding"};
        int k = 2;
        var ans = new Problem_692_TopKFreqWords_2().topKFrequent1(words, k);
        System.out.println(ans);
    }


}
