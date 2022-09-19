package LeetCode;

public class Problem_1752_CheckRotatedArray {

    // 假定是一个合法的选择数组, 找最小值
    // IMP: 下面代码错误, case: 1, 1, 2, 1 处理不了
    // 没有重复值可以这么做
    public boolean check(int[] nums) {
        int N = nums.length;
        int L = 0;
        int R = N - 1;
        while (L < R) {
            int mid = L + ((R - L) >> 1);
            if (nums[mid] < nums[R]) {
                R = mid; // mid是一个潜在的答案
            } else if (nums[mid] > nums[R]) { // 最小值在右侧
                L = mid + 1;
            } else {
                R--;
            }
        }
        // L == R最小
        // 从L位置开始检查N个数是否是升序
        for (int i = 0; i < N - 1; i++) {
            int cur = (L + i) % N;
            int next = (L + i + 1) % N;
            if (nums[next] < nums[cur]) return false;
        }

        return true;
    }


    public boolean check1(int[] nums) {
        int count = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) { // 由大变小最多只可能发生一次
                count++;
                if (count > 1) {
                    return false;
                }
            }
        }
        if (count == 1) {
            return nums[nums.length - 1] <= nums[0]; // 保证最后一个跟第一个也满足大小关系
        }
        return true;
    }

    public static void main(String[] args) {
        // int[] nums = new int[]{3, 4, 5, 1, 2}; // true
        // int[] nums = new int[]{2, 1, 3, 4}; // false
        // int[] nums = new int[]{2, 1}; // false
        // int[] nums = new int[]{3, 2, 1}; // false
        int[] nums = new int[]{1, 1, 2, 1}; // false
        var ans = new Problem_1752_CheckRotatedArray().check(nums);
        System.out.println(ans);
    }
}
