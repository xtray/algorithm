package LeetCode;

// ref: Problem_1734_Decode

public class Problem_1720_Decode {
    public int[] decode(int[] encoded, int first) {
        int N = encoded.length + 1;
        int[] ans = new int[N];
        ans[0] = first;
        for (int i = 1; i < N; i++) {
            ans[i] = ans[i - 1] ^ encoded[i - 1];
        }
        return ans;
    }
}
