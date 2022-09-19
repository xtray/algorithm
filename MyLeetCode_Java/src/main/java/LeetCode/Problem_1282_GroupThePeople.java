package LeetCode;

import java.util.*;

public class Problem_1282_GroupThePeople {

    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        List<List<Integer>> ans = new ArrayList<>();
        if (groupSizes == null || groupSizes.length == 0) {
            return ans;
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        int idx = 0;
        for (int g : groupSizes) {
            map.computeIfAbsent(g, key -> new ArrayList<>()).add(idx++);
        }
        for (int key : map.keySet()) {
            List<Integer> list = new ArrayList<>();
            for (int num : map.get(key)) {
                list.add(num);
                if (list.size() == key) {
                    ans.add(list);
                    list = new ArrayList<>();
                }
            }
        }
        return ans;
    }
}
