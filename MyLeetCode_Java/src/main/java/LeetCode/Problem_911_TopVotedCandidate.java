package LeetCode;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Problem_911_TopVotedCandidate {

    class TopVotedCandidate {

        private Map<Integer, Integer> map = new HashMap<>(); // key : 人, 票数
        private TreeMap<Integer, Integer> treemap = new TreeMap<>(); // key: 时间, maxid

        public TopVotedCandidate(int[] persons, int[] times) {
            int maxVal = 0;
            int maxId = 0;
            for (int i = 0; i < times.length; i++) {
                map.put(persons[i], map.getOrDefault(persons[i], 0) + 1);
                if (map.get(persons[i]) >= maxVal) {
                    maxVal = map.get(persons[i]);
                    maxId = persons[i];
                }
                treemap.put(times[i], maxId);
            }
        }

        public int q(int t) {
            return treemap.floorEntry(t).getValue();
        }
    }

    public static void main(String[] args) {
        int[] persons = new int[]{0,1,1,0,0,1,0};
        int[] times = new int[]{0,5,10,15,20,25,30};
        var sl = new Problem_911_TopVotedCandidate().new TopVotedCandidate(persons, times);
        int [] res = new int[]{3,12,25,15,24,8};
        for(var time : res) {
            System.out.print(sl.q(time ) + " ");
        }
        System.out.println();

    }

}
