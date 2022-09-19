package _DailyTarget;

// 长度为N 的数组, 数值都在0~N之内, 共N+1个数, 缺一个

public class Problem_JZ53_II_MissingNumber_2 {

    // 递增排序数组, O(N)
    public int missingNumber(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return -1;
        }
        int N = nums.length;
        for (int i = 0; i < N; i++) {
            if (nums[i] != i) {
                return i;
            }
        }
        return N;
    }

    // NOTE: 递增排序数组
    // IMP: 二分寻找不相等的分割点!
    // 假设缺失元素为x, 那么x左侧必然有num[i] = i, 右侧不满足
    public int missingNumber1(int[] nums) {
        int n = nums.length;
        int l = 0;
        int r = n - 1;
        int pos = -1; // 找分割点
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (nums[mid] != mid) {
                pos = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return pos == -1 ? n : pos;
    }

    // 假设是无序的
    public int missingNumber2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int N = nums.length;
        int i = 0;
        while (i < N) {
            if (nums[i] == N) {
                i++;
                continue;
            }
            if (nums[i] != i) {
                swap(nums, i, nums[i]);
            } else {
                i++;
            }
        }
        for (i = 0; i < N; i++) {
            if (nums[i] != i) {
                return i;
            }
        }
        return N;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }





}
