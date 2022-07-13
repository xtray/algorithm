package LeetCode;

import java.util.Arrays;

// ref: Problem_1720_Decode

public class Problem_1734_Decode {
    public int[] decode(int[] encoded) {
        int N = encoded.length + 1;
        // 1...N
        int x = 0;
        for (int i = 1; i <= N; i++) {
            x ^= i;
        }
        int[] ans = new int[N];
        ans[0] = x;
        for (int i = 1; i < encoded.length; i+=2) {
            ans[0] ^= encoded[i];
        }
        for (int i = 1; i < N; i++) {
            ans[i] = ans[i - 1] ^ encoded[i - 1];
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] encoded = {6,5,4,6};
        var ans = new Problem_1734_Decode().decode(encoded);
        System.out.println(Arrays.toString(ans));
    }
}
