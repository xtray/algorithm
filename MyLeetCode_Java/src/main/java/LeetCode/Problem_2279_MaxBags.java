package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class Problem_2279_MaxBags {

    public int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
        int N = capacity.length;
        List<Integer> list = new ArrayList<>();
        int res = 0;
        long total = 0;
        for (int i = 0; i < N; i++) {
            int cur = capacity[i] - rocks[i];
            if (cur == 0) {
                res++;
            } else if (cur > 0) {
                list.add(cur);
                total += cur;
            }
        }
        if (additionalRocks >= total) {
            return N;
        }
        list.sort((o1, o2) -> o1 - o2);
        for (int num : list) {
            if (additionalRocks >= num) {
                additionalRocks -= num;
                res++;
            } else {
                break;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        // int[] capacity = {2, 3, 4, 5};
        // int[] rocks = {1, 2, 4, 4};
        // int additionalRocks = 2; // 3
        int[] capacity = {10, 2, 2};
        int[] rocks = {2, 2, 0};
        int additionalRocks = 100; // 3
        var ans = new Problem_2279_MaxBags().maximumBags(capacity, rocks, additionalRocks);
        System.out.println(ans);
    }
}
