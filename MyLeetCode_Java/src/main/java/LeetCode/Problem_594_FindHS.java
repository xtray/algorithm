package LeetCode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Problem_594_FindHS {

    // 与顺序无关
    public int findLHS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 以每个数做最小值,找所有与他+1 的数量累加
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
        }
        int maxLen = 0;
        for (int num : count.keySet()) {
            if (count.containsKey(num + 1)) { // 没有比当前大的就是 0
                int cur = count.get(num) + count.get(num + 1);
                maxLen = Math.max(maxLen, cur);
            }
        }
        return maxLen;
    }

    // 利用单调性
    // 存在长度越大最长的可能性越大这种单调性, 故可以用双指针
    public int findLHS1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums); // 与顺序无关
        int maxLen = 0;
        for(int i=1;i<nums.length;i++) {
            // 因为排序了, 以每一个数作为最小值, 向右找符合条件的数
            int j = i;
            while (j<nums.length && (nums[j] - nums[i-1] == 0 || nums[j] - nums[i-1] == 1)) {
                j++;
            }
            // j == nums.length or 差值不为0/1
            if(nums[j-1] - nums[i-1] == 1) { // j == nums.length 的情况也在内
                maxLen = Math.max(maxLen, j-i+1);
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        Problem_594_FindHS sl = new Problem_594_FindHS();
//        int[] nums = new int[]{1, 1, 1, 1};
//        int[] nums = new int[]{1,3,2,2,5,2,3,7};
        int[] nums = new int[]{1,2,2,1};
        int ans = sl.findLHS(nums);
        System.out.println(ans);
        int ans1 = sl.findLHS1(nums);
        System.out.println(ans1);
    }
}
