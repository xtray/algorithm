package _DailyTarget;

import java.util.Arrays;

public class Problem_1619_ArrayMeanAfterRemove {


    public double trimMean(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0D;
        }
        Arrays.sort(arr);
        int N = arr.length;
        int k = (int) (N * 0.05);
        double sum = 0;
        for (int i = k; i < N - k; i++) {
            sum += arr[i];
        }
        return sum / (N - 2 * k);
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3};
        var ans = new Problem_1619_ArrayMeanAfterRemove().trimMean(nums);
        System.out.println(ans);

    }
}
