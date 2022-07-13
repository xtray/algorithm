package Contest.LC.W284;

import java.util.ArrayList;
import java.util.List;

public class Problem_2200_FindKDistanceIdx {
    public List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        List<Integer> ans = new ArrayList<>();
        int i = 0; // 不回退的
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] == key) { // 找到一个目标, 向两边扩散
                while (i <= Math.min(nums.length - 1, j + k)) {
                    if (Math.abs(i - j) <= k) {
                        ans.add(i);
                    }
                    i++;
                }
                if (i == nums.length) {
                    break;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        // int[] nums = {3, 4, 9, 1, 3, 9, 5};
        // int key = 9;
        // int k = 1;
        int[] nums = {2,2,2,2,2};
        int key = 2;
        int k = 2;
        var ans = new Problem_2200_FindKDistanceIdx().findKDistantIndices(nums, key, k);
        for (var num : ans) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
