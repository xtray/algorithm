package _DailyTarget;

public class Problem_JZII004_SingleNumber {

    public int singleNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Integer.MAX_VALUE;
        }
        int N = nums.length;
        int[] cnt = new int[N];
        int ans = 0;
        for (int num : nums) {
            for (int i = 0; i < 32; i++) {
                if ((num >> i & 1) != 0) {
                    cnt[i]++;
                }
            }
        }
        for (int i = 0; i < 32; i++) {
            ans |= (cnt[i] % 3) << i;
        }
        return ans;
    }


    public int singleNumber1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Integer.MAX_VALUE;
        }
        int N = nums.length;
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            int cnt = 0;
            for (int num : nums) {
                // if (((num >> i) & 1) != 0) {
                //     cnt++;
                // }
                cnt += (num >> i) & 1; // 会快很多
            }
            // if(cnt % 3 != 0) {
            //     ans |= 1<<i;
            // }
            ans |= (cnt % 3) << i; // cnt % 3 只会是 0 或者 1
        }
        return ans;
    }
}
