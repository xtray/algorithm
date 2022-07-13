package Contest.LC.W296;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Problem_2295_ReplacElementInArray {

    public int[] arrayChange(int[] nums, int[][] operations) {

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        for (int[] op : operations) {
            int idx = map.get(op[0]);
            map.remove(op[0]);
            map.put(op[1], idx);
        }
        for (int key : map.keySet()) {
            nums[map.get(key)] = key;
        }
        return nums;
    }

    public static void main(String[] args) {
        // int[] nums = {1,2,4,6};
        // int[][] operations = {{1,3},{4,7},{6,1}};
        int[] nums = {1,2};
        int[][] operations = {{1,3},{2,1},{3,2}};
        var ans = new Problem_2295_ReplacElementInArray().arrayChange(nums, operations);
        System.out.println(Arrays.toString(ans));
    }
}
