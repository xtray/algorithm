package LeetCode;

import java.util.Arrays;

public class Problem_1619_ArrayMeanAfterRemove {

    public double trimMean(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0D;
        }
        double ans = 0D;
        Arrays.sort(arr);
        int N = arr.length;
        int K = (int) (N * 0.05);
        for (int i = K; i < N - K; i++) {
            ans += arr[i];
        }
        return ans / (N - 2 * K);
    }

    public static void main(String[] args) {
        int[] nums = {1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3};
        var ans = new Problem_1619_ArrayMeanAfterRemove().trimMean(nums);
        System.out.println(ans);

    }
}
