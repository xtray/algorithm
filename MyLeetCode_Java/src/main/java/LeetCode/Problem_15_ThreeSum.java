package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// IMP: 去重的办法, 生成结果的时候才去考虑去重

public class Problem_15_ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length <= 2) {
            return new ArrayList<>();
        }
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums); // 去除重复的
        int N = nums.length;
        int pre = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            if (nums[i] == pre) continue;
            int target = -nums[i];
            List<List<Integer>> res = getTwoSum(nums, i + 1, target);
            if (!res.isEmpty()) {
                for (List<Integer> item : res) {
                    item.add(0, nums[i]);
                }
                ans.addAll(res);
            }
            pre = nums[i];
        }
        return ans;
    }

    // i 位置到结尾, 有没有两个数只和等于target
    private List<List<Integer>> getTwoSum(int[] nums, int index, int target) {
        if (index == nums.length || index + 1 == nums.length) {
            return new ArrayList<>();
        }
        List<List<Integer>> ans = new ArrayList<>();
        int L = index;
        int R = nums.length - 1;
        while (L < R) {
            int sum = nums[L] + nums[R];
            if (sum < target) {
                L++;
            } else if (sum > target) {
                R--;
            } else {
                // 只有生成结果的时候, 后面新结果跟上次一致才要去重
                List<Integer> res = new ArrayList<>();
                res.add(nums[L]);
                res.add(nums[R]);
                ans.add(res);
                while (L + 1 <= R && (nums[L + 1] == nums[L])) {
                    L++;
                }
                while (R - 1 >= L && nums[R - 1] == nums[R]) {
                    R--;
                }
                L++;
                R--;
            }

        }
        return ans;
    }

    // IMP: 再看看
    public List<List<Integer>> threeSum2(int[] nums) {
        int N = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        // 枚举 a
        for (int i = 0; i < N; ++i) {
            // 需要和上一次枚举的数不相同
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // c 对应的指针初始指向数组的最右端
            int third = N - 1;
            int target = -nums[i];
            // 枚举 b
            for (int j = i + 1; j < N; ++j) {
                // 需要和上一次枚举的数不相同
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                // 需要保证 b 的指针在 c 的指针的左侧
                while (j < third && nums[j] + nums[third] > target) {
                    --third;
                }
                // 如果指针重合，随着 b 后续的增加
                // 就不会有满足 a+b+c=0 并且 b<c 的 c 了，可以退出循环
                if (j == third) {
                    break;
                }
                if (nums[j] + nums[third] == target) {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(nums[third]);
                    ans.add(list);
                }
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        // int[] nums = {-2, 0, 0, 2, 2};
        int[] nums = {-4,-2,1,-5,-4,-4,4,-2,0,4,0,-2,3,1,-5,0};
        var ans = new Problem_15_ThreeSum().threeSum(nums);
        for (var list : ans) {
            for (var num : list) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
