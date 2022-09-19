package _Exercise;

import java.util.Arrays;

public class Problem_135_Candy {

    public int candy(int[] ratings) {
        if (ratings == null || ratings.length == 0) {
            return 0;
        }
        int ans = 0;
        int N = ratings.length;
        int[] right = new int[N];
        right[N - 1] = 1;
        for (int i = N - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                right[i] = right[i + 1] + 1;
            } else {
                right[i] = 1;
            }
        }
        int left = 1;
        for (int i = 0; i < N; i++) {
            left = i == 0 ? 1 : (ratings[i] > ratings[i - 1] ? left + 1 : 1);
            ans += Math.max(left, right[i]);
        }
        return ans;
    }

    public int candy1(int[] ratings) {
        if (ratings == null || ratings.length == 0) {
            return 0;
        }
        int ans = 0;
        int N = ratings.length;
        int[] right = new int[N];
        Arrays.fill(right, 1);
        for (int i = N - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                right[i] = right[i + 1] + 1;
            }
        }
        int left = 1;
        ans += right[0];
        for (int i = 1; i < N; i++) {
            left = ratings[i] > ratings[i - 1] ? left + 1 : 1;
            ans += Math.max(left, right[i]);
        }
        return ans;
    }
}
