package LeetCode.Jianzhi;

public class Problem_JZII004_SingleNumber {

    public int singleNumber(int[] nums) {

        int[] cnt = new int[32];
        for (int num : nums) {
            fillCnt(num, cnt);
        }
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            if (cnt[i] % 3 != 0) {
                ans |= 1 << i;
            }
        }
        return ans;
    }

    private void fillCnt(int num, int[] cnt) {
        for (int i = 0; i < 32; i++) {
            if ((num & (1 << i)) != 0) {
                cnt[i]++;
            }
        }
    }

    // 检查每一位
    // NOTE: 回看!!
    public int singleNumber2(int[] nums) {

        int ans = 0;
        for (int i = 0; i < 32; ++i) {
            int total = 0;
            for (int num : nums) {
                total += ((num >> i) & 1);
            }
            if (total % 3 != 0) {
                ans |= (1 << i);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {2, 2, 3, 2};
        var ans = new Problem_JZII004_SingleNumber().singleNumber(nums);
        System.out.println(ans);
    }
}
