package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// IMP: 去重的办法, 生成结果的时候才去考虑去重

public class Problem_18_FourSum {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        if (nums == null || nums.length < 4) {
            return ans;
        }
        Arrays.sort(nums);
        int N = nums.length;
        for (int i = 0; i < N - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            if ((long) nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
                break;
            }
            if ((long) nums[i] + nums[N - 3] + nums[N - 2] + nums[N - 1] < target) {
                continue;
            }
            for (int j = i + 1; j < N - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                if ((long) nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) {
                    break;
                }
                if ((long) nums[i] + nums[j] + nums[N - 2] + nums[N - 1] < target) {
                    continue;
                }
                int L = j + 1, R = N - 1;
                while (L < R) {
                    int sum = nums[i] + nums[j] + nums[L] + nums[R];
                    if (sum == target) {
                        ans.add(Arrays.asList(nums[i], nums[j], nums[L], nums[R]));
                        while (L < R && nums[L] == nums[L + 1]) {
                            L++;
                        }
                        L++; // IMP: 多加一个跳过相等的
                        while (L < R && nums[R] == nums[R - 1]) {
                            R--;
                        }
                        R--; // IMP: 多减一个跳过相等的
                    } else if (sum < target) {
                        L++;
                    } else {
                        R--;
                    }
                }
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        // int[] nums = {1,0,-1,0,-2,2};
        // int target = 0;
        int[] nums = {-7, 7, 7, 7, 7};
        int target = 28;
        var ans = new Problem_18_FourSum().fourSum(nums, target);
        for (var list : ans) {
            for (var num : list) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
