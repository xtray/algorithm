package _Contest.LC.W302;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem_2342_MaxSum {

    // 1. 获取每个数的数位和, 相同的数位和放到map
    // map key: 数位和, value: list
    public int maximumSum(int[] nums) {

        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int num : nums) {
            int digitSum = getDigitSum(num);
            map.computeIfAbsent(digitSum, key -> new ArrayList<>()).add(num);
        }
        // 删除只有一项的
        var it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, List<Integer>> entry = it.next();
            if (entry.getValue().size() == 1) {
                it.remove();
            }
        }
        if (map.size() == 0) { // 不存在数对
            return -1;
        }
        int ans = 0;
        for (List<Integer> list : map.values()) {
            int res = getMaxValue(list);
            ans = Math.max(ans, res);
        }
        return ans;
    }

    private int getMaxValue(List<Integer> list) {
        list.sort((o1, o2) -> o2 - o1);
        return list.get(0) + list.get(1);
    }

    private int getDigitSum(int num) {
        int ans = 0;
        while (num != 0) {
            ans += num % 10;
            num /= 10;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {18, 43, 36, 13, 7, 63};
        // int[] nums = {10, 12, 19, 14};
        var ans = new Problem_2342_MaxSum().maximumSum(nums);
        System.out.println(ans);
    }
}
