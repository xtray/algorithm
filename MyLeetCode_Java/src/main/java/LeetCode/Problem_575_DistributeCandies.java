package LeetCode;

import java.util.*;

public class Problem_575_DistributeCandies {

    private int maxKind = 0;

    // 暴力解法, 每一个糖果选择去 A,或 B,最后去重
    public int distributeCandies1(int[] candyType) {
        if (candyType == null || candyType.length == 0) {
            return 0;
        }
        List<Integer> path = new ArrayList<>();
        process(candyType, 0, candyType.length / 2, path);
        return maxKind;
    }

    // 当前来到 i 位置, 剩余待处理 rest
    // 收集所有结果
    private void process(int[] candyType, int i, int rest, List<Integer> path) {
        if (i == candyType.length) {
            // 统计 path 种类
            maxKind = Math.max(maxKind, getKind(path));
            return;
        }
        // i 位置给 A
        if (rest > 0) {
            path.add(candyType[i]);
            process(candyType, i + 1, rest - 1, path);
            path.remove(Integer.valueOf(candyType[i]));
        }
        // i位置不给 A
        process(candyType, i + 1, rest, path);
    }

    private int getKind(List<Integer> path) {
        Set<Integer> set = new HashSet<>(path);
        return set.size();
    }

    // 贪心
    public int distributeCandies2(int[] candyType) {
        if (candyType == null || candyType.length == 0) {
            return 0;
        }

        int N = candyType.length;
        Set<Integer> set = new HashSet<>();
        for (int candy: candyType) {
            set.add(candy);
        }
        return Math.min(set.size(), N / 2);
    }

    public static void main(String[] args) {
        Problem_575_DistributeCandies sl = new Problem_575_DistributeCandies();

//        int[] candies = new int[]{1,1,2,2,3,3};
        int[] candies = new int[]{3,3,3,3};
//        int[] candies = new int[]{1, 1, 2, 3};

        int ans = sl.distributeCandies1(candies);
        System.out.println(ans);

    }
}
