package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem_398_RandomPickIndex {

    private Map<Integer, List<Integer>> map;

    public Problem_398_RandomPickIndex(int[] nums) {
        map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.computeIfAbsent(nums[i], key -> new ArrayList<>()).add(i);
        }
    }

    public int pick(int target) {
        List<Integer> list = map.get(target);
        int size = list.size();
        return list.get((int) (Math.random() * size));
    }

    // IMP: 蓄水池抽样
    private int[] nums;

    public void init(int[] nums) {
        this.nums = nums;
    }

    public int pick2(int target) {
        int ans = 0;
        int cnt = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                cnt++;
                // 以cnt分之1的概率决定要不要当前数
                if ((int) (Math.random() * cnt) == 0) {
                    ans = i;
                }
            }
        }
        return ans;
    }
}
