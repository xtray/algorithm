package LeetCode;

import java.util.*;

public class Problem_532_FindPairs {

    public int findPairs(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int ans = 0;
        for (int num : nums) {
            if (!map.containsKey(num)) continue; // 统计过了, 不再统计
            if (k == 0) {
                int cnt = map.getOrDefault(num, 0);
                if (cnt > 1) {
                    ans++;
                }
            } else {
                int cntDown = map.getOrDefault(num - k, 0);
                int cntUp = map.getOrDefault(num + k, 0);
                if (cntDown != 0) {
                    ans++;
                }
                if (cntUp != 0) {
                    ans++;
                }
            }
            // num统计过了, 清零
            map.remove(num);
        }
        return ans;
    }

    // 使用rank数组离散化
    public int findPairs1(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int N = nums.length;
        Arrays.sort(nums);
        int[] cnt = new int[N];
        for (int num : nums) {
            cnt[rank(nums, num)]++;
        }
        int ans = 0;
        for (int num : nums) {
            if (cnt[rank(nums, num)] == 0) continue; // NOTE: 重复的数字过滤, 统计过了, 不再统计
            if (k == 0) {
                if (cnt[rank(nums, num)] > 1) {
                    ans++;
                }
            } else {
                int cntDown = rank(nums, num - k) != -1 ? cnt[rank(nums, num - k)] : 0;
                int cntUp = rank(nums, num + k) != -1 ? cnt[rank(nums, num + k)] : 0;
                if (cntDown != 0) {
                    ans++;
                }
                if (cntUp != 0) {
                    ans++;
                }
            }
            // num统计过了, 清零
            cnt[rank(nums, num)] = 0;
        }
        return ans;
    }

    // NOTE: 离散化的技巧
    // 找到>= num最左的位置
    public static int rank(int[] sorted, int num) {
        int l = 0;
        int r = sorted.length - 1;
        int m = 0;
        int pos = -1;
        while (l <= r) {
            m = (l + r) / 2;
            if (sorted[m] >= num) {
                pos = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        // NOTE: 注意判断找到的ans是不是num值
        return pos != -1 ? sorted[pos] == num ? pos : -1 : -1;
    }


    // https://leetcode.cn/problems/k-diff-pairs-in-an-array/solution/by-ac_oier-ap3v/
    // IMP: 双指针的做法, 不好理解, 多看!!
    public int findPairs2(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int N = nums.length;
        Arrays.sort(nums);
        List<Integer> list = new ArrayList<>();
        list.add(nums[0]);
        // 数组去重, 放入list
        for (int i = 1; i < N; i++) {
            if (nums[i] != nums[i - 1]) {
                list.add(nums[i]);
            }
        }
        int[] cnt = new int[N]; // 排第i位的数的个数
        cnt[0]++;
        // 数组中的数按他排序的下标做对应后统计计数
        for (int i = 1, j = 0; i < N; i++) {
            if (nums[i] != nums[i - 1]) {
                j++; // 新起一个序号
            }
            cnt[j]++;
        }
        int ans = 0;
        int L = 0;
        int R = 0;
        int idx = 0;
        N = list.size();
        for (int num : list) { // NOTE: 遍历每一个不重复的数, 每个数的个数在cnt[idx]里
            if (k == 0) {
                if (cnt[idx] > 1) {
                    ans++;
                }
            } else {
                // k固定, a,b随着num增加单调递增, 不回退!!
                int a = num - k;
                int b = num + k;
                while (L < N && list.get(L) < a) {
                    L++;
                } // L越界, 或者 list.get(L) >= a
                while (R < N && list.get(R) < b) {
                    R++;
                }// R越界, 或者 list.get(R) >= b
                if (L < N && list.get(L) == a && cnt[L] > 0) ans++;
                // L存在, 且等于a, 找到, 且数量>0
                if (R < N && list.get(R) == b && cnt[R] > 0) ans++;
            }
            cnt[idx++] = 0; // NOTE: 防止重复统计, 比如 1, 3  K=2, 1的时候+2(1,3), 3的时候-2(1,3)
        }
        return ans;
    }

    // https://leetcode.cn/problems/k-diff-pairs-in-an-array/solution/shu-zu-zhong-de-k-by-jiang-hui-4-6cnw/
    // IMP: 排序+双指针, 方法2 可以忽略了, 太复杂了, 没必要
    public int findPairs3(int[] nums, int k) {
        int N = nums.length;
        int res = 0;
        Arrays.sort(nums);
        // j不回退
        for (int i = 0, j = 0; i < N - 1 && j < N; i++) {
            //重复的不计算，如果相同nums[i-1]已经计算过了
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            //NOTE: 需要j > i，有可能去重时导致i >= j
            while (j <= i) {
                j++;
            }
            //找到target 和方法二一样，只找nums[i] + k
            while (j < N && (nums[j] < nums[i] + k)) {
                j++;
            }
            //找到目标值
            if (j < N && nums[j] == nums[i] + k) {
                res++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {3, 1, 4, 1, 5};
        int k = 2; //2
        // int[] nums = {1, 2, 4, 4, 3, 3, 0, 9, 2, 3};
        // int k = 3; // 2
        var ans = new Problem_532_FindPairs().findPairs(nums, k);
        System.out.println(ans);
        var ans1 = new Problem_532_FindPairs().findPairs2(nums, k);
        System.out.println(ans1);
    }
}
