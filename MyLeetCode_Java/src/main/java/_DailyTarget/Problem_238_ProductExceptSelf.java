package _DailyTarget;

public class Problem_238_ProductExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int N = nums.length;
        int[] ans = new int[N];
        int zeroCnt = 0;
        int zeroPos = -1;
        int product = 1;
        int idx = 0;
        for (int num : nums) {
            if (num == 0) {
                zeroCnt++;
                zeroPos = idx;
            } else {
                product *= num;
            }
            idx++;
        }
        if (zeroCnt > 1) return ans;
        if (zeroCnt == 1) {
            ans[zeroPos] = product;
            return ans;
        }
        for (int i = 0; i < N; i++) {
            ans[i] = product / nums[i];
        }
        return ans;
    }

    public int[] productExceptSelf1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        int N = nums.length;
        int[] right = new int[N];
        right[N - 1] = nums[N - 1];
        for (int i = N - 2; i >= 0; i--) {
            right[i] = nums[i] * right[i + 1];
        }
        int left = 1;
        int[] ans = new int[N];
        for (int i = 0; i < N - 1; i++) {
            ans[i] = left * right[i + 1];
            left *= nums[i];
        }
        ans[N - 1] = left;
        return ans;
    }
}
