package _Contest.LC.JK;

import java.util.*;

public class Problem_JK04_ChipGame {

    public double chipGame(int[] nums, int kind) {
        int[] cur = new int[kind];
        for (int i = 0; i < nums.length; i++) {
            cur[i] = nums[i];
        }
        Arrays.sort(cur);
        return chipGame(new int[kind], cur, new HashMap<>(Map.of(Arrays.toString(cur), 0.)));
    }

    private double chipGame(int[] curr, int[] nums, HashMap<String, Double> map) {
        if (!map.containsKey(Arrays.toString(curr))) {
            double count = 0, result = 0;
            for (int i = 0; i < curr.length;) {
                int j = i;
                while (j < curr.length && curr[i] == curr[j]) {
                    j++;
                }
                if (curr[j - 1] < nums[j - 1]) {
                    curr[j - 1]++;
                    result += (j - i) * chipGame(curr, nums, map);
                    curr[j - 1]--;
                    count += j - i;
                }
                i = j;
            }
            result = (result + curr.length) / count;
            map.put(Arrays.toString(curr), result);
        }
        return map.get(Arrays.toString(curr));
    }
}
