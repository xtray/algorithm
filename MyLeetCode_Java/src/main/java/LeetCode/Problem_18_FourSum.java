package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// IMP: 去重的办法, 生成结果的时候才去考虑去重

public class Problem_18_FourSum {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return ans;
        }
        Arrays.sort(nums);
        int N = nums.length;
        for (int i = 0; i < N - 3; i++) { // 枚举第一个数
            // 保证第一个数字不一样, 后面就是三元组的问题
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // 大过滤, 可有可无
            if ((long) nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
                break;
            }
            if ((long) nums[i] + nums[N - 3] + nums[N - 2] + nums[N - 1] < target) {
                continue;
            }
            for (int j = i + 1; j < N - 2; j++) { // 从第一个数下一个位置, 枚举第二个数
                // 保证第而个数字不一样, 后面就是二元组的问题
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                // 大过滤, 可有可无
                if ((long) nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) {
                    break;
                }
                if ((long) nums[i] + nums[j] + nums[N - 2] + nums[N - 1] < target) {
                    continue;
                }
                // 二元组问题
                int L = j + 1;
                int R = N - 1;
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

    // 2022.04.29: 精简, 好理解的写法
    public List<List<Integer>> fourSum2(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return ans;
        }
        Arrays.sort(nums);
        int N = nums.length;
        // 倒着枚举, 减少List的插入成本
        for (int i = N - 1; i > 2; i--) { // 枚举倒数第一个数
            // 保证第一个数字不一样, 后面就是三元组的问题
            if (i != N - 1 && nums[i] == nums[i + 1]) {
                continue;
            }
            // 倒着枚举, 减少List的插入成本
            for (int j = i - 1; j > 1; j--) { // 从第一个数前一个位置, 枚举倒数第二个数
                // 保证第而个数字不一样, 后面就是二元组的问题
                if (j != i - 1 && nums[j] == nums[j + 1]) {
                    continue;
                }
                // 二元组问题
                int aim = target - nums[i] - nums[j];
                List<List<Integer>> curList = twoSum(nums, 0, j - 1, aim);
                for (List<Integer> item : curList) {
                    // 二元组得到的每一个答案, 把我自己这个东西塞到链表的最后的位置，构成三元/四元组
                    item.add(nums[j]);
                    item.add(nums[i]);
                    ans.add(item);
                }
            }
        }
        return ans;
    }

    // nums[start...end]范围上, 有多少个不同二元组, 相加和==target, 全返回
    public static List<List<Integer>> twoSum(int[] nums, int start, int end, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        int L = start;
        int R = end;
        while (L < R) {
            int sum = nums[L] + nums[R];
            if (sum > target) {
                R--;
            } else if (sum < target) {
                L++;
            } else { // 相等
                // NOTE: 去重操作!!, 当前来到的 L 他跟左侧的数不一样的时候，我收集这个答案
                if (L == 0 || nums[L] != nums[L - 1]) {
                    List<Integer> curAns = new ArrayList<>();
                    curAns.add(nums[L]);
                    curAns.add(nums[R]);
                    ans.add(curAns);
                }
                L++;
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
