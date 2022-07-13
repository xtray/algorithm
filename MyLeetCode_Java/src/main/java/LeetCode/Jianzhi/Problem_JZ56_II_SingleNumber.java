package LeetCode.Jianzhi;

public class Problem_JZ56_II_SingleNumber {

    public int singleNumber(int[] nums) {
        int[] cnt = new int[32];

        for (int num : nums) {
            for (int i = 0; i < 32; i++) {
                if ((num & (1 << i)) != 0) {
                    cnt[i]++;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            if (cnt[i] % 3 != 0) {
                ans |= 1 << i;
            }
        }
        return ans;
    }
}
