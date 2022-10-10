package _Exercise;

public class Problem_2176_CountPairs {

    public int countPairs(int[] nums, int k) {
        int N = nums.length;
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (nums[i] == nums[j] && i * j % k == 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
