package _LintCode;

import java.util.HashMap;
import java.util.Map;

public class Problem_56_TwoSum {

    public int[] twoSum(int[] numbers, int target) {
        if (numbers == null || numbers.length == 0) {
            return new int[0];
        }
        int N = numbers.length;
        int[] ans = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            int x = target - numbers[i];
            if (map.containsKey(x)) {
                ans[0] = map.get(x);
                ans[1] = i;
                break;
            } else {
                map.put(numbers[i], i);
            }
        }
        return ans;
    }
}
