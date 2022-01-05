package LeetCode;

public class Problem_1995_CountQuadruplets {

    // https://leetcode-cn.com/problems/count-special-quadruplets/solution/tong-ji-te-shu-si-yuan-zu-by-leetcode-so-50e2/
    // 枚举 a, b, c
    // 用数组统计 d 的值
    public int countQuadruplets(int[] nums) {
        if (nums == null || nums.length < 4) {
            return 0;
        }

        int[] count = new int[1000];
        int N = nums.length;
        int ans = 0;
        for (int c = N - 2; c >= 2; c--) {
            int d = c + 1;
            count[nums[d]]++;
            for (int a = 0; a < c; a++) {
                for (int b = a + 1; b < c; b++) {
                    int sum = nums[a] + nums[b] + nums[c];
                    if (count[sum] > 0) {
                        ans += count[sum];
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int [] nums = new int[]{23,39,3,35,40,37};
        var ans = new Problem_1995_CountQuadruplets().countQuadruplets(nums);
        System.out.println(ans);
    }
}
