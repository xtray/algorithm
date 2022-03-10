package LeetCode;

public class Problem_798_BestRotation {

    public int bestRotation(int[] nums) {
        int N = nums.length;
        int[] diff = new int[N + 1];
        for (int i = 0; i < N; i++) {
            int L = (i - (N - 1) + N) % N;
            int R = (i - nums[i] + N) % N;
            if (L <= R) {
                add(diff, L, R);
            } else {
                add(diff, 0, R);
                add(diff, L, N - 1);
            }
        }
        // 求前缀和
        for(int i = 1; i<=N; i++) {
            diff[i] += diff[i-1];
        }
        int ans = 0; // 这个 0 是 index
        for(int i = 1; i < N; i++) {
            if(diff[i] > diff[ans]) {
                ans = i;
            }
        }
        return ans;
    }

    private void add(int[] diff, int L, int R) {
        diff[L] += 1;
        diff[R + 1] -= 1;
    }

    public static void main(String[] args) {
        // int[] nums = {2,3,1,4,0};
        int[] nums = {1,3,0,2,4};
        var ans = new Problem_798_BestRotation().bestRotation(nums);
        System.out.println(ans);
    }
}
