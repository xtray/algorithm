package LeetCode.JZOffer;

public class Problem_JZ17_PrintNum {
    public int[] printNumbers(int n) {
        if(n < 1) {
            return new int[0];
        }
        int mask = (int)Math.pow(10, n) - 1;
        int[] ans = new int[mask];
        for(int i = 1; i<= mask; i++) {
            ans[i-1] = i;
        }
        return ans;
    }
}
