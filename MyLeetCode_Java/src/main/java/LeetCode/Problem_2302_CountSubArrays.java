package LeetCode;

public class Problem_2302_CountSubArrays {

    public long countSubarrays(int[] nums, long k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int N = nums.length;
        int L = 0;
        int R = 0;
        long sum = 0;
        long ans = 0;
        while (R < N) {
            int len = R - L + 1;
            sum += nums[R];
            if (sum * len < k) {
                R++;
            } else {
                // R 不可以了
                // [L,R)
                ans += (1L << (R - L)) - 1;
                L = R;
                sum = 0;
            }
        }
        ans += (1L << (R - L)) - 1;
        return ans;
    }

    public static void main(String[] args) {
        // int[] nums = new int[]{2, 1, 4, 3, 5};
        // long k = 10; // 6
        int[] nums = new int[]{1, 1, 1};
        long k = 5; // 5
        var ans = new Problem_2302_CountSubArrays().countSubarrays(nums, k);
        System.out.println(ans);
    }

}
