package LeetCode;

import java.util.Arrays;

public class Problem_1304_SumZero {

    public int[] sumZero(int n) {
        int[] ans = new int[n];
        int base = 1;
        for (int i = 0; i < (n >>1); i++) {
            ans[i] = base;
            ans[i + (n >>1)] = -base; // NOTE: (n >>1)要加括号, 优先级低于+
            base++;
        }
        // if ((n & 1) != 0) {
        //     ans[n - 1] = 0;
        // }
        return ans;
    }


    public static void main(String[] args) {
        int n = 4;
        var ans = new Problem_1304_SumZero().sumZero(n);
        System.out.println(Arrays.toString(ans));
    }
}
