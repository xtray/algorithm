package AlgoExpert;

import java.util.Arrays;

// 135.分糖果问题
// Hard
public class Problem_135_MinRewards {

    public static int minRewards(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[] left = new int[N];
        Arrays.fill(left, 1);
        for (int i = 1; i < N; i++) {
            if (arr[i - 1] < arr[i]) {
                left[i] = left[i - 1] + 1;
            }
        }
        int[] right = new int[N];
        Arrays.fill(right, 1);
        for (int i = N - 2; i >= 0; i--) {
            if (arr[i] > arr[i + 1]) {
                right[i] = right[i + 1] + 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < N; i++) {
            ans += Math.max(left[i], right[i]);
        }
        return ans;
    }

    public static int minRewards2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[] left = new int[N];
        for (int i = 1; i < N; i++) {
            if (arr[i - 1] < arr[i]) {
                left[i] = left[i - 1] + 1;
            }
        }
        int[] right = new int[N];
        for (int i = N - 2; i >= 0; i--) {
            if (arr[i] > arr[i + 1]) {
                right[i] = right[i + 1] + 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < N; i++) {
            ans += Math.max(left[i], right[i]);
        }
        return ans + N;
    }

    public static void main(String[] args) {
        int[] scores = new int[] {8, 4, 2, 1, 3, 6, 7, 9, 5};
        var ans = minRewards(scores);
        System.out.println(ans);
        System.out.println("====================");
        var ans2 = minRewards2(scores);
        System.out.println(ans2);
    }
}
