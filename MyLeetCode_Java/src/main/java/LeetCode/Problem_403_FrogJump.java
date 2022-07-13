package LeetCode;

import java.util.*;

public class Problem_403_FrogJump {

    // 暴力递归
    public boolean canCross(int[] stones) {
        if (stones == null || stones.length == 0) {
            return false;
        }
        Set<Integer> set = new HashSet<>();
        for (int s : stones) {
            set.add(s);
        }
        return process(stones, 1, 1, stones[stones.length - 1], set);
    }

    // 当前来到cur位置
    // 前一个跳的步长pre
    // 目标end
    // set: 石头存在的位置
    private boolean process(int[] stones, int cur, int pre, int end, Set<Integer> set) {
        if (cur == end) {
            return true;
        }
        if (!set.contains(cur)) {
            return false;
        }
        // 当前在cur位置
        // 可以跳pre-1, pre, pre+1
        boolean ans = false;
        ans = (pre > 1) && process(stones, cur + pre - 1, pre - 1, end, set);
        ans |= process(stones, cur + pre, pre, end, set);
        ans |= process(stones, cur + pre + 1, pre + 1, end, set);
        return ans;
    }

    // 记忆化搜索
    public boolean canCross2(int[] stones) {
        if (stones == null || stones.length == 0) {
            return false;
        }
        Set<Integer> set = new HashSet<>();
        for (int s : stones) {
            set.add(s);
        }
        Map<String, Boolean> dp = new HashMap<>();
        return process2(stones, 1, 1, stones[stones.length - 1], set, dp);
    }

    // 当前来到cur位置
    // 前一个跳的步长pre
    // 目标end
    // set: 石头存在的位置
    private boolean process2(int[] stones, int cur, int pre, int end, Set<Integer> set, Map<String, Boolean> dp) {
        if (cur == end) {
            return true;
        }
        if (!set.contains(cur)) {
            return false;
        }
        String key = cur + "_" + pre;
        if (dp.containsKey(key)) {
            return dp.get(key);
        }
        // 当前在cur位置
        // 可以跳pre-1, pre, pre+1
        boolean ans = false;
        ans = (pre > 1) && process2(stones, cur + pre - 1, pre - 1, end, set, dp);
        ans |= process2(stones, cur + pre, pre, end, set, dp);
        ans |= process2(stones, cur + pre + 1, pre + 1, end, set, dp);
        dp.put(key, ans);
        return ans;
    }

    public boolean canCross3(int[] stones) {
        if (stones == null || stones.length == 0) {
            return false;
        }
        Set<Integer> set = new HashSet<>();
        for (int s : stones) {
            set.add(s);
        }
        Map<Integer, Map<Integer, Boolean>> dp = new HashMap<>();
        return process3(stones, 1, 1, stones[stones.length - 1], set, dp);
    }

    // 当前来到cur位置
    // 前一个跳的步长pre
    // 目标end
    // set: 石头存在的位置
    private boolean process3(int[] stones, int cur, int pre, int end, Set<Integer> set, Map<Integer, Map<Integer, Boolean>> dp) {
        if (cur == end) {
            return true;
        }
        if (!set.contains(cur)) {
            return false;
        }
        if (dp.containsKey(cur) && dp.get(cur).containsKey(pre)) {
            return dp.get(cur).get(pre);
        }
        // 当前在cur位置
        // 可以跳pre-1, pre, pre+1
        boolean ans = false;
        ans = (pre > 1) && process3(stones, cur + pre - 1, pre - 1, end, set, dp);
        ans |= process3(stones, cur + pre, pre, end, set, dp);
        ans |= process3(stones, cur + pre + 1, pre + 1, end, set, dp);
        dp.computeIfAbsent(cur, key -> new HashMap<>()).put(pre, ans);
        return ans;
    }
}
