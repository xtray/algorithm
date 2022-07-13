package LeetCode;

public class Problem_2317_MaxXor {

    /**
     * A & (A ^ B) = A & (~A&B + A&~B) = A & ~A & B + A & A & ~B = A & ~B
     * 按题意 ~B 可以是任意一个数, 即: A 跟 任意一个数做与操作
     */

    public int maximumXOR(int[] nums) {

        int[] cnt = new int[32];
        for (int num : nums) {
            for (int i = 0; i < 32; i++) {
                cnt[i] += (num & (1 << i)) != 0 ? 1 : 0;
            }
        }
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            if (cnt[i] > 0) {
                ans |= 1 << i;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        // int[] nums = {3,2,4,6};
        int[] nums = {1,2,3,9,2};
        var ans = new Problem_2317_MaxXor().maximumXOR(nums);
        System.out.println(ans);
    }
}
